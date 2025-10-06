package com.generation.autori_e_libri.datddare;

import org.springframework.stereotype.Service;

@Service
public class Matematica
{

    //fattoriale = moltiplicazione di tutti i numeri
    //da 1 a quello indicato
    // fattoriale 5    -> 5!
    //1*2*3*4*5 = 120


    //1) CREARE LO STUB del metodo (spesso fatto nelle interfacce)
    //   firma + documentazione+ throw di eccezione non ancora fatto
    /**
     * Metodo che calcola fattoriale di n tramite algoritmo iterativo
     * @param n Il numero di cui vogliamo calcolare il fattoriale
     * @return Il fattoriale di n
     * @throws IllegalArgumentException Se n < 0
     */
    public long fattoriale(int n) throws IllegalArgumentException
    {
        if(n<0)
            throw new IllegalArgumentException("No numeri negativi");

        if(n<=1)
            return 1;


        int res = 1;

        for(int i=2;i<=n;i++)
            res*=i;

        return res;
    }

    public String concatenazineBrutta(int n)
    {
        StringBuilder res = new StringBuilder("paperino");

        for(int i=0;i<n;i++)
            res.append("paperino");

        return res.toString();
    }

}
