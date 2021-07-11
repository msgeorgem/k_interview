package com.kantar.sessionsjob;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

class ResultsTest {
	
	@Test
	void fullTest() {
		
		String ACTUAL = "";
		String EXPECTED = "";
		
		String fileImportPathExpected = "src/test/resources/expected-sessions.psv"; 
		String fileImportPathResults = "src/test/resources/actual-sessions.psv";
		
		
		List<OutputSession> exp = getReadPSVOutput(fileImportPathExpected);
		List<OutputSession> act =  getReadPSVOutput(fileImportPathResults);
		
		for (int i = 0; i < act.size(); i++) {
			
			ACTUAL =   act.get(i).getHomeNo()+"|"+act.get(i).getChannel()+"|"+act.get(i).getStarttime()+"|"+act.get(i).getActivity()+"|"+act.get(i).getEndTime()+"|"+act.get(i).getDuration();
			EXPECTED = exp.get(i).getHomeNo()+"|"+exp.get(i).getChannel()+"|"+exp.get(i).getStarttime()+"|"+exp.get(i).getActivity()+"|"+exp.get(i).getEndTime()+"|"+exp.get(i).getDuration();
			assertEquals(EXPECTED, ACTUAL);
		}
	}
	
	public static List<OutputSession> getReadPSVOutput(String filePath)  {
		
		
		CSVReader reader;
		List<OutputSession> outputSessions = null;

        HeaderColumnNameTranslateMappingStrategy<OutputSession> beanStrategy = new HeaderColumnNameTranslateMappingStrategy<OutputSession>();
    	beanStrategy.setType(OutputSession.class);
    	
    	Map<String, String> columnMapping = new HashMap<String, String>();
    	
    	columnMapping.put("HomeNo", "homeNo");
    	columnMapping.put("Channel", "channel");
    	columnMapping.put("Starttime", "starttime");
    	columnMapping.put("Activity", "activity");
    	columnMapping.put("EndTime", "endTime");
    	columnMapping.put("Duration", "duration");

    	beanStrategy.setColumnMapping(columnMapping);
	
		CsvToBean<OutputSession> csvToBean = new CsvToBean<OutputSession>();
		
		try {
			reader = new CSVReader(new FileReader(filePath),'|');
			
			outputSessions = csvToBean.parse(beanStrategy, reader);
			
			Collections.sort(outputSessions);
			
			
		} catch (FileNotFoundException e) {

			System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Results file has not been generated yet!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.print("\n");
			System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Run program to generate results file!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.print("\n");
			System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!> java -jar target/SessionsJob-1.0.jar <!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.print("\n");
			System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!ResultsTest is going to fail!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.print("\n");
		}
		return outputSessions;
}
	
}
