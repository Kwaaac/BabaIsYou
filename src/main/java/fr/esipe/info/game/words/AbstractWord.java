package fr.esipe.info.game.words;

public abstract class AbstractWord implements Word {
    private final String word;

    public AbstractWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return "Word{" +
                word +
                '}';
    }

    @Override
    public String displayNoun() {
        if (word.length() == 2) {
            return " " + word + " ";
        }
        else if (word.length() == 3) {
            return  word + " ";
        }
        return word;
    }
}
