package com.o0u0o.missyou.repository;

import com.o0u0o.missyou.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    /**
     * 通过微信用户openid查询
     * @param openid
     * @return
     */
    Optional<User> findByOpenid(String openid);

    User findFirstById(Long id);

    User findbyUnifyUid(String uuid);

}
