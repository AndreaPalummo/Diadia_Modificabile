package it.unitoma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GiocatoreTest {

	private Giocatore giocatore;

	@BeforeEach
	void setUp() throws Exception {
		giocatore = new Giocatore();
	}

	@Test
	void testInitCfu() {
		assertEquals(5, giocatore.getCfu());
	}
	
	@Test
	void testDecrementaCfu() {
		giocatore.decrementaCfu();
		assertEquals(4, giocatore.getCfu());
	}

}
