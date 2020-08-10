package com.o0u0o.missyou.repository;

import com.o0u0o.missyou.model.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName BannerRepository
 * @Author aiuiot
 * @UpdateUser aiuiot
 * @Date 2020/7/20 下午11:21
 * @Descripton: BannerRepository接口
 * 1、需要继承JpaRepository
 * 2、JpaRepository的范型参数<操作的实体,该实体主键的类型> 即 JpaRepository<Banner, Long>
 * @Version: v0.0.1
 **/

public interface BannerRepository extends JpaRepository<Banner, Long> {

    /**
     * 根据轮播ID查询
     * @param id 轮播ID
     * @return
     */
    Banner findOneById(Long id);

    /**
     * 根据轮播名查询
     * @param name
     * @return
     */
    Banner findOneByName(String name);
}
