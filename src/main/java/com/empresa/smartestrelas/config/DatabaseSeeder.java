package com.empresa.smartestrelas.config;


import com.empresa.smartestrelas.model.Equipamentos;
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
                Equipamentos e1 = new Equipamentos();
                e1.setNome("Barbell");
                equipRepo.save(e1);

                Equipamentos bench = new Equipamentos();
                bench.setNome("Bench");
                equipRepo.save(bench);

                Equipamentos dumbbell = new Equipamentos();
                dumbbell.setNome("Dumbbell");
                equipRepo.save(dumbbell);
            }

            if (musculosRepo.count() == 0) {
                Musculos chest = new Musculos();
                chest.setNome("Peitoral maior");
                chest.setNomeEn("Pectoralis major");
                musculosRepo.save(chest);

                Musculos deltoid = new Musculos();
                deltoid.setNome("Deltoide anterior");
                deltoid.setNomeEn("Anterior deltoid");
                musculosRepo.save(deltoid);

                Musculos triceps = new Musculos();
                triceps.setNome("Tríceps braquial");
                triceps.setNomeEn("Triceps brachii");
                musculosRepo.save(triceps);
            }

            System.out.println("Base de dados populada com sucesso!");
        };
    }
}
