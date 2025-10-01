package com.generation.autori_e_libri.model.repositories;

import com.generation.autori_e_libri.model.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
}
