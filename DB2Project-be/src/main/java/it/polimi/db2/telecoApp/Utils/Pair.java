package it.polimi.db2.telecoApp.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pair<T, Q> {
    public static <T,Q> Pair<T,Q> of(T first, Q second){
        return new Pair<>(first, second);
    }


    T first;
    Q second;
}
