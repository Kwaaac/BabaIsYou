package fr.esipe.info.memento;

import fr.esipe.info.memento.commands.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class History {
    private List<Pair> history;
    private int virtualSize;

    public History(){
        this.history = new ArrayList<>();
        this.virtualSize = 0;
    }

    public void push(Command command, Memento memento){
        if(this.virtualSize != history.size() && this.virtualSize > 0){
            this.history = this.history.subList(0, virtualSize);
        }
        this.history.add(new Pair(command, memento));
        this.virtualSize = this.history.size();
    }

    public boolean undo(){
        Pair pair = this.getUndo();
        if(pair == null){
            return false;
        }
        pair.getMemento().restore();
        return true;
    }

    private Pair getUndo(){
        if(this.virtualSize == 0){
            return null;
        }
        this.virtualSize = Math.max(0, virtualSize - 1);
        return this.history.get(virtualSize);
    }

    private class Pair{
        Command command;
        Memento memento;
        Pair(Command command, Memento memento){
            this.command = Objects.requireNonNull(command);
            this.memento = Objects.requireNonNull(memento);
        }

        private Command getCommand() {
            return this.command;
        }

        private Memento getMemento() {
            return this.memento;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "command=" + command +
                    ", memento=" + memento +
                    '}';
        }
    }
}
