package Forms;

import Entities.Coordinates;
import Managers.ConsoleManager;

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
                askDouble("координата x", " (десятичная дробь, поле не может быть null)", x -> x != null),
                askInteger("координата y", " (целое число, поле не может быть null)", x -> x != null)
        );
    }
}