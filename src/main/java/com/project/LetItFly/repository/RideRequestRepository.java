package com.project.LetItFly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.LetItFly.model.RideRequest;
import com.project.LetItFly.model.User;

@Repository
public interface RideRequestRepository extends JpaRepository<RideRequest, Integer> {

    RideRequest findRideRequestById(int id);

    RideRequest findRideRequestByPassengerId(User passengerId);

    List<RideRequest> findRideRequestByDriverId(User passengerId);

}
