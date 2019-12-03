package com.xiaoshu.common.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import com.xiaoshu.admin.model.Bdcquery;
import com.xiaoshu.admin.model.DJshare;


@SuppressWarnings("deprecation")
public class ExcelUtils {
	
	public static List<Bdcquery> getAll(MultipartFile file) throws Exception{
		String fileName = file.getOriginalFilename();	
		if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
			throw new Exception("上传文件格式不正确");
		}
		boolean isExcel2003 = true;
		if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
			isExcel2003 = false;
		}	
		InputStream is =  file.getInputStream();
		Workbook wb =   null;
		if (isExcel2003) {
			wb = new HSSFWorkbook(is);
		} else {
			wb = new XSSFWorkbook(is);
		}
		Sheet sheet = wb.getSheetAt(0);
		List<Bdcquery> list = new ArrayList<Bdcquery>();
		if(sheet!=null){	        
			for (int r = 1; r <= sheet.getLastRowNum(); r++) {
				Bdcquery Bdcquery = new Bdcquery();
				Row row = sheet.getRow(r);
				row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
				Bdcquery.setQlr(row.getCell(2).getStringCellValue());
				Bdcquery.setZjh(row.getCell(3).getStringCellValue());
				list.add(Bdcquery);
			}
		}
		return list;
	}
	
	/**
	 * 导出三河原始库权属信息*/
	public static HSSFWorkbook daochu(List<DJshare> list) throws Exception {
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("表一");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("序号");
		cell.setCellStyle(style);
		cell = row.createCell((short) 1);
		cell.setCellValue("业务号");
		cell.setCellStyle(style);
		cell = row.createCell((short) 2);
		cell.setCellValue("权利人名称");
		cell.setCellStyle(style);
		cell = row.createCell((short) 3);
		cell.setCellValue("不动产单元号");
		cell.setCellStyle(style);
		cell = row.createCell((short) 4);
		cell.setCellValue("不动产证号");
		cell.setCellStyle(style);
		cell = row.createCell((short) 5);
		cell.setCellValue("坐落");
		cell.setCellStyle(style);

		// 第五步，写入实体数据 实际应用中这些数据从数据库得到，

		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow((int) i + 1);
			// 第四步，创建单元格，并设置值
			row.createCell((short) 0).setCellValue(i+1);
			row.createCell((short) 1).setCellValue(list.get(i).getYwh());
			row.createCell((short) 2).setCellValue(list.get(i).getQlrmc());
			row.createCell((short) 3).setCellValue(list.get(i).getBdcdyh());
			row.createCell((short) 4).setCellValue(list.get(i).getBdcqzh());
			row.createCell((short) 5).setCellValue(list.get(i).getFdzl());
			// row.createCell((short) 5).setCellValue(sxkDY.getDymj());
		}
		for(int i = 0; i < list.size(); i++){
			sheet.autoSizeColumn(i);
		}
		setSizeColumn(sheet, list.size());
		return wb;
	}
	
	// 自适应宽度(中文支持)
    private static void setSizeColumn(HSSFSheet sheet, int size) {
        for (int columnNum = 0; columnNum < size; columnNum++) {
            int columnWidth = sheet.getColumnWidth(columnNum) / 256;
            for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                HSSFRow currentRow;
                //当前行未被使用过
                if (sheet.getRow(rowNum) == null) {
                    currentRow = sheet.createRow(rowNum);
                } else {
                    currentRow = sheet.getRow(rowNum);
                }
 
                if (currentRow.getCell(columnNum) != null) {
                    HSSFCell currentCell = currentRow.getCell(columnNum);
                    if (currentCell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                        int length = currentCell.getStringCellValue().getBytes().length;
                        if (columnWidth < length) {
                            columnWidth = length;
                        }
                    }
                }
            }
            sheet.setColumnWidth(columnNum, columnWidth * 256);
        }
    }

}
