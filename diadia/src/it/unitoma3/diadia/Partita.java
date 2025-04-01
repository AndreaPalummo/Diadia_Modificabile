package it.unitoma3.diadia;

import it.unitoma3.diadia.ambienti.Labirinto;
import it.unitoma3.diadia.giocatore.Giocatore;

public class Partita {

	private boolean finita;
	private Labirinto labirinto;
	private Giocatore giocatore;
	
	public Partita(){
		this.labirinto = new Labirinto();
		this.labirinto.creaStanze();
		this.giocatore = new Giocatore();
		this.finita = false;
	}

	public boolean vinta() {
		return this.labirinto.getStanzaCorrente().getNome().equals(this.labirinto.getStanzaVincente().getNome());
	}

	public boolean isFinita() {
		return this.finita || vinta() || (this.giocatore.getCfu() == 0);
	}

	public void setFinita() {
		this.finita = true;
	}

	public Labirinto getLabirinto() {
		return this.labirinto;
	}	
	
	public Giocatore getGiocatore() {
		return this.giocatore;
	}
}
