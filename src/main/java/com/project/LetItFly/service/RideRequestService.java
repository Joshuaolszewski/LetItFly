package com.project.LetItFly.service;

import java.util.List;

import com.project.LetItFly.model.RideRequest;
import com.project.LetItFly.requestModel.RideRequestRequest;

public interface RideRequestService {

    public RideRequest findRideRequestById(int id);

    public List<RideRequest> findAllRideRequest();

    public int save(RideRequestRequest rideRequestRequest);

    public String delete(int id);

    public RideRequest findRideRequestByPassengerId(int passengerId);

    public List<RideRequest> findRideRequestByDriverId(int driverId);

    public String updateCoordinatesPassenger(int passengerId, double curLat, double curLong);

    public String setDriverToRideRequest(int driverId, int rideId);

    public int getDriverIdOfRideRequest(int passengerId);

    public String deleteByPassengerId(int passengerId);
}
