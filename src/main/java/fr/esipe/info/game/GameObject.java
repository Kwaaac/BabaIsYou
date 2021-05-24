package fr.esipe.info.game;

import fr.esipe.info.VectorCoord;
import fr.esipe.info.manager.GameManager;

public interface GameObject {
    VectorCoord getPos();

    void setPos(VectorCoord pos);
}
