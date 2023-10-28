package com.project.LetItFly.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.LetItFly.model.DriverStatus;
import com.project.LetItFly.requestModel.DriverStatusRequest;
import com.project.LetItFly.service.DriverStatusService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/driver-status")
public class DriverStatusController {

    private final DriverStatusService driverStatusService;

    @GetMapping("/findById")
    public ResponseEntity<DriverStatus> findById(@RequestParam("id") int id) {
        DriverStatus ds = driverStatusService.findDriverStatusById(id);
        return ResponseEntity.ok(ds);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<DriverStatus>> findAll() {
        List<DriverStatus> ds = driverStatusService.findAllDriverStatus();
        return ResponseEntity.ok(ds);
    }

    @GetMapping("/findByDriverId")
    public ResponseEntity<DriverStatus> findByDriverId(@RequestParam("driverId") int driverId) {
        DriverStatus ds = driverStatusService.findDriverStatusByUserId(driverId);
        return ResponseEntity.ok(ds);
    }

    // save to db
    // return: "SUCCESS"
    @PostMapping("/save")
    public String save(@RequestBody DriverStatusRequest driverStatusRequest) {
        return driverStatusService.save(driverStatusRequest);
    }

    // delete
    // return: "SUCCESS" or "NOT EXIST" (no record with that driver id)
    @GetMapping("/deleteByDriverId")
    public String deleteByDriverId(@RequestParam("driverId") int driverId) {
        return driverStatusService.deleteByDriverId(driverId);
    }

    // update current coordinates
    @GetMapping("/updateCoordinatesDriver")
    public String updateCoordinatesDriver(@RequestParam("driverId") int driverId,
            @RequestParam("curLat") double curLat, @RequestParam("curLong") double curLong) {
        return driverStatusService.updateCoordinatesDriver(driverId, curLat, curLong);
    }

    // update dispatch status
    // it will be updated to TRUE automatically through the process of adding driver
    // to a ride request
    // this request is intentionally for setting it back to FALSE after ride
    // completed
    // return: "SUCCESS" or "NOT EXIST" (no record of driver status for that driver)
    @GetMapping("updateDispatchStatus")
    public String updateDispatchStatus(@RequestParam("driverId") int driverId,
            @RequestParam("dispatch") boolean dispatch) {
        return driverStatusService.updateDispatchStatus(driverId, dispatch);
    }
}
