package com.project.LetItFly.requestModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RideRequestRequest {

    private int id;
    private double curLat;
    private double curLong;
    private double destLat;
    private double destLong;
    private int passengerId;
    private int driverId;
    private String date;
    private String timeRequest;
    private String distance;
    private String duration;
    private double cost;
    private String pickupLocation;
    private String destination;
    private boolean start; // default: false if not passed in
}
