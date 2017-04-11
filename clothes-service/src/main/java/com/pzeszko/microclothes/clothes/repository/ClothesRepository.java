package com.pzeszko.microclothes.clothes.repository;

import com.pzeszko.microclothes.clothes.model.Clothes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Admin on 08.04.2017.
 */
@Repository
public interface ClothesRepository extends JpaRepository<Clothes, String> {
}
