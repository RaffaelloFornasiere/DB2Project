package it.polimi.db2.telecoApp.dataaccess.entities;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;


@Table(name = "Purchases")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
public class PurchasesEntity {

    @Id
    private long id;

    @Column( name = "purchase_date")
    private LocalDateTime purchase_date;

    @ManyToOne
    @JoinColumn( name = "user")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn( name = "package")
    private PackageEntity packageEntity;
}
