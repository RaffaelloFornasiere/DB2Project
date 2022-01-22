package it.polimi.db2.telecoApp.dataaccess.entities;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "orders")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
public class OrderEntity {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column( name = "order_date")
    private LocalDateTime orderDate;

    @ManyToOne
    @JoinColumn( name = "user")
    private UserEntity user;

    @ManyToOne
    @JoinColumn( name = "package")
    private ServicePackageEntity servicePackageEntity;

    @ManyToOne
    @JoinColumn( name= "validity_period_id")
    private ValidityPeriodEntity validityPeriod;

    @Column( name = "start_date")
    private LocalDate startDate;

    @ManyToMany
    @ToString.Exclude
    @JoinTable(
            name = "orders_optional_packages",
            joinColumns = {@JoinColumn(name = "id_order")},
            inverseJoinColumns = {@JoinColumn(name = "id_optional_package")})
    private List<OptionalPackageEntity> optionalPackages;
}
