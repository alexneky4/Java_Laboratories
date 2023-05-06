package uaic.info.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import uaic.info.model.Album;
import uaic.info.model.Artist;
import uaic.info.model.Genre;
import uaic.info.persistance.PersistenceManager;

import java.util.List;

public class AlbumRepository extends AbstractRepository{

//    private EntityManager em;

    public AlbumRepository() {
//        this.em = PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
        super(Album.class);
    }
//
//    public void create(Album album)
//    {
//        if(!findByNameAndArtist(album.getTitle(),album.getArtist()).isEmpty())
//            return;
//        EntityTransaction et = em.getTransaction();
//        et.begin();
//        em.persist(album);
//        et.commit();
//    }
//
//    public Album findById(Integer id)
//    {
//        return em.find(Album.class,id);
//    }
//
//    public List<Album> findByName(String name)
//    {
//        return em.createNamedQuery("Album.findByName")
//                .setParameter("name", name)
//                .getResultList();
//    }
//
//    public List<Album> findAll()
//    {
//        return em.createNamedQuery("Album.findAll").getResultList();
//    }

    public List<Album> findByArtist(Artist artist)
    {
        return em.createNamedQuery("Album.findByArtist")
                .setParameter("artist", artist)
                .getResultList();
    }

    public List<Album> findByNameAndArtist(String name, Artist artist)
    {
        return em.createNamedQuery("Album.findByNameAndArtist")
                .setParameter("name",name)
                .setParameter("artist", artist)
                .getResultList();
    }

//    public void closeManager()
//    {
//        em.close();
//    }
}
