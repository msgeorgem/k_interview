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
		
		
		 List<InputStatement> inputStatements = Utils.getInputPSV(fileImportPathInput);
		
		
		 for (InputStatement inputStatement : inputStatements) {
			 ACTUAL =  inputStatement.getHomeNo()+"|"+inputStatement.getChannel()+"|"+inputStatement.getStarttime()+"|"+inputStatement.getActivity();
			 break;
		    }
		
		assertEquals(EXPECTED, ACTUAL);

	}
}
