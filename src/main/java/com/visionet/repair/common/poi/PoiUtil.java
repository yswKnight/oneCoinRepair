package com.visionet.repair.common.poi;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PoiUtil {
	
	public static final String EXCEL_AFTER2007_SUFFIX = ".xlsx";
	
	public static void writeExcel(List<String> rowNameList,
			List<Map<Integer, String>> dataList, 
			String outFilePath, String sheetName) throws Exception {
		// 工作区
		Workbook wb = new XSSFWorkbook();
		
		OutputStream os = new FileOutputStream(outFilePath);
		// 创建格式
		CellStyle cellStyle = wb.createCellStyle();
		DataFormat format = wb.createDataFormat();
		// 创建sheet
		Sheet sheet = wb.createSheet(sheetName);
		sheet.setDefaultColumnWidth(15);
		// 设置标题
		Row row = sheet.createRow(0);
		Cell cell = null;
		for (int i = 0; i < rowNameList.size(); i++) {
			// sheet.setColumnWidth(i, 4000); //设置列宽
			cell = row.createCell(i);
			Font font = wb.createFont();
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			CellStyle cs = wb.createCellStyle();
			cs.setFont(font);
			cell.setCellStyle(cs);
			cell.setCellValue(rowNameList.get(i));
		}
		// 写入内容
		for (int i = 0; i < dataList.size(); i++) {
			row = sheet.createRow(i + 1);
			Map<Integer, String> rowData = dataList.get(i);
			for (int j = 0; j < rowData.size(); j++) {
				cell = row.createCell(j);
				Object value = rowData.get(j);
				if (value == null) {
					cell.setCellValue("");
				} else if (value instanceof Integer) {
					cell.setCellValue((Integer) value);
				} else if (value instanceof Long) {
					cell.setCellValue((Long) value);
				} else if (value instanceof Double) {
					cellStyle.setDataFormat(format.getFormat("#,##0.00"));
					cell.setCellStyle(cellStyle);
					cell.setCellValue(Double.parseDouble(value.toString()));
				}else {
					cell.setCellValue(value.toString());
				}
			}
		}
		// 写文件
		wb.write(os);
		os.close();
	}
	
}
