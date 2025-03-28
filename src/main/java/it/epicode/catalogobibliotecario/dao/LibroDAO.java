package it.epicode.catalogobibliotecario.dao;

import it.epicode.catalogobibliotecario.entities.Libro;
import jakarta.persistence.EntityManager;
import java.util.List;

public class LibroDAO {
    private EntityManager em;

    public LibroDAO(EntityManager em) {
        this.em = em;
    }

    public List<Libro> findByAutore(String autore) {
        return em.createQuery("SELECT l FROM Libro l WHERE l.autore = :autore", Libro.class)
                .setParameter("autore", autore)
                .getResultList();
    }
}
