package com.maintrot.basya.dtoes;

public class MasterServiceRequest {
    private Long masterId;
    private Long salonServiceId;

    public MasterServiceRequest() {}

    public MasterServiceRequest(Long masterId, Long salonServiceId) {
        this.masterId = masterId;
        this.salonServiceId = salonServiceId;
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
