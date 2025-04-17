package Commands;

import Forms.HumanBeingForm;
import Managers.CollectionManager;
import Managers.ConsoleManager;

/**
 * Команда "add_if_min".
 * Описание команды: добавить новый элемент, если его значение меньше, чем у наименьшего элемента коллекции.
 */
public class AddIfMin implements CommandInterface{
    ConsoleManager console;
    CollectionManager collection;

    public AddIfMin(ConsoleManager console, CollectionManager collection) {
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
        if (collection.addIfMin(new HumanBeingForm(console).build())){
            console.print("Элемент добавлен!");
        }
        else {
            console.print("Элемент не добавлен");
        }
        return 0;
    }

    @Override
    public String toString() {
        return ": добавить новый элемент, если он минимальный в коллекции";
    }
}
