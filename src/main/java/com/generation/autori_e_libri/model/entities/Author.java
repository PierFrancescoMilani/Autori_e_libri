package com.generation.autori_e_libri.model.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import java.util.HashSet;

import java.util.Set;


@Entity
@Getter
@Setter
public class Author extends BaseEntity
{
    @NotBlank@NotNull
    private String fullName;
    @Past
    private LocalDate dob;
    @NotBlank@NotNull
    private String nationality;





    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Book> books = new HashSet<>();


    public int numbersOfBooks()
    {
        return books.size();
    }

    public int numbersOfBooksInStocks()
    {
        int somma=0;
        for (Book b : books)
            if (b.isInStock())
                somma++;

        return somma;
    }

    public void addBook(Book b)
    {
        books.add(b);
        b.setAuthor(this);
    }

}
