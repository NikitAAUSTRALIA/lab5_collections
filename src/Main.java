import Managers.*;
import Commands.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ConsoleManager console = new ConsoleManager();
        CommandManager commandManager = new CommandManager(console);
        CollectionManager collectionManager = new CollectionManager();

        String filename;
        if (args.length != 1) {
            console.printError("Необходимо передать имя файла в качестве аргумента, использую файл по умолчанию: data.xml");
            filename = "src/data.xml";
        } else {
            filename = args[0];
        }

        XmlFileReader reader = new XmlFileReader(filename, console);
        IdManager.setCollectionManager(collectionManager);

        commandManager.addCommand("help", new Help(console, commandManager));
        commandManager.addCommand("info", new Info(console, collectionManager));
        commandManager.addCommand("show", new Show(console, collectionManager));
        commandManager.addCommand("add", new Add(console, collectionManager));
        commandManager.addCommand("update", new Update(console, collectionManager));
        commandManager.addCommand("remove_by_id", new RemoveById(console, collectionManager));
        commandManager.addCommand("clear", new Clear(console, collectionManager));
        commandManager.addCommand("save", new Save(reader, collectionManager, console));
        commandManager.addCommand("execute_script", new ExecuteScript(console, commandManager));
        commandManager.addCommand("exit", new Exit(console));
        commandManager.addCommand("add_if_min", new AddIfMin(console, collectionManager));
        commandManager.addCommand("add_if_max", new AddIfMax(console, collectionManager));
        commandManager.addCommand("count_less_than_impact_speed", new CountLessThanImpactSpeed(console, collectionManager));
        commandManager.addCommand("filter_less_than_car", new FilterLessThanCar(console, collectionManager));
        commandManager.addCommand("head", new Head(console, collectionManager));
        commandManager.addCommand("min_by_impact_speed", new MinByImpactSpeed(console, collectionManager));

        new InteractiveManager(commandManager, reader, console, collectionManager).interactiveMode();
    }
}