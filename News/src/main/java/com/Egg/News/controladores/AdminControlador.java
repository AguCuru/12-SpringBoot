package com.Egg.News.controladores;

import com.Egg.News.servicios.NoticiaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminControlador {

    @Autowired
    private NoticiaServicio noticiaServicio;

    @GetMapping("/dashboard")
    public String panelAministrativo(ModelMap modelo) {

        return "panel.html";
    }


}
