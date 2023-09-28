package com.nucleus.generator.repo;

import com.nucleus.generator.entity.CustomMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomMethodRepo extends JpaRepository<CustomMethod, String> {
}
