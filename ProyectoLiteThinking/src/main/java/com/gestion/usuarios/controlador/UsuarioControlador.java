package com.gestion.usuarios.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebResourcesRuntimeHints;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.usuarios.datos.UsuariosDatos;
import com.gestion.usuarios.excepciones.ResourceNotFoundException;
import com.gestion.usuarios.modelo.Usuarios;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins="http://localhost:4200")
public class UsuarioControlador {
	
	@Autowired
	private UsuariosDatos datos;
	
	//Lista todos los usuarios
	@GetMapping("/usuarios")
	public List<Usuarios> listarTodosLosUsuarios(){
		return datos.findAll();
		
	}
	
	@PostMapping("/usuarios")
	public Usuarios guardarUsuario(@RequestBody Usuarios usuario) {
		return datos.save(usuario);
	}
	
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<Usuarios> obtenerUsuarioId(@PathVariable Long id){
		Usuarios usuario = datos.findById(id).orElseThrow(()-> new ResourceNotFoundException(("No existe el usuario con el ID" + id)));
		return ResponseEntity.ok(usuario);
	}
	
	@PutMapping("/usuarios/{id}")
	public ResponseEntity<Usuarios> actualizarUsuario(@PathVariable Long id,@RequestBody Usuarios detallesUsuario){
		Usuarios usuario = datos.findById(id).orElseThrow(()-> new ResourceNotFoundException(("No existe el usuario con el ID" + id)));
		usuario.setName(detallesUsuario.getName());
		usuario.setEmail(detallesUsuario.getEmail());
		Usuarios usuarioActualizado= datos.save(usuario);
		return ResponseEntity.ok(usuarioActualizado);
	}
	
		@DeleteMapping("/usuarios/{id}")
		public ResponseEntity<Map<String,Boolean>> eliminarUsuario(@PathVariable Long id){
			Usuarios usuarios = datos.findById(id)
					            .orElseThrow(() -> new ResourceNotFoundException("No existe el usuario con el ID : " + id));
			
			datos.delete(usuarios);
			Map<String, Boolean> respuesta = new HashMap<>();
			respuesta.put("eliminar",Boolean.TRUE);
			return ResponseEntity.ok(respuesta);
	    }
	

}
