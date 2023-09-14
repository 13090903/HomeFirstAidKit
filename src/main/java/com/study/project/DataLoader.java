package com.study.project;

import com.study.project.repo.IllnessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private final IllnessRepository illnessRepository;

    @Autowired
    public DataLoader(IllnessRepository illnessRepository) {
        this.illnessRepository = illnessRepository;
    }

    public void run(ApplicationArguments args) {
//        for (int i = 0; i < 10; i++) {
//            illnessRepository.save(new Illness("Болезнь", "Какая-то болезнь"));
//        }
    }
}
