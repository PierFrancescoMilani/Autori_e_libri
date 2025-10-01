package com.generation.autori_e_libri.model.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Author extends BaseEntity
{

    private String fullName;
    private LocalDate dob;
    private String nationality;

    //la cascade deve essere ALL
    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Book> books = new HashSet<>();

    public int numbersOfBooks()
    {
        return books.size();
    }

    public int numbersOfBooksInStocks()
    {
        int sommaStock = 0;
        for(Book b : books)
            if(b.isInStock())
                sommaStock++;
        return sommaStock;
    }

    public void addBook(Book b)
    {
        books.add(b);
        b.setAuthor(this);
    }

}
