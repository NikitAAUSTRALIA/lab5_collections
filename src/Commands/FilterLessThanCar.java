package Commands;


import Entities.Car;
import Entities.HumanBeing;
import Forms.CarForm;
import Managers.CollectionManager;
import Managers.ConsoleManager;

/**
 * Команда "filter_less_than_car".
 * Описание команды: вывести элементы, значение поля Car которых меньше заданного.
 */
public class FilterLessThanCar implements CommandInterface{
    ConsoleManager console;
    CollectionManager collection;

    public FilterLessThanCar(ConsoleManager console, CollectionManager collection) {
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
        var response = collection.filterLessThanCar(new CarForm(console).build());
        if (response.isEmpty()) {
            console.println("Элементов не найдено");
        } else {
            console.println("Элементы, значение поля Car которых меньше заданного:");
            response.stream().map(HumanBeing::toString).forEach(console::println);
        }
        return 0;
    }

    @Override
    public String toString() {
        return ": вывести элементы, значение поля Car которых меньше заданного";
    }
}
