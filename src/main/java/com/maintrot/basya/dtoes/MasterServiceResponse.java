package com.maintrot.basya.dtoes;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class MasterServiceResponse {
    private Long id;
    private Long masterId;
    private Long salonServiceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMasterId() {
        return masterId;
    }

    public void setMasterId(Long masterId) {
        this.masterId = masterId;
    }

    public Long getSalonServiceId() {
        return salonServiceId;
    }

    public void setSalonServiceId(Long salonServiceId) {
        this.salonServiceId = salonServiceId;
    }
}
