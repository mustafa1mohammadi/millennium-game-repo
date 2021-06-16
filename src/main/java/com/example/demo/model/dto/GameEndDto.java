package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor

public class GameEndDto {

    @Getter
    @Setter
    private Integer gameId;

    @Getter
    @Setter
    private Integer winnerId;

}
