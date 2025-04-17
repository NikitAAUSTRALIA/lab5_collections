package Managers;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Менеджер выполнения программы.
 */
@AllArgsConstructor
public class InteractiveManager {
    private CommandManager commandManager;
    private XmlFileReader reader;
    private ConsoleManager console;
    private CollectionManager collection;

    /**
     * Интерактивный режим.
     */
    public void interactiveMode() {
        Scanner scanner = ScannerManager.getScanner();
        try {
            reader.validFileText(reader.readFile(), collection);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            try {
                console.print(">>> ");
                String[] userCommand = scanner.nextLine().trim().split(" ");
                commandManager.executeCommand(userCommand[0].toLowerCase(), Arrays.copyOfRange(userCommand, 1, userCommand.length));
            } catch (NoSuchElementException e) {
                return;
            } catch (Exception e) {
                console.printError(e.getMessage());
            }
        }
    }
}