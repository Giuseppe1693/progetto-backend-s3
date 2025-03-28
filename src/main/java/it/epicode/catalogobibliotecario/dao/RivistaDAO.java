package it.epicode.catalogobibliotecario.dao;

import it.epicode.catalogobibliotecario.entities.Rivista;
import jakarta.persistence.EntityManager;

public class RivistaDAO {
    private EntityManager em;

    public RivistaDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Rivista rivista) {
        em.getTransaction().begin();
        em.persist(rivista);
        em.getTransaction().commit();
    }
}
