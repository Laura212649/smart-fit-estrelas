package com.empresa.smartestrelas.config;


import com.empresa.smartestrelas.model.Equipamento;
import com.empresa.smartestrelas.model.Musculos;
import com.empresa.smartestrelas.repository.EquipamentoRepository;
import com.empresa.smartestrelas.repository.MusculosRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseSeeder {
    @Bean
    CommandLineRunner initDatabase(EquipamentoRepository equipRepo, MusculosRepository musculosRepo) {
        return args -> {
            if (equipRepo.count() == 0) {
                Equipamento e1 = new Equipamento();
                e1.setNome("Barbell");
                equipRepo.save(e1);

                Equipamento bench = new Equipamento();
                bench.setNome("Bench");
                equipRepo.save(bench);

                Equipamento dumbbell = new Equipamento();
                dumbbell.setNome("Dumbbell");
                equipRepo.save(dumbbell);
            }

            if (musculosRepo.count() == 0) {
                Musculos chest = new Musculos();
                chest.setNome("Peitoral maior");
                chest.setNomeEn("Pectoralis major");
                musculosRepo.save(chest);
            }

            System.out.println("Base de dados populada com sucesso!");
        };
    }
}
