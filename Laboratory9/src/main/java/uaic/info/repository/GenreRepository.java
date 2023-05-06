package uaic.info.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import uaic.info.model.Artist;
import uaic.info.model.Genre;
import uaic.info.persistance.PersistenceManager;

import java.util.List;


public class GenreRepository extends  AbstractRepository{

//    private EntityManager em;

    public GenreRepository()
    {
//        this.em = PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
        super(Genre.class);
    }

//    public void create(Genre genre)
//    {
//        if(!findByName(genre.getName()).isEmpty())
//            return;
//        EntityTransaction et = em.getTransaction();
//        et.begin();
//        em.persist(genre);
//        et.commit();
//    }
//
//    public Genre findById(Integer id)
//    {
//        return em.find(Genre.class,id);
//    }
//
//    public List<Genre> findByName(String name)
//    {
//        return em.createNamedQuery("Genre.findByName")
//                .setParameter("name", name)
//                .getResultList();
//    }
//
//    public List<Genre> findAll()
//    {
//        return em.createNamedQuery("Genre.findAll").getResultList();
//    }
//    public void closeManager()
//    {
//        em.close();
//    }
}
