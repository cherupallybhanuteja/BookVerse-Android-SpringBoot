package org.androidspringbootbackend.repository;

import org.androidspringbootbackend.model.Manga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
public class DbSeeder {

    private final MangaRepository mangaRepository;

    @Autowired
    public DbSeeder(MangaRepository mangaRepository) {
        this.mangaRepository = mangaRepository;
    }

    @PostConstruct
    public void init() {
        // Clear existing data
        this.mangaRepository.deleteAll();

        // Add 50 Manga records
        List<Manga> mangas = Arrays.asList(
                Manga.builder().title("Theory of Relativity").author("Albert Einstein").year(1921).status(true).url("https://upload.wikimedia.org/wikipedia/commons/d/d3/Albert_Einstein_Head.jpg").build(),
                Manga.builder().title("AC Electricity").author("Nikola Tesla").year(1893).status(true).url("https://upload.wikimedia.org/wikipedia/commons/d/d4/N.Tesla.JPG").build(),
                Manga.builder().title("Hamlet").author("William Shakespeare").year(1601).status(true).url("https://upload.wikimedia.org/wikipedia/commons/thumb/a/a2/Shakespeare.jpg/800px-Shakespeare.jpg").build(),
                Manga.builder().title("The Hobbit").author("J.R.R. Tolkien").year(1937).status(true).url("https://upload.wikimedia.org/wikipedia/en/4/4a/TheHobbit_FirstEdition.jpg").build(),
                Manga.builder().title("Black Holes").author("Stephen Hawking").year(1988).status(true).url("https://upload.wikimedia.org/wikipedia/commons/e/eb/Stephen_Hawking.StarChild.jpg").build(),
                Manga.builder().title("First Programmer").author("Ada Lovelace").year(1843).status(true).url("https://upload.wikimedia.org/wikipedia/commons/a/a4/Ada_Lovelace_portrait.jpg").build()
        );



        // Save all mangas to the database
        this.mangaRepository.saveAll(mangas);
    }
}
