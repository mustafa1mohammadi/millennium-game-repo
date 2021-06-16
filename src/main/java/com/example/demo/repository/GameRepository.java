package com.example.demo.repository;

import com.example.demo.model.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GameRepository extends JpaRepository<Game, Integer> {

}
