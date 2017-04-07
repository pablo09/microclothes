package service;

import com.pzeszko.microclothes.shoes.model.Shoes;
import com.pzeszko.microclothes.shoes.repository.ShoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 07.04.2017.
 */
@Service
@Transactional(readOnly = true)
public class ShoesServiceImpl implements ShoesService {

    @Autowired
    private ShoesRepository shoesRepository;

    @Override
    public List<Shoes> findAll() {
        return shoesRepository.findAll();
    }
}
