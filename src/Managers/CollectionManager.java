package Managers;

import lombok.Getter;
import lombok.Setter;
import Entities.*;

import java.util.*;

/**
 * Класс для управления коллекцией.
 */
@Getter
@Setter
public class CollectionManager {
    /**
     * Время инициализации коллекции.
     */
    public static Date initializationTime = new Date();

    /**
     * Коллекция.
     */
    private LinkedList<HumanBeing> collection = new LinkedList<HumanBeing>();

    /**
     * Добавить элемент в коллекцию.
     *
     * @param o элемент
     */
    public void add(HumanBeing o) {
        collection.add(o);
    }

    /**
     * Обновить элемент коллекции по id.
     *
     * @param id id элемента
     * @param o  новый элемент
     */
    public void update(long id, HumanBeing o) {
        collection.stream().filter(HumanBeing -> HumanBeing.getId() == id).forEach(HumanBeing -> {
            HumanBeing.setName(o.getName());
            HumanBeing.setCoordinates(o.getCoordinates());
            HumanBeing.setCreationDate(o.getCreationDate());
            HumanBeing.setRealHero(o.getRealHero());
            HumanBeing.setHasToothpick(o.getHasToothpick());
            HumanBeing.setImpactSpeed(o.getImpactSpeed());
            HumanBeing.setWeaponType(o.getWeaponType());
            HumanBeing.setMood(o.getMood());
            HumanBeing.setCar(o.getCar());
        });
    }

    /**
     * Добавить элемент в коллекцию, если он меньше минимального.
     *
     * @param o элемент
     * @return true если элемент добавлен, false иначе
     */
    public boolean addIfMin(HumanBeing o) {
        if (collection.isEmpty() || Collections.min(collection).compareTo(o) > 0) {
            collection.add(o);
            return true;
        }
        return false;
    }

    /**
     * Добавить элемент в коллекцию, если он больше максимального.
     *
     * @param o элемент
     * @return true если элемент добавлен, false иначе
     */
    public boolean addIfMax(HumanBeing o) {
        if (collection.isEmpty() || Collections.max(collection).compareTo(o) < 0) {
            collection.add(o);
            return true;
        }
        return false;
    }

    /**
     * Удалить элемент по id.
     *
     * @param id id элемента
     */
    public void removeById(long id) {
        collection.removeIf(o -> o.getId() == id);
    }

    /**
     * Очистить коллекцию.
     */
    public void clear() {
        initializationTime = new Date();
        collection.clear();
    }

    /**
     * Вернуть список элементов, значение поля name которых содержит заданную подстроку.
     *
     * @param name подстрока
     */
    public List<HumanBeing> filterContainsName(String name) {
        return collection.stream().filter(o -> o.getName().contains(name)).toList();
    }

    /**
     * Удалить элементы, которые больше заданного.
     *
     * @param o объект для сравнения
     */
    public void removeGreater(HumanBeing o) {
        collection.removeIf(HumanBeing -> HumanBeing.compareTo(o) > 0);
    }

    /**
     * Удалить элементы, значение поля annualTurnover которых меньше заданного.
     *
     * @param o объект для сравнения
     */
    public void removeLower(HumanBeing o) {
        collection.removeIf(HumanBeing -> HumanBeing.compareTo(o) < 0);
    }

    /**
     * Вернуть элемент по id.
     *
     * @param id id элемента
     */
    public HumanBeing getById(long id) {
        return collection.stream().filter(o -> o.getId() == id).findFirst().orElse(null);
    }

    /**
     * Вернуть количество элементов у которых ImpactSpeed меньше заданного.
     *
     * @param ImpactSp ImpactSpeed
     * @return количество элементов
     */
    public int CountLessThanImpactSpeed(int ImpactSp){
        int count = 0;
        for (HumanBeing item : collection) {
            if (item.getImpactSpeed() < ImpactSp){
                count+=1;
            }
        }
        return count;
    }

    /**
     * Вернуть список элементов, значение поля Car которых меньше заданного.
     *
     * @param c Car для сравнения
     * @return список элементов
     */
    public List<HumanBeing> filterLessThanCar(Car c) {
        return collection.stream().filter(o -> (c.compareTo(o.getCar()) > 0)).toList();
    }

    /**
     * Проверить пустая ли коллекция
     *
     * @return если коллекция пустая, true
     */
    public boolean isEmpty(){
        return collection.isEmpty();
    }

    /**
     * Вернуть первый элемент коллекции
     *
     * @return элемент коллекции
     */
    public HumanBeing head(){
        return collection.getFirst();
    }

    /**
     * Вернуть любой минимальный элемент коллекции по значению поля ImpactSpeed.
     *
     * @return минимальный элемент коллекции ImpactSpeed
     */
    public HumanBeing minByImpactSpeed() {
        return Collections.min(collection, Comparator.comparingInt(HumanBeing::getImpactSpeed));
    }
}