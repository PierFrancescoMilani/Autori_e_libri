package com.generation.autori_e_libri;

import com.generation.autori_e_libri.datddare.OrarioLinearizzato;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class OrarioLinearizzatoConsegna
{

    @Test
    void testCreazioneBuono()
    {
        //il secondo e terzo costruttore
        OrarioLinearizzato o1 = new OrarioLinearizzato(3,39);
        assertEquals(219,o1.getValoreMinuti(),"Calcolo sbagliato");
        OrarioLinearizzato o2 = new OrarioLinearizzato("03:39");
        assertEquals(219,o2.getValoreMinuti(),"Calcolo sbagliato");
    }

    @Test
    void testCreazioneNegativo()
    {
        //il secondo e terzo costruttore
        try
        {
            OrarioLinearizzato o1 = new OrarioLinearizzato(2600);
            fail("Doveva dare eccezione");
        }
        catch (Exception e){}

        try
        {
            OrarioLinearizzato o2 = new OrarioLinearizzato(27,36);
            fail("Doveva dare eccezione");
        }
        catch (Exception e){}

        try
        {
            OrarioLinearizzato o3 = new OrarioLinearizzato(23,80);
            fail("Doveva dare eccezione");
        }
        catch (Exception e){}

        try
        {
            OrarioLinearizzato o4 = new OrarioLinearizzato("27:36");
            fail("Doveva dare eccezione");
        }
        catch (Exception e){}

        try
        {
            OrarioLinearizzato o5 = new OrarioLinearizzato("aa:36");
            fail("Doveva dare eccezione");
        }
        catch (Exception e){}

        try
        {
            OrarioLinearizzato o6 = new OrarioLinearizzato("22:aa");
            fail("Doveva dare eccezione");
        }
        catch (Exception e){}

        try
        {
            OrarioLinearizzato o7 = new OrarioLinearizzato(-200);
            fail("Doveva dare eccezione");
        }
        catch (Exception e){}

        try
        {
            OrarioLinearizzato o8 = new OrarioLinearizzato(-3,36);
            fail("Doveva dare eccezione");
        }
        catch (Exception e){}

        try
        {
            OrarioLinearizzato o9 = new OrarioLinearizzato(23,-10);
            fail("Doveva dare eccezione");
        }
        catch (Exception e){}

        try
        {
            OrarioLinearizzato o10 = new OrarioLinearizzato("21:-36");
            fail("Doveva dare eccezione");
        }
        catch (Exception e){}

        try
        {
            OrarioLinearizzato o11 = new OrarioLinearizzato("-22:36");
            fail("Doveva dare eccezione");
        }
        catch (Exception e){}


    }

    @Test
    void testConversioneNormale()
    {
        OrarioLinearizzato o1 = new OrarioLinearizzato(342);
        assertEquals("05:42",o1.toOrarioNormale(),"Conversione errata");
        OrarioLinearizzato o2 = new OrarioLinearizzato(308);
        assertEquals("05:08",o2.toOrarioNormale(),"Conversione errata");
        OrarioLinearizzato o3 = new OrarioLinearizzato(1429);
        assertEquals("23:49",o3.toOrarioNormale(),"Conversione errata");
    }

    @Test
    void testAggiuntaMin()
    {
        OrarioLinearizzato o1 = new OrarioLinearizzato(800);
        o1.aggiungiMinuti(60);
        assertEquals(860,o1.getValoreMinuti(),"Aggiunta errata");

        OrarioLinearizzato o2 = new OrarioLinearizzato(1430);
        o1.aggiungiMinuti(25);
        assertEquals(15,o2.getValoreMinuti(),"Aggiunta errata");

        OrarioLinearizzato o3 = new OrarioLinearizzato(1430);
        o1.aggiungiMinuti(10);
        assertEquals(0,o3.getValoreMinuti(),"Aggiunta errata");
    }

    @Test
    void testSottrazioneMin()
    {
        OrarioLinearizzato o1 = new OrarioLinearizzato(800);
        o1.sottraiMinuti(60);
        assertEquals(740,o1.getValoreMinuti(),"Sottrazione errata");

        OrarioLinearizzato o2 = new OrarioLinearizzato(30);
        o1.sottraiMinuti(50);
        assertEquals(1420,o2.getValoreMinuti(),"Sottrazione errata");

        OrarioLinearizzato o3 = new OrarioLinearizzato(30);
        o1.sottraiMinuti(30);
        assertEquals(0,o3.getValoreMinuti(),"Sottrazione errata");

        OrarioLinearizzato o4 = new OrarioLinearizzato(30);
        o1.sottraiMinuti(31);
        assertEquals(1439,o4.getValoreMinuti(),"Sottrazione errata");
    }
}
