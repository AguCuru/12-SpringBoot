package com.Egg.News.entidades;

import com.Egg.News.enumeraciones.Rol;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Administrador extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Administrador(Long id, String email, String password, Rol rol) {
        super(email, password, rol);
        this.id = id;
    }
    

    public Administrador(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    
}
