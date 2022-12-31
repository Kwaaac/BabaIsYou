package fr.esipe.info.game.factories;

import fr.esipe.info.game.enums.EnumProp;
import fr.esipe.info.game.states.*;

import java.util.HashMap;
import java.util.function.Supplier;

public class StateRegistry {

    //  states
    private static final DefeatState defeatState = new DefeatState();
    private static final FireState fireState = new FireState();
    private static final HotState hotState = new HotState();
    private static final MeltState meltState = new MeltState();
    private static final NormalState normalState = new NormalState();
    private static final PushState pushState = new PushState();
    private static final SinkState sinkState = new SinkState();
    private static final StopState stopState = new StopState();
    private static final WinState winState = new WinState();
    private static final YouState youState = new YouState();
    private static final HashMap<EnumProp, Supplier<? extends State>> map;
    static {
        map = new HashMap<>();
        register(EnumProp.FIRE, () -> fireState);
        register(EnumProp.DEFEAT, () -> defeatState);
        register(EnumProp.PUSH, () -> pushState);
        register(EnumProp.MELT, () -> meltState);
        register(EnumProp.HOT, () -> hotState);
        register(EnumProp.YOU, () -> youState);
        register(EnumProp.WIN, () -> winState);
        register(EnumProp.SINK, () -> sinkState);
        register(EnumProp.STOP, () -> stopState);
        register(EnumProp.NONE, () -> normalState);
    }

    private static void register(EnumProp enumProp, Supplier<? extends State> supplier) {
        map.put(enumProp, supplier);
    }

    public static State create(EnumProp enumProp) {
        return map.computeIfAbsent(enumProp, l -> {
                    throw new IllegalArgumentException("Unknown " + enumProp);
                })
                .get();
    }
}
