package com.incetutku.orderservice.service;

import com.incetutku.orderservice.dto.CartItemRequest;
import com.incetutku.orderservice.model.CartItem;
import com.incetutku.orderservice.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartItemRepository cartItemRepository;

    public boolean addToCart(Long userId, CartItemRequest cartItemRequest) {
//        Optional<Product> optionalProduct = productRepository.findById(cartItemRequest.getProductId());
//        if (optionalProduct.isEmpty()) {
//            return false;
//        }
//
//
//        Product product = optionalProduct.get();
//        if (product.getStockQuantity() < cartItemRequest.getQuantity()) {
//            return false;
//        }
//
//        Optional<User> optionalUser = userRepository.findById(Long.valueOf(userId));
//        if (optionalUser.isEmpty()) {
//            return false;
//        }
//
//        User user = optionalUser.get();

        CartItem existingCartItem = cartItemRepository.findByUserIdAndProductId(userId, cartItemRequest.getProductId());
        if (!Objects.isNull(existingCartItem)) {
            // Update Quantity
            existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItemRequest.getQuantity());
            existingCartItem.setPrice(BigDecimal.valueOf(1000.00));
            cartItemRepository.save(existingCartItem);
        } else {
            // Create new cart item
            CartItem cartItem = new CartItem();
            cartItem.setUserId(userId);
            cartItem.setProductId(cartItemRequest.getProductId());
            cartItem.setQuantity(cartItemRequest.getQuantity());
            cartItem.setPrice(BigDecimal.valueOf(1000.00));
            cartItemRepository.save(cartItem);
        }
        return true;
    }

    @Transactional
    public boolean deleteItemFromCart(Long userId, Long productId) {
        CartItem cartItem = cartItemRepository.findByUserIdAndProductId(userId, productId);

        if (!Objects.isNull(cartItem)) {
            cartItemRepository.delete(cartItem);
            return true;
        }
        return false;
    }

    public List<CartItem> getCart(Long userId) {
        return cartItemRepository.findByUserId(userId);
    }

    public void clearCart(Long userId) {
        cartItemRepository.deleteByUserId(userId);
    }
}
