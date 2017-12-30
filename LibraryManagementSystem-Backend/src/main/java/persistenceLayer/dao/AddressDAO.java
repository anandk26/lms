package persistenceLayer.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import persistenceLayer.model.AddressModel;

public class AddressDAO {
    
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    
	public boolean writeData(AddressModel address) {
		try{
            this.entityManagerFactory = Persistence.createEntityManagerFactory("LMS_PU");
            this.entityManager = this.entityManagerFactory.createEntityManager();
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(address);
            this.entityManager.getTransaction().commit();
        } catch(Exception ex) {
            return false;
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
		return true;
	}
	
	public AddressModel readData(String addressId) {
		AddressModel address = new AddressModel();
		return address;
	}
}
