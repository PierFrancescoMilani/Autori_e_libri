package com.generation.autori_e_libri.services;

import com.generation.autori_e_libri.model.dtos.InputBookDto;
import com.generation.autori_e_libri.model.dtos.OutputBookDto;
import com.generation.autori_e_libri.model.entities.Book;
import com.generation.autori_e_libri.model.repositories.AuthorRepository;
import com.generation.autori_e_libri.model.repositories.BookRepository;
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

    //TODO 8 completare corpo tutti metodi

    //lettura convertita
    public List<OutputBookDto> findAllBooksAsDtos()
    {
        List<Book> books= bRepo.findAll();

        return books.stream().map(b-> convertToOutput(b)).toList();
    }

    //scrittura convertita
    public void save(InputBookDto dto)
    {
        Book b = convertToEntity(dto);
        bRepo.save(b);
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
        res.setNCopies(dto.getNumberOfCopies());
        res.setAuthor(aRepo.findById(dto.getAuthor_id()).get());
        //TODO 9 trovare grazie a repository di autore l'autore con id
        // dto.getAuthor_id(), associarlo al libro


        return res;
    }
}
