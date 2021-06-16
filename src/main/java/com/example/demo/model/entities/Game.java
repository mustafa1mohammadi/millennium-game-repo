package com.example.demo.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Game {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter
    @Setter
    @Column(name = "title")
    private String title;

    @Getter
    @Column(name = "gamePoints")
    private int gamePoints;

    @Getter
    @ManyToOne
    @JoinColumn(name = "first_player_id", nullable = false)
    private Player firstPlayer;

    @Getter
    @ManyToOne
    @JoinColumn(name = "second_player_id", nullable = false)
    private Player secondPlayer;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name="winner_id", nullable = true)
    private Player winner;

    public Game(String title, int gamePoints, Player firstPlayer, Player secondPlayer) {
        this.title = title;
        this.gamePoints = gamePoints;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }
}
