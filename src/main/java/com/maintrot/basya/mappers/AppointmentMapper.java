package com.maintrot.basya.mappers;

import com.maintrot.basya.dtoes.AppointmentRequest;
import com.maintrot.basya.dtoes.AppointmentResponse;
import com.maintrot.basya.models.Appointment;

public interface AppointmentMapper {
    AppointmentResponse toResponse(Appointment appointment);
    Appointment toEntity(AppointmentRequest appointmentRequest);
}
