package cibertec.edu.GestionCitas.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Cita")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "Hora", nullable = false)
    private LocalTime hora;

    @Column(name = "Observaciones")
    private String observaciones;

    @Enumerated(EnumType.STRING)
    @Column(name = "Estado", nullable = false)
    private EstadoCita estado;

    public enum EstadoCita {
        Pendiente, Confirmada,  Cancelada,
    }

    //Foreign Keys
    @ManyToOne
    @JoinColumn(name = "PacienteId")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "MedicoId")
    private Medico medico;

    public Cita() {
    }

    public Cita(Long id, LocalDate fecha, LocalTime hora, String observaciones, EstadoCita estado, Paciente paciente, Medico medico) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.observaciones = observaciones;
        this.estado = estado;
        this.paciente = paciente;
        this.medico = medico;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public EstadoCita getEstado() {
        return estado;
    }

    public void setEstado(EstadoCita estado) {
        this.estado = estado;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
}
