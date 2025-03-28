package it.epicode.catalogobibliotecario.dao;

import it.epicode.catalogobibliotecario.entities.Prestito;
import jakarta.persistence.EntityManager;
import java.util.List;

public class PrestitoDAO {
    private EntityManager em;

    public PrestitoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Prestito prestito) {
        em.getTransaction().begin();
        em.persist(prestito);
        em.getTransaction().commit();
    }

    public List<Prestito> findPrestitiInCorsoByNumeroTessera(String numeroTessera) {
        return em.createQuery("SELECT p FROM Prestito p WHERE p.utente.numeroTessera = :numeroTessera AND p.dataEffettivaRestituzione IS NULL", Prestito.class)
                .setParameter("numeroTessera", numeroTessera)
                .getResultList();
    }

    public List<Prestito> findPrestitiScaduti() {
        return em.createQuery("SELECT p FROM Prestito p WHERE p.dataPrevistaRestituzione < CURRENT_DATE AND p.dataEffettivaRestituzione IS NULL", Prestito.class)
                .getResultList();
    }
}
