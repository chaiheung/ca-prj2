package com.caprj2.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Member {
    private Integer id;
    private String email;
    private String password;
    private String nickname;
    private LocalDateTime inserted;
}
