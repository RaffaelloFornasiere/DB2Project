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
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
public class PurchasesEntity {
    @Id
    private long id;

    @Column( name = "user")
    private String user;

    @Column( name = "purchase_date")
    private LocalDateTime purchase_date;

    @Column( name = "packages")
    private long packages;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "user")
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "packages")
    private PackageEntity packageEntity;


}
