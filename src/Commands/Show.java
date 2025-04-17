package Commands;

import Managers.CollectionManager;
import Managers.ConsoleManager;

/**
 * Команда "show".
 * Описание команды: вывести в стандартный поток вывода все элементы коллекции в строковом представлении.
 */
public class Show implements CommandInterface {
    ConsoleManager console;
    CollectionManager collection;

    public Show(ConsoleManager console, CollectionManager collection) {
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
        if (collection.isEmpty()) {
            console.println("Коллекция пуста!");
        } else {
            console.println("Элементы коллекции:");
            collection.getCollection().stream().map(Object::toString).forEach(console::println);
        }
        return 0;
    }

    @Override
    public String toString() {
        return ": вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
