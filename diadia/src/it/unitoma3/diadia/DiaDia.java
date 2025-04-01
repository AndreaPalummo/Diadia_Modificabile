package it.unitoma3.diadia;
//import java.util.Scanner;

import it.unitoma3.diadia.ambienti.Stanza;
import it.unitoma3.diadia.attrezzi.Attrezzo;

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

	private IOConsole io;

	public DiaDia() {
		this.partita = new Partita();
		this.io = new IOConsole();
	}

	public void gioca() {
		String istruzione; 
		//Scanner scannerDiLinee;

		io.mostraMessaggio(MESSAGGIO_BENVENUTO);

		//scannerDiLinee = new Scanner(System.in);		

		do		
			istruzione = io.leggiRiga();
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
			io.mostraMessaggio("COMANDO INESISTENTE");

		if(this.partita.isFinita()) {
			if (this.partita.vinta()) {
				io.mostraMessaggio("Bravo, hai vinto!!");
				return true;
			} else if(this.partita.getGiocatore().getCfu() <= 0){
				io.mostraMessaggio("Peccato, hai finito i CFU!!");
				return true;
			}	
		}
		return false;
	}   

	//gli attrezzi posati vengono rimossi dalla borsa e aggiunti alla stanza
	private void posa(String nomeAttrezzo) {
		if(nomeAttrezzo == null) {
	        io.mostraMessaggio("Quale attrezzo vuoi posare?");
	        return;
	    }
		
	    if(this.partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo)) {
	        Attrezzo attrezzo = this.partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
	        
	        this.partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
	        io.mostraMessaggio("Attrezzo " + nomeAttrezzo + " rimosso dalla borsa");
	        
	        if(!this.partita.getLabirinto().getStanzaCorrente().hasAttrezzo(nomeAttrezzo)) {
	            this.partita.getLabirinto().getStanzaCorrente().addAttrezzo(attrezzo);
	            io.mostraMessaggio("Attrezzo " + nomeAttrezzo + " inserito nella stanza");
	        } else {
	            io.mostraMessaggio("Attrezzo " + nomeAttrezzo + " già presente nella stanza");
	        }
	    } else {
	        io.mostraMessaggio("Attrezzo " + nomeAttrezzo + " non presente nella borsa");
	    }
	}

	//gli attrezzi presi vengono rimossi dalla stanza e aggiunti alla borsa
	private void prendi(String nomeAttrezzo) {
	    if(nomeAttrezzo == null) {
	        io.mostraMessaggio("Quale attrezzo vuoi prendere?");
	        return;
	    }
	    
	    if(this.partita.getLabirinto().getStanzaCorrente().hasAttrezzo(nomeAttrezzo)) {
	        Attrezzo attrezzo = this.partita.getLabirinto().getStanzaCorrente().getAttrezzo(nomeAttrezzo);
	        
	        if(this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzo)) {
	            this.partita.getLabirinto().getStanzaCorrente().removeAttrezzo(attrezzo);
	            io.mostraMessaggio("Attrezzo " + nomeAttrezzo + " prelevato dalla stanza e aggiunto alla borsa");
	        } else {
	        	io.mostraMessaggio("Non c'è più spazio nella borsa per l'attrezzo " + nomeAttrezzo);
	        }
	    } else {
	        io.mostraMessaggio("Attrezzo " + nomeAttrezzo + " non presente nella stanza");
	    }
	}

	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			io.mostraMessaggio(elencoComandi[i]+" ");
		io.mostraMessaggio(" ");
	}

	private void vai(String direzione) {
		if(direzione==null)
			io.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null) {
			io.mostraMessaggio("CFU rimanenti: " + this.partita.getGiocatore().getCfu());
			io.mostraMessaggio("Direzione inesistente");
		}else{
			this.partita.getLabirinto().setStanzaCorrente(prossimaStanza);
			this.partita.getGiocatore().decrementaCfu();
		}

		io.mostraMessaggio(this.partita.getLabirinto().getStanzaCorrente().getDescrizione());
	}

	private void fine() {
		io.mostraMessaggio("Grazie di aver giocato!");
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
		
	}
}