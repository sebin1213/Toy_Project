package com.example.MiniShop.repository;


import com.example.MiniShop.domain.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

//@RequiredArgsConstructor
//public class CartRepositoryImpl implements CartRepositoryCustom {
//    private final EntityManager em;
//
//    @Override
//    public List<CartItem> findCartItemCustom(String userId) {
//        QMember member = QMember.member;
//        QCart cart = QCart.cart;
//        QItem item = QItem.item;
//        QItemImg itemImg = QItemImg.itemImg;
//
//        JPAQueryFactory query = new JPAQueryFactory(em);
//
//        return query
//                .select()
//                .from(cart)
//                .join(member.cart, cart)
//                .where(statusEq(orderSearch.getOrderStatus()), member.userid.eq(userId))
//                .limit(1000)
//                .fetch();
//
//    }
//}
