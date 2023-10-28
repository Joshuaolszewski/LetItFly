package com.project.LetItFly.requestModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DriverStatusRequest {

    private int id;
    private int userId;
    private boolean dispatch;
    private int seatAvailable;
    private double curLat;
    private double curLong;

}
