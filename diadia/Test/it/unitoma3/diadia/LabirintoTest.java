package it.unitoma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unitoma3.diadia.ambienti.Labirinto;

class LabirintoTest {

	private Labirinto labirinto;

	@BeforeEach
	void setUp() throws Exception {
		labirinto = new Labirinto();
		labirinto.creaStanze();
	}

	@Test
	void testCreaStanze() {
		assertNotNull(labirinto.getStanzaCorrente());
		assertNotNull(labirinto.getStanzaVincente());
		
		 assertEquals("Atrio", labirinto.getStanzaCorrente().getNome());
		 assertEquals("Biblioteca", labirinto.getStanzaVincente().getNome());
		 
		 
	}

}
