package tn.essat.dao;
 
import java.util.List;
 
import javax.persistence.NoResultException;
 
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.mindrot.jbcrypt.BCrypt;
 
import tn.essat.model.Categorie;
import tn.essat.model.Emprunt;
import tn.essat.model.Livre;
import tn.essat.model.Utilisateur;


public class GestionImp implements IGestion {
	Session session;

	public GestionImp() {

	StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
	Metadata metadata =  new MetadataSources(serviceRegistry).getMetadataBuilder().build();
	SessionFactory sessionFactory=metadata.getSessionFactoryBuilder().build();
	session=sessionFactory.openSession();
	}
	@Override
	public List<Categorie> getAllCats() {
		 Query<Categorie> req = session.createQuery("Select c from Categorie c");
		 return req.getResultList();

	}

	@Override
	public List<Livre> getAllLivresByCats(int id) {
		 Query<Livre> req = session.createQuery("Select lv from Livre lv where lv.cat.id=?1");
		 req.setParameter(1, id);
		 return req.getResultList();

	}

	@Override
	public Livre getLivreById(int id) {
		return session.load(Livre.class, id);
	}

	@Override
	public Categorie getCatsById(int id) {
		return session.load(Categorie.class, id);
	}

	@Override
	public List<Emprunt> getAllEmpruntsByUser(int id) {
		Query query = session.createQuery("FROM Emprunt");
	    return query.getResultList();
	}

	@Override
	public void addEmprunt(Emprunt emp) {
		session.getTransaction().begin();	
		session.save(emp);
		session.getTransaction().commit();
		}

	@Override
	public Utilisateur verifUser(String username, String password) {
		 
	    Query<Utilisateur> req =
	            session.createQuery("select u from Utilisateur u where u.login = ?1");
 
	    req.setParameter(1, username);
 
	    List<Utilisateur> list = req.getResultList();
 
	    if (list.isEmpty()) {
	        return null;
	    }
 
	    Utilisateur u = list.get(0);
 
	    if (BCrypt.checkpw(password, u.getPassword())) {
	        return u;
	    }
 
	    return null;
	}	

	@Override
	public void addLivre(Livre lv) {
		session.getTransaction().begin();
		session.save(lv);
		session.getTransaction().commit();
	}
 

	@Override
    public void addUser(Utilisateur u) {

		session.getTransaction().begin();	
		session.save(u);
		session.getTransaction().commit();

}
	@Override
	public void addCategorie(Categorie cat) {
		// TODO Auto-generated method stub
		session.getTransaction().begin();
		session.save(cat);
		session.getTransaction().commit();
	}
	
	@Override
	public void deleteLivre(int id) {
		session.getTransaction().begin();
		Livre lv = session.load(Livre.class, id);
		session.delete(lv);
		session.getTransaction().commit();
	}
	
	@Override
	public void deleteCategorie(int id) {
		session.getTransaction().begin();
		Categorie cat = session.load(Categorie.class, id);
		session.delete(cat);
		session.getTransaction().commit();
	}


	
}
