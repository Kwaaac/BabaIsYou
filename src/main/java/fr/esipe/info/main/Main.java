package fr.esipe.info.main;

import fr.esipe.info.factories.NounFactory;
import fr.esipe.info.factories.OperatorFactory;
import fr.esipe.info.factories.PropertyFactory;
import fr.esipe.info.factories.WordFactory;
import fr.esipe.info.game.enums.EnumOp;
import fr.esipe.info.game.words.Operator;
import fr.esipe.info.game.words.Word;
import fr.esipe.info.manager.LevelManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        LevelManager level = new LevelManager("bob", 2, 2);
        WordFactory factory;
        Scanner scanner = new Scanner(System.in);
        Word result = new Operator(EnumOp.IS);
        var choice = scanner.nextLine();
        switch (choice.charAt(0)){
            case 'n':
                factory = new NounFactory();
                result = factory.createWord("baba");
                break;
            case 'o':
                factory = new OperatorFactory();
                result = factory.createWord("is");
                break;
            case 'p':
                factory = new PropertyFactory();
                result = factory.createWord("sink");
                break;
            default:
                break;
        }

        System.out.println(result);
    }
}
