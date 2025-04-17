package Commands;

import Forms.HumanBeingForm;
import Managers.CollectionManager;
import Managers.ConsoleManager;

/**
 * Команда "update".
 * Описание команды: обновить значение элемента коллекции, id которого равен заданному.
 */
public class Update implements CommandInterface {
    ConsoleManager console;
    CollectionManager collection;

    public Update(ConsoleManager console, CollectionManager collection) {
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
        if (args.length != 1) {
            console.printError("Команда принимает один аргумент!");
            return 1;
        }
        if (!args[0].matches("\\d+")) {
            console.printError("id должен быть числом!");
            return 2;
        }
        long id = Long.parseLong(args[0]);
        if (collection.getById(id) == null) {
            console.printError("Элемента с таким id нет в коллекции!");
            return 3;
        }
        collection.update(id, new HumanBeingForm(console).build());
        console.println("Обновлен элемент с id " + args[0]);
        return 0;
    }

    @Override
    public String toString() {
        return " <id> : обновить значение элемента коллекции, id которого равен заданному";
    }
}