package cibertec.edu.GestionCitas.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Usuario")
public class Usuario {
    //Datos Generales
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "Email", length = 100, nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "Rol", nullable = false)
    private RolType rol;

    public enum RolType {
        Admin, Medico, Paciente
    }

    //Ingresar al panel
    @Column(name = "Username", length = 100, nullable = false, unique = true)
    private String username;

    @Column(name = "Password", length = 100, nullable = false)
    private String password;

    //Navegación
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Medico> medicos = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Paciente> pacientes = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(Long id, String nombre, String email, RolType rol, String username, String password) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.rol = rol;
        this.username = username;
        this.password = password;
    }

    public Usuario(Long id, String nombre, String email, RolType rol, String username, String password, List<Medico> medicos, List<Paciente> pacientes) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.rol = rol;
        this.username = username;
        this.password = password;
        this.medicos = medicos;
        this.pacientes = pacientes;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RolType getRol() {
        return rol;
    }

    public void setRol(RolType rol) {
        this.rol = rol;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Medico> getMedicos() {
        return medicos;
    }

    public void setMedicos(List<Medico> medicos) {
        this.medicos = medicos;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }
}
