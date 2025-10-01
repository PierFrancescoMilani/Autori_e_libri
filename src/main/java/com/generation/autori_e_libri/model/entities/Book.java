package com.generation.autori_e_libri.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Book extends BaseEntity
{
    private String title;
    private double price;
    private int nCopies;
    private int pages;
    private int year;

    //TODO 1 - Completare relazione con autore con annotazioni
    @ManyToOne(fetch = FetchType.EAGER)
    private Author author;

    public boolean isInStock()
    {
        return nCopies>0;
    }
}
