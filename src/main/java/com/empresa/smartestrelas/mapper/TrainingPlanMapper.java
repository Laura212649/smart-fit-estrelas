package com.empresa.smartestrelas.mapper;


import com.empresa.smartestrelas.dto.*;
import com.empresa.smartestrelas.model.*;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring") // Permite injetar o Mapper com @Autowired
public interface TrainingPlanMapper {

    // 1. Mapeamento do Plano Principal
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", ignore = true) // Definido manualmente via Service
    @Mapping(target = "weeks", source = "weeks")
    TrainingPlan toEntity(TrainingPlanRequest request);

    // 2. Mapeamento da Semana
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "trainingPlan", ignore = true) // Configurado no AfterMapping
    @Mapping(target = "dias", source = "days")
    TrainingPlanWeek toEntity(TrainingPlanWeekRequest request);

    // 3. Mapeamento do Dia
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "semana", ignore = true) // Configurado no AfterMapping
    @Mapping(target = "exercicios", source = "exercicios")
    TrainingPlanDay toEntity(TrainingPlanDayRequest request);

    // 4. Mapeamento do Slot de Exercício
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dia", ignore = true) // Configurado no AfterMapping
    @Mapping(target = "exercicio", ignore = true) // Buscado no banco pelo Service antes de salvar
    ExercicioSlot toEntity(ExercicioSlot slotDto);

    @AfterMapping
    default void vincularRelacionamentos(TrainingPlanRequest request, @MappingTarget TrainingPlan plano) {
        if (plano.getWeeks() != null) {
            plano.getWeeks().forEach(week -> {
                week.setTrainingPlan(plano);
                if (week.getDias() != null) {
                    week.getDias().forEach(day -> {
                        day.setSemana(week);
                        if (day.getExercicios() != null) {
                            day.getExercicios().forEach(slot -> slot.setDia(day));
                        }
                    });
                }
            });
        }
    }
}
