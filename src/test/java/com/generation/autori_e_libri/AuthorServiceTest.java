package com.generation.autori_e_libri;

import org.junit.jupiter.api.Test;

//TODO 1 Annotare qui come BookService
public class AuthorServiceTest
{

    //Todo 2 Scrivere refreshDb (copiancollate quello dell'altro test, ma fate in modo di avere un autore senza libri)


    //TODO 3 - Scrivere metodi
    @Test
    void testFindALlAuthors()
    {
        //testare che la lista di dto letta contenga 3 autori
    }

    @Test
    void testSaveAuthorsGood()
    {
        //modificare il metodo save per fare restituire un dto output
        //verificare che il metodo si comporti bene
        //uguale a quello dei libri
    }

    @Test
    void testSaveAuthorsBad()
    {
        //provare a salvare autore con data di nascita
        //nel futuro, deve dare eccezione
    }

    @Test
    void testDeleteGood()
    {
       //leggere autore senza libri
        //cancellarlo
        //controllare che ci siano solo 2 autori nel db
    }

    @Test
    void testDeleteBad()
    {
        //leggere autore con libri
        //cancellarlo
        //controllare che dia eccezione
    }
}
