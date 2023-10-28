package com.project.LetItFly.service;

import java.util.List;

import com.project.LetItFly.model.DriverStatus;
import com.project.LetItFly.model.User;
import com.project.LetItFly.requestModel.DriverStatusRequest;

public interface DriverStatusService {

    public List<DriverStatus> findAllDriverStatus();

    public DriverStatus findDriverStatusById(int id);

    public DriverStatus findDriverStatusByUserId(int userId);

    // save
    public String save(DriverStatusRequest dsRequest);

    // update current coordinates of driver
    public String updateCoordinatesDriver(int driverId, double curLat, double curLong);

    // delete
    public String deleteByDriverId(int driverId);

    // upadte dispatch status of driver
    public String updateDispatchStatus(int driverId, boolean dispatch);

}
