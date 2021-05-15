package fr.esipe.info.game.words;

public abstract class AbstractWord implements Word {
    private final String word;

    public AbstractWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return "|\t " + word + "\t |";
    }
}
