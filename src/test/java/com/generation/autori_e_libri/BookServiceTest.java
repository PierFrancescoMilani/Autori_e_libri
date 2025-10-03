package com.generation.autori_e_libri;

import com.generation.autori_e_libri.model.dtos.InputBookDto;
import com.generation.autori_e_libri.model.dtos.OutputBookDto;
import com.generation.autori_e_libri.model.entities.Author;
import com.generation.autori_e_libri.model.entities.Book;
import com.generation.autori_e_libri.model.repositories.AuthorRepository;
import com.generation.autori_e_libri.model.repositories.BookRepository;
import com.generation.autori_e_libri.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//usa come configurazione application-database_fittizio.properties
@ActiveProfiles("database_fittizio")
public class BookServiceTest
{

    //un metodo di test è un metodo void annotato con @Test
    //il suo obiettivo è testare un singolo metodo di una classe
    @Test
    void saveNewBookGood()
    {
        //un test ha successo se il metodo viene terminato
        //fallisce se esegue un fail o una assertion sbagliata

        //1 - Caso di salvataggio corretto, il libro va bene
        //CREO INPUT METODO A MANO
        InputBookDto bustinaMinerva = new InputBookDto();
        bustinaMinerva.setTitle("La bustina di Minerva");
        bustinaMinerva.setPrice(20.00); // Prezzo stimato
        bustinaMinerva.setPages(336);
        bustinaMinerva.setYear(2020);
        bustinaMinerva.setNumberOfCopies(15);
        UUID idDiEco = aRepo.dammiIdAutore("Umberto Eco");
        bustinaMinerva.setAuthor_id(idDiEco);

        OutputBookDto risultatoMetodo = serv.save(bustinaMinerva);
        //verifico che nel db ci siano 7 libri
        long numeroLibriDb = bRepo.count();
        //VERIFICA che numero=7
        //expected -> valore atteso, voluto
        //actual -> valore ottenuto, reale, che abbiamo
        //message -> messaggio di errore nel caso siano diversi
        assertEquals(7,numeroLibriDb,"Numero di libri sbagliato");
        //controllo il dto campo per campo
        assertNotNull(risultatoMetodo.getId(),"ID NULLO");
        assertEquals("La bustina di Minerva",risultatoMetodo.getTitle(),"Titolo dto sbagliato");
        assertEquals("Umberto Eco",risultatoMetodo.getAuthorName(),"Autore dto sbagliato");
        assertEquals(2020,risultatoMetodo.getYear(),"Anno dto sbagliato");
        assertEquals(336,risultatoMetodo.getPages(),"Pagine dto sbagliato");
        assertEquals(20.00,risultatoMetodo.getPrice(),"Price dto sbagliato");
        assertTrue(risultatoMetodo.isInStock(), "In Stock dto sbagliato");
    }

    @Test
    void saveNewBookBad()
    {
        InputBookDto bustinaMinerva = new InputBookDto();
        bustinaMinerva.setTitle("La bustina di Minerva");
        bustinaMinerva.setPrice(20.00); // Prezzo stimato
        bustinaMinerva.setPages(336);
        bustinaMinerva.setYear(2020);
        bustinaMinerva.setNumberOfCopies(15);
        UUID idNonDiEco = UUID.randomUUID();//NON è ID DI ECO
        bustinaMinerva.setAuthor_id(idNonDiEco);

        //DAti non validi, metodo che da eccezioni
        try
        {
            serv.save(bustinaMinerva);
            //se arriva alla riga 81 significa che NON ha dato eccezione, che quindi ha salvato qualcosa
            fail("Ha comunque salvato il libro senza autore");
        }
        catch (Exception e)
        {
            //BUONO, ha fallito come doveva
        }

    }

    //configuro ciò che voglio testare
    @Autowired
    BookService serv;

    //decido delle operazioni da fare prima di ogni test
    //-ripulire completamente database e reinserire i dati da 0
    @Autowired
    AuthorRepository aRepo;
    @Autowired
    BookRepository bRepo;

    //lui viene automaticamente eseguito prima di ogni metodo di test
    @BeforeEach
    public void refreshDb()
    {
        bRepo.deleteAll();
        aRepo.deleteAll();
        // Crea Autori
        Author a1 = new Author();
        a1.setFullName("Umberto Eco");
        a1.setDob(LocalDate.of(1932, 1, 5));
        a1.setNationality("Italiana");

        Author a2 = new Author();
        a2.setFullName("George Orwell");
        a2.setDob(LocalDate.of(1903, 6, 25));
        a2.setNationality("Britannica");

        Author a3 = new Author();
        a3.setFullName("Jane Austen");
        a3.setDob(LocalDate.of(1775, 12, 16));
        a3.setNationality("Britannica");

        List<Author> autori = List.of(a1, a2, a3);
        aRepo.saveAll(autori);

        // Per ogni autore inserisci 2 libri

        // Libri per Umberto Eco
        Book b1 = new Book();
        b1.setTitle("Il nome della rosa");
        b1.setPrice(18.90);
        b1.setPages(500);
        b1.setYear(1980);
        b1.setNCopies(25);
        b1.setAuthor(a1);

        Book b2 = new Book();
        b2.setTitle("Il pendolo di Foucault");
        b2.setPrice(21.00);
        b2.setPages(700);
        b2.setYear(1988);
        b2.setNCopies(12);
        b2.setAuthor(a1);

        // Libri per George Orwell
        Book b3 = new Book();
        b3.setTitle("1984");
        b3.setPrice(15.50);
        b3.setPages(328);
        b3.setYear(1949);
        b3.setNCopies(40);
        b3.setAuthor(a2);

        Book b4 = new Book();
        b4.setTitle("La fattoria degli animali");
        b4.setPrice(12.99);
        b4.setPages(112);
        b4.setYear(1945);
        b4.setNCopies(35);
        b4.setAuthor(a2);

        // Libri per Jane Austen
        Book b5 = new Book();
        b5.setTitle("Orgoglio e pregiudizio");
        b5.setPrice(14.30);
        b5.setPages(432);
        b5.setYear(1813);
        b5.setNCopies(32);
        b5.setAuthor(a3);

        Book b6 = new Book();
        b6.setTitle("Emma");
        b6.setPrice(13.50);
        b6.setPages(474);
        b6.setYear(1815);
        b6.setNCopies(28);
        b6.setAuthor(a3);

        List<Book> libri = List.of(b1, b2, b3, b4, b5, b6);
        bRepo.saveAll(libri);
    }

}
