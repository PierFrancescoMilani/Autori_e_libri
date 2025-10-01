package com.generation.autori_e_libri.services;

import com.generation.autori_e_libri.model.dtos.InputAuthorDto;
import com.generation.autori_e_libri.model.dtos.OutputAuthorDto;
import com.generation.autori_e_libri.model.entities.Author;
import com.generation.autori_e_libri.model.repositories.AuthorRepository;
import org.hibernate.ObjectDeletedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthorService
{
    @Autowired
    private AuthorRepository repo;


    //lettura convertita
    public List<OutputAuthorDto> findAllAuthorsAsDtos()
    {
        List<Author> autori = repo.findAll();

//        List<OutputAuthorDto> res = new ArrayList<>();
//        for(Author a :autori)
//            res.add(convertToOutput(a));
//        return res;
        return autori.stream().map(a->convertToOutput(a)).toList();
    }

    //salvataggio convertito
    public void save(InputAuthorDto dto)
    {
        Author a = convertToEntity(dto);
        repo.save(a);
    }

    public void delete(UUID id)
    {
        Optional<Author> op = repo.findById(id);

        if(op.isEmpty())
            throw new NoSuchElementException("Autore con id "+id+" non presente");

        Author a = op.get();

        if(a.numbersOfBooksInStocks()>0)
            throw new IllegalArgumentException("Non posso cancella autore con libri in stock");

        repo.delete(a);
    }


    private OutputAuthorDto convertToOutput(Author e)
    {
        OutputAuthorDto res = new OutputAuthorDto();
        res.setId(e.getId());
        res.setDob(e.getDob());
        res.setFullName(e.getFullName());
        res.setNationality(e.getNationality());
        res.setNumberOfBooks(e.numbersOfBooksInStocks());

        return res;
    }

    private Author convertToEntity(InputAuthorDto dto)
    {
        Author res = new Author();
        res.setDob(dto.getDob());
        res.setFullName(dto.getFullName());
        res.setNationality(dto.getNationality());
        return res;
    }

}
