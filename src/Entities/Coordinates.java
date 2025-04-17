package Entities;

import lombok.*;

/**
 * Класс Координат.
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Coordinates {
    private Double x; //Поле не может быть null
    private Integer y; //Поле не может быть null
}
