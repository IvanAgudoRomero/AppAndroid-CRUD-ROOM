package com.example.trabajomovilesroom.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    public int id;

    @ColumnInfo(name="nombre")
    public String nombre;

    @ColumnInfo(name="password")
    public String password;

    @ColumnInfo(name="puntos")
    public int puntos;

    public User() {
    }

    public User(int id, String nombre, String password, int puntos) {
        this.id = id;
        this.nombre = nombre;
        this.password = password;
        this.puntos = puntos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}
