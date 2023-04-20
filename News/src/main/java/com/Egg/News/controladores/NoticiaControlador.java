package com.Egg.News.controladores;

import com.Egg.News.entidades.Noticia;
import com.Egg.News.excepciones.MiException;
import com.Egg.News.servicios.NoticiaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/noticia") //localhost:8080/noticia
public class NoticiaControlador {

    @Autowired
    private NoticiaServicio noticiaServicio;

    @GetMapping("/lista")
    public String lista( ModelMap modelo) {

        List<Noticia> noticias = noticiaServicio.listarNoticias();

        modelo.addAttribute("noticias", noticias);

        return "noticia_list.html";
    }
    
        @GetMapping("/lista/{id}")
    public String lista(@PathVariable Long id, ModelMap modelo) {

        Noticia noticia = noticiaServicio.getOne(id);
        modelo.addAttribute("noticia", noticia);

        return "noticia_cuerpo.html";
    }

    @GetMapping("/inicio")
    public String inicio(ModelMap modelo) {
//
//        List<Noticia> noticias = noticiaServicio.listarNoticias();
//
//        modelo.addAttribute("noticias", noticias);

        return "inicio.html";
    }
    
        @GetMapping("/leer")
    public String leer(ModelMap modelo) {

        List<Noticia> noticias = noticiaServicio.listarNoticias();

        modelo.addAttribute("noticias", noticias);

        return "leer.html";
    }

    @GetMapping("/inicio/{id}")
    public String inicio(@PathVariable Long id, ModelMap modelo) {
        Noticia noticia = noticiaServicio.getOne(id);
        modelo.addAttribute("noticia", noticia);
        return "noticia_cuerpo.html";
    }

    @GetMapping("/crearNoti") //localhost:8080/noticia/crearNoti
    public String crearNoti(ModelMap modelo) {

        List<Noticia> noticias = noticiaServicio.listarNoticias();

        modelo.addAttribute("noticias", noticias);

        return "noticia_form.html";
    }

    @PostMapping("/crear")
    public String crear(@RequestParam String titulo, @RequestParam String cuerpo, @RequestParam Noticia creador, ModelMap modelo) {

        try {
            noticiaServicio.crearNoticia(titulo, cuerpo, creador);

            modelo.put("exito", "La noticia se guardo correctamente!");

        } catch (MiException ex) {

            List<Noticia> noticias = noticiaServicio.listarNoticias();

            modelo.addAttribute("noticias", noticias);

            modelo.put("error", ex.getMessage());

            return "noticia_form.html";
        }
        return "noticia_form.html";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable Long id, ModelMap modelo) {
        modelo.put("noticia", noticiaServicio.getOne(id));
        return "noticia_modificar.html";
    }

    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable Long id, String titulo, String cuerpo,Noticia creador, ModelMap modelo) {
        try {
            noticiaServicio.modificarNoticia(id, titulo, cuerpo, creador);

            return "redirect:../lista";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "noticia_modificar.html";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, ModelMap modelo) {
        noticiaServicio.eliminar(id);

        return "redirect:../lista";
    }

}
