package com.project.LetItFly.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.LetItFly.model.DriverStatus;
import com.project.LetItFly.model.User;
import com.project.LetItFly.repository.DriverStatusRepository;
import com.project.LetItFly.repository.UserRepository;
import com.project.LetItFly.requestModel.DriverStatusRequest;

@Service
public class DriverStatusServiceImpl implements DriverStatusService {
    private DriverStatusRepository driverStatusRepository;
    private UserRepository userRepository;

    @Autowired
    public DriverStatusServiceImpl(DriverStatusRepository driverStatusRepository, UserRepository userRepository) {
        this.driverStatusRepository = driverStatusRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<DriverStatus> findAllDriverStatus() {
        return driverStatusRepository.findAll();
    }

    @Override
    public DriverStatus findDriverStatusById(int id) {
        return driverStatusRepository.findDriverStatusById(id);
    }

    @Override
    public DriverStatus findDriverStatusByUserId(int userId) {
        // retrieve User object
        User user = userRepository.findUserById(userId);

        if (user == null) {
            return null;
        }

        return driverStatusRepository.findDriverStatusByUserId(user);
    }

    @Override
    public String save(DriverStatusRequest dsRequest) {

        // retrieve the driver object
        User driver = userRepository.findUserById(dsRequest.getUserId());

        // check if there's already a record about this driver in db
        // set new coordinates if existed
        DriverStatus ds = driverStatusRepository.findDriverStatusByUserId(driver);
        if (ds != null) {
            ds.setCurLat(dsRequest.getCurLat());
            ds.setCurLong(dsRequest.getCurLong());

        } else {

            // convert the request object to driverstatus object
            ds = new DriverStatus(dsRequest);
            // associate driver to driver status object
            ds.setUserId(driver);
        }

        driverStatusRepository.save(ds);
        return "SUCCESS";
    }

    @Override
    public String deleteByDriverId(int driverId) {
        // retrieve driver object
        User driver = userRepository.findUserById(driverId);

        DriverStatus driverStatus = driverStatusRepository.findDriverStatusByUserId(driver);

        // check if there's a driver status object
        if (driverStatus == null) {
            return "NOT EXIST";
        } else {
            driverStatusRepository.delete(driverStatus);
            return "SUCCESS";
        }

    }

    @Override
    public String updateCoordinatesDriver(int driverId, double curLat, double curLong) {
        // retrieve driver object
        User driver = userRepository.findUserById(driverId);

        // retrieve driver status object
        DriverStatus driverStatus = driverStatusRepository.findDriverStatusByUserId(driver);

        // check if existed
        if (driverStatus == null) {
            return "NOT EXIST";
        } else {
            // update and update to db
            driverStatus.setCurLat(curLat);
            driverStatus.setCurLong(curLong);
            driverStatusRepository.save(driverStatus);
            return "SUCCESS";
        }

    }

    @Override
    public String updateDispatchStatus(int driverId, boolean dispatch) {
        // retrieve driver object
        User driver = userRepository.findUserById(driverId);

        // retrieve driver status object
        DriverStatus driverStatus = driverStatusRepository.findDriverStatusByUserId(driver);

        if (driverStatus == null) {
            return "NOT EXIST";
        } else {
            // update dispatch
            driverStatus.setDispatch(dispatch);
            driverStatusRepository.save(driverStatus);
            return "SUCCESS";
        
        }

    }

}
