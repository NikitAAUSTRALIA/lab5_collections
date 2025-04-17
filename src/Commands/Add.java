package Commands;

import Entities.HumanBeing;
import Forms.HumanBeingForm;
import Managers.CollectionManager;
import Managers.ConsoleManager;


/**
 * Команда "Add".
 * Описание команды: добавить новый элемент в коллекцию.
 */
public class Add implements CommandInterface{
    ConsoleManager console;
    CollectionManager collection;

    public Add(ConsoleManager console, CollectionManager collection) {
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
        try {
            collection.add(new HumanBeingForm(console).build());
            console.println("Элемент успешно добавлен!");
            return 0;
        }
        catch (Exception ex){
            console.printError(ex.toString());
            return 2;
        }
    }

    @Override
    public String toString() {
        return ": добавить новый элемент в коллекцию";
    }
}
