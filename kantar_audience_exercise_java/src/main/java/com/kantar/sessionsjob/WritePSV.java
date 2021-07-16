package com.kantar.sessionsjob;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVWriter;

public class WritePSV {
	
	
	String filePath;
	List<OutputSession> outputSessions;
	
	
	public WritePSV(String filePath, List<OutputSession> outputSessions) {
		super();
		this.filePath = filePath;
		this.outputSessions = outputSessions;
	}




	
	/**
	* Saves List<OutputSession> to PSV file with using provided file path
	*
	* @param filePath --file Path
	* @param outputSessions --output sessions
	*/

	public Boolean saveOutputToPSV()  {
		
		CSVWriter writer = null;
		List<String[]> outputList = new ArrayList<String[]>();
		outputList.add(new String[]{"HomeNo","Channel","Starttime","Activity","EndTime","Duration"});
		
		for (OutputSession outputSession : outputSessions) {
			outputList.add(new String[]{outputSession.getHomeNo(),outputSession.getChannel(),outputSession.getStarttime(),outputSession.getActivity(),outputSession.getEndTime(),outputSession.getDuration()});
		}
		
//		for (String[] item : outputList) {
//			System.out.print(item[0] +"|"+ item[1] +"|"+ item[2] +"|"+ item[3]+"|"+item[4]+"|"+item[5]); 
//	    	System.out.print("\n"); 
//		}

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

}
