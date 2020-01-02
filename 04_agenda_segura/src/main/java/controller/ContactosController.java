package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dao.AgendaDao;
import model.Contacto;

@RestController
public class ContactosController {
	@Autowired
	AgendaDao agenda;
	@GetMapping(value="contactos",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Contacto>> recuperarContactos() {
		List<Contacto> contactos = agenda.devolverContactos();
		//return contactos;
		HttpHeaders headers = new HttpHeaders();
		headers.add("total", contactos.size()+"");
		return new ResponseEntity<List<Contacto>>(contactos,headers,HttpStatus.OK);
	}
	@GetMapping(value="contactos/{idContacto}",produces=MediaType.APPLICATION_JSON_VALUE)
	public Contacto recuperarContactosId(@PathVariable("idContacto") int idContacto) {	
		return agenda.recuperarContacto(idContacto);
	}
	@GetMapping(value = "buscar/{email}",produces = MediaType.APPLICATION_JSON_VALUE)
	public Contacto buscarPorEmail(@PathVariable("email") String email) {
		return agenda.recuperarContacto(email);
	}
	@PostMapping(value="contactos",consumes=MediaType.APPLICATION_JSON_VALUE)
	public void agregarContactos(@RequestBody Contacto contacto) {
		agenda.agregarContacto(contacto);
		
	}
	@DeleteMapping(value="eliminarPorEmail/{email}")
	public void eliminarContactos(@PathVariable("email") String email) {		
		agenda.eliminarContacto(email);
		//request.setAttribute("contactos", contactos);
	}
	@DeleteMapping(value="eliminarPorId/{idContacto}")
	public void eliminarContactosId(@PathVariable("idContacto") int idContacto) {
		agenda.eliminarContactoId(idContacto);
		
	}@PutMapping(value="contactos",consumes=MediaType.APPLICATION_JSON_VALUE)
	public void actualizarContacto(@RequestBody Contacto contacto) {
		agenda.actualizarContacto(contacto);
	}

	
}
