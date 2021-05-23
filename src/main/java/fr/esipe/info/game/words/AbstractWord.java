package fr.esipe.info.game.words;

import fr.esipe.info.VectorCoord;
import fr.esipe.info.game.enums.ColorPrint;
import fr.esipe.info.game.states.PushState;
import fr.esipe.info.game.states.State;

public abstract class AbstractWord implements Word {
    private final String word;
    private State state;
    private VectorCoord coord;

    public AbstractWord(String word) {
        this.word = word;
        this.state = new PushState();
        this.coord = new VectorCoord(0,0);
    }

    @Override
    public String toString() {
        return word;
    }

    String textColor() {
        return "";
    }

    @Override
    public boolean isWord(){
        return true;
    }

    @Override
    public String printCommandLineEntity() {
        var res = word;


        res = textColor() + res;

        if (word.length() == 2) {
            res = " " + res + " ";
        } else if (word.length() == 3) {
            res += " ";
        }

        return res + ColorPrint.ANSI_RESET.getAsciiCode();
    }

    @Override
    public void changeState(State state){
        System.out.println("Peux pas !");
    }

    @Override
    public State getState(){
        return this.state;
    }

    public String getWord(){
        return this.word;
    }
}
