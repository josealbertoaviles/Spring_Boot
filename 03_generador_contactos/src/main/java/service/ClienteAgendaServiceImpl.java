package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import model.Item;
@Service
public class ClienteAgendaServiceImpl implements ClienteAgendaService {
	@Autowired
	RestTemplate template;
	@Override
	public void procesarContacto(Item item) {
		String url = "http://localhost:8080";
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
}
