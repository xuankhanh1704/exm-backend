package org.se06203.campusexpensemanagement.persistence.repository;

import org.se06203.campusexpensemanagement.persistence.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, Long> {

    List<Categories> findByName(String categoryName);
}
