
package com.Egg.News.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Noticia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String cuerpo;
    
//    @ManyToOne
//    private Periodista creador;

    public Noticia() {
    }

    public Noticia(Long id, String titulo, String cuerpo) {
        this.id = id;
        this.titulo = titulo;
        this.cuerpo = cuerpo;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

//    public Periodista getCreador() {
//        return creador;
//    }
//
//    public void setCreador(Periodista creador) {
//        this.creador = creador;
//    }
    
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Noticia{");
        sb.append("titulo=").append(titulo);
        sb.append(", cuerpo=").append(cuerpo);
        sb.append('}');
        return sb.toString();
    }
    
    
    
    
}
