package com.generation.autori_e_libri;

import com.generation.autori_e_libri.datddare.Matematica;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestMatematica
{
    @Autowired
    Matematica mat;

    @Test
    void testaFattorialeBuono()
    {
        long r1 = mat.fattoriale(0); //è 1
        assertEquals(1,r1,"Fattoriale 0 sbagliato");

        long r2 = mat.fattoriale(1); //è 1
        assertEquals(1,r2,"Fattoriale 1 sbagliato");

        long r3 = mat.fattoriale(5); //è 120
        assertEquals(120,r3,"Fattoriale 5 sbagliato");
    }

    @Test
    void testaFattorialeCattivo()
    {
        try
        {
            mat.fattoriale(-10);
            fail("Doveva dare eccezione");
        }
        catch (IllegalArgumentException e)
        {
            //BENE, Ha dato eccezione, come doveva
        }
    }

    @Test
    void testaConcatenzazioneGrande()
    {
        long start =System.nanoTime();//tempo quando è stata eseguita questa riga

        String res = mat.concatenazineBrutta(100000);

        long end =System.nanoTime();
        long tempoEsecuzione = end-start;
        boolean minoreDi1Sec = tempoEsecuzione<1000000000;
        System.out.println(tempoEsecuzione);
        assertTrue(minoreDi1Sec,"Ci ha messo "+(tempoEsecuzione/1000000)+" millisecondi");
    }
}
