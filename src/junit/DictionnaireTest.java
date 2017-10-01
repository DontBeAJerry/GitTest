package junit;

import static org.junit.Assert.*;

import org.junit.Test;

import main.Dictionnaire;

public class DictionnaireTest {

	@Test
	public void testRchMot() {
		
		Dictionnaire d = new Dictionnaire();
		
		if (!d.rchMot("bonjour")) {
			fail("Bonjour");
		}
	}

}
