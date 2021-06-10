package fr.esipe.info.main;

import fr.esipe.info.game.enums.EnumProp;
import fr.esipe.info.game.enums.Legend;
import fr.esipe.info.game.rule.Rules;
import fr.esipe.info.manager.Chrono;
import fr.esipe.info.manager.GameManager;
import fr.esipe.info.manager.LevelManager;
import fr.umlv.zen5.Application;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        var gameRules = new Rules();
        var levels = new ArrayList<Path>();
        var chrono = new Chrono();

        if (args.length % 2 == 1) {
            throw new IllegalArgumentException("Wrong use of arguments");
        }

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--levels-name") || args[i].equals("--level-name")) {
                if (!levels.isEmpty()) {
                    throw new IllegalArgumentException("Misuse of level selection --levels-name and --level-name");
                }
                var path = Paths.get(args[++i]);
                try {
                    if (Files.isDirectory(path)) {
                        levels.addAll(Files.list(path).collect(Collectors.toList()));
                    } else if (Files.exists(path)) {
                        levels.add(path);
                    } else {
                        throw new IllegalArgumentException("File does not exist");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (args[i].equals("--execute")) {
                gameRules.add(Legend.getEntity(args[++i]), EnumProp.valueOf(args[i + 2]));
                i += 2;
            }
        }

        Application.run(new Color(17, 15, 15), context -> {
            GameManager gameManager = GameManager.getInstance();
            gameManager.setRules(gameRules);
            gameManager.setHeight((int) context.getScreenInfo().getHeight());
            gameManager.setWidth((int) context.getScreenInfo().getWidth());

            for (Path level : levels) {
                try {
                    LevelManager levelManager = new LevelManager(level);
                    gameManager.setLevelManager(levelManager);
                    context.renderFrame(graphics -> levelManager.render(graphics, false));

                    while (levelManager.processEvent(context) && !levelManager.isLose() && !levelManager.isWin() && !levelManager.isQuit() && !levelManager.isNext()) {
                        if (chrono.getTimePassed() > 500) {
                            chrono.reset();
                            context.renderFrame(graphics -> levelManager.render(graphics, true));
                        }
                    }

                    if (levelManager.isWin()) {
                        System.out.println("Bravo, c'est une victoire !");
                    } else if (levelManager.isLose()) {
                        System.out.println("Bravo, c'est une défaite ! (euh bravo ?)");
                    } else if (levelManager.isQuit()) {
                        System.out.println("Bye Bye!");
                        context.exit(0);
                    } else {
                        System.out.println("Niveau suivant");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    context.exit(1);
                    return;
                }
            }
            context.exit(0);
        });
    }
}
