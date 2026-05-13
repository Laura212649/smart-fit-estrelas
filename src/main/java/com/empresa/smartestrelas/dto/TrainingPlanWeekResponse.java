package com.empresa.smartestrelas.dto;

import java.util.List;

public record TrainingPlanWeekResponse(
        Long id,
        Integer weekNumber,
        List<TrainingPlanDayResponse> days
) {}
