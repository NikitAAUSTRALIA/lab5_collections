package Entities;

import lombok.*;

/**
 * Класс машины.
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Car implements Comparable<Car>{
    private String name; //Поле не может быть null

    @Override
    public int compareTo(Car o) {
        return name.compareTo(o.getName());
    }
}
