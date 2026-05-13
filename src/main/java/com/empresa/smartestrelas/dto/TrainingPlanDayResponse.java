package com.empresa.smartestrelas.dto;

import com.empresa.smartestrelas.model.SplitFocus;

import java.time.DayOfWeek;
import java.util.List;

public class TrainingPlanDayResponse {
    Long id,
    DayOfWeek dayOfWeek,
    SplitFocus splitFocus,
    List<ExercicioSlotResponse> exercises
}
