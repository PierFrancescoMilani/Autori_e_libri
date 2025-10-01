package com.generation.autori_e_libri.controllers;

import com.generation.autori_e_libri.model.dtos.InputAuthorDto;
import com.generation.autori_e_libri.model.dtos.OutputAuthorDto;
import com.generation.autori_e_libri.services.AuthorService;
import com.generation.autori_e_libri.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/authors")
public class AuthorController
{
    @Autowired
    AuthorService serv;

    @GetMapping
    public List<OutputAuthorDto> getAll()
    {
        return serv.findAllAuthorsAsDtos();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id)
    {
        serv.delete(id);
    }

    @PostMapping
    public void insert(@RequestBody InputAuthorDto dto)
    {
        serv.save(dto);
    }

}
