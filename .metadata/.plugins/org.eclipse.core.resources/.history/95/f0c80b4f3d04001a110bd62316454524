package com.nt.service;

import java.util.Iterator;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nt.dao.GSTNRepo;
import com.nt.model.GstProduct;

@Service
public class GstProductServiceImpl implements GstProductService {
    
	@Autowired
	private GSTNRepo repo;
	
	@Override
	public boolean saveDataFromUploadFile(MultipartFile file) {
		 boolean isflag=false;
         String extension=FilenameUtils.getExtension(file.getOriginalFilename());
         
         if(extension.equalsIgnoreCase("xls") || extension.equalsIgnoreCase("xlsx")) {
         	isflag=readDataFromExcel(file);
         }
		return isflag;
	}

	private boolean readDataFromExcel(MultipartFile file) {
		  Workbook workbook=getWorkbook(file);
          Sheet sheet=workbook.getSheetAt(0);
          Iterator<Row> rows=sheet.iterator();
          rows.next();
               while (rows.hasNext()) {
      		        Row row=rows.next();
      		      GstProduct product=new GstProduct();
      		        if (row.getCell(0).getCellType()==Cell.CELL_TYPE_NUMERIC) {
							     // user.setFirstname(row.getCell(0).getStringCellValue());
      		        	         product.setGroupCode((int) row.getCell(0).getNumericCellValue());
						}
      		          if (row.getCell(1).getCellType()==Cell.CELL_TYPE_NUMERIC) {
      		        	          product.setProductcode((int) row.getCell(1).getNumericCellValue());
					     }
      		        if (row.getCell(2).getCellType()==Cell.CELL_TYPE_STRING) {
						      product.setProductname(row.getCell(2).getStringCellValue());
					    }
      		        if (row.getCell(3).getCellType()==Cell.CELL_TYPE_NUMERIC) {
      		        	 product.setPreGST((int) row.getCell(3).getNumericCellValue());
					    }
      		           if (row.getCell(4).getCellType()==Cell.CELL_TYPE_NUMERIC) {
      		        	 product.setGST((int) row.getCell(4).getNumericCellValue());
						}
      		         product.setFileType(FilenameUtils.getExtension(file.getOriginalFilename()));
      		        repo.save(product);
      		        
			     }
			return true;
		}

		private Workbook getWorkbook(MultipartFile file) {
          Workbook workbook=null;
          String extension=FilenameUtils.getExtension(file.getOriginalFilename());
          try {
               if(extension.equalsIgnoreCase("xlsx")) {
          	            workbook=new XSSFWorkbook(file.getInputStream());
                }else if(extension.equalsIgnoreCase("xls")) {
          	            workbook=new HSSFWorkbook(file.getInputStream());
                }
          }catch (Exception e) {
                  e.printStackTrace();
          }
			return workbook;
		}



	@Override
	public int findGstRate() {
		// TODO Auto-generated method stub
		return 0;
	}

}
