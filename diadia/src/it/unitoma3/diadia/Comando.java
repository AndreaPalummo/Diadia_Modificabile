package it.unitoma3.diadia;
import java.util.Scanner;

public class Comando {

    private String nome;
    private String parametro;

    public Comando(String istruzione) {
    	Scanner scannerDiParole = new Scanner(istruzione);

		if (scannerDiParole.hasNext())
			this.nome = scannerDiParole.next(); 

		if (scannerDiParole.hasNext())
			this.parametro = scannerDiParole.next();
    }

    public String getNome() {
        return this.nome;
    }

    public String getParametro() {
        return this.parametro;
    }

    public boolean sconosciuto() {
        return (this.nome == null);
    }
}