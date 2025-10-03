package com.generation.autori_e_libri.datddare;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//orario come minuti a partire da mezzanotte
public class OrarioLinearizzato
{

    private int valoreMinuti;

    //nei costrutti se alla fine valoreMinuti eccede 1439 (che sarebbero 23:59) o è minore di 0
    //completo tranne per eccezione
    public OrarioLinearizzato(int valoreMinuti)
    {
        this.valoreMinuti =valoreMinuti;
    }

    //prende in ingresso ore e minuti e deve convertire
    //deve dare eccezione anche se i minuti sono più di 59
    public OrarioLinearizzato(int ore,int minuti)
    {
    }

    //prende in ingresso String "HH:mm", es "09:13"
    //deve dare eccezione se orario non reale (es "27:72","aa:15"),
    //altrimenti estrarre i dati per riempire valoreMinuti
    public OrarioLinearizzato(String orario)
    {
    }

    //METODI STUB (solo firma con ritorno fittizo, dovete completarli)

    //deve darmi orario in base a valore minuti
    //es: valoreMinuti è 342 -> "05:42"
    public String toOrarioNormale()
    {
        return "";
    }

    //se il valore di valoreMinuti rimane nell'intervallo 0 - 1439 allora basta così
    //se sfora in una delle due direzioni deve "ripartire"
    // valoreMinuti = 1430 aggiungo 25 min
    //allora dopo deve valere 15

    // valoreMinuti = 30 tolgo 50
    //allora dopo deve valere 1420
    public void aggiungiMinuti(int min)
    {

    }

    public void sottraiMinuti(int min)
    {

    }

}
