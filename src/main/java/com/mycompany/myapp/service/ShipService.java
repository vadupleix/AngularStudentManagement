package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Course;
import com.mycompany.myapp.domain.Ship;
import com.mycompany.myapp.domain.dto.CourseDto;
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

    public List<Ship> findByFreq(long[] freqArr, double acc, String count){
        return shipRepository.findByFreq(freqArr[0], freqArr[1], freqArr[2], freqArr[3], freqArr[4], acc, freqArr[5], count);
    }

    public void addShip(Ship ship) throws Exception{
        //Optional<Course> Ship = shipRepository.findByName(ship.getShipName());

        //if(courseDto.isPresent()){
        //    throw new Exception("Course is existing.");
        //}

        Ship shipNew = Ship.builder().shipName(ship.getShipName()).country(ship.getCountry())
            .type(ship.getType()).freqVeryLow(ship.getFreqVeryLow())
            .freqLow(ship.getFreqLow()).freqMed(ship.getFreqMed())
            .freqHigh(ship.getFreqHigh()).freqVeryHigh(ship.getFreqVeryHigh())
            .tpk(ship.getTpk()).freqActive(ship.getFreqActive())
            .numBlades(ship.getNumBlades())
            .build();

        try {
            shipRepository.saveAndFlush(shipNew);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

}
