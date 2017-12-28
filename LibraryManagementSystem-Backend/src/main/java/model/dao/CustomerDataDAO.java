package model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.entities.CustomerDataModel;

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
	
	public CustomerDataModel readData(String customerId) {
		CustomerDataModel customer = new CustomerDataModel();
		return customer;
	}
}
