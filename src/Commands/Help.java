package Commands;

import Managers.CommandManager;
import Managers.ConsoleManager;

/**
 * Команда "help".
 * Описание команды: вывести справку по доступным командам.
 */
public class Help implements CommandInterface {
    ConsoleManager console;
    CommandManager manager;

    public Help(ConsoleManager console, CommandManager manager) {
        this.console = console;
        this.manager = manager;
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
        console.println("Доступные команды:");
        manager.getCommands().forEach((name, command) -> console.println(name + command.toString()));
        return 0;
    }

    @Override
    public String toString() {
        return ": вывести справку по доступным командам";
    }
}
