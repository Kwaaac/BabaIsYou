package fr.esipe.info.game;

import fr.esipe.info.game.words.Noun;

import java.util.Objects;

public class Entity implements BoardEntity {
    private Noun noun;

    public Entity(Noun noun) {
        Objects.requireNonNull(noun);
        this.noun = noun;
    }

    public Entity(Entity target) {
        if (target != null) {
            this.noun = target.noun;
        }
    }

    public Entity clone() {
        return new Entity(this);
    }

    public Noun getNoun() {
        return noun;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Entity)) return false;
        Entity entity = (Entity) o;
        return entity.noun == noun;
    }

    @Override
    public String toString() {
        return "Entity{" +
                noun +
                '}';
    }

    @Override
    public String displayNoun() {
        return noun.displayNoun();
    }
}
