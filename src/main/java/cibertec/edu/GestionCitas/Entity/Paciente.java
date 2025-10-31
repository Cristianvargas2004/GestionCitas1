package cibertec.edu.GestionCitas.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Paciente")
public class Paciente {
    // Datos Generales
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "Apellidos", length = 100, nullable = false)
    private String apellidos;

    @Column(name = "DNI", length = 8, nullable = false, unique = true)
    private String dni;

    @Column(name = "Email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "Telefono", length = 9, nullable = false)
    private String telefono;

    // Ingresar al panel
    @Column(name = "Username", length = 100, nullable = false, unique = true)
    private String username;

    // Foreign Key
    @ManyToOne
    @JoinColumn(name = "UsuarioId")
    private Usuario usuario;

    // Navegación
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cita> citas = new ArrayList<>();

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Diagnostico> diagnosticos = new ArrayList<>();

    public Paciente() {
    }

    public Paciente(Long id, String nombre, String apellidos, String dni, String email, String telefono,
            String username, Usuario usuario) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.email = email;
        this.telefono = telefono;
        this.username = username;
        this.usuario = usuario;
    }

    public Paciente(Long id, String nombre, String apellidos, String dni, String email, String telefono,
            String username, Usuario usuario, List<Cita> citas, List<Diagnostico> diagnosticos) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.email = email;
        this.telefono = telefono;
        this.username = username;
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
