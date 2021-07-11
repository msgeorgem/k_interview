package com.kantar.sessionsjob;

/**
* Class of inputStatements used to read PSV input file
*/

public class InputStatement implements Comparable<InputStatement> {
	

    private String homeNo;
    private String channel;
    private String starttime;
    private String activity;

	public InputStatement() {
		super();
	}

	public String getHomeNo() {
		return homeNo;
	}

	/**
	* Class constructor of inputStatements
	*
	* @param homeNo 
	* @param channel 
	* @param starttime 
	* @param filePath 
	*/
	
	public InputStatement(String homeNo, String channel, String starttime, String activity) {
		this.homeNo = homeNo;
		this.channel = channel;
		this.starttime = starttime;
		this.activity = activity;
	}


	public void setHomeNo(String homeNo) {
		this.homeNo = homeNo;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	@Override
	public int compareTo(InputStatement arg) {
		
		
		int result = homeNo.compareTo(arg.homeNo);
		
		if (result != 0) {
			return result;
		}
		return starttime.compareTo(arg.starttime);
	}
	
	@Override
	public String toString() {
		return "InputStatement [homeNo=" + homeNo + ", channel=" + channel + ", starttime=" + starttime + ", activity="
				+ activity +  "]";
	}
	
}
