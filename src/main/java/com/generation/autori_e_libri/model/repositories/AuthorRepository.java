package com.generation.autori_e_libri.model.repositories;

import com.generation.autori_e_libri.model.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

    @Query("select a.id FROM Author a WHERE a.fullName=:nome")
    UUID dammiIdAutore(String nome);

}
