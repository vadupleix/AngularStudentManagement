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
        String[] freq = freqList.split("&", 8);
        long [] freqN = new long [6];
        for(int i = 0; i < 6; i++) {
            freqN[i] = Integer.parseInt(freq[i]);
        }
        double acc = Double.parseDouble(freq[6]);
        String count = freq[7];
        List<Ship> allShips = shipService.findByFreq(freqN, acc, count);

        return new ResponseEntity<>(allShips, HttpStatus.OK);
    }

    @PostMapping(path = "/api/ship/postShip", produces = "application/json")
    public HttpStatus postShip(@RequestBody @NotNull Ship ship) {
        try {
            shipService.addShip(ship);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }


}
