package Forms;

import Entities.Car;
import Managers.ConsoleManager;

import java.util.Objects;

/**
 * Форма для создания объекта класса {@link Car}.
 */
public class CarForm extends Form<Car> {
    public CarForm(ConsoleManager console) {
        super(console);
    }

    /**
     * Формирует объект класса {@link Car}.
     *
     * @return Объект класса {@link Car}
     */
    @Override
    public Car build() {
        return new Car(askString("название машины", " (поле не может быть null)", Objects::nonNull));
    }
}