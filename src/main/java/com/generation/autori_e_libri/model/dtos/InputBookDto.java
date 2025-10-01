package com.generation.autori_e_libri.model.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class InputBookDto
{
    private String title;
    private int year;
    private int pages;
    private double price;
    private int numberOfCopies;
    private UUID author_id;
}
