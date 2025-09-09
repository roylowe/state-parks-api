package com.parks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/parks")

public class ParkController {

    @Autowired
    private ParkService parkService;

    @GetMapping
    public ResponseEntity<?> getParksByState(@RequestParam String state) {
        List<Map<String, Object>> parks = parkService.fetchParks(state);
        return ResponseEntity.ok(parks);
    }
}