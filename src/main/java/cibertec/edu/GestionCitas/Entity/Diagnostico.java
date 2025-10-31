package cibertec.edu.GestionCitas.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Diagnostico")
public class Diagnostico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "Diagnostico", nullable = false)
    private String diagnostico;

    @Column(name = "Tratamiento", nullable = false)
    private String tratamiento;

    // Foreign Keys
    @ManyToOne
    @JoinColumn(name = "PacienteId")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "MedicoId")
    private Medico medico;

    public Diagnostico() {
    }

    public Diagnostico(Long id, LocalDate fecha, String diagnostico, String tratamiento, Paciente paciente,
            Medico medico) {
        this.id = id;
        this.fecha = fecha;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
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

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
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
