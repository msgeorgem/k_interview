package com.kantar.sessionsjob;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang3.time.DateUtils;



/**
* Utils class holding methods transforming data 
*/
public class Utils {	
	


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
