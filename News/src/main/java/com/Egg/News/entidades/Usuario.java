package com.Egg.News.entidades;

import com.Egg.News.enumeraciones.Rol;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity
@Getter @Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;
    private String email;
    private String password;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaAlta;
    private Boolean activo;

    @Enumerated(EnumType.STRING)
    private Rol rol;
    
    @OneToOne
    private Imagen imagen;

    public Usuario() {
    }

    public Usuario(String email, String password, Rol rol) {
        this.email = email;
        this.password = password;
        this.rol = rol;
    }


    
    

}
