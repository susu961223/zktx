package com.xiaoshu.common.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.apache.commons.codec.digest.DigestUtils.sha1;

public class FileUtil {

    private static final int CHUNK_SIZE = 1 << 22;

    public static String calcETag(InputStream inputStream, long fileLength) throws IOException,NoSuchAlgorithmException {
        String etag ;
        if (fileLength <= CHUNK_SIZE) {
            byte[] fileData = new byte[(int) fileLength];
            inputStream.read(fileData, 0, (int) fileLength);
            byte[] sha1Data = Encodes.sha1(fileData);
            int sha1DataLen = sha1Data.length;
            byte[] hashData = new byte[sha1DataLen + 1];
            System.arraycopy(sha1Data, 0, hashData, 1, sha1DataLen);
            hashData[0] = 0x16;
            etag = Encodes.urlSafeBase64Encode(hashData);
        } else {
            int chunkCount = (int) (fileLength / CHUNK_SIZE);
            if (fileLength % CHUNK_SIZE != 0) {
                chunkCount += 1;
            }
            byte[] allSha1Data = new byte[0];
            for (int i = 0; i < chunkCount; i++) {
                byte[] chunkData = new byte[CHUNK_SIZE];
                int bytesReadLen = inputStream.read(chunkData, 0, CHUNK_SIZE);
                byte[] bytesRead = new byte[bytesReadLen];
                System.arraycopy(chunkData, 0, bytesRead, 0, bytesReadLen);
                byte[] chunkDataSha1 = sha1(bytesRead);
                byte[] newAllSha1Data = new byte[chunkDataSha1.length
                        + allSha1Data.length];
                System.arraycopy(allSha1Data, 0, newAllSha1Data, 0,
                        allSha1Data.length);
                System.arraycopy(chunkDataSha1, 0, newAllSha1Data,
                        allSha1Data.length, chunkDataSha1.length);
                allSha1Data = newAllSha1Data;
            }
            byte[] allSha1DataSha1 = sha1(allSha1Data);
            byte[] hashData = new byte[allSha1DataSha1.length + 1];
            System.arraycopy(allSha1DataSha1, 0, hashData, 1,
                    allSha1DataSha1.length);
            hashData[0] = (byte) 0x96;
            etag = Encodes.urlSafeBase64Encode(hashData);
        }
        inputStream.close();
        return etag;
    }
    
	public static List<String> getAllFileName(String path,List<String> fjPath) {
		List<String> list = new ArrayList<String>();
        File file = new File(path);
        File [] files = file.listFiles();
        String [] names = file.list();
        if(names != null){
        	String [] completNames = new String[names.length];
        	for(int i=0;i<names.length;i++){
        	completNames[i]=path+names[i];
        }
        	fjPath.addAll(Arrays.asList(completNames));
        }
        return fjPath;
     }
	
    @SuppressWarnings("rawtypes")
	public static List getAllFileNames(String path,List<String> fjPath) {
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                fjPath.add(tempList[i].getName());
            }
            if (tempList[i].isDirectory()) {
                getAllFileName(tempList[i].getAbsolutePath(),fjPath);
            }
        }
        return fjPath;
    }

}
