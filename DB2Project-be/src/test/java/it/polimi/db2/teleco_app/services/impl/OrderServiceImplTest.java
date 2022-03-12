package it.polimi.db2.teleco_app.services.impl;

import it.polimi.db2.teleco_app.dataaccess.repositories.OrderRepository;
import it.polimi.db2.teleco_app.services.mappers.OrderMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderServiceImplTest {

//    @Autowired
//    OrderRepository orderRepository;
//
//    @Autowired
//    OrderMapper orderMapper;
//
//    @Test
//    void testSomething(){
//        assertThat(orderRepository).isNotNull();
//        var aux = orderRepository.findAll().stream()
//                .map(orderMapper::toTarget).toList();
//        System.out.println(aux);
//    }
//
//    @Test
//    void testEqualityFind(){
//        var a = orderRepository.findAllByOrderDateAfterAndOrderDateBeforeJPQL(LocalDateTime.MIN, LocalDateTime.MAX);
//        var b = orderRepository.findAllByOrderDateAfterAndOrderDateBeforeNative(LocalDateTime.MIN, LocalDateTime.MAX);
//
//
//        assertThat(a).isEqualTo(b);//.isEqualTo(c);
//
//    }

}