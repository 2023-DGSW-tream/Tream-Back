package hs.kr.dgsw.treamprototype.domain.category.domain.repository;

import hs.kr.dgsw.treamprototype.domain.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    void deleteAllByPost_Id(Long id);

    void deleteCategoryByPost_Id(Long id);

    List<Category> findByPost_Id(Long id);

}
