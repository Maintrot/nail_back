package com.maintrot.basya.services.impl;

import com.maintrot.basya.dtoes.AppointmentRequest;
import com.maintrot.basya.dtoes.AppointmentResponse;
import com.maintrot.basya.mappers.AppointmentMapper;
import com.maintrot.basya.models.Appointment;
import com.maintrot.basya.models.SalonService;
import com.maintrot.basya.models.User;
import com.maintrot.basya.repositories.AppointmentRepository;
import com.maintrot.basya.repositories.SalonServiceRepository;
import com.maintrot.basya.repositories.UserRepository;
import com.maintrot.basya.services.AppointmentService;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private final SalonServiceRepository salonServiceRepository;
    private final UserRepository userRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper, SalonServiceRepository salonServiceRepository, UserRepository userRepository) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
        this.salonServiceRepository = salonServiceRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AppointmentResponse createAppointment(AppointmentRequest appointmentRequest) {
        User master = userRepository.findById(appointmentRequest.getMasterId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!"USER_MASTER".equals(master.getRole())) {
            throw new RuntimeException("User is not a master");
        }
        User client = userRepository.findById(appointmentRequest.getClientId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!"USER_CLIENT".equals(master.getRole())) {
            throw new RuntimeException("User is not a client");
        }
        SalonService salonService = salonServiceRepository.findById(appointmentRequest.getServiceId())
                .orElseThrow(() -> new RuntimeException("Salon service not found"));
        Appointment appointment = appointmentMapper.toEntity(appointmentRequest);
        appointment.setMaster(master);
        appointment.setClient(client);
        appointment.setSalonService(salonService);
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return appointmentMapper.toResponse(savedAppointment);
    }

    @Override
    public AppointmentResponse getAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        return appointmentMapper.toResponse(appointment);
    }

    @Override
    public List<AppointmentResponse> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream()
                .map(appointmentMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentResponse updateAppointment(Long id, AppointmentRequest appointmentRequest) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        if (appointmentRequest.getClientId() != null) {
            User client = userRepository.findById(appointmentRequest.getMasterId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            if (!"USER_CLIENT".equals(client.getRole())) {
                throw new RuntimeException("User is not a client");
            }
            appointment.setClient(client);
        }
        if (appointmentRequest.getMasterId() != null) {
            User master = userRepository.findById(appointmentRequest.getMasterId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            if (!"USER_MASTER".equals(master.getRole())) {
                throw new RuntimeException("User is not a master");
            }
            appointment.setMaster(master);
        }
        if (appointmentRequest.getServiceId() != null) {
            SalonService salonService = salonServiceRepository.findById(appointmentRequest.getServiceId())
                    .orElseThrow(() -> new RuntimeException("Salon service not found"));
            appointment.setSalonService(salonService);
        }
        if (appointmentRequest.getDate() != null) {
            appointment.setDate(java.time.LocalDate.parse(appointmentRequest.getDate()));
        }
        if (appointmentRequest.getTime() != null) {
            appointment.setTime(java.time.LocalTime.parse(appointmentRequest.getTime()));
        }
        appointment.setPhoto(appointmentRequest.getPhoto());
        appointment.setText(appointmentRequest.getText());
        Appointment updatedAppointment = appointmentRepository.save(appointment);
        return appointmentMapper.toResponse(updatedAppointment);
    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}
