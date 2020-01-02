package service;

import java.util.List;

import model.Item;

public interface ClienteAgendaService {

	void procesarContacto(Item item);

	List<Item> devolverContactos();

}