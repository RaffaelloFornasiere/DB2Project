package it.polimi.db2.teleco_app.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pair<T, Q> {
    T first;
    Q second;

    public static <T, Q> Pair<T, Q> of(T first, Q second) {
        return new Pair<>(first, second);
    }
}
