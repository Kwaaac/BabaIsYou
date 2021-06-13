package fr.esipe.info.memento;

import fr.esipe.info.memento.commands.MoveCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * class that reprensent the history of every Boards since the begining of a level
 */
public class History {
    private List<Pair> history;
    private int virtualSize;

    /**
     * Constructor
     */
    public History() {
        this.history = new ArrayList<>();
        this.virtualSize = 0;
    }

    /**
     * Add a new pair of memento/command into the history
     *
     * @param command The new command
     * @param memento The new memento
     */
    public void push(MoveCommand command, Memento memento) {
        if (this.virtualSize != history.size() && this.virtualSize > 0) {
            this.history = this.history.subList(0, virtualSize);
        }

        this.history.add(new Pair(command, memento));
        this.virtualSize = this.history.size();
    }

    /**
     * Restore the last state of the board with the last memento
     */
    public void undo() {
        Pair pair = this.getUndo();
        if (pair == null) {
            return;
        }
        pair.getMemento().restore();
    }

    /**
     * Get the last state of the board
     *
     * @return A Pair corresponding to the last state of the board
     */
    private Pair getUndo() {
        if (this.virtualSize == 0) {
            return null;
        }
        this.virtualSize = Math.max(0, virtualSize - 1);
        return this.history.get(virtualSize);
    }


    /**
     * Class representing a pair of command and memento allowing us to store the two datas in a single object
     */
    private class Pair {
        MoveCommand command;
        Memento memento;

        /**
         * Constructor
         *
         * @param command Command
         * @param memento Memento
         */
        Pair(MoveCommand command, Memento memento) {
            this.command = Objects.requireNonNull(command);
            this.memento = Objects.requireNonNull(memento);
        }

        /**
         * Getter of the memento
         *
         * @return The memento of the pair
         */
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
