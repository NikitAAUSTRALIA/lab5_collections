package Commands;

import Managers.CollectionManager;
import Managers.ConsoleManager;

/**
 * Команда "info".
 * Описание команды: вывести в стандартный поток вывода информацию о коллекции.
 */
public class Info implements CommandInterface {
    ConsoleManager console;
    CollectionManager collection;

    public Info(ConsoleManager console, CollectionManager collection) {
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
        console.println("Дата инициализации коллекции: " + CollectionManager.initializationTime);
        var collectionList = collection.getCollection();
        console.println("Тип коллекции: " + collectionList.getClass().getName());
        console.println("Размер коллекции: " + collectionList.size());
        return 0;
    }

    @Override
    public String toString() {
        return ": вывести в стандартный поток вывода информацию о коллекции";
    }
}