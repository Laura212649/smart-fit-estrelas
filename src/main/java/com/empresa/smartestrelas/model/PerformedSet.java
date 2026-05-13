package com.empresa.smartestrelas.model;

import jakarta.persistence.Embeddable;

@Embeddable
public record PerformedSet(Integer reps, Double weightKg) {
} // Registro de repetições e peso real
