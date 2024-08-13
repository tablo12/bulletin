package com.bulletin.repository;

import com.bulletin.entity.Bulletin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class BulletinRepositoryTests {

    @Autowired
    private BulletinRepository bulletinRepository;

    @Test
    public void insertDummies(){

        IntStream.rangeClosed(1, 300).forEach(i -> {

            Bulletin bulletin = Bulletin.builder()
                    .title("Title...." + i)
                    .content("Content...." + i)
                    .writer("User" + (i % 10))
                    .build();

            System.out.println(bulletinRepository.save(bulletin));
        });
    }

    @Test
    public void updateTest() {

        Optional<Bulletin> result = bulletinRepository.findById(300L);

        if(result.isPresent()){

            Bulletin bulletin = result.get();

            bulletin.changeTitle("Changed Title....");
            bulletin.changeContent("Changed Content....");

            bulletinRepository.save(bulletin);

        }
    }

    @Test
    public void testQuery1() {



    }
}
