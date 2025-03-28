package it.epicode.catalogobibliotecario.dao;

import it.epicode.catalogobibliotecario.entities.ElementoCatalogo;
import jakarta.persistence.EntityManager;

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
}
