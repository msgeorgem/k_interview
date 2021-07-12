package com.kantar.sessionsjob;

import java.util.List;

public class ResultsGenerateForResultsTest {
	
public static void main(String[] args) {
    	
    	
    	String fileImportPath = "src/test/resources/input-statements.psv"; 
    	String fileOutputPath = "src/test/resources/actual-sessions-test.psv";
        
       // Reading input statements file from fileImportPath and loading to inputStatements List
        List<InputStatement> inputStatements = Utils.getInputPSV(fileImportPath);
        
        // Transforming inputStatements List into outputSessions List
        List<OutputSession> outputSessions =  Utils.transformInputResults(inputStatements);
        
        // Storing outputSessions List to "target/actual-sessions.psv"
        Utils.saveOutputToPSV(fileOutputPath, outputSessions);

    }

}
