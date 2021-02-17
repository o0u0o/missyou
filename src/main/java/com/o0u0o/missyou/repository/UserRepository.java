package com.o0u0o.missyou.repository;

import com.o0u0o.missyou.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @ClassName UserRepository
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/2/17 1:13 下午
 * @Descripton: 用户数据仓库
 * @Version: v0.0.1
 **/
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

    User findByUnifyUid(String uuid);

}
