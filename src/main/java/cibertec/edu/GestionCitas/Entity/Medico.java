package cibertec.edu.GestionCitas.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Medico")
public class Medico {
    // Datos Generales
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "Especialidad", length = 100, nullable = false)
    private String especialidad;

    @Column(name = "Telefono")
    private String telefono;

    @Column(name = "Email", length = 100, nullable = false)
    private String email;

    // Foreign Key
    @ManyToOne
    @JoinColumn(name = "UsuarioId")
    private Usuario usuario;

    // Navegación
    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cita> citas = new ArrayList<>();

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Diagnostico> diagnosticos = new ArrayList<>();

    public Medico() {
    }

    public Medico(Long id, String nombre, String especialidad, String telefono, String email, Usuario usuario) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.telefono = telefono;
        this.email = email;
        this.usuario = usuario;
    }

    public Medico(Long id, String nombre, String especialidad, String telefono, String email, Usuario usuario,
            List<Cita> citas, List<Diagnostico> diagnosticos) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.telefono = telefono;
        this.email = email;
        this.usuario = usuario;
        this.citas = citas;
        this.diagnosticos = diagnosticos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    public List<Diagnostico> getDiagnosticos() {
        return diagnosticos;
    }

    public void setDiagnosticos(List<Diagnostico> diagnosticos) {
        this.diagnosticos = diagnosticos;
    }
}
