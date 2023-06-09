
package com.Egg.News.repositorios;

import com.Egg.News.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,Long> {
    
    @Query ("SELECT u FROM Usuario u WHERE u.email = :email")
    public Usuario buscarPorEmail(@Param("email")String email);
    
    @Query ("SELECT u FROM Usuario u WHERE u.rol = :rol")
    public Usuario buscarPorRol(@Param("rol")String rol);
    
}
