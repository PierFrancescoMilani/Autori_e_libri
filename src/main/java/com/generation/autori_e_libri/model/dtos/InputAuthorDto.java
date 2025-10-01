package com.generation.autori_e_libri.model.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class InputAuthorDto
{
    private String fullName;
    private LocalDate dob;
    private String nationality;
}
