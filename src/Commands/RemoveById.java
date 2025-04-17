package Commands;

import Managers.CollectionManager;
import Managers.ConsoleManager;

/**
 * Команда "remove_by_id".
 * Описание команды: удалить элемент коллекции с указанным id.
 */
public class RemoveById implements CommandInterface{
    ConsoleManager console;
    CollectionManager collection;

    public RemoveById(ConsoleManager console, CollectionManager collection){
        this.collection = collection;
        this.console = console;
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
        try {
            long id = Long.parseLong(args[0]);
            if (collection.getById(id) == null) {
                console.printError("Элемент с id " + id + " не найден");
                return 2;
            }
            collection.removeById(id);
            console.println("Элемент с id " + id + " удален");
            return 0;
        } catch (NumberFormatException e) {
            console.printError("Неверный формат аргумента!");
            return 3;
        }
    }

    @Override
    public String toString() {
        return " <id> : удалить элемент из коллекции по его id";
    }
}
