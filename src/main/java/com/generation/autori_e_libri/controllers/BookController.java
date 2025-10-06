package com.generation.autori_e_libri.controllers;

import com.generation.autori_e_libri.model.dtos.InputAuthorDto;
import com.generation.autori_e_libri.model.dtos.InputBookDto;
import com.generation.autori_e_libri.model.dtos.OutputAuthorDto;
import com.generation.autori_e_libri.model.dtos.OutputBookDto;
import com.generation.autori_e_libri.services.AuthorService;
import com.generation.autori_e_libri.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/api/books")
public class BookController
{

    @Autowired
    BookService serv;

    @GetMapping
    public List<OutputBookDto> getAll()
    {
        String s = "ciao".toLowerCase();
        return serv.findAllBooksAsDtos();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id)
    {
        serv.delete(id);
    }

    @PostMapping
    public OutputBookDto insert(@RequestBody InputBookDto dto)
    {
        return serv.save(dto);
    }
}
