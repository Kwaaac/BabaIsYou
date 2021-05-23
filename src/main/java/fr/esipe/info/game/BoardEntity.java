package fr.esipe.info.game;

public interface BoardEntity extends GameObject {
    String printCommandLineEntity();

    default boolean isEntity(){
        return false;
    }
}
