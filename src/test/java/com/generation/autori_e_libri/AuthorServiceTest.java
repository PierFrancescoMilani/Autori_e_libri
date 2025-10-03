package com.generation.autori_e_libri;

import com.generation.autori_e_libri.model.dtos.InputAuthorDto;
import com.generation.autori_e_libri.model.dtos.OutputAuthorDto;
import com.generation.autori_e_libri.model.dtos.OutputBookDto;
import com.generation.autori_e_libri.model.entities.Author;
import com.generation.autori_e_libri.model.entities.Book;
import com.generation.autori_e_libri.model.repositories.AuthorRepository;
import com.generation.autori_e_libri.model.repositories.BookRepository;
import com.generation.autori_e_libri.services.AuthorService;
import org.assertj.core.api.Fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

// 1 Annotare qui come BookService
@SpringBootTest
@ActiveProfiles("database_fittizio")
public class AuthorServiceTest
{
    @Autowired
    AuthorService serv;

    @Autowired
    AuthorRepository aRepo;

    @Autowired
    BookRepository bRepo;

    // 2 Scrivere refreshDb (copiancollate quello dell'altro test, ma fate in modo di avere un autore senza libri)
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

        Author a4 = new Author();
        a4.setFullName("MYNAMEISFADE");
        a4.setDob(LocalDate.of(2018, 9, 16));
        a4.setNationality("Italiana");

        List<Author> autori = List.of(a1, a2, a3, a4);
        aRepo.saveAll(autori);

        // Per ogni autore inserisci 2 libri

        // Libri per Umberto Eco
        Book b1 = new Book();
        b1.setTitle("Il nome della rosa");
        b1.setPrice(18.90);
        b1.setPages(500);
        b1.setYear(1980);
        b1.setNumberOfCopies(25);
        b1.setAuthor(a1);

        Book b2 = new Book();
        b2.setTitle("Il pendolo di Foucault");
        b2.setPrice(21.00);
        b2.setPages(700);
        b2.setYear(1988);
        b2.setNumberOfCopies(12);
        b2.setAuthor(a1);

        // Libri per George Orwell
        Book b3 = new Book();
        b3.setTitle("1984");
        b3.setPrice(15.50);
        b3.setPages(328);
        b3.setYear(1949);
        b3.setNumberOfCopies(40);
        b3.setAuthor(a2);

        Book b4 = new Book();
        b4.setTitle("La fattoria degli animali");
        b4.setPrice(12.99);
        b4.setPages(112);
        b4.setYear(1945);
        b4.setNumberOfCopies(35);
        b4.setAuthor(a2);

        // Libri per Jane Austen
        Book b5 = new Book();
        b5.setTitle("Orgoglio e pregiudizio");
        b5.setPrice(14.30);
        b5.setPages(432);
        b5.setYear(1813);
        b5.setNumberOfCopies(32);
        b5.setAuthor(a3);

        Book b6 = new Book();
        b6.setTitle("Emma");
        b6.setPrice(13.50);
        b6.setPages(474);
        b6.setYear(1815);
        b6.setNumberOfCopies(28);
        b6.setAuthor(a3);

        List<Book> libri = List.of(b1, b2, b3, b4, b5, b6);
        bRepo.saveAll(libri);
    }


    //TODO 3 - Scrivere metodi
    @Test
    void testFindALlAuthors()
    {
        //testare che la lista di dto letta contenga 4 autori
        List<OutputAuthorDto> auto = serv.findAllAuthorsAsDtos();

        assertEquals(4,auto.size(),"Numero di autori sbagliato");

    }

    @Test
    void testSaveAuthorsGood()
    {
        //modificare il metodo save per fare restituire un dto output
        //verificare che il metodo si comporti bene
        //uguale a quello dei libri

        InputAuthorDto Dag = new InputAuthorDto();
        Dag.setFullName("DagrarioIta");
        Dag.setDob(LocalDate.of(1995,3,13));
        Dag.setNationality("Pugliese");

        serv.save(Dag);




    }

    @Test
    void testSaveAuthorsBad()
    {
        //provare a salvare autore con data di nascita
        //nel futuro, deve dare eccezione
        InputAuthorDto Dag = new InputAuthorDto();
        Dag.setFullName("FigliodiDagrarioIta");
        Dag.setDob(LocalDate.of(2035,9,19));
        Dag.setNationality("Pugliese/Tettesco");

        try {
            serv.save(Dag);
            fail("ha comunque salvato autore");
        }
        catch (Exception e) {}
    }

    @Test
    void testDeleteGood() {
        //leggere autore senza libri
        //cancellarlo
        //controllare che ci siano solo 3 autori nel db
        Optional<Author> auto = aRepo.findById(aRepo.dammiIdAutore("MYNAMEISFADE"));
        Author a = auto.get();
        aRepo.delete(a);

        assertEquals(3, aRepo.findAll().size(), "Numero di autori sbagliato");
    }

    @Test
    void testDeleteBad()
    {
        //leggere autore con libri
        //cancellarlo
        //controllare che dia eccezione

        UUID auto = aRepo.dammiIdAutore("Jane Austen");

        try {
            serv.delete(auto);
            fail("AO lo sta cancellando lo stesso");
        }
        catch (Exception e) {}

    }
}
