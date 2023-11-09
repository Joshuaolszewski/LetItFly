package com.project.LetItFly.model;

import com.project.LetItFly.requestModel.RideRequestRequest;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ride_request")
public class RideRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "cur_lat")
    private double curLat;

    @Column(name = "cur_long")
    private double curLong;

    @Column(name = "dest_lat")
    private double destLat;

    @Column(name = "dest_long")
    private double destLong;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name = "passenger_id")
    private User passengerId;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name = "driver_id")
    private User driverId;

    @Column(name = "date")
    private String date;

    @Column(name = "time_request")
    private String timeRequest;

    @Column(name = "distance")
    private String distance;

    @Column(name = "duration")
    private String duration;

    @Column(name = "cost")
    private double cost;

    @Column(name = "pickup_location")
    private String pickupLocation;

    @Column(name = "destination")
    private String destination;

    @Column(name = "start")
    private boolean start; // default false

    public RideRequest(RideRequestRequest r) {
        this.id = r.getId();
        this.curLat = r.getCurLat();
        this.curLong = r.getCurLong();
        this.destLat = r.getDestLat();
        this.destLong = r.getDestLong();
        this.date = r.getDate();
        this.timeRequest = r.getTimeRequest();
        this.distance = r.getDistance();
        this.duration = r.getDuration();
        this.cost = r.getCost();
        this.pickupLocation = r.getPickupLocation();
        this.destination = r.getDestination();
        this.start = r.isStart();
    }

}
