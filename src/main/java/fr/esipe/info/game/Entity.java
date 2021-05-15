package fr.esipe.info.game;

import fr.esipe.info.game.words.Noun;

import java.util.Objects;

public class Entity implements BoardEntity{
    private Noun noun;

    public Entity(Noun noun){
        Objects.requireNonNull(noun);
        this.noun = noun;
    }
}
