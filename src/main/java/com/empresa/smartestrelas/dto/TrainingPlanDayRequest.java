package com.empresa.smartestrelas.dto;

import com.empresa.smartestrelas.model.ExercicioSlot;
import com.empresa.smartestrelas.model.SplitFocus;

import java.time.DayOfWeek;
import java.util.List;

public record TrainingPlanDayRequest(
        DayOfWeek dayOfWeek, // MONDAY a SUNDAY [cite: 164]
        SplitFocus splitFocus, // ex: UPPER_PUSH, LOWER_PULL [cite: 165]
        List<ExercicioSlot> exercicios
) {}
