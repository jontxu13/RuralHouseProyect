package dataAccess;

import java.io.File;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import domain.RuralHouse;
import domain.Offer;


public class DataAccess  {
	protected static EntityManager  db;
	protected static EntityManagerFactory emf;
	String fileName = "RuralHouse.odb";

	public DataAccess(boolean ini) {
		if(ini) {
			new File(fileName).delete();
		}
		emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
		db = emf.createEntityManager();
		System.out.println("Base de datos abierta");
	}

	public DataAccess()  {	
		 new DataAccess(false);
	}
	
	public void storeRuralH(String city ,String addres) {
		db.getTransaction().begin();
		RuralHouse h = new RuralHouse(city, addres);
		db.persist(h);
		db.getTransaction().commit();
		System.out.println("Casas insertadas: " + h);
		storeOffers(h.getOffers());
	}
	
	public void storeOffers(Collection<Offer> o) {
		db.getTransaction().begin();
		db.persist(o);
		db.getTransaction().commit();
		System.out.println("Oferta insertadas: " + o);
	}
	
/*
	public void updateRooms(Offer o, int n) {
			
		Offer offer= db.find(Offer.class,o.getDate());
		if (offer==null)
		 System.out.println(o.getDate() + " no está en la base de datos");
		 else {
			 if()
		db.getTransaction().begin();
		offer.setSingleNumber(n);
		db.getTransaction().commit();
		}
		
	}
	*/
	
}