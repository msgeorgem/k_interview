package com.kantar.sessionsjob;

import java.util.List;

public class ResultsGenerateForResultsTest {
	
public static void main(String[] args) {
    	
    	
    	String fileImportPath = "src/test/resources/input-statements.psv"; 
    	String fileOutputPath = "src/test/resources/actual-sessions-test.psv";
        
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
