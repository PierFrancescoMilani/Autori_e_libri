package com.generation.autori_e_libri.model.repositories;

import com.generation.autori_e_libri.model.entities.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID>
{
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM book WHERE id = :id", nativeQuery = true)
    void cancellaPerFavore(UUID id);
}
