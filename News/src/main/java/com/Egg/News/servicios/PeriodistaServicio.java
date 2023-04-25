package com.Egg.News.servicios;

import com.Egg.News.entidades.Imagen;
import com.Egg.News.entidades.Periodista;
import com.Egg.News.enumeraciones.Rol;
import com.Egg.News.excepciones.MiException;
import com.Egg.News.repositorios.PeriodistaRepositorio;
import com.Egg.News.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PeriodistaServicio {

    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @Autowired 
    private ImagenServicio imagenServicio;
    
    @Autowired
    private PeriodistaRepositorio periodistaRepositorio;    

    
    @Transactional
    public List<Periodista> listarPeriodistas() {

        List<Periodista> periodistas = new ArrayList();

        periodistas = periodistaRepositorio.findAll();

        return periodistas;
    }    
}

