package com.Egg.News.servicios;

import com.Egg.News.entidades.Imagen;
import com.Egg.News.entidades.Usuario;
import com.Egg.News.enumeraciones.Rol;
import com.Egg.News.excepciones.MiException;
import com.Egg.News.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired 
    private ImagenServicio imagenServicio;
    
    @Transactional
    public void registrar(MultipartFile archivo, String nombre, String email, String password, String password2) throws MiException {

        validar(nombre, email, password, password2);

        Usuario usuario = new Usuario();

        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        usuario.setRol(Rol.USER);
        usuario.setFechaAlta(new Date());
        usuario.setActivo(true);
        
        Imagen imagen = imagenServicio.guardar(archivo);
        usuario.setImagen(imagen);

        usuarioRepositorio.save(usuario);
    }
    
    
    @Transactional
    public void actualizar(MultipartFile archivo, Long idUsuario, String nombre, String email, String password, String password2) throws MiException {

        validar(nombre, email, password, password2);

        Optional<Usuario> respuesta = usuarioRepositorio.findById(idUsuario);
        if (respuesta.isPresent()) {

            Usuario usuario = respuesta.get();
            usuario.setNombre(nombre);
            usuario.setEmail(email);

            usuario.setPassword(new BCryptPasswordEncoder().encode(password));

            usuario.setRol(Rol.USER);
            
            Long idImagen = null;
            


            
            if (usuario.getImagen() != null || archivo != null) {

                idImagen = usuario.getImagen().getId();
                
                Imagen imagen = imagenServicio.actualizar(archivo, idImagen);  

                usuario.setImagen(imagen);

            }
            
            
            
            
            
            usuarioRepositorio.save(usuario);
        }

        

    } 

    @Transactional
    public void actualizar( Long idUsuario, String nombre, String email, String password, String password2) throws MiException {

        validar(nombre, email, password, password2);

        Optional<Usuario> respuesta = usuarioRepositorio.findById(idUsuario);
        if (respuesta.isPresent()) {

            Usuario usuario = respuesta.get();
            usuario.setNombre(nombre);
            usuario.setEmail(email);

            usuario.setPassword(new BCryptPasswordEncoder().encode(password));

            usuario.setRol(Rol.USER);

            usuarioRepositorio.save(usuario);
        }

        

    }    
    private void validar(String nombre, String email, String password, String password2) throws MiException {

        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("el nombre no puede ser nulo o estar vacío");
        }
        if (email.isEmpty() || email == null) {
            throw new MiException("el email no puede ser nulo o estar vacio");
        }
        if (password.isEmpty() || password == null || password.length() <= 5) {
            throw new MiException("La contraseña no puede estar vacía, y debe tener más de 5 dígitos");
        }

        if (!password.equals(password2)) {
            throw new MiException("Las contraseñas ingresadas deben ser iguales");
        }

    }
    
    

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepositorio.buscarPorEmail(email);

        if (usuario != null) {

            List<GrantedAuthority> permisos = new ArrayList();

            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString()); //ROLE_USER

            permisos.add(p);
            
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

            HttpSession session = attr.getRequest().getSession(true);

            session.setAttribute("usuariosession", usuario);            

            return new User(usuario.getEmail(), usuario.getPassword(), permisos);
        } else {
            return null;
        }

    }
    
    public Usuario getOne(Long id){
        return usuarioRepositorio.getOne(id);
    }

}    
        
    



