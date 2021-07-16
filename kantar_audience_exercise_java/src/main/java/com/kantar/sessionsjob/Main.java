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
        
        ReadPSV readPSV = new ReadPSV(fileImportPath);
        List<InputStatement> inputStatements = readPSV.getInputPSV();
        
        // Transforming inputStatements List into outputSessions List
        TransformInputToResults transformInputToResults = new TransformInputToResults(inputStatements);
        List<OutputSession> outputSessions =  transformInputToResults.transformInputResults();
        
        // Storing outputSessions List to "target/actual-sessions.psv"
        WritePSV writePSV = new WritePSV(fileOutputPath,outputSessions);
        writePSV.saveOutputToPSV();
    }

}
