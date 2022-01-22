package it.polimi.db2.telecoApp.services.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pair<T, Q> {
    T first;
    Q second;
}
