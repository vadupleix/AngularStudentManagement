package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Ship;
import com.mycompany.myapp.domain.dto.CourseDto;
import com.mycompany.myapp.domain.dto.CourseWithTNDto;
import com.mycompany.myapp.service.ShipService;
import io.swagger.annotations.Api;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping
@Api(value="Ship Service Controller", description = "Controller for find ships information")
public class ShipController {
    @Autowired
    private ShipService shipService;

    @GetMapping(path = "/api/ship/findByName/{shipName}", produces = "application/json")
    public HttpEntity<List<Ship>> findByName(@NotNull @PathVariable("shipName") String shipName){
        List<Ship> allShips = shipService.findByName(shipName);

        return new ResponseEntity<>(allShips, HttpStatus.OK);
    }

    @GetMapping(path = "/api/ship/findByFreq/{freqList}", produces = "application/json")
    public HttpEntity<List<Ship>> findByFreq(@NotNull @PathVariable("freqList") String freqList){
        String[] freq = freqList.split("&", 5);
        long [] freqN = new long [5];
        for(int i = 0; i < 5; i++) {
            freqN[i] = Integer.parseInt(freq[i]);
        }

        List<Ship> allShips = shipService.findByFreq(freqN);

        return new ResponseEntity<>(allShips, HttpStatus.OK);
    }



}
