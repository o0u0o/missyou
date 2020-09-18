package com.o0u0o.missyou.repository;

import com.o0u0o.missyou.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByIsRootOrderByIndexAsc(Boolean isRoot);
}
