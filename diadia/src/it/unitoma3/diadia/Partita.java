package it.unitoma3.diadia;

public class Partita {

	private boolean finita;
	private Labirinto labirinto = new Labirinto();
	private Giocatore giocatore = new Giocatore();
	
	public Partita(){
		this.labirinto.creaStanze();
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
