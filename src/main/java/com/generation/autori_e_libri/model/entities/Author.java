package com.generation.autori_e_libri.model.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Author extends BaseEntity
{
    @NotNull  @Past
    private LocalDate dob;
    @NotNull @NotBlank
    private String name, surname;
    @NotNull @NotBlank
    private String nationality;

    //la cascade deve essere ALL
    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Book> books = new HashSet<>();

    public void addBook(Book b) {
        books.add(b);
        b.setAuthor(this);
    }

    public String nameAndSurname() {
        return name+" "+surname;
    }
    public int numbersOfBooks()
    {
        return books.size();
    }

    public int numbersOfBooksInStocks()
    {
        return (int) books.stream().filter(b -> b.isInStock()).count();
    }



}
