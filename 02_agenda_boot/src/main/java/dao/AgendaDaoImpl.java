package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.Contacto;
//inyeccion de la dependencia de conexion
@Repository("agendaService")
public class AgendaDaoImpl implements AgendaDao {
	
	@PersistenceContext // se pdria decir que es como autowired, inyecta un objeto pero en jpa
	EntityManager em;
	@Transactional //esa etiqueta solo es necesaria en los metodos que realizan algun tipo de aacion en la base de datos
	@Override
	public boolean agregarContacto(Contacto contacto) {
		em.persist(contacto);
		return true;
	}

	@Override
	public Contacto recuperarContacto(String email) {
		//String jpql="Select c From Contacto c where c.email=?1";
		TypedQuery<Contacto> tqr = em.createNamedQuery("Contacto.findByEmail",Contacto.class);
		//TypedQuery<Contacto> tqr=em.createQuery(jpql,Contacto.class);
		tqr.setParameter(1,email);
		//No hay que hacer casting
		/*try {
			return tqr.getSingleResult();
		}catch(NoResultException ex) {
			return null;
		}catch(NonUniqueResultException ex) {
			return tqr.getResultList().get(0);
		}*/
		List<Contacto> contactos = tqr.getResultList();
		return contactos.size()>0?contactos.get(0):null;
		
		
	}
	@Transactional
	@Override
	public void eliminarContacto(String email) {
		//String jpql = "delete from Contacto c where c.email=?1";
		//String jpql = "delete from Contacto c where c.email=:em1"; esto es si se le quiere dar nombre
		//Query q = em.createQuery(jpql);Contacto.deleteByEmail
		Query q = em.createNamedQuery("Contacto.deleteByEmail");
		q.setParameter(1, email);//aqui en vez de 1 se pondria em1 en la posicion
		q.executeUpdate();
		
	}

	@Override
	public List<Contacto> devolverContactos() {
		/*String jpql = "select c From Contacto c";
		TypedQuery<Contacto> tqr = em.createQuery(jpql,Contacto.class);*/
		//Utilizacion de namedQuery
		TypedQuery<Contacto> tqr = em.createNamedQuery("Contacto.findAll",Contacto.class);
		return tqr.getResultList();
	}
	@Transactional
	@Override
	public void eliminarContactoId(int idContacto) {
		Contacto contacto = em.find(Contacto.class, idContacto);
		if(contacto!=null) {
			em.remove(contacto);
		}
		
	}

	@Override
	public Contacto recuperarContacto(int idContacto) {
		return em.find(Contacto.class, idContacto);
	}

	@Override
	@Transactional
	public void actualizarContacto(Contacto contacto) {
		em.merge(contacto);
		
	}
	

}
