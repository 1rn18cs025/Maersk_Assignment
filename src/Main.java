import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.STVolDepTypeImpl;

public class Main {

	public static void main(String[] args) throws Exception {
		File directoryPath = new File(".\\input");
		
		//to get all files in input directory
	     File filesList[] = directoryPath.listFiles();
		
	     for(int i=0; i<filesList.length; i++) {
	    	 System.out.println(filesList[i].getName());
	     }
	     
	     ArrayList<SimilarFiles> similarFiles = new ArrayList<>();
	     
	     
//	     Iterate through each file to do operation
	     for(File file : filesList) {
	    	 FileInputStream inputStream = new FileInputStream(file);
	    	 String fileName = file.getName();
	    	 
	    	 //open workbook to get atrributes
	    	 XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
	 		XSSFSheet sheet = workbook.getSheetAt(0);
	 		XSSFRow row = sheet.getRow(0);
	 		int cols = sheet.getRow(1).getLastCellNum();
	 		ArrayList<String> colAttr = new ArrayList<>();
	 		for(int i=0; i<cols; i++) {
	 		    String rowAttr = row.getCell(i).getStringCellValue();
	 		    colAttr.add(rowAttr);
	 		}
	    	 
	    	 
	    	 boolean isFound = false;
	    	 
	    	 //add similar type of files in class Similar files
	    	 for(SimilarFiles sf : similarFiles) {
	    		 if(sf.checkSameFile(colAttr)) {
	    			 sf.addFile(file);
	    			 sf.addFileNames(fileName);
	    			 isFound = true;
	    			 break;
	    		 }
	    	 }
	    	 
	    	 if(!isFound) {
	    		 SimilarFiles tempSf = new SimilarFiles();
	    		 tempSf.addFileNames(fileName);
	    		 tempSf.addFile(file);
	    		 tempSf.setColumnAttributes(colAttr);
	    		 similarFiles.add(tempSf);
	    	 }
	    	 
	      }
	     
	     
	     for(int i=0; i<similarFiles.size(); i++) {
	    	 getdajfkaljs(similarFiles.get(i));
	     }
	     
	     
	}
	
	//create new output file with name including all other file name and data
	public static void getdajfkaljs(SimilarFiles sf) throws Exception {
		String fileName = "";
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
		XSSFSheet xssfSheet = xssfWorkbook.createSheet("Sheet");
		XSSFRow xssfRow = xssfSheet.createRow(0);
		
		for(int i=0; i<sf.getColumnAttributes().size(); i++) {
			xssfRow.createCell(i).setCellValue(sf.getColumnAttributes().get(i));
		}
		

 		int j=1;
		
		for(int i=0; i<sf.getColumnFiles().size(); i++) {
			File ffFile =sf.getColumnFiles().get(i);
			String fileNameString = ffFile.getName().split(".xlsx")[0];
			if(i==sf.getFileNames().size()-1) {
				fileName += fileNameString;
			}else {
				fileName+=fileNameString+"-";	
			}

			FileInputStream inputStream = new FileInputStream(ffFile);
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
	 		XSSFSheet sheet = workbook.getSheetAt(0);
	 		int cols = sheet.getRow(1).getLastCellNum();
	 		int rows = sheet.getLastRowNum();
	 		
	 		for(int l=1; l<=rows; l++) {

				XSSFRow row = sheet.getRow(l);
				XSSFRow tempRow = xssfSheet.createRow(j);
				for(int k=0; k<cols; k++) {
					switch(row.getCell(k).getCellType()) {
					case NUMERIC : {

							tempRow.createCell(k).setCellValue(row.getCell(k).getNumericCellValue());
							
						} break;
					case	BOOLEAN: {
							boolean valString = row.getCell(k).getBooleanCellValue();
							tempRow.createCell(k).setCellValue(valString);
							
						}break;
						default:{
							String valString = row.getCell(k).getStringCellValue();
							tempRow.createCell(k).setCellValue(valString);
							
						}
					}
				}

				j++;
	 		}
	 		
//	 		inputStream.close();
		}
		
		String outputPathString = ".\\output\\"+ fileName+".xlsx";
		FileOutputStream outputStream = new FileOutputStream(outputPathString);
		xssfWorkbook.write(outputStream);
		outputStream.close();
		
	}
	
//	public void galdjfa(XSSFSheet xssfSheet, File file) throws Exception {
//		FileInputStream inputStream = new FileInputStream(file);
//		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
// 		XSSFSheet sheet = workbook.getSheetAt(0);
// 		int j=1;
// 		int cols = sheet.getRow(1).getLastCellNum();
// 		int rows = sheet.getLastRowNum();
// 		
// 		for(int i=1; i<rows; i++) {
//
//			XSSFRow row = sheet.getRow(i);
//			XSSFRow tempRow = xssfSheet.createRow(j);
//			for(int k=0; k<cols; k++) {
//				String valString = row.getCell(k).getStringCellValue();
//				tempRow.createCell(j).setCellValue(valString);
//			}
// 		}
// 		
//	}
	
	

}
