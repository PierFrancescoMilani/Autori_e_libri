package com.generation.autori_e_libri.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Book extends BaseEntity
{
    @NotNull @NotBlank
    private String title;
    private double price;
    private int numberOfCopies;
    private int pages;
    private int year;

    // 1 - Completare relazione con autore con annotazioni
    @ManyToOne(fetch = FetchType.EAGER)
    private Author author;

    public boolean isInStock()
    {
        return numberOfCopies >0;
    }
}
