package uaic.info.main;

import uaic.info.model.Artist;
import uaic.info.repository.ArtistRepository;

public class Main {

    public static void main(String args[]) {
//        EntityManagerFactory emf =
//                Persistence.createEntityManagerFactory("ExamplePU");
//        EntityManager em = emf.createEntityManager();
//
//        em.getTransaction().begin();
//        Artist artist = new Artist("Beatles");
//        em.persist(artist);
//
//        Artist a = (Artist)em.createQuery(
//                        "select e from Artist e where e.name='Beatles'")
//                .getSingleResult();
//        a.setName("The Beatles");
//        em.getTransaction().commit();
//        em.close();
//        emf.close();

        ArtistRepository artistRepository = new ArtistRepository();
        Artist a = new Artist("Bard");
        artistRepository.create(a);
        artistRepository.closeManager();
    }
}
