package com.maintrot.basya.services;

import com.maintrot.basya.dtoes.AppointmentRequest;
import com.maintrot.basya.dtoes.AppointmentResponse;

import java.util.List;

public interface AppointmentService {
    AppointmentResponse createAppointment(AppointmentRequest appointmentRequest);
    AppointmentResponse getAppointment(Long id);
    List<AppointmentResponse> getAllAppointments();
    AppointmentResponse updateAppointment(Long id, AppointmentRequest appointmentRequest);
    void deleteAppointment(Long id);
}
