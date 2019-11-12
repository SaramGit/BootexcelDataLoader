package com.nt.service;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;

import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nt.model.User;
import com.nt.repo.SpringReadFileRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import ch.qos.logback.core.html.CssBuilder;

@Service
@Transactional
public class SpringReadFileServiceImpl implements SpringReadFileService {

	    @Autowired
	    private SpringReadFileRepository repository;

		@Override
		public List<User> findAll() {
			List<User> users=(List<User>) repository.findAll();
			return users;
		}

		@Override
		public boolean saveDataFromUploadFile(MultipartFile file) {
            boolean isflag=false;
            String extension=FilenameUtils.getExtension(file.getOriginalFilename());
            
            if(extension.equalsIgnoreCase("json")) {
            	isflag=readDataFromJson(file);
            }else if(extension.equalsIgnoreCase("csv")) {
            	isflag=readDataFromCsv(file);
            }else if(extension.equalsIgnoreCase("xls") || extension.equalsIgnoreCase("xlsx")) {
            	isflag=readDataFromExcel(file);
            }else if(extension.equalsIgnoreCase("pdf")) {
            	isflag=readDataFromPdf(file);
            }
			return isflag;
		}

		private boolean readDataFromPdf(MultipartFile file) {
			 try {
				PDFTextStripper tStripper=new  PDFTextStripper();
				PDDocument document = PDDocument.load(file.getInputStream());
				document.getClass();
				            String content = null;
							if (!document.isEncrypted()) {
				             String   pdfFileInText = tStripper.getText(document);
				             String[]   lines = pdfFileInText.split("\\r\\n\\r\\n");
				                for (String line : lines) {
				                    //System.out.println(line);
				                    content += line;
				                }
				            }
				System.out.println(content.trim());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}

		private boolean readDataFromExcel(MultipartFile file) {
            Workbook workbook=getWorkbook(file);
            Sheet sheet=workbook.getSheetAt(0);
            Iterator<Row> rows=sheet.iterator();
            rows.next();
                 while (rows.hasNext()) {
        		        Row row=rows.next();
        		        User user=new User();
        		        if (row.getCell(0).getCellType()==Cell.CELL_TYPE_STRING) {
							      user.setFirstname(row.getCell(0).getStringCellValue());
						}
        		        if (row.getCell(1).getCellType()==Cell.CELL_TYPE_STRING) {
						      user.setLastname(row.getCell(1).getStringCellValue());
					    }
        		        if (row.getCell(2).getCellType()==Cell.CELL_TYPE_STRING) {
						      user.setEmail(row.getCell(2).getStringCellValue());
					    }
        		        if (row.getCell(3).getCellType()==Cell.CELL_TYPE_NUMERIC) {
        		        	  String phonenumber=NumberToTextConverter.toText(row.getCell(3).getNumericCellValue());
						      user.setPhonenumber(phonenumber);
					    }
        		        else if (row.getCell(3).getCellType()==Cell.CELL_TYPE_STRING) {
        		        	user.setPhonenumber(row.getCell(3).getStringCellValue());
						}
        		        user.setFileType(FilenameUtils.getExtension(file.getOriginalFilename()));
        		        repository.save(user);
        		        
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

		private boolean readDataFromCsv(MultipartFile file) {

			try {
				InputStreamReader reader=new InputStreamReader(file.getInputStream());
                CSVReader csvReader=new CSVReaderBuilder(reader).withSkipLines(1).build();
                List<String[]> rows=csvReader.readAll();
                for (String[] row : rows) {
					  repository.save(new User(row[0],row[1], row[2],row[3],FilenameUtils.getExtension(file.getOriginalFilename())));
				}
				return true;
			} catch (Exception e) {
				return false;
			}
			
		}

		private boolean readDataFromJson(MultipartFile file) {
            try {
				InputStream inputStream=file.getInputStream();
				ObjectMapper mapper=new ObjectMapper();
				List<User> users=Arrays.asList(mapper.readValue(inputStream, User[].class));
				if(users!=null &&users.size()!=0) {
					   for (User user : users) {
						    user.setFileType(FilenameUtils.getExtension(file.getOriginalFilename()));
						    repository.save(user);
					}
				}
            	return true;
			} catch (Exception e) {
				return false;
			}
			
		}
	    
	    
}
