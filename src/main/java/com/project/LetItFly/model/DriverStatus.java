package com.project.LetItFly.model;

import com.project.LetItFly.requestModel.DriverStatusRequest;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "driver_status")
public class DriverStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name = "user_id")
    private User userId;

    @Column(name = "dispatch")
    private boolean dispatch;

    @Column(name = "seat_available")
    private int seatAvailable;

    @Column(name = "curLat")
    private double curLat;

    @Column(name = "curLong")
    private double curLong;

    public DriverStatus(DriverStatusRequest dsRequest) {
        this.id = dsRequest.getId();
        this.dispatch = dsRequest.isDispatch();
        this.seatAvailable = dsRequest.getSeatAvailable();
        this.curLat = dsRequest.getCurLat();
        this.curLong = dsRequest.getCurLong();
    }

}
