package com.bulletin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BulletinDTO {

    private Long bno;

    private String title;

    private String content;

    private String writer;

    private LocalDateTime regDate, modDate;

}
