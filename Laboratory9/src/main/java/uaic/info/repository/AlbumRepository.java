package uaic.info.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import uaic.info.model.Artist;
import uaic.info.persistance.PersistenceManager;

import java.util.List;

public class AlbumRepository {

//    private EntityManager em;
//
//    public AlbumRepository() {
//        this.em = PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
//    }
//
//    public void create(Artist artist)
//    {
//        EntityTransaction et = em.getTransaction();
//        et.begin();
//        em.persist(artist);
//        et.commit();
//    }
//
//    public Artist findById(Integer id)
//    {
//        return em.find(Artist.class,id);
//    }
//
//    public List<Artist> findByName(String name)
//    {
//        return em.createNamedQuery("Artist.findByName")
//                .setParameter("name", name)
//                .getResultList();
//    }

}
