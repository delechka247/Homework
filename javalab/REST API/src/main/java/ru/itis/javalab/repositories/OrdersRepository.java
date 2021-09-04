package ru.itis.javalab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab.models.Order;

public interface OrdersRepository extends JpaRepository<Order, Long> {

}
