package com.pzeszko.microclothes.shoes.repository;

import com.pzeszko.microclothes.shoes.model.Shoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Admin on 07.04.2017.
 */
@Repository
public interface ShoesRepository extends JpaRepository<Shoes, String> {
}
