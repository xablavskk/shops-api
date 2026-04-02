package com.xvlkk.sp.shops.service;

import com.xvlkk.sp.shops.dto.CartDTO;
import com.xvlkk.sp.shops.exceptions.BusinessException;
import com.xvlkk.sp.shops.exceptions.ResourceNotFoundException;
import com.xvlkk.sp.shops.mapper.CartMapper;
import com.xvlkk.sp.shops.model.Cart;
import com.xvlkk.sp.shops.model.CartItem;
import com.xvlkk.sp.shops.model.ProductSku;
import com.xvlkk.sp.shops.repository.CartRepository;
import com.xvlkk.sp.shops.repository.ProductSkuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductSkuRepository productSkuRepository;
    private final CartMapper cartMapper;

    public CartDTO findByUser(Long cdUser) {
        return cartMapper.toDto(cartRepository.findByUserCdUser(cdUser)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for user: " + cdUser)));
    }

    public CartDTO findBySession(String sessionId) {
        return cartMapper.toDto(cartRepository.findByDsSessionId(sessionId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for session: " + sessionId)));
    }

    @Transactional
    public CartDTO addItem(Long cartId, Long skuId, int qty) {
        if (qty <= 0) throw new BusinessException("Quantity must be greater than zero");

        Cart cart = findCartOrThrow(cartId);
        ProductSku sku = productSkuRepository.findById(skuId)
                .orElseThrow(() -> new ResourceNotFoundException("SKU not found: " + skuId));

        cart.getItems().stream()
                .filter(i -> i.getProductSku().getCdProductSku().equals(skuId))
                .findFirst()
                .ifPresentOrElse(
                        item -> item.setQtCartItem(item.getQtCartItem() + qty),
                        () -> {
                            CartItem newItem = new CartItem();
                            newItem.setCart(cart);
                            newItem.setProductSku(sku);
                            newItem.setQtCartItem(qty);
                            cart.getItems().add(newItem);
                        }
                );

        return cartMapper.toDto(cartRepository.save(cart));
    }

    @Transactional
    public CartDTO removeItem(Long cartId, Long skuId) {
        Cart cart = findCartOrThrow(cartId);
        cart.getItems().removeIf(i -> i.getProductSku().getCdProductSku().equals(skuId));
        return cartMapper.toDto(cartRepository.save(cart));
    }

    @Transactional
    public CartDTO updateItemQty(Long cartId, Long skuId, int qty) {
        if (qty <= 0) return removeItem(cartId, skuId);

        Cart cart = findCartOrThrow(cartId);
        cart.getItems().stream()
                .filter(i -> i.getProductSku().getCdProductSku().equals(skuId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Item not found in cart"))
                .setQtCartItem(qty);

        return cartMapper.toDto(cartRepository.save(cart));
    }

    @Transactional
    public void clearCart(Long cartId) {
        Cart cart = findCartOrThrow(cartId);
        cart.getItems().clear();
        cartRepository.save(cart);
    }

    public CartDTO create(CartDTO dto) {
        return cartMapper.toDto(cartRepository.save(cartMapper.toEntity(dto)));
    }

    private Cart findCartOrThrow(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found: " + cartId));
    }
}
