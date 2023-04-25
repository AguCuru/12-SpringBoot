package com.Egg.News.servicios;

import com.Egg.News.entidades.Periodista;
import com.Egg.News.entidades.Usuario;
import com.Egg.News.repositorios.UsuarioRepositorio;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministradorServicio {
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @Transactional
    public void sueldoMensualPeriodista(Integer sueldoMensual){
        Periodista periodista = new Periodista();
        
        periodista.setSueldoMensual(sueldoMensual);
    }
}
