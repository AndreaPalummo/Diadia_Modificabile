package it.unitoma3.diadia;

public class Giocatore {
	
	static final private int CFU_INIZIALI = 5;

	private int cfu = CFU_INIZIALI;
	
	private Borsa borsa = new Borsa();
	
	public Giocatore() {}
	
	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}
	
	public void decrementaCfu() {
		this.cfu--;
		
		setCfu(this.cfu);
		
		System.out.println("CFU rimanenti: " + this.cfu);
	}
	
	public Borsa getBorsa() {
		return this.borsa;
	}
}
