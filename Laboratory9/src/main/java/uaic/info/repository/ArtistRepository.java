package uaic.info.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import uaic.info.model.Artist;
import uaic.info.persistance.PersistenceManager;

import java.util.List;

public class ArtistRepository extends  AbstractRepository{


    public ArtistRepository() {
        super(Artist.class);
    }

//    public void create(Artist artist)
//    {
//        if(!findByName(artist.getName()).isEmpty())
//            return;
//        EntityTransaction et = em.getTransaction();
//        et.begin();
//        em.persist(artist);
//        et.commit();
//    }

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
//
//    public List<Artist> findAll()
//    {
//        return em.createNamedQuery("Artist.findAll").getResultList();
//    }
//    public void closeManager()
//    {
//        em.close();
//    }

}
