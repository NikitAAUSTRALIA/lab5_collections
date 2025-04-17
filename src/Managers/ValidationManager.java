package Managers;

import Entities.*;

/**
 * Класс для валидации объектов.
 */
public class ValidationManager {

    private ValidationManager() {
    }

    /**
     * Проверяет валидность человека.
     *
     * @param o               человек
     * @param collectionManager менеджер коллекции
     * @return true, если объект валиден, иначе false
     */
    public static boolean isValidHumanBeing(HumanBeing o) {
        return o.getId() > 0 && // Нужно добавить

                o.getName() != null &&
                !o.getName().isEmpty() &&

                o.getCoordinates() != null &&
                isValidCoordinates(o.getCoordinates()) &&

                o.getCreationDate() != null &&

                o.getImpactSpeed() != null &&

                o.getWeaponType() != null &&

                o.getMood() != null &&

                o.getCar() != null &&
                isValidCar(o.getCar());
    }

    /**
     * Проверяет валидность машины.
     *
     * @param o объект машины
     * @return true, если объект валиден, иначе false
     */
    public static boolean isValidCar(Car o) {
        return o.getName() != null;
    }

    /**
     * Проверяет валидность координат.
     *
     * @param o объект координат
     * @return true, если объект валиден, иначе false
     */
    public static boolean isValidCoordinates(Coordinates o) {
        return o.getX() != null &&
                o.getY() != null;
    }
}