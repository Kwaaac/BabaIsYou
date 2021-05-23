package fr.esipe.info.game;

import fr.esipe.info.VectorCoord;
import fr.esipe.info.game.enums.EnumOp;
import fr.esipe.info.game.enums.EnumProp;
import fr.esipe.info.game.words.Noun;

import java.util.Objects;

public class Entity extends GameObject implements BoardEntity {
    private Noun noun;

    public Entity(Noun noun, VectorCoord vc) {
        super(Objects.requireNonNull(vc));
        this.noun = Objects.requireNonNull(noun);
    }

    public Entity(Entity target) {
        this(target.noun, target.getPos());
    }

    public Entity(Noun word) {
        this(word, VectorCoord.vectorOutOfTheLoop());
    }

    public Entity clone() {
        return new Entity(this);
    }

    public Noun getNoun() {
        return noun;
    }

    public void changeNoun(Noun noun) {
        this.noun = noun;
    }

    @Override
    public boolean usesProperties(EnumOp op, EnumProp prop) {
        return noun.hasProperty(op, prop);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Entity)) return false;
        Entity entity = (Entity) o;
        return entity.noun == noun;
    }

    @Override
    public int hashCode() {
        return Objects.hash(noun);
    }

    @Override
    public String toString() {
        return "Entity{" +
                noun +
                '}';
    }

    @Override
    public String printCommandLineEntity() {
        return noun.toString();
    }
}
