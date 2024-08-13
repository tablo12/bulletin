package com.bulletin.service;

import com.bulletin.dto.BulletinDTO;
import com.bulletin.dto.PageRequestDTO;
import com.bulletin.dto.PageResultDTO;
import com.bulletin.entity.Bulletin;

public interface BulletinService {

    Long register(BulletinDTO dto);

    BulletinDTO read(Long bno);

    void remove(Long bno);

    void modify(BulletinDTO dto);

    PageResultDTO<BulletinDTO, Bulletin> getList(PageRequestDTO requestDTO);

    default Bulletin dtoToEntity(BulletinDTO dto) {

        Bulletin entity = Bulletin.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();

        return entity;
    }

    default BulletinDTO entityToDto(Bulletin entity) {

        BulletinDTO dto = BulletinDTO.builder()
                .bno(entity.getBno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();

        return dto;

    }

}
