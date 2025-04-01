package it.unitoma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BorsaTest {

	private Borsa borsa;
	private Attrezzo martello;

	@BeforeEach
	void setUp() throws Exception {
		borsa = new Borsa();
		martello = new Attrezzo("martello", 5);
	}

	@Test
	void testAddAttrezzo() {
		assertTrue(borsa.addAttrezzo(martello));
	}
	
	@Test
	void testGetAttrezzo() {
		borsa.addAttrezzo(martello);
		assertEquals(martello, borsa.getAttrezzo("martello"));
	}
	
	@Test
	void testGetPeso() {
		borsa.addAttrezzo(martello);
		borsa.addAttrezzo(martello);
		assertEquals(10, borsa.getPeso());
	}
	
	@Test
	void testHasAttrezzo() {
		assertFalse(borsa.hasAttrezzo("martello"));
		borsa.addAttrezzo(martello);
		assertTrue(borsa.hasAttrezzo("martello"));
	}

	@Test
	void testRemoveAttrezzo() {
		borsa.addAttrezzo(martello);
		
		assertEquals(martello, borsa.removeAttrezzo("martello"));
	}
}
