package com.poc.makemytrip.dao;

import com.poc.makemytrip.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenDao  extends JpaRepository<Token, Integer> {
    @Query(value = """
      select t from Token t inner join User u\s
      on t.user.ID = u.ID\s
      where u.ID = :id and (t.expired = false or t.revoked = false)\s
      """)
    List<Token> findAllValidTokenByUser(Integer id);

    Optional<Token> findByToken(String token);
}
