package com.example.practicajuego21;

public class Persona {
    private String nombre;
    private Integer id;
    private Integer numero;
    private String created_at;
    private String update_at;

    public Persona(String nombre, Integer id, Integer numero, String created_at, String update_at) {
        this.nombre = nombre;
        this.id = id;
        this.numero = numero;
        this.created_at = created_at;
        this.update_at = update_at;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(String update_at) {
        this.update_at = update_at;
    }
}
