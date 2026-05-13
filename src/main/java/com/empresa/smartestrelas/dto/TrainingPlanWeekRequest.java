package com.empresa.smartestrelas.dto;

import java.util.List;

public record TrainingPlanWeekRequest(
        Integer weekNumber,
        List<TrainingPlanDayRequest> days
) {}
