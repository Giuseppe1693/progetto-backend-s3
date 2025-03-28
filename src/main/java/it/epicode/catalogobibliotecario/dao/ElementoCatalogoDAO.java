package it.epicode.catalogobibliotecario.dao;

import it.epicode.catalogobibliotecario.entities.ElementoCatalogo;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ElementoCatalogoDAO {
    private EntityManager em;

    public ElementoCatalogoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(ElementoCatalogo elemento) {
        em.getTransaction().begin();
        em.persist(elemento);
        em.getTransaction().commit();
    }

    public ElementoCatalogo findByISBN(String isbn) {
        return em.find(ElementoCatalogo.class, isbn);
    }

    public void delete(String isbn) {
        em.getTransaction().begin();
        ElementoCatalogo el = em.find(ElementoCatalogo.class, isbn);
        if (el != null) em.remove(el);
        em.getTransaction().commit();
    }

    public List<ElementoCatalogo> ricercaPerAnno(int anno) {
        return em.createQuery("SELECT e FROM ElementoCatalogo e WHERE e.annoPubblicazione = :anno", ElementoCatalogo.class)
                .setParameter("anno", anno)
                .getResultList();
    }

    public List<ElementoCatalogo> ricercaPerTitolo(String titolo) {
        return em.createQuery("SELECT e FROM ElementoCatalogo e WHERE LOWER(e.titolo) LIKE :titolo", ElementoCatalogo.class)
                .setParameter("titolo", "%" + titolo.toLowerCase() + "%")
                .getResultList();
    }
}
