package com.incetutku.ecom.repository;

import com.incetutku.ecom.model.CartItem;
import com.incetutku.ecom.model.Product;
import com.incetutku.ecom.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByUserAndProduct(User user, Product product);

    void deleteByUserAndProduct(User user, Product product);
}
