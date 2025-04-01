package it.unitoma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unitoma3.diadia.ambienti.Stanza;
import it.unitoma3.diadia.attrezzi.Attrezzo;

class StanzaTest {

	private Stanza stanza;
	private Stanza stanzaAdiacente;
	private Attrezzo martello;
	private Attrezzo cacciavite;

	@BeforeEach
	void setUp() throws Exception {
		stanza = new Stanza("Pippo");
		stanzaAdiacente = new Stanza("Pluto");
		martello = new Attrezzo("martello", 5);
		cacciavite = new Attrezzo("cacciavite", 2);
	}

	@Test
	void testUnaStanzaAdiacente() {
		this.stanza.impostaStanzaAdiacente("sud", stanzaAdiacente);
		assertEquals(stanzaAdiacente, stanza.getStanzaAdiacente("sud"));
	}
	
	@Test
	void testStanzeAdiacentiVuote() {
		assertNull(stanza.getStanzaAdiacente("nord"));
		assertNull(stanza.getStanzaAdiacente("est"));
		assertNull(stanza.getStanzaAdiacente("ovest"));
		assertNull(stanza.getStanzaAdiacente("sud"));
	}
	 
	 @Test
	 public void testAddAttrezzo() {
		 assertTrue(stanza.addAttrezzo(cacciavite));
		 assertTrue(stanza.addAttrezzo(martello));
		 
		 assertFalse(stanza.addAttrezzo(martello)); 
	 }
	 
	 @Test
	 public void testRemvoeAttrezzo() {
		 assertTrue(stanza.addAttrezzo(martello));
		 assertTrue(stanza.addAttrezzo(cacciavite));
		 
		 assertTrue(stanza.removeAttrezzo(cacciavite));
	 }
}
