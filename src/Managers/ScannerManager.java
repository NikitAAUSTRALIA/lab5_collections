package Managers;

import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

/**
 * Класс для сканера.
 */
public class ScannerManager {
    @Getter
    @Setter
    private static Scanner scanner = new Scanner(System.in);
    private ScannerManager() {
    }

}