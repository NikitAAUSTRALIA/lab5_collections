package Commands;

import Managers.CollectionManager;
import Managers.ConsoleManager;

/**
 * Команда "min_by_impact_speed".
 * Описание команды: вывести любой объект из коллекции, значение поля ImpactSpeed которого является минимальным.
 */
public class MinByImpactSpeed implements CommandInterface {
    ConsoleManager console;
    CollectionManager collection;

    public MinByImpactSpeed(ConsoleManager console, CollectionManager collection) {
        this.console = console;
        this.collection = collection;
    }

    /**
     * Выполнение команды.
     *
     * @param args аргументы
     */
    @Override
    public int execute(String[] args) {
        if (args.length != 0) {
            console.printError("Команда не принимает аргументы!");
            return 1;
        }
        console.println("Элемент с минимальным значением поля ImpactSpeed:");
        console.println(collection.minByImpactSpeed().toString());
        return 0;
    }

    @Override
    public String toString() {
        return ": вывести любой объект из коллекции, значение поля fullName которого является минимальным";
    }
}