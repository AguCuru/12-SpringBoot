package com.Egg.News.controladores;

import com.Egg.News.entidades.Periodista;
import com.Egg.News.entidades.Usuario;
import com.Egg.News.servicios.NoticiaServicio;
import com.Egg.News.servicios.PeriodistaServicio;
import com.Egg.News.servicios.UsuarioServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminControlador {

    @Autowired
    private NoticiaServicio noticiaServicio;
    
    @Autowired
    private UsuarioServicio usuarioServicio;    
    
    @Autowired
    private PeriodistaServicio periodistaServicio;     

    @GetMapping("/dashboard")
    public String panelAministrativo(ModelMap modelo) {

        return "panel.html";
    } 
   @GetMapping("/usuarios")
    public String listar(ModelMap modelo) {
        List<Usuario> usuarios = usuarioServicio.listarUsuarios();
        modelo.addAttribute("usuarios", usuarios);

        return "usuario_list";
    }
    
    @GetMapping("/modificarRol/{id}")
    public String cambiarRol(@PathVariable Long id){
        usuarioServicio.cambiarRol(id);
        
       return "redirect:/admin/usuarios";
    }
    
   @GetMapping("/periodistas")
    public String listarPeriodistas(ModelMap modelo) {
        List<Periodista> periodistas = periodistaServicio.listarPeriodistas();
        modelo.addAttribute("periodistas", periodistas);

        return "periodista_list";
    }    

}
