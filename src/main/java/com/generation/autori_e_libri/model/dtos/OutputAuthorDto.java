package com.generation.autori_e_libri.model.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class OutputAuthorDto
{
    private UUID id;
    private String fullName;
    private int numberOfBooks;
    private LocalDate dob;
    private String nationality;
}
