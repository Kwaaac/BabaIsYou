package fr.esipe.info.game.words;

import fr.esipe.info.VectorCoord;
import fr.esipe.info.game.AbstractGameObject;

import java.util.Objects;

public abstract class AbstractWord extends AbstractGameObject implements Word {
    private final String word;

    public AbstractWord(String word, VectorCoord vc) {
        super(vc);
        this.word = word;
    }

    @Override
    public String toString() {
        return word;
    }

    String textColor() {
        return "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AbstractWord that = (AbstractWord) o;
        return Objects.equals(word, that.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), word);
    }
}
