package com.maintrot.basya.mappers.impl;

import com.maintrot.basya.dtoes.AppointmentRequest;
import com.maintrot.basya.dtoes.AppointmentResponse;
import com.maintrot.basya.enums.AppointmentStatus;
import com.maintrot.basya.mappers.AppointmentMapper;
import com.maintrot.basya.models.Appointment;
import com.maintrot.basya.models.SalonService;
import com.maintrot.basya.models.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class AppointmentMapperImpl implements AppointmentMapper {

    @Override
    public AppointmentResponse toResponse(Appointment appointment) {
        AppointmentResponse appointmentResponse = new AppointmentResponse();
        appointmentResponse.setId(appointment.getId());
        appointmentResponse.setClientId(appointment.getClient().getId());
        appointmentResponse.setMasterId(appointment.getMaster().getId());
        appointmentResponse.setDate(appointment.getDate().toString());
        appointmentResponse.setTime(appointment.getTime().toString());
        appointmentResponse.setStatus(appointment.getStatus().name());
        appointmentResponse.setPhoto(appointment.getPhoto());
        appointmentResponse.setText(appointment.getText());
        appointmentResponse.setFeedback(appointment.getFeedback());
        return appointmentResponse;
    }

    @Override
    public Appointment toEntity(AppointmentRequest appointmentRequest) {
        Appointment appointment = new Appointment();
        User client = new User();
        client.setId(appointmentRequest.getClientId());
        appointment.setClient(client);
        User master = new User();
        master.setId(appointmentRequest.getMasterId());
        appointment.setMaster(master);
        SalonService salonService = new SalonService();
        salonService.setId(appointmentRequest.getServiceId());
        appointment.setSalonService(salonService);
        appointment.setDate(LocalDate.parse(appointmentRequest.getDate()));
        appointment.setTime(LocalTime.parse(appointmentRequest.getTime()));
        appointment.setStatus(AppointmentStatus.SCHEDULED);
        appointment.setPhoto(appointmentRequest.getPhoto());
        appointment.setText(appointmentRequest.getText());
        return appointment;
    }
}
