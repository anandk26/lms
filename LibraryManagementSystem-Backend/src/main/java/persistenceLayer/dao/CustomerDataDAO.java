package persistenceLayer.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import persistenceLayer.entities.CustomerDataModel;

@Component
public class CustomerDataDAO {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    
    public boolean writeData(CustomerDataModel customer) {
        try{
            this.entityManagerFactory = Persistence.createEntityManagerFactory("LMS_PU");
            this.entityManager = this.entityManagerFactory.createEntityManager();
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(customer);
            this.entityManager.getTransaction().commit();
        } catch(Exception ex) {
            return false;
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
		return true;
	}
	
	public CustomerDataModel readData(Long customerId) {
		CustomerDataModel customer = null;
        try{
            this.entityManagerFactory = Persistence.createEntityManagerFactory("LMS_PU");
            this.entityManager = this.entityManagerFactory.createEntityManager();
            this.entityManager.getTransaction().begin();
            customer = this.entityManager.find(CustomerDataModel.class, customerId);
            this.entityManager.getTransaction().commit();
        } catch(Exception ex) {
            return customer;
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
		return customer;
	}
    
    public CustomerDataModel readData(String username) {
		CustomerDataModel customer = null;
        try{
            this.entityManagerFactory = Persistence.createEntityManagerFactory("LMS_PU");
            this.entityManager = this.entityManagerFactory.createEntityManager();
            this.entityManager.getTransaction().begin();
            Query query = this.entityManager.
                createQuery("Select cust from CustomerDataModel cust where cust.logindata.username= :username");
            query.setParameter("username", username);
            customer = (CustomerDataModel) query.getSingleResult();
            this.entityManager.getTransaction().commit();
        } catch(Exception ex) {
            return customer;
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
		return customer;
	}
}
