package com.kronos.musica.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Entity
@Table(name = "canciones")
public class Cancion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreCancion;
    private Double duracion;
    private LocalDate fechaLanzamiento;

    @ManyToOne
    private Cantante cantante;

    public Cancion() {
    }

    public Cancion(String nombreCancion, Double duracion, String fechaLanzamiento, Cantante cantante) {
        this.nombreCancion = nombreCancion;
        this.duracion = duracion;

        try {
            this.fechaLanzamiento = LocalDate.parse(fechaLanzamiento);
        }catch (DateTimeParseException e){
            this.fechaLanzamiento = null;
        }

        this.cantante = cantante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCancion() {
        return nombreCancion;
    }

    public void setNombreCancion(String nombreCancion) {
        this.nombreCancion = nombreCancion;
    }

    public Double getDuracion() {
        return duracion;
    }

    public void setDuracion(Double duracion) {
        this.duracion = duracion;
    }

    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public Cantante getCantante() {
        return cantante;
    }

    public void setCantante(Cantante cantante) {
        this.cantante = cantante;
    }

    @Override
    public String toString() {
        return
                "id= " + id +
                ", nombreCancion= '" + nombreCancion + '\'' +
                ", duracion= " + duracion +
                ", fechaLanzamiento= " + fechaLanzamiento +
                ", cantante= " + cantante.getNombre() ;
    }
}
