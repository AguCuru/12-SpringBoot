package com.Egg.News.entidades;

import com.Egg.News.enumeraciones.Rol;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Periodista extends Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private ArrayList<Noticia> noticias;
    private Integer sueldoMensual;

    public Periodista(Long id, ArrayList<Noticia> noticias, Integer sueldoMensual, String email, String password, Rol rol) {
        super(email, password, rol);
        this.id = id;
        this.noticias = noticias;
        this.sueldoMensual = sueldoMensual;
    }



    public ArrayList<Noticia> getNoticias() {
        return noticias;
    }

    public void setNoticias(ArrayList<Noticia> noticias) {
        this.noticias = noticias;
    }

    public Integer getSueldoMensual() {
        return sueldoMensual;
    }

    public void setSueldoMensual(Integer sueldoMensual) {
        this.sueldoMensual = sueldoMensual;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    
    

}
