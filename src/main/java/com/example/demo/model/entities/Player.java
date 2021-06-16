package com.example.demo.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Player{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Getter
    @Setter
    private Integer id;

    @Setter
    @Getter
    @Column(name = "name")
    private String name;

    @Setter
    @Getter
    @Column(name = "totalPoints")
    private long totalPoints;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fk_game")
    private Game game;

    public Player(String name, long totalPoints) {
        this.name = name;
        this.totalPoints = totalPoints;
    }

    public Player(Integer id, String name, long totalPoints) {
        this.id = id;
        this.name = name;
        this.totalPoints = totalPoints;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", totalPoints=" + totalPoints +
                '}';
    }
}