package com.example.MiniShop.repository;

import com.example.MiniShop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUserid(String userid);
    Member findByEmail(String email);
    List<Member> findAll();

}
