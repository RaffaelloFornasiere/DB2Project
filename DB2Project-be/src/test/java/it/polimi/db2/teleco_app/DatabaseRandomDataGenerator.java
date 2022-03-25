package it.polimi.db2.teleco_app;

import it.polimi.db2.teleco_app.services.*;

import it.polimi.db2.teleco_app.services.models.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import java.time.*;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DatabaseRandomDataGenerator {


    @Autowired
    OptionalPackageService optionalPackageService;
    @Autowired
    OrderService orderService;
    @Autowired
    PackageService packageService;
    @Autowired
    ServiceService serviceService;
    @Autowired
    UserService userService;
    @Autowired
    ValidityPeriodService validityPeriodService;
    @Autowired
    EntityManager entityManager;


    @Test
    @Rollback(false)
    void GenerateRandomOrders() {
        Random ran = new Random();
        var packages = packageService.findAll();
        var validityPeriods = validityPeriodService.findAll();
        var optionalPackages = optionalPackageService.findAll();
        var users = userService.findAll();
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        for (int i = 0; i <100; i++) {

            Mockito.when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(users.get(ran.nextInt(users.size())));
            var initDate = LocalDateTime.now()
                    .minusMonths(ran.nextInt(1))
                    .minusWeeks(ran.nextInt(4))
                    .minusDays(ran.nextInt(7))
                    .minusHours(ran.nextInt(24))
                    .minusSeconds(ran.nextInt(3600));
            var finalDate = LocalDateTime.now();

            var servicePackage = packages.get(ran.nextInt(packages.size()));
            var orderDate = between(initDate.toInstant(OffsetDateTime.now().getOffset()), finalDate.toInstant(OffsetDateTime.now().getOffset()));
            var validityPeriod = validityPeriods.get(ran.nextInt(validityPeriods.size()));

            AtomicInteger j = new AtomicInteger();
            List<OptionalPackage> optionalPackage = ran.nextBoolean()?
                    optionalPackages.stream().filter(op -> {
                        j.getAndIncrement();
                        return ran.nextInt(optionalPackages.size()) < j.get();
                    }).toList(): List.of();

            var amount = packages.stream().flatMap(p -> p.getTelecomServices().stream()).map(s -> s.getDetails().getCostMonth()).reduce(Double::sum).orElse(0.0) +
                    optionalPackage.stream().map(OptionalPackage::getMonthlyFee).reduce(Double::sum).orElse(0.0) +
                    validityPeriod.getFee();

            var order = new Order(
                    null, orderDate, null, servicePackage, validityPeriod, optionalPackage,
                    orderDate.plusMonths(ran.nextInt(1))
                            .plusWeeks(ran.nextInt(4))
                            .plusDays(ran.nextInt(7))
                            .plusHours(ran.nextInt(24))
                            .plusSeconds(ran.nextInt(3600)).toLocalDate(),
                    amount
            );
            orderService.buy(order, ran.nextDouble()<0.92);
        }
    }

    @Test
    @Rollback(false)
    void ResolvePayments(){
        Random ran = new Random();
        var users = userService.findAll();
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        users.forEach(u -> {
            Mockito.when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(u);
            var orders = orderService.getRejectedOrders();
            orders.forEach(o -> {if (ran.nextBoolean()) orderService.buy(o, ran.nextBoolean() || ran.nextBoolean() || ran.nextBoolean() || ran.nextBoolean());});
        });


    }

    public static LocalDateTime between(Instant startInclusive, Instant endExclusive) {
        long startSeconds = startInclusive.getEpochSecond();
        long endSeconds = endExclusive.getEpochSecond();
        long random = ThreadLocalRandom
                .current()
                .nextLong(startSeconds, endSeconds);

        return LocalDateTime.ofInstant(Instant.ofEpochSecond(random), ZoneId.systemDefault());
    }
}
