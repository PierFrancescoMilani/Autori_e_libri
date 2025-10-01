package com.generation.autori_e_libri.model.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OutputBookDto
{
    private UUID id;
    private String title;
    private String authorName;
    private int year;
    private int pages;
    private double price;
    private boolean inStock;
}
