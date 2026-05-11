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
                Equipamento barbell = new Equipamento();
                barbell.setName("Barbell");
                equipRepo.save(barbell);

                Equipamento bench = new Equipamento();
                barbell.setName("Bench");
                equipRepo.save(bench);
            }

            if (musculosRepo.count() == 0) {
                Musculos chest = new Musculos();
                chest.setName("Peitoral maior");
                chest.setNameEn("Pectoralis major");
                musculosRepo.save(chest);
            }

            System.out.println("Base de dados populada com sucesso!");
        };
    }
}
