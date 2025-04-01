package it.unitoma3.diadia.ambienti;

import it.unitoma3.diadia.attrezzi.Attrezzo;
import it.unitoma3.diadia.giocatore.Borsa;

public class Stanza {
	
	static final private int NUMERO_MASSIMO_DIREZIONI = 4;

	private static final int NUMERO_MASSIMO_ATTREZZI = 10;
	
	private String nome;
    
    private Stanza[] stanzeAdiacenti;
    private int numeroStanzeAdiacenti;
    
	private String[] direzioni;
	
	private Borsa borsa = new Borsa();

	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;

    public Stanza(String nome) {
        this.nome = nome;
        this.numeroStanzeAdiacenti = 0;
        this.direzioni = new String[NUMERO_MASSIMO_DIREZIONI];
        this.stanzeAdiacenti = new Stanza[NUMERO_MASSIMO_DIREZIONI];
        this.attrezzi = new Attrezzo[NUMERO_MASSIMO_ATTREZZI];
    }
    
    public Borsa getBorsa() {
		return this.borsa;
	}
    
    public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
        boolean aggiornato = false;
    	for(int i=0; i<this.direzioni.length; i++)
        	if (direzione.equals(this.direzioni[i])) {
        		this.stanzeAdiacenti[i] = stanza;
        		aggiornato = true;
        	}
    	if (!aggiornato)
    		if (this.numeroStanzeAdiacenti < NUMERO_MASSIMO_DIREZIONI) {
    			this.direzioni[numeroStanzeAdiacenti] = direzione;
    			this.stanzeAdiacenti[numeroStanzeAdiacenti] = stanza;
    		    this.numeroStanzeAdiacenti++;
    		}
    }

	public Stanza getStanzaAdiacente(String direzione) {
        Stanza stanza = null;
		for(int i=0; i<this.numeroStanzeAdiacenti; i++)
        	if (this.direzioni[i].equals(direzione))
        		stanza = this.stanzeAdiacenti[i];
        return stanza;
	}

    public String getNome() {
        return this.nome;
    }

    public String getDescrizione() {
        return this.toString();
    }

    public String toString() {
    	StringBuilder risultato = new StringBuilder();
    	risultato.append(this.nome);
    	risultato.append("\nUscite: ");
    	
    	for (String direzione : this.direzioni)
    		if (direzione!=null)
    			risultato.append(" " + direzione);
    	
    	risultato.append("\nAttrezzi nella stanza: ");
    	
    	for (Attrezzo attrezzo : this.attrezzi) {
    		if(attrezzo != null)
    			risultato.append(attrezzo.toString()+" ");
    	}
    	
    	return risultato.toString();
    }
    
	public String[] getDirezioni() {
		String[] direzioni = new String[this.numeroStanzeAdiacenti];
	    for(int i=0; i<this.numeroStanzeAdiacenti; i++)
	    	direzioni[i] = this.direzioni[i];
	    return direzioni;
    }
	
	public Attrezzo[] getAttrezzi() {
        return this.attrezzi;
    }
	
	public boolean addAttrezzo(Attrezzo attrezzo) {
	    if (attrezzo == null)
	        return false;
	    
	    for(int i = 0; i < this.numeroAttrezzi; i++) {
	    	if(this.attrezzi[i] != null && this.attrezzi[i].equals(attrezzo))
	    		return false;
	    }
	        
	    if (this.numeroAttrezzi < NUMERO_MASSIMO_ATTREZZI) {
	        this.attrezzi[numeroAttrezzi] = attrezzo;
	        this.numeroAttrezzi++;
	        return true;
	    } else {
	        return false;
	    }
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
	    if (nomeAttrezzo == null) return false;
	    
	    for (Attrezzo attrezzo : this.attrezzi) {
	        if (attrezzo != null && attrezzo.getNome().equals(nomeAttrezzo))
	            return true;
	    }
	    return false;
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
	    if (nomeAttrezzo == null) return null;
	    
	    for (Attrezzo attrezzo : this.attrezzi) {
	        if (attrezzo != null && attrezzo.getNome().equals(nomeAttrezzo))
	            return attrezzo;
	    }
	    return null;    
	}
	
	public boolean removeAttrezzo(Attrezzo attrezzo) {
	    if(attrezzo == null)
	        return false;
	        
	    if(this.numeroAttrezzi == 0) {
	        System.out.println("La lista è vuota");
	        return false;
	    }

	    for(int i = 0; i < this.numeroAttrezzi; i++) {
	        if(this.attrezzi[i] != null && this.attrezzi[i].equals(attrezzo)) {
	            // Sposta gli elementi successivi
	            for(int j = i; j < this.numeroAttrezzi - 1; j++) {
	                this.attrezzi[j] = this.attrezzi[j + 1];
	            }
	            
	            // Imposta a null l'ultima posizione
	            this.attrezzi[this.numeroAttrezzi - 1] = null;
	            this.numeroAttrezzi--;
	            
	            return true;
	        }
	    }
	    return false;
	}

}