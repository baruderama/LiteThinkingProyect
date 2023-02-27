package com.gestion.usuarios.datos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.usuarios.modelo.Usuarios;

@Repository
public interface UsuariosDatos extends JpaRepository<Usuarios, Long> {

}
