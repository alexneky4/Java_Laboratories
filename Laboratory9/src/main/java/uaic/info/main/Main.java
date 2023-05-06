package uaic.info.main;

import uaic.info.model.Album;
import uaic.info.model.Artist;
import uaic.info.model.Genre;
import uaic.info.persistance.PersistenceManager;
import uaic.info.repository.AlbumRepository;
import uaic.info.repository.ArtistRepository;
import uaic.info.repository.GenreRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

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
        Artist a1 = new Artist("Bard");
        Artist a2 = new Artist("Gremlin");
        Artist a3 = new Artist("Gnome");
        artistRepository.create(a1);
        artistRepository.create(a2);
        artistRepository.create(a3);
        System.out.println(artistRepository.findByName("Bard"));
        artistRepository.closeManager();

        GenreRepository genreRepository = new GenreRepository();
        Genre g1 = new Genre("Rock");
        Genre g2 = new Genre("Pop");
        genreRepository.create(g1);
        genreRepository.create(g2);
        System.out.println(genreRepository.findAll());
        genreRepository.closeManager();

        AlbumRepository albumRepository = new AlbumRepository();
        Album album1 = new Album("Munch",LocalDate.of(2022,5,4),a1, Arrays.asList(g1));
        Album album2 = new Album("Cruncher", LocalDate.of(2020,2,14),a2,Arrays.asList(g1,g2));
        albumRepository.create(album1);
        albumRepository.create(album2);
        System.out.println(albumRepository.findByArtist(a1));
        System.out.println(albumRepository.findByName("Cruncher"));
        albumRepository.closeManager();

        PersistenceManager.getInstance().closeEntityManagerFactory();

    }
}
