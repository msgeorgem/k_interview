package com.kantar.sessionsjob;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;


/**
* Utils class holding methods for reading PSV, transforming data and loading results to PSV file
*/
public class Utils {	
	
/**
* Reads PSV file with input statements
*
* @param filePath file Path
* @return inputStatements
*/
public static List<InputStatement> getInputPSV(String filePath)  {
	
	
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
			
//			for (InputStatement inputStatement : inputStatements) {
//		    	System.out.print(inputStatement); 
//		    	System.out.print("\n"); 
//		    	}
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		return inputStatements;	
}

/**
* Saves List<OutputSession> to PSV file with using provided file path
*
* @param filePath --file Path
* @param outputSessions --output sessions
*/

public static Boolean saveOutputToPSV(String filePath, List<OutputSession> outputSessions)  {
	
	CSVWriter writer = null;
	List<String[]> outputList = new ArrayList<String[]>();
	outputList.add(new String[]{"HomeNo","Channel","Starttime","Activity","EndTime","Duration"});
	
	for (OutputSession outputSession : outputSessions) {
		outputList.add(new String[]{outputSession.getHomeNo(),outputSession.getChannel(),outputSession.getStarttime(),outputSession.getActivity(),outputSession.getEndTime(),outputSession.getDuration()});
	}
	
//	for (String[] item : outputList) {
//		System.out.print(item[0] +"|"+ item[1] +"|"+ item[2] +"|"+ item[3]+"|"+item[4]+"|"+item[5]); 
//    	System.out.print("\n"); 
//	}

	try 
	{
	  writer = new CSVWriter(new FileWriter(filePath), '|', CSVWriter.NO_QUOTE_CHARACTER);
	  writer.writeAll(outputList);  
	  writer.close();
	} 
	catch (IOException e)
	{
		e.printStackTrace();
	}
	return null;
}


/**
* Transforms List<InputStatement> into List<OutputSession>
* 
* @param intputStatements --intput statements
* @return outputSessions 
*/

public static List<OutputSession> transformInputResults (List<InputStatement> intputStatements) {
	
	List<OutputSession> outputSessions = new ArrayList<OutputSession>();

	for (int i = 0; i < intputStatements.size(); i++) {
		
		
//    	System.out.print(intputStatements.get(i).getHomeNo() + "|" + intputStatements.get(i).getChannel() + "|" + intputStatements.get(i).getStarttime()  + "|" + intputStatements.get(i).getActivity()); 
//    	System.out.print("\n"); 
    	
    	OutputSession outputSession = new OutputSession(
    			intputStatements.get(i).getHomeNo(),
    			intputStatements.get(i).getChannel(),
    			intputStatements.get(i).getStarttime(),
    			intputStatements.get(i).getActivity(), null,null);
    	
    	outputSessions.add(outputSession);

    }

	for (int i = 0; i < outputSessions.size(); i++) {
		
		String homeNo = outputSessions.get(i).getHomeNo();
		
		int j = i + 1;
		if(j < intputStatements.size()){  
			if (intputStatements.get(j).getHomeNo().equals(homeNo)) {
				outputSessions.get(i).setEndTime(intputStatements.get(j).getStarttime());
				outputSessions.get(i).setDuration(calculateSessionTime(outputSessions.get(i).getStarttime(), outputSessions.get(i).getEndTime()));
				outputSessions.get(i).setEndTime(calculateEndTimeMinusSecond(outputSessions.get(i).getEndTime()));
			} else {
				outputSessions.get(i).setEndTime(calculateCeilingEndTimeMinusSecond(outputSessions.get(i).getStarttime()));
				outputSessions.get(i).setDuration(calculateSessionTimeForCeiling(outputSessions.get(i).getStarttime(), outputSessions.get(i).getEndTime()));
			}  
	    } else {
	    	outputSessions.get(i).setEndTime(calculateCeilingEndTimeMinusSecond(outputSessions.get(i).getStarttime()));
	    	outputSessions.get(i).setDuration(calculateSessionTimeForCeiling(outputSessions.get(i).getStarttime(), outputSessions.get(i).getEndTime()));
	    }
	}

//	for (OutputSession outputSession : outputSessions) {
//		System.out.print(outputSession.getHomeNo() + "|" + outputSession.getChannel() + "|" + outputSession.getStarttime()  + "|" + outputSession.getActivity()+ "|" + outputSession.getEndTime()+ "|" + outputSession.getDuration()); 
//    	System.out.print("\n"); 
//   
//    }
	
    return outputSessions;
}

/**
* Transforms String date into Date format
* 
* @param dateString --"yyyyMMddHHmmss" date String
* @return date 
*/

public static Date convertStringToDateFormat(String dateString) {
	
	Date date = null;
	    
	try {
		date = new SimpleDateFormat("yyyyMMddHHmmss").parse(dateString);
	} catch (ParseException e) {
		e.printStackTrace();
	}  
	//System.out.println(dateString +"\t"+date1);
	    
	return date;
}

/**
* Transforms Date format into date in string format
* 
* @param date --date Date
* @return strDate
*/

public static String convertDateFormattoString(Date date) {
	
	DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");  
    
    String strDate = dateFormat.format(date);  
    //System.out.println("Converted String: " + strDate);  
    
    return strDate;
} 


/**
* Calculates session time providing duration in seconds in string format 
* 
* @param startDateS --start datetime of session String
* @param endDateS --end datetime of session String
* @return duration
*/

public static String calculateSessionTime(String startDateS , String endDateS) {
	
	Date startDate = convertStringToDateFormat(startDateS);
	Date endDate = convertStringToDateFormat(endDateS); 
	
	long differenceInSeconds = (endDate.getTime() - startDate.getTime())/1000;
    
    String duration = Long.toString(differenceInSeconds);  
    //System.out.println("Converted Duration String: " + duration);  
    
    return duration;
} 

/**
* Calculates session time providing duration in seconds in string format 
* Contains correction for "one second" 
* 
* @param startDateS --start datetime of session String
* @param endDateS --end datetime of session String
* @return duration
*/

public static String calculateSessionTimeForCeiling(String startDateS , String endDateS) {
	
	Date startDate = convertStringToDateFormat(startDateS);
	Date endDate = convertStringToDateFormat(endDateS); 
	
	long differenceInSeconds = (endDate.getTime() - startDate.getTime() + 1000)/1000;
    
    String duration = Long.toString(differenceInSeconds);  
    //System.out.println("Converted Duration String: " + duration);  
    
    return duration;
} 

/**
* Converts(corrects) end datetime by a second returning end datetime in string format  
*
* @param endDateS --end datetime of session String
* @return endTimeMinusSecondS
*/

public static String calculateEndTimeMinusSecond(String endDateS) {
	
	Date endDate = convertStringToDateFormat(endDateS); 
	long endTimeMinusSecond = (endDate.getTime() - 1000);
	Date date = new Date(endTimeMinusSecond);   
    String endTimeMinusSecondS = convertDateFormattoString(date);  
    //System.out.println("Converted Duration String: " + endTimeMinusSecondS);  
    
    return endTimeMinusSecondS;
}


/**
* Converts start datetime to end datetime minus second in string format   
*
* @param startDateS --start datetime of session String
* @return endTimeMinusSecondS
*/

public static String calculateCeilingEndTimeMinusSecond(String startDateS) {
	
	Date endDate = convertStringToDateFormat(startDateS);
	Date ceilingEndDate = DateUtils.ceiling(endDate, Calendar.DATE);
	long endTimeMinusSecond = (ceilingEndDate.getTime() - 1000);
	Date date = new Date(endTimeMinusSecond);   
    String endTimeMinusSecondS = convertDateFormattoString(date);  
    //System.out.println("Converted Duration String: " + endTimeMinusSecondS);  
    
    return endTimeMinusSecondS;
} 

}
