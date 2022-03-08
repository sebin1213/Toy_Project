package com.example.MiniShop.service;

import com.example.MiniShop.domain.*;
import com.example.MiniShop.repository.CartItemRepository;
import com.example.MiniShop.repository.CartRepository;
import com.example.MiniShop.repository.ItemRepository;
import com.example.MiniShop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    public Cart createCart(Cart cart){
        return cartRepository.save(cart);
    }

    public void addCart(String userId,Long itemId, int quantity){
        Optional<Item> item_op = itemRepository.findById(itemId);
        if (item_op.isPresent()){
            Item item = item_op.get();
            if (item.getStockQuantity() >= quantity) {
                Member member = memberRepository.findByUserid(userId);
                CartItem cartItem = CartItem.createCartItem(item, quantity);
                Cart cart = member.getCart();
                cart.addCartItem(cartItem);
                createCart(cart);
            }
        }
        else {
            throw new NoSuchElementException();
        }
    }

    public void deleteCart(Long itemId,String userId){
        Member member = memberRepository.findByUserid(userId);
        Cart cart = member.getCart();
        for (CartItem cartItem: cart.getCartItems()){
            if(cartItem.getItem().getId()==itemId){
                cart.removeCartItem(cartItem);
                cartRepository.deleteById(cartItem.getId());
                break;
            }
        }

    }

    public List<CartItem> cartItemList(String userId){

        return memberRepository.findByUserid(userId).getCart().getCartItems();

    }


}
