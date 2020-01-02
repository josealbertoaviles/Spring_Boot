package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import model.Item;
import service.ClienteAgendaService;

@RestController
public class GeneradorController {
	@Autowired
	ClienteAgendaService service;

	@GetMapping(value = "contactos/{nombre}/{email}/{edad}")
	public void ProcesarContacto(@PathVariable("nombre") String nombre,
			@PathVariable("email") String email,
			@PathVariable("edad") int edad) {
		service.procesarContacto(new Item(0,nombre,email,edad));
	}
}
