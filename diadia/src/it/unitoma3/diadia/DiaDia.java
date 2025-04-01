package it.unitoma3.diadia;
import java.util.Scanner;

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "posa", "prendi"};

	private Partita partita;

	public DiaDia() {
		this.partita = new Partita();
	}

	public void gioca() {
		String istruzione; 
		Scanner scannerDiLinee;

		System.out.println(MESSAGGIO_BENVENUTO);

		scannerDiLinee = new Scanner(System.in);		

		do		
			istruzione = scannerDiLinee.nextLine();
		while (!processaIstruzione(istruzione));
	}   

	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);

		if(comandoDaEseguire.getNome() == null)
			return false;

		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;

		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());

		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		
		else if(comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		
		else if(comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		
		else
			System.out.println("Comando sconosciuto");

		if(this.partita.isFinita()) {
			if (this.partita.vinta()) {
				System.out.println("Hai vinto!");
				return true;
			} else if(this.partita.getGiocatore().getCfu() <= 0){
				System.out.println("Hai finito i CFU!!");
				return true;
			}	
		}
		return false;
	}   

	//gli attrezzi posati vengono rimossi dalla borsa e aggiunti alla stanza
	private void posa(String nomeAttrezzo) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("inserire il peso dell'oggetto: ");
		int peso = scanner.nextInt();
		
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
		
		if(this.partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo) != false) {
			this.partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
			System.out.println("Attrezzo " + nomeAttrezzo + " rimosso dalla borsa");
		} else {
			System.out.println("Attrezzo " + nomeAttrezzo + " non presente nella borsa");
		}
		
		if(this.partita.getLabirinto().getStanzaCorrente().hasAttrezzo(nomeAttrezzo) == false) {
			this.partita.getLabirinto().getStanzaCorrente().addAttrezzo(attrezzo);
			System.out.println("Attrezzo " + nomeAttrezzo + " inserito nella stanza");
		} else {
			System.out.println("Attrezzo " + nomeAttrezzo + " già presente nella stanza");
		}
		
	}

	//gli attrezzi presi vengono rimossi dalla stanza e aggiunti alla borsa
	private void prendi(String nomeAttrezzo) {
		
	}

	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			System.out.print(elencoComandi[i]+" ");
		System.out.println();
	}

	private void vai(String direzione) {
		if(direzione==null)
			System.out.println("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null) {
			System.out.println("CFU rimanenti: " + this.partita.getGiocatore().getCfu());
			System.out.println("Direzione inesistente");
		}else{
			this.partita.getLabirinto().setStanzaCorrente(prossimaStanza);
			this.partita.getGiocatore().decrementaCfu();
		}

		System.out.println(this.partita.getLabirinto().getStanzaCorrente().getDescrizione());
	}

	private void fine() {
		System.out.println("Grazie di aver giocato!");
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
}