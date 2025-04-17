package Commands;

import Managers.CollectionManager;
import Managers.ConsoleManager;
import Managers.XmlFileReader;

/**
 * Команда "save".
 * Описание команды: сохранить коллекцию в файл.
 */
public class Save implements CommandInterface{
    XmlFileReader reader;
    CollectionManager collection;
    ConsoleManager console;

    public Save(XmlFileReader reader, CollectionManager collection, ConsoleManager console){
        this.reader = reader;
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
        if (args.length != 0) {
            console.printError("Команда не принимает аргументы!");
            return 1;
        }
        try {
            if (reader.saveCollection(collection)){
                console.println("Коллекция сохранена в файл");
                return 0;
            }
            else{
                console.println("Произошла ошибка при записи в файл");
                return 2;
            }
        } catch (Exception e) {
            console.printError(e.getMessage());
            return 2;
        }
    }

    @Override
    public String toString(){
        return ": сохранить коллекцию в файл";
    }
}
