package com.kantar.sessionsjob;

import java.util.List;


public class Main {

    // See README.txt for usage example
	
	/**
	* Example usage using the provided test files:
	* java -jar target/SessionsJob-1.0.jar 'src/test/resources/input-statements.psv' 'target/actual-sessions.psv'
	*/

    public static void main(String[] args) {
    	
    	String fileImportPath = "";
    	String fileOutputPath = "";

        if (args.length < 2) {
        	
        	System.err.println("Missing arguments: <input-statements-file> <output-sessions-file>");
            System.exit(1);

        } else {

        	fileImportPath = args[0];
        	fileOutputPath = args[1];
       }
        
       // Reading input statements file from fileImportPath and loading to inputStatements List
        List<InputStatement> inputStatements = Utils.getInputPSV(fileImportPath);
        
        // Transforming inputStatements List into outputSessions List
        List<OutputSession> outputSessions =  Utils.transformInputResults(inputStatements);
        
        // Storing outputSessions List to "target/actual-sessions.psv"
        Utils.saveOutputToPSV(fileOutputPath, outputSessions);

    }

}
