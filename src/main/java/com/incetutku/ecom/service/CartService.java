package com.incetutku.ecom.service;

import com.incetutku.ecom.dto.CartItemRequest;
import com.incetutku.ecom.model.CartItem;
import com.incetutku.ecom.model.Product;
import com.incetutku.ecom.model.User;
import com.incetutku.ecom.repository.CartItemRepository;
import com.incetutku.ecom.repository.ProductRepository;
import com.incetutku.ecom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    public boolean addToCart(String userId, CartItemRequest cartItemRequest) {
        Optional<Product> optionalProduct = productRepository.findById(cartItemRequest.getProductId());
        if (optionalProduct.isEmpty())
            return false;

        Product product = optionalProduct.get();
        if (product.getStockQuantity() < cartItemRequest.getQuantity()) {
            return false;
        }

        Optional<User> optionalUser = userRepository.findById(Long.valueOf(userId));
        if (optionalUser.isEmpty()) {
            return false;
        }

        User user = optionalUser.get();

        CartItem existingCartItem = cartItemRepository.findByUserAndProduct(user, product);
        if (!Objects.isNull(existingCartItem)) {
            // Update Quantity
            existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItemRequest.getQuantity());
            existingCartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(existingCartItem.getQuantity())));
            cartItemRepository.save(existingCartItem);
        } else {
            // Create new cart item
            CartItem cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setQuantity(cartItemRequest.getQuantity());
            cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(cartItemRequest.getQuantity())));
            cartItemRepository.save(cartItem);
        }
        int stock = product.getStockQuantity() - cartItemRequest.getQuantity();
        product.setStockQuantity(stock);
        if (stock == 0) {
            product.setIsActive(false);
        }
        productRepository.save(product);

        return true;
    }
}
