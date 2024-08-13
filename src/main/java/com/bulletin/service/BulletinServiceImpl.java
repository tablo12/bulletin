package com.bulletin.service;

import com.bulletin.dto.BulletinDTO;
import com.bulletin.dto.PageRequestDTO;
import com.bulletin.dto.PageResultDTO;
import com.bulletin.entity.Bulletin;
import com.bulletin.repository.BulletinRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class BulletinServiceImpl implements BulletinService{

    private final BulletinRepository repository;
    @Override
    public Long register(BulletinDTO dto) {

        log.info("DTO------------------------------");
        log.info(dto);

        Bulletin entity = dtoToEntity(dto);

        log.info(entity);

        repository.save(entity);

        return entity.getBno();

    }

    @Override
    public BulletinDTO read(Long bno) {

        Optional<Bulletin> result = repository.findById(bno);

        return result.isPresent()? entityToDto(result.get()) : null;

    }

    @Override
    public void remove(Long bno) {

        repository.deleteById(bno);

    }

    @Override
    public void modify(BulletinDTO dto) {

        Optional<Bulletin> result = repository.findById(dto.getBno());

        if(result.isPresent()) {

            Bulletin entity = result.get();

            entity.changeTitle(dto.getTitle());

            entity.changeContent(dto.getContent());

            repository.save(entity);

        }

    }

    @Override
    public PageResultDTO<BulletinDTO, Bulletin> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPagealbe(Sort.by("bno").descending());

        Page<Bulletin> result = repository.findAll(pageable);

        Function<Bulletin, BulletinDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result, fn );
    }


}
