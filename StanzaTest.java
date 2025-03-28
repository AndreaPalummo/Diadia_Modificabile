package it.unitoma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StanzaTest {

	private Stanza stanza;
	private Attrezzo attrezzo;

	@BeforeEach
	void setUp() throws Exception {
		this.stanza = new Stanza("Pippo");
		this.attrezzo = new Attrezzo("spada", 200);
	}

	@Test
	void testHasAttrezzoStanzaVuota() {
		assertFalse(this.stanza.hasAttrezzo(this.attrezzo.getNome()));
	}
	
	@Test
	void testHasAttrezzo() {
		this.stanza.addAttrezzo(attrezzo);
		assertTrue(this.stanza.hasAttrezzo(this.attrezzo.getNome()));
	}
	
	@Test
	void testRemoveAttrezzo() {
		assertFalse(this.stanza.removeAttrezzo(attrezzo));
		this.stanza.addAttrezzo(attrezzo);
		assertTrue(this.stanza.removeAttrezzo(attrezzo));
	}

}
