package Commands;

import Managers.CollectionManager;
import Managers.ConsoleManager;

/**
 * Команда "head".
 * Описание команды: вывести первый элемент коллекции.
 */
public class Head implements CommandInterface{
    ConsoleManager console;
    CollectionManager collection;

    public Head(ConsoleManager console, CollectionManager collection){
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
        if (collection.isEmpty()){
            console.printError("Коллекция пустая!");
            return 2;
        }
        console.println(collection.head().toString());
        return 0;
    }

    @Override
    public String toString() {
        return ": вывести первый элемент коллекции";
    }
}
