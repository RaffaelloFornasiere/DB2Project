package it.polimi.db2.teleco_app.dataaccess.entities;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
@Entity
@EntityListeners(ReadOnlyEntity.class)
@Table(name = "alerts")
public class AlertEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @OneToOne
    @JoinColumn(name = "last_billing")
    BillingEntity billing;
}


