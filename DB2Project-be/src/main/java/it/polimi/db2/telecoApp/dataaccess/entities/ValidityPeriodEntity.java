package it.polimi.db2.telecoApp.dataaccess.entities;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Accessors(chain = true)
@Table(name = "validity_periods")
public class ValidityPeriodEntity {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "months")
    private Integer months;

    @Column(name = "fee")
    private Double fee;
}
