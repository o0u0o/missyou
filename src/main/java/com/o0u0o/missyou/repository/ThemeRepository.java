package com.o0u0o.missyou.repository;

import com.o0u0o.missyou.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {

    //SQL JPQL(Java Persistence Query Language) JPQL是操作model对象的
    @Query("select t from Theme t where t.name  in (:names)")
    List<Theme> findByNames(@Param("names") List<String> names);

    Optional<Theme> findByName(String name);
}
