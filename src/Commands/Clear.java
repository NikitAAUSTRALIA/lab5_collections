package Commands;

import Managers.CollectionManager;
import Managers.ConsoleManager;

/**
 * Команда "clear".
 * Описание команды: очистить коллекцию.
 */
public class Clear implements CommandInterface{
    ConsoleManager console;
    CollectionManager collection;

    public Clear(ConsoleManager console, CollectionManager collection) {
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

        console.println("Коллекция очищена");
        collection.clear();

        return 0;
    }

    @Override
    public String toString(){
        return ": очистить коллекцию";
    }
}
