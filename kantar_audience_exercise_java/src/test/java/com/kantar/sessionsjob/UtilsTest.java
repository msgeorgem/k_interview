package com.kantar.sessionsjob;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Test;

class UtilsTest {

	@Test
	void testReadFirstItemInInput() {
		final String EXPECTED = "1234|603|20200101070000|PlayBack";
		String ACTUAL =""; 
		
		String fileImportPathInput = "src/test/resources/input-statements.psv";
		
		ReadPSV readPSV = new ReadPSV(fileImportPathInput);
		
		List<InputStatement> inputStatements = readPSV.getInputPSV();		
		for (InputStatement inputStatement : inputStatements) {
			ACTUAL =  inputStatement.getHomeNo()+"|"+inputStatement.getChannel()+"|"+inputStatement.getStarttime()+"|"+inputStatement.getActivity();
			break;
		    }
		
		assertEquals(EXPECTED, ACTUAL);
	}
	
	@Test
	void testCalculateSessionTime() {
		final String EXPECTED = "1800" ;
		 
		String startDateS = "20200101180000";
		String endDateS = "20200101183000";
		String ACTUAL = Utils.calculateSessionTime(startDateS , endDateS);
		
		assertEquals(EXPECTED, ACTUAL); 
	}
	
	@Test
	void testCalculateSessionTimeForCeiling() {
		final String EXPECTED = "10800" ;
		 
		String startDateS = "20200101210000";
		String endDateS = "20200101235959";
		String ACTUAL = Utils.calculateSessionTimeForCeiling(startDateS , endDateS);
		
		assertEquals(EXPECTED, ACTUAL); 
	}
	
	@Test
	void testCalculateCeilingEndTimeMinusSecond() {
		final String EXPECTED = "20200102235959" ;
		 
		String startDateS = "20200102060000";
		String ACTUAL = Utils.calculateCeilingEndTimeMinusSecond(startDateS);
		
		assertEquals(EXPECTED, ACTUAL); 
	}
}
