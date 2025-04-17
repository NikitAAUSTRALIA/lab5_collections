package Commands;

import Managers.CollectionManager;
import Managers.ConsoleManager;

/**
 * Команда "count_less_than_impact_speed".
 * Описание команды: вывести количество элементов, значение поля impactSpeed которых меньше заданного.
 */
public class CountLessThanImpactSpeed implements CommandInterface{
    ConsoleManager console;
    CollectionManager collection;

    public CountLessThanImpactSpeed(ConsoleManager console, CollectionManager collection) {
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
            console.printError("ImpactSpeed должен быть числом!");
            return 2;
        }
        console.println(String.valueOf(collection.CountLessThanImpactSpeed(Integer.parseInt(args[0]))));
        return 0;
    }

    @Override
    public String toString(){
        return " <ImpactSpeed> : посчитать количество элементов в коллекции у которых ImpactSpeed меньше чем заданный";
    }
}
