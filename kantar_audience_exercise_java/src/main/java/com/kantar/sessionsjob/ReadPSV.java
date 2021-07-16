package com.kantar.sessionsjob;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

public class ReadPSV {
	

	String filePath;
	
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public ReadPSV(String filePath) {
		super();
		this.filePath = filePath;
	}



	/**
	* Reads PSV file with input statements
	*
	* @param filePath file Path
	* @return inputStatements
	*/
	public List<InputStatement> getInputPSV()  {
		
		
		CSVReader reader;
		List<InputStatement> inputStatements = null;

		HeaderColumnNameTranslateMappingStrategy<InputStatement> beanStrategy = new HeaderColumnNameTranslateMappingStrategy<InputStatement>();
		beanStrategy.setType(InputStatement.class);
	    	
		Map<String, String> columnMapping = new HashMap<String, String>();
		columnMapping.put("Activity", "activity");
		columnMapping.put("Channel", "channel");
		columnMapping.put("HomeNo", "homeNo");
		columnMapping.put("Starttime", "starttime");

		beanStrategy.setColumnMapping(columnMapping);
		
		CsvToBean<InputStatement> csvToBean = new CsvToBean<InputStatement>();
			
		try {
			reader = new CSVReader(new FileReader(filePath),'|');
				
			inputStatements = csvToBean.parse(beanStrategy, reader);
			Collections.sort(inputStatements);
				
//				for (InputStatement inputStatement : inputStatements) {
//			    	System.out.print(inputStatement); 
//			    	System.out.print("\n"); 
//			    	}
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			}
			
		return inputStatements;	
	}

}
