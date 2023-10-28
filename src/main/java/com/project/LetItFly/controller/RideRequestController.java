package com.project.LetItFly.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.LetItFly.model.RideRequest;
import com.project.LetItFly.requestModel.RideRequestRequest;
import com.project.LetItFly.service.RideRequestService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ride-request")
public class RideRequestController {

    private final RideRequestService rideRequestService;

    @GetMapping("/findById")
    public ResponseEntity<RideRequest> findById(@RequestParam("id") int id) {
        RideRequest rideRequest = rideRequestService.findRideRequestById(id);
        return ResponseEntity.ok(rideRequest);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<RideRequest>> findAll() {
        List<RideRequest> rideRequests = rideRequestService.findAllRideRequest();
        return ResponseEntity.ok(rideRequests);

    }

    // return the id of the ride request
    // -1: if failed saving the ride request
    @PostMapping("/save")
    public int save(@RequestBody RideRequestRequest rideRequestRequest) {
        return rideRequestService.save(rideRequestRequest);
    }

    // id of the ride request
    // return: "SUCCESS"
    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        return rideRequestService.delete(id);
    }

    // id of the passenger
    // return: "SUCCESS" or " NOT EXIST" (if no current ride request from passenger)
    @GetMapping("/deleteByPassengerId")
    public String deleteByPassengerId(@RequestParam("passengerId") int passengerId) {
        return rideRequestService.deleteByPassengerId(passengerId);
    }

    // find current ride request by passenger id
    @GetMapping("/findByPassengerId")
    public ResponseEntity<RideRequest> findByPassengerId(@RequestParam("id") int id) {
        RideRequest rideRequest = rideRequestService.findRideRequestByPassengerId(id);
        return ResponseEntity.ok(rideRequest);
    }

    // find current rides taken by driver id
    @GetMapping("/findByDriverId")
    public ResponseEntity<List<RideRequest>> findByDriverId(@RequestParam("id") int id) {
        List<RideRequest> rideRequests = rideRequestService.findRideRequestByDriverId(id);
        return ResponseEntity.ok(rideRequests);
    }

    // update current coordinates of passenger
    // return: "SUCCESS" or "NOT EXIST" (passenger doesn't have ride request)
    @GetMapping("/updateCoordinatesPassenger")
    public String updateCoordinatesPassenger(@RequestParam("passengerId") int passengerId,
            @RequestParam("curLat") double curLat,
            @RequestParam("curLong") double curLong) {
        return rideRequestService.updateCoordinatesPassenger(passengerId, curLat, curLong);
    }

    // get current coordinates of passenger
    // Ride request object has them. Just retrieve the object.

    // add the driver id to the ride (the one accepts the ride)
    // this will also set the start attribute of the ride to TRUE
    // this will also set the dispatch attribute of driver status object to TRUE
    // return: "SUCCESS"
    @GetMapping("/setDriverToRideRequest")
    public String setDriverToRideRequest(@RequestParam("driverId") int driverId, @RequestParam("rideId") int rideId) {
        return rideRequestService.setDriverToRideRequest(driverId, rideId);
    }

    // get driver id of the ride request
    // -1: if no driver has accepted the ride yet
    @GetMapping("/getDriverIdOfRideRequest")
    public int getDriverIdOfRideRequest(@RequestParam("passengerId") int passengerId) {
        return rideRequestService.getDriverIdOfRideRequest(passengerId);
    }
}
