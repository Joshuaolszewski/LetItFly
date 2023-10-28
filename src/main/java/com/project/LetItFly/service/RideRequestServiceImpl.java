package com.project.LetItFly.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.LetItFly.model.DriverStatus;
import com.project.LetItFly.model.RideRequest;
import com.project.LetItFly.model.User;
import com.project.LetItFly.repository.DriverStatusRepository;
import com.project.LetItFly.repository.RideRequestRepository;
import com.project.LetItFly.repository.UserRepository;
import com.project.LetItFly.requestModel.RideRequestRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl implements RideRequestService {

    private final RideRequestRepository rideRequestRepository;
    private final UserRepository userRepository;
    private final DriverStatusRepository driverStatusRepository;

    @Override
    public RideRequest findRideRequestById(int id) {
        return rideRequestRepository.findRideRequestById(id);
    }

    @Override
    public List<RideRequest> findAllRideRequest() {
        return rideRequestRepository.findAll();
    }

    @Override
    public int save(RideRequestRequest rideRequestRequest) {
        RideRequest rideRequest = new RideRequest(rideRequestRequest);

        // find passenger object from passenger id
        User user = userRepository.findUserById(rideRequestRequest.getPassengerId());

        // associate passenger object to the ride request
        rideRequest.setPassengerId(user);

        try {
            RideRequest rq = rideRequestRepository.save(rideRequest);
            return rq.getId();
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public String delete(int id) {
        rideRequestRepository.deleteById(id);
        return "SUCCESS";
    }

    // @Override
    public RideRequest findRideRequestByPassengerId(int passengerId) {
        // retrieve the passenger object first
        User passenger = userRepository.findUserById(passengerId);

        return rideRequestRepository.findRideRequestByPassengerId(passenger);
    }

    @Override
    public List<RideRequest> findRideRequestByDriverId(int driverId) {
        // retrieve the passenger object first
        User driver = userRepository.findUserById(driverId);

        return rideRequestRepository.findRideRequestByDriverId(driver);
    }

    @Override
    public String updateCoordinatesPassenger(int passengerId, double curLat, double curLong) {

        // retrieve passenger object
        User passenger = userRepository.findUserById(passengerId);

        // retrive the ride that the passenger currently requesting
        RideRequest rideRequest = rideRequestRepository.findRideRequestByPassengerId(passenger);

        // update coords
        if (rideRequest == null) {
            return "NOT EXIST";
        } else {
            rideRequest.setCurLat(curLat);
            rideRequest.setCurLong(curLong);
        }

        // save to db
        rideRequestRepository.save(rideRequest);
        return "SUCCESS";
    }

    @Override
    public String setDriverToRideRequest(int driverId, int rideId) {

        // retrieve driver object
        User driver = userRepository.findUserById(driverId);

        // retrieve ride request object
        RideRequest rideRequest = rideRequestRepository.findRideRequestById(rideId);

        // set driver to the ride request
        rideRequest.setDriverId(driver);

        // set start attribute to TRUE
        rideRequest.setStart(true);

        // update db
        rideRequestRepository.save(rideRequest);

        // set dispatch status of driver to TRUE
        DriverStatus driverStatus = driverStatusRepository.findDriverStatusByUserId(driver);
        driverStatus.setDispatch(true);
        driverStatusRepository.save(driverStatus);

        return "SUCCESS";
    }

    @Override
    public int getDriverIdOfRideRequest(int passengerId) {

        // retrieve passenger object
        User passenger = userRepository.findUserById(passengerId);

        // retrieve the ride request object
        RideRequest rideRequest = rideRequestRepository.findRideRequestByPassengerId(passenger);

        // check if there's a driver
        if (rideRequest.getDriverId() != null) {
            return rideRequest.getDriverId().getId();
        } else {
            return -1;
        }
    }

    @Override
    public String deleteByPassengerId(int passengerId) {
        // retrieve passenger id
        User passenger = userRepository.findUserById(passengerId);

        // retrieve ride request
        RideRequest rideRequest = rideRequestRepository.findRideRequestByPassengerId(passenger);

        if (rideRequest == null) {
            return "NOT EXIST";
        } else {
            rideRequestRepository.delete(rideRequest);
            return "SUCCESS";
        }
    }

}
