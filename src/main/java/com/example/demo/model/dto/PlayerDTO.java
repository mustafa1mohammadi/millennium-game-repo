package com.example.demo.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class PlayerDTO {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private long totalPoints;

    public PlayerDTO(String name, long totalPoints) {
        this.name = name;
        this.totalPoints = totalPoints;
    }

}
