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
        if(valoreMinuti>1439 || valoreMinuti<0)
            throw new RuntimeException();
        this.valoreMinuti =valoreMinuti;

    }

    //prende in ingresso ore e minuti e deve convertire
    //deve dare eccezione anche se i minuti sono più di 59
    public OrarioLinearizzato(int ore,int minuti)
    {
        int totMin = (ore*60)+minuti;
        if(minuti>59 || ore<0 || minuti<0 || totMin>1439 || totMin<0)
            throw new RuntimeException();
        this.valoreMinuti =totMin;
    }

    //prende in ingresso String "HH:mm", es "09:13"
    //deve dare eccezione se orario non reale (es "27:72","aa:15"),
    //altrimenti estrarre i dati per riempire valoreMinuti
    public OrarioLinearizzato(String orario)
    {
        try {
            String[] times = orario.split(":");
            int ora = Integer.parseInt(times[0]);
            int minuti = Integer.parseInt(times[1]);
            int totMin = (ora*60)+minuti;
            if(minuti>59 || ora<0 || minuti<0 || totMin>1439 || totMin<0 )
                throw new RuntimeException();
            this.valoreMinuti =totMin;
        } catch (Exception e) {
            throw new RuntimeException("I valori non sono corretti");
        }
    }

    //METODI STUB (solo firma con ritorno fittizo, dovete completarli)

    //deve darmi orario in base a valore minuti
    //es: valoreMinuti è 342 -> "05:42"
    public String toOrarioNormale()
    {
        int ore = this.valoreMinuti/60;
        int minuti = this.valoreMinuti%60;
        if(ore>9 && minuti>9)
            return ore+":"+minuti;
        else if (ore<10 && minuti>9)
            return "0"+ore+":"+minuti;
        else
            return "0"+ore+":0"+minuti;
    }

    //se il valore di valoreMinuti rimane nell'intervallo 0 - 1439 allora basta così
    //se sfora in una delle due direzioni deve "ripartire"
    // valoreMinuti = 1430 aggiungo 25 min
    //allora dopo deve valere 15

    // valoreMinuti = 30 tolgo 50
    //allora dopo deve valere 1420
    public void aggiungiMinuti(int min)
    {
        int diff = 0;
        if ((valoreMinuti+min)>1440) {
            diff = 1440 - valoreMinuti;
            this.valoreMinuti = min - diff;
        }
        else if(valoreMinuti+min==1440)
            this.valoreMinuti=0;
        else
            this.valoreMinuti=valoreMinuti+min;
    }

    public void sottraiMinuti(int min)
    {

        int diff = 0;
        if ((valoreMinuti-min)<0) {
            diff = 1440 + valoreMinuti;
            this.valoreMinuti = diff-min;
        }
        else
            this.valoreMinuti=valoreMinuti-min;
    }

}
