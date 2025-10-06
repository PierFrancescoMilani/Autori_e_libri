package com.generation.autori_e_libri.services;

import com.generation.autori_e_libri.model.dtos.InputAuthorDto;
import com.generation.autori_e_libri.model.dtos.InputBookDto;
import com.generation.autori_e_libri.model.dtos.OutputAuthorDto;
import com.generation.autori_e_libri.model.dtos.OutputBookDto;
import com.generation.autori_e_libri.model.entities.Author;
import com.generation.autori_e_libri.model.entities.Book;
import com.generation.autori_e_libri.model.repositories.AuthorRepository;
import com.generation.autori_e_libri.model.repositories.BookRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;


@Service
public class BookService
{

     @Autowired
     private BookRepository bRepo;
     @Autowired
     private AuthorRepository aRepo;

    //lettura convertita


    public List<OutputBookDto> findAllBooksAsDtos()
    {
        List<Book> books= bRepo.findAll();

        return books.stream().map(b-> convertToOutput(b)).toList();
    }

    //scrittura convertita

    /**
     * This method receives a <b style="color:red">InputDto</b>  <br/>
     * <img height=100 src="https://www.topolino.it/wp-content/uploads/2019/11/Paperino_360_ok.png">
     * save the book created converting that input in an Entity,
     * then convert the entity in an OutputDto to return
     * @param dto A dto obtained in the controller converting a JSON body request
     * @return OutputDto of the saved book
     * @throws ConstraintViolationException If the fields of the dto are not compatible with the validation described in the entity
     * @throws NoSuchElementException If the id of the author isn't present in the database
     */
    public OutputBookDto save(InputBookDto dto) throws ConstraintViolationException,NoSuchElementException
    {
        Book b = convertToEntity(dto);
        b=bRepo.save(b);//save the book in the db and refresh b

        return convertToOutput(b);
    }

    public void delete(UUID id)
    {
        Optional<Book> op= bRepo.findById(id);

        if(op.isEmpty())
            throw new NoSuchElementException("Book con "+id+"non presente");

        Book b = op.get();

        bRepo.cancellaPerFavore(b.getId());
    }

    private OutputBookDto convertToOutput(Book e)
    {
        OutputBookDto res = new OutputBookDto();
        res.setId(e.getId());
        res.setTitle(e.getTitle());
        res.setAuthorName(e.getAuthor().getFullName());
        res.setYear(e.getYear());
        res.setPages(e.getPages());
        res.setPrice(e.getPrice());
        res.setInStock(e.isInStock());

        return res;
    }

    private Book convertToEntity(InputBookDto dto)
    {
        Book res = new Book();
        res.setTitle(dto.getTitle());
        res.setYear(dto.getYear());
        res.setPages(dto.getPages());
        res.setPrice(dto.getPrice());
        res.setNumberOfCopies(dto.getNumberOfCopies());
        res.setAuthor(aRepo.findById(dto.getAuthor_id()).get());
        //9 trovare grazie a repository di autore l'autore con id
        // dto.getAuthor_id(), associarlo al libro


        return res;
    }
}
