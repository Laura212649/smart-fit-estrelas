package com.empresa.smartestrelas.dto;

import java.time.LocalDate;

public record ProgressTrackingResponse(
        LocalDate data,
        Double weightKg
) {}
