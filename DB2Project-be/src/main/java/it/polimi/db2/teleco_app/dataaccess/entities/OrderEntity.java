package it.polimi.db2.teleco_app.dataaccess.entities;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @ManyToOne
    @JoinColumn(name = "user")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "package_id")
    private ServicePackageEntity servicePackageEntity;

    @ManyToOne
    @JoinColumn(name = "validity_period_id")
    private ValidityPeriodEntity validityPeriod;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "suspended")
    private Boolean suspended;

    @Column(name = "total_value")
    private Double totalValue;


    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @ToString.Exclude
    @JoinTable(
            name = "orders_optional_packages",
            joinColumns = {@JoinColumn(name = "id_order")},
            inverseJoinColumns = {@JoinColumn(name = "id_optional_package")})
    private List<OptionalPackageEntity> optionalPackages;
}
