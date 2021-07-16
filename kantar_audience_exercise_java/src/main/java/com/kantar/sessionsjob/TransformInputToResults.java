package com.kantar.sessionsjob;

import java.util.ArrayList;
import java.util.List;

public class TransformInputToResults {
	
	
	List<InputStatement> intputStatements;
	
	

	public TransformInputToResults(List<InputStatement> intputStatements) {
		super();
		this.intputStatements = intputStatements;
	}


	public List<InputStatement> getIntputStatements() {
		return intputStatements;
	}


	public void setIntputStatements(List<InputStatement> intputStatements) {
		this.intputStatements = intputStatements;
	}



	/**
	* Transforms List<InputStatement> into List<OutputSession>
	* 
	* @param intputStatements --intput statements
	* @return outputSessions 
	*/

	public List<OutputSession> transformInputResults() {
		
		List<OutputSession> outputSessions = new ArrayList<OutputSession>();

		for (int i = 0; i < intputStatements.size(); i++) {
			
//	    	System.out.print(intputStatements.get(i).getHomeNo() + "|" + intputStatements.get(i).getChannel() + "|" + intputStatements.get(i).getStarttime()  + "|" + intputStatements.get(i).getActivity()); 
//	    	System.out.print("\n"); 
	    	
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
					outputSessions.get(i).setDuration(Utils.calculateSessionTime(outputSessions.get(i).getStarttime(), outputSessions.get(i).getEndTime()));
					outputSessions.get(i).setEndTime(Utils.calculateEndTimeMinusSecond(outputSessions.get(i).getEndTime()));
				} else {
					outputSessions.get(i).setEndTime(Utils.calculateCeilingEndTimeMinusSecond(outputSessions.get(i).getStarttime()));
					outputSessions.get(i).setDuration(Utils.calculateSessionTimeForCeiling(outputSessions.get(i).getStarttime(), outputSessions.get(i).getEndTime()));
				}  
		    } else {
		    	outputSessions.get(i).setEndTime(Utils.calculateCeilingEndTimeMinusSecond(outputSessions.get(i).getStarttime()));
		    	outputSessions.get(i).setDuration(Utils.calculateSessionTimeForCeiling(outputSessions.get(i).getStarttime(), outputSessions.get(i).getEndTime()));
		    }
		}

//		for (OutputSession outputSession : outputSessions) {
//			System.out.print(outputSession.getHomeNo() + "|" + outputSession.getChannel() + "|" + outputSession.getStarttime()  + "|" + outputSession.getActivity()+ "|" + outputSession.getEndTime()+ "|" + outputSession.getDuration()); 
//	    	System.out.print("\n"); 
	//   
//	    }
		
	    return outputSessions;
	}

}
