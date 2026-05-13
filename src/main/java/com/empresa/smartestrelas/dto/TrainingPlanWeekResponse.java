package com.empresa.smartestrelas.dto;

public record TrainingPlanWeekResponse(
        Long id,
        Integer weekNumber,
        List<TrainingPlanDayResponse> days
) {}
