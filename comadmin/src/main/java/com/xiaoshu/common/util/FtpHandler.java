package com.xiaoshu.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FtpHandler {

	private static String interHost;

	private static int interPort;

	private static String interUsername;

	private static String interPassword;

	private static FTPClient ftpInter;

	private static boolean connFlag = false;

	public FtpHandler(String interHost, int interPort, String interUsername, String interPassword) {
		this.interHost = interHost;
		this.interPort = interPort;
		this.interUsername = interUsername;
		this.interPassword = interPassword;
	}

	// 获取上传FTP的url
	public String ftpUrl() {
		return interHost;
	}
	//获取FTP的用户名
		public String interUsername() {
			return interUsername;
		}
		//获取ftp的密码
		public String interPassword() {
			return interPassword;
		}
		//获取ftp的端口
		public int interPort() {
			return interPort;
		}
		
	// 上传附件
	public boolean putFile1(String filename, InputStream input, String fjfl, String year, String slbh)
			throws IOException {
		connect();
		ftpInter.setFileType(FTPClient.BINARY_FILE_TYPE);
		boolean result = false;
		try {
			ftpInter.enterLocalPassiveMode();
			filename = new String(filename.getBytes("gbk"), "ISO-8859-1");
			fjfl = new String(fjfl.getBytes("gbk"), "ISO-8859-1");
			// 切换到目录年月
			boolean change = ftpInter.changeWorkingDirectory(year);
			if (change == true) {
				// 切换目录受理编号
				boolean change1 = ftpInter.changeWorkingDirectory(slbh);
				if (change1) {
					// 切换目录分类
					boolean change2 = ftpInter.changeWorkingDirectory(fjfl);
					if (change2) {
						System.out.println("111111111111111111111" + filename);
						result = ftpInter.storeFile(filename, input);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 上传头像
	public boolean uploadFtp(MultipartFile file, String path) throws IOException {
		ftpInter.enterLocalPassiveMode();
		connect();
		ftpInter.setFileType(FTPClient.BINARY_FILE_TYPE);
		boolean result = false;
		try {
			path = new String(path.getBytes("gbk"), "ISO-8859-1");
			String filename = new String(file.getOriginalFilename().getBytes("gbk"), "ISO-8859-1");
			// 切换到目录年月
			boolean change = ftpInter.changeWorkingDirectory(path);
			if (change == true) {
				result = ftpInter.storeFile(filename, file.getInputStream());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 获取ftp某一文件（路径）下的文件名字,用于查看文件列表
	public static File asFile(String path, File file) throws IOException {
		connect();
		OutputStream os = new FileOutputStream(file);
		path = new String(path.getBytes("gbk"), "ISO-8859-1");
		InputStream inputStream = ftpInter.retrieveFileStream(path);
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
			os.write(buffer, 0, bytesRead);
		}
		inputStream.close();
		return file;
	}

	// 获取inputstream
	public static InputStream getInputStream1(String path) throws IOException {
		connect();
		path = new String(path.getBytes("gbk"), "ISO-8859-1");
		InputStream inputStream = ftpInter.retrieveFileStream(path);
		return inputStream;
	}

	public static InputStream getInputStream(String path) throws UnsupportedEncodingException {
		connect();
		path = new String(path.getBytes("gbk"), "ISO-8859-1");
		InputStream inputStream = null;
		try {
			inputStream = ftpInter.retrieveFileStream(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inputStream;
	}

	// 连接ftp
	public static boolean connect() {
		String connecting = "";
		try {
			log.info("开始连接" + interHost);
			connecting = interHost;
			if (ftpInter == null) {
				ftpInter = new FTPClient();
			}
			ftpInter.connect(interHost, interPort);
			ftpInter.login(interUsername, interPassword);
			log.info("连接" + interHost + "成功");
		} catch (Exception e) {
			log.error(e.getMessage());
			log.error(connecting + "连接失败");
			return false;
		}
		connFlag = true;
		return true;
	}

	public boolean isConnected() {
		return connFlag;
	}
	
	public boolean createDirImg(String name) throws IOException{
		boolean s = connect();
		if(s){
			ftpInter.enterLocalPassiveMode();
			connFlag = ftpInter.changeWorkingDirectory("/");
			name = new String(name.getBytes("gbk"), "ISO-8859-1");
			ftpInter.makeDirectory(name);
			connFlag=ftpInter.changeWorkingDirectory(name);
		}
		return connFlag;
	}
	// 创建文件夹
	public boolean createDir(String year, String slbh, String fjfl) {
		try {
			boolean s = connect();
			if (s) {
				ftpInter.enterLocalPassiveMode();
				connFlag = ftpInter.changeWorkingDirectory("/");
				fjfl = new String(fjfl.getBytes("gbk"), "ISO-8859-1");
				ftpInter.makeDirectory(year);
				ftpInter.changeWorkingDirectory(year);
				ftpInter.makeDirectory(slbh);
				connFlag = ftpInter.changeWorkingDirectory(slbh);
				ftpInter.makeDirectory(fjfl);
				connFlag = ftpInter.changeWorkingDirectory(fjfl);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return connFlag;
	}

	public static boolean startDown(String filename, String localBaseDir, String remoteBaseDir) throws Exception {
		if (connect()) {
			try {
				FTPFile[] files = null;
				boolean changedir = ftpInter.changeWorkingDirectory(remoteBaseDir);
				if (changedir) {
					ftpInter.setControlEncoding("GBK");
					files = ftpInter.listFiles();
					for (int i = 0; i < files.length; i++) {
						try {
							if (filename.equals(files[i].getName())) {
								downloadFile(files[i], localBaseDir, remoteBaseDir);
								return true;
							}
						} catch (Exception e) {
							log.error("<" + files[i].getName() + ">下载失败");
						}
					}
				}
			} catch (Exception e) {
				log.error("下载过程中出现异常");
			}
		} else {
			log.error("链接失败！");
		}
		return false;
	}

	/**
	 * 
	 * 下载FTP文件 当你需要下载FTP文件的时候，调用此方法 根据获取的文件名，本地地址，远程地址进行下载
	 * 
	 * @param ftpFile
	 * @param relativeLocalPath
	 * @param relativeRemotePath
	 */
	private static void downloadFile(FTPFile ftpFile, String relativeLocalPath, String relativeRemotePath) {
		if (ftpFile.isFile()) {
			if (ftpFile.getName().indexOf("?") == -1) {
				OutputStream outputStream = null;
				try {
					File entryDir = new File(relativeLocalPath);
					// 如果文件夹路径不存在，则创建文件夹
					if (!entryDir.exists() || !entryDir.isDirectory()) {
						entryDir.mkdirs();
					}
					File locaFile = new File(relativeLocalPath + ftpFile.getName());
					// 判断文件是否存在，存在则返回
					if (locaFile.exists()) {
						return;
					} else {
						outputStream = new FileOutputStream(relativeLocalPath + ftpFile.getName());
						ftpInter.retrieveFile(ftpFile.getName(), outputStream);
						outputStream.flush();
						outputStream.close();
					}
				} catch (Exception e) {
				} finally {
					try {
						if (outputStream != null) {
							outputStream.close();
						}
					} catch (IOException e) {
						log.error("输出文件流异常");
					}
				}
			}
		} else {
			String newlocalRelatePath = relativeLocalPath + ftpFile.getName();
			String newRemote = new String(relativeRemotePath + ftpFile.getName().toString());
			File fl = new File(newlocalRelatePath);
			if (!fl.exists()) {
				fl.mkdirs();
			}
			try {
				newlocalRelatePath = newlocalRelatePath + '/';
				newRemote = newRemote + "/";
				String currentWorkDir = ftpFile.getName().toString();
				boolean changedir = ftpInter.changeWorkingDirectory(currentWorkDir);
				if (changedir) {
					FTPFile[] files = null;
					files = ftpInter.listFiles();
					for (int i = 0; i < files.length; i++) {
						downloadFile(files[i], newlocalRelatePath, newRemote);
					}
				}
				if (changedir) {
					ftpInter.changeToParentDirectory();
				}
			} catch (Exception e) {

			}
		}
	}

	public boolean removeFj(String fileName, String Slbh) throws IOException {
		connect();
		ftpInter.setFileType(FTPClient.BINARY_FILE_TYPE);
		ftpInter.enterLocalPassiveMode();
		Slbh = new String(Slbh.getBytes("gbk"), "ISO-8859-1");
		boolean s = ftpInter.changeWorkingDirectory(Slbh);
		if (s) {
			s = ftpInter.deleteFile(new String(fileName.getBytes("gbk"), "ISO-8859-1"));
		}
		return s;
	}

	public boolean disconnect() {
		connFlag = false;
		try {
			if (ftpInter != null) {
				int reply = ftpInter.getReplyCode();
				if (!FTPReply.isPositiveCompletion(reply)) {
					ftpInter.disconnect();
					System.err.println("FTP server refused connection.");
					System.exit(1);
				}
				ftpInter.logout();
				log.info("断开" + interHost);
			}
		} catch (IOException e) {
			log.error(e.getMessage());
			return false;
		} finally {
			try {
				ftpInter.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return true;
	}

}
