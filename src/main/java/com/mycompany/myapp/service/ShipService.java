package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Ship;
import com.mycompany.myapp.repository.ShipRepository;
import com.mycompany.myapp.repository.UserCourseRepository;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ShipService {
    @Autowired
    private ShipRepository shipRepository;
    static double THRES = 0.96;


    List<Ship> ships = new ArrayList<>();


    public List<Ship> findByName(String name){
        return shipRepository.findByName(name);
    }

    public List<Ship> findByFreq(long[] freqArr){
        return shipRepository.findByFreq(freqArr[0], freqArr[1], freqArr[2], freqArr[3], freqArr[4], THRES);
    }

}
