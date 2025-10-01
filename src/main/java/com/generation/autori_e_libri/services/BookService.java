package com.generation.autori_e_libri.services;

import com.generation.autori_e_libri.model.dtos.InputAuthorDto;
import com.generation.autori_e_libri.model.dtos.InputBookDto;
import com.generation.autori_e_libri.model.dtos.OutputAuthorDto;
import com.generation.autori_e_libri.model.dtos.OutputBookDto;
import com.generation.autori_e_libri.model.entities.Author;
import com.generation.autori_e_libri.model.entities.Book;

import java.util.List;
import java.util.UUID;

//TODO 6 annotare
public class BookService
{
    //TODO 7 autowirare tutte e 2 le repository

    //TODO 8 completare corpo tutti metodi

    //lettura convertita
    public List<OutputBookDto> findAllBooksAsDtos()
    {
        return null;
    }

    //scrittura convertita
    public void save(InputBookDto dto)
    {
    }

    public void delete(UUID id)
    {
    }

    private OutputBookDto convertToOutput(Book e)
    {
        return null;
    }

    private Book convertToEntity(InputBookDto dto)
    {
        //TODO 9 trovare grazie a repository di autore l'autore con id
        // dto.getAuthor_id(), associarlo al libro


        return null;
    }
}
