package it.unitoma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PartitaTest {

	private Partita partita;

	@BeforeEach
	void setUp() throws Exception {
		this.partita = new Partita();
	}

	@Test
	void test() {
		assertFalse(this.partita.isFinita());
		this.partita.setFinita();
		assertTrue(this.partita.isFinita());
	}

	@Test
	void test1() {
		partita.setCfu(0);
		assertFalse(this.partita.vinta());
	}
	
	@Test
	void test2() {
		partita.setStanzaCorrente(partita.getStanzaVincente());
		assertTrue(this.partita.vinta());
	}
}
