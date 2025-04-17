package Forms;

import Entities.Coordinates;
import Managers.ConsoleManager;

import java.util.Objects;

/**
 * Форма для создания объекта класса {@link Coordinates}.
 */
public class CoordinatesForm extends Form<Coordinates> {
    public CoordinatesForm(ConsoleManager console) {
        super(console);
    }

    /**
     * Формирует объект класса {@link Coordinates}.
     *
     * @return Объект класса {@link Coordinates}
     */
    @Override
    public Coordinates build() {
        return new Coordinates(
                askDouble("координата x", " (десятичная дробь, поле не может быть null)", Objects::nonNull),
                askInteger("координата y", " (целое число, поле не может быть null)", Objects::nonNull)
        );
    }
}