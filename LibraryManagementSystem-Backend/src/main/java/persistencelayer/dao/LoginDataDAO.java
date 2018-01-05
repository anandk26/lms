package persistencelayer.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Component;

import persistencelayer.entities.LoginDataModel;

@Component
public class LoginDataDAO {
	
	private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

	public boolean writeData(LoginDataModel login) {
		try{
            this.entityManagerFactory = Persistence.createEntityManagerFactory("LMS_PU");
            this.entityManager = this.entityManagerFactory.createEntityManager();
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(login);
            this.entityManager.getTransaction().commit();
        } catch(Exception ex) {
            return false;
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
		return true;
	}
	
	public LoginDataModel readData(String username) {
		LoginDataModel login = new LoginDataModel();
        try{
            this.entityManagerFactory = Persistence.createEntityManagerFactory("LMS_PU");
            this.entityManager = this.entityManagerFactory.createEntityManager();
            this.entityManager.getTransaction().begin();
            login = this.entityManager.find(LoginDataModel.class, username);
            this.entityManager.getTransaction().commit();
        } catch(Exception ex) {
            return null;
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
		return login;
	}
}
