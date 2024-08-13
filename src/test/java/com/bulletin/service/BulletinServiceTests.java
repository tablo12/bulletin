package com.bulletin.service;

import com.bulletin.dto.BulletinDTO;
import com.bulletin.dto.PageRequestDTO;
import com.bulletin.dto.PageResultDTO;
import com.bulletin.entity.Bulletin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BulletinServiceTests {

    @Autowired
    private BulletinService service;

    @Test
    public void testRegister() {

        BulletinDTO bulletinDTO = BulletinDTO.builder()
                .title("Sample Title...")
                .content("Sample Contetn...")
                .writer("user0")
                .build();

        System.out.println(service.register(bulletinDTO));

    }

    @Test
    public void testList() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();

        PageResultDTO<BulletinDTO, Bulletin> resultDTO = service.getList(pageRequestDTO);

        System.out.println("PREV: " + resultDTO.isPrev());
        System.out.println("NEXT: " + resultDTO.isNext());
        System.out.println("TOTAL: " + resultDTO.getTotalPage());
        System.out.println("------------------------------------");

        for(BulletinDTO bulletinDTO : resultDTO.getDtoList()) {

            System.out.println(bulletinDTO);

        }

        System.out.println("======================================");
        resultDTO.getPageList().forEach(i -> System.out.println(i));

    }



}
