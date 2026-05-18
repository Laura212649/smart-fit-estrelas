package com.empresa.smartestrelas.dto;



import java.time.LocalDate;


public record PersonalRecordResponse (

        Long id,
        String titulo,
        Double maxWeight,
        Long reps,
        LocalDate data

){}
