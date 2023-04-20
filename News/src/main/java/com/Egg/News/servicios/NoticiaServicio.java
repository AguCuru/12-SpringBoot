
package com.Egg.News.servicios;


import com.Egg.News.entidades.Noticia;
import com.Egg.News.excepciones.MiException;
import com.Egg.News.repositorios.NoticiaRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NoticiaServicio {


    @Autowired
    private NoticiaRepositorio noticiaRepositorio;
    
    @Transactional
    public void crearNoticia(String titulo, String cuerpo, Noticia creador) throws MiException {
        
        validar(titulo,cuerpo,creador);
        
        Noticia noticia = new Noticia();
        noticia.setTitulo(titulo);
        noticia.setCuerpo(cuerpo);
        noticia.setCreador(creador.getCreador());

        noticiaRepositorio.save(noticia);

    }

    public List<Noticia> listarNoticias() {

        List<Noticia> noticias = new ArrayList();

        noticias = noticiaRepositorio.findAll();


        return noticias;
    }
    
    public void leer (Long id) {
        
        Noticia noticia = noticiaRepositorio.getById(id);
        
        
    }
    
    
    


    
    
    public void modificarNoticia(Long id, String titulo, String cuerpo, Noticia creador ) throws MiException{
        
        validar(titulo,cuerpo,creador);
        
        Optional<Noticia> respuesta = noticiaRepositorio.findById(id);
        
        
        if(respuesta.isPresent()){
            Noticia noticia = respuesta.get();
            
            noticia.setTitulo(titulo);
            
            noticia.setCuerpo(cuerpo);
            
            noticia.setCreador(creador.getCreador());
            
            noticiaRepositorio.save(noticia);
            
        }
    }
    
    public void eliminar(Long id) {
        
        Noticia noticia = noticiaRepositorio.getById(id);
        noticiaRepositorio.delete(noticia);
    }
            
    
    public Noticia getOne(Long id){
        return noticiaRepositorio.getOne(id);
    }
    
    private void validar(String titulo, String cuerpo, Noticia creador) throws MiException {
        
        if (titulo == null || titulo.isEmpty() & cuerpo.isEmpty() || cuerpo == null ) {
            throw new MiException("El titulo y el cuerpo no pueden estar vacios");
        }        

        if (titulo == null || titulo.isEmpty() ) {
            throw new MiException("El titulo no puede estar vacio");
        }

        if (cuerpo.isEmpty() || cuerpo == null) {
            throw new MiException("El cuerpo no puede estar vacio");
        }
        
        if (creador == null) {
            throw new MiException("Indique el creador de la noticia");
        }
        
        

    }
}
