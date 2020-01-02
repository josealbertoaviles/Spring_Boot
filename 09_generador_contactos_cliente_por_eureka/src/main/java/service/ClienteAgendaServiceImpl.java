package service;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import model.Item;
@Service
public class ClienteAgendaServiceImpl implements ClienteAgendaService {
	@Autowired
	RestTemplate template;
	String url = "http://servicio-agenda";
	@Override
	public void procesarContacto(Item item) {
		
		//recibe  del controlador los datos del nuevo contacto y tiene que 
		//interaccionar con el servicio remoto para realizar la tarea encomendada
	
		Item contacto = template.getForObject(url+"/buscar/"+ item.getEmail(), Item.class);
		if (contacto!=null) {
			contacto.setEdad(item.getEdad());
			contacto.setNombre(item.getNombre());
			template.put(url+"/contactos", contacto);
		}else {
			template.postForLocation(url+"/contactos", item);
		}
		
	}
	@Override
	public List<Item> devolverContactos(){
		ResponseEntity<Item[]> contactos=  template.getForEntity(url +"/contactos", Item[].class);
		HttpHeaders headers = contactos.getHeaders();
		//mostrar el valor de un encabezado
		System.out.println(headers.get("total").toString());
		return Arrays.asList(contactos.getBody());
	}
}
