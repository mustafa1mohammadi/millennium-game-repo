package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class GameStartDto {

    @Getter
    @Setter
    private String gameTitle;

    @Getter
    @Setter
    private int gamePoints;

    @Getter
    @Setter
    private Integer firstPlayer;

    @Getter
    @Setter
    private Integer secondPlayer;
}
