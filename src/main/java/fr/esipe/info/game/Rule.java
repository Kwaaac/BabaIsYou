package fr.esipe.info.game;

import fr.esipe.info.game.words.Noun;
import fr.esipe.info.game.words.Operator;
import fr.esipe.info.game.words.Property;

import java.util.Objects;

public class Rule {
    private Noun target;
    private Operator operator;
    private Property property;

    public Rule(Noun target, Operator operator, Property property){
        Objects.requireNonNull(target);
        Objects.requireNonNull(operator);
        Objects.requireNonNull(property);
        this.target = target;
        this.operator = operator;
        this.property = property;
    }

    public Noun getTarget() {
        return target;
    }

    public void setTarget(Noun target) {
        this.target = target;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    @Override
    public String toString() {
        return this.target + " " + this.operator + " " + this.property;
    }
}
