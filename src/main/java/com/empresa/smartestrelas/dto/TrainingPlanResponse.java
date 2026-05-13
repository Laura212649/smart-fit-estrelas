package com.empresa.smartestrelas.dto;

import com.empresa.smartestrelas.model.Goal;

public record TrainingPlanResponse(
        Long id,
        String name,
        Goal goal,
        Integer weekCount,
        List<TrainingPlanWeekResponse> weeks
) {}
