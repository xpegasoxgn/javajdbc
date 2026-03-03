package model;

public class Usuario {
    private Long id;
    private String nombre;
    private String email;
    private Long departamentoId;
    private String departamentoNombre;


    
    public Usuario() {
    }
    public Usuario(Long id, String nome, String email) {
        this.id = id;
        this.nombre = nome;
        this.email = email;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nombre;
    }
    public void setNome(String nome) {
        this.nombre = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(Long departamentoId) {
        this.departamentoId = departamentoId;
    }

    public String getDepartamentoNombre() {
        return departamentoNombre;
    }

    public void setDepartamentoNombre(String departamentoNombre) {
        this.departamentoNombre = departamentoNombre;
    }
    
}
