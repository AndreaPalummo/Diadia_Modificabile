package it.unitoma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PartitaTest {

	private Partita partita;

	@BeforeEach
	void setUp() throws Exception {
		this.partita = new Partita();
	}

	@Test
	void testVerificaFinePartita() {
		assertFalse(this.partita.isFinita());
		this.partita.setFinita();
		assertTrue(this.partita.isFinita());
	}

	@Test
	void testVerificaCfu() {
		partita.getGiocatore().setCfu(0);
		assertFalse(this.partita.vinta());
	}
	
	@Test
	void testFinePartitaPerVittoria() {
		partita.getLabirinto().setStanzaCorrente(partita.getLabirinto().getStanzaVincente());
		assertTrue(this.partita.vinta());
	}
}
