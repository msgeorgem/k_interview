package com.kantar.sessionsjob;


/**
* Class of OutputSession used to load to PSV output file.
* Class inherits after InputStatement
*/

public class OutputSession extends InputStatement{
	
	private String endTime;
	private String duration;
	
	
    public OutputSession() {
		super();
	}
    
    /**
	* Class constructor of OutputSession
	*
	* @param homeNo 
	* @param channel 
	* @param starttime 
	* @param filePath
	* @param endTime
	* @param duration  
	*/
        
	public OutputSession(String homeNo, String channel,String starttime,String activity,String endTime, String duration) {
		super(homeNo,channel,starttime,activity);
		this.endTime = endTime;
		this.duration = duration;
	}


	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}


	@Override
	public String toString() {
		return "OutputSession [ homeNo=" + getHomeNo() + ", channel=" + getChannel() + ",starttime=" + getStarttime() + ",activity=" + getActivity() + ",endTime=" + endTime + ", duration =" + duration + "]";
	}
	
	

}
