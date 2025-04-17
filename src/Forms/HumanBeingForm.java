package Forms;

import Entities.*;
import Managers.ConsoleManager;
import Managers.IdManager;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Форма для создания объекта класса {@link HumanBeing}.
 */
public class HumanBeingForm extends Form<HumanBeing>{
    private final ConsoleManager console;

    public HumanBeingForm(ConsoleManager console) {
        super(console);
        this.console = console;
    }

    /**
     * Формирует объект класса {@link HumanBeing}.
     *
     * @return Объект класса {@link HumanBeing}
     */
    @Override
    public HumanBeing build() {
        return new HumanBeing(
                IdManager.generateId(),
                askString("имя человека", " (поле не может быть null, строка не может быть пустой)", s -> !s.isEmpty()),
                askCoordinates(),
                LocalDate.now(),
                askBoolean("реальный ли человек"),
                askBoolean("есть ли у него зубочистка"),
                askInteger("скорость", " (поле не может быть null)", Objects::nonNull),
                (WeaponType) askEnum("тип оружия", WeaponType.values(), s -> true),
                (Mood) askEnum("настроение", Mood.values(), s -> true),
                askCar());
    }

    private Coordinates askCoordinates(){
        return new CoordinatesForm(console).build();
    }

    private Car askCar(){
        return new CarForm(console).build();
    }
}
