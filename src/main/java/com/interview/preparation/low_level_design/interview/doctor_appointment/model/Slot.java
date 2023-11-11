package com.interview.preparation.low_level_design.interview.doctor_appointment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Slot {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Override
    public boolean equals(Object o){
        Slot s = (Slot) o;
        return this.startTime.equals(s.startTime) &&
                this.endTime.equals(s.endTime);
    }
}
