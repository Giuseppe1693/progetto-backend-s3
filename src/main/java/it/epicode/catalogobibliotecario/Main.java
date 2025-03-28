package it.epicode.catalogobibliotecario;

import it.epicode.catalogobibliotecario.dao.*;
import it.epicode.catalogobibliotecario.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogobibliotecario");
        EntityManager em = emf.createEntityManager();

        ElementoCatalogoDAO elementoDAO = new ElementoCatalogoDAO(em);
        LibroDAO libroDAO = new LibroDAO(em);
        RivistaDAO rivistaDAO = new RivistaDAO(em);
        UtenteDAO utenteDAO = new UtenteDAO(em);
        PrestitoDAO prestitoDAO = new PrestitoDAO(em);

        Libro libro1 = new Libro("1234567890", "Il Signore degli Anelli", 1954, 1178, "J.R.R. Tolkien", "Fantasy");
        Libro libro2 = new Libro("2233445566", "1984", 1949, 328, "George Orwell", "Distopico");
        Libro libro3 = new Libro("3344556677", "Il Nome della Rosa", 1980, 503, "Umberto Eco", "Giallo storico");

        elementoDAO.save(libro1);
        elementoDAO.save(libro2);
        elementoDAO.save(libro3);

        Rivista rivista1 = new Rivista("0987654321", "National Geographic", 2021, 100, Periodicita.SEMESTRALE);
        Rivista rivista2 = new Rivista("5566778899", "Scientific American", 2022, 90, Periodicita.MENSILE);
        Rivista rivista3 = new Rivista("6677889900", "TIME", 2023, 60, Periodicita.SETTIMANALE);

        elementoDAO.save(rivista1);
        elementoDAO.save(rivista2);
        elementoDAO.save(rivista3);

        Utente utente = new Utente("Giuseppe", "Batti", "1993-05-16", "T12345");
        utenteDAO.save(utente);

        Prestito prestito = new Prestito(utente, libro1, LocalDate.now());
        prestitoDAO.save(prestito);

        System.out.println("Ricerca elemento per ISBN '1234567890':");
        ElementoCatalogo trovatoISBN = elementoDAO.findByISBN("1234567890");
        System.out.println("- Trovato: " + trovatoISBN.getTitolo());

        System.out.println("\nRicerca libri di J.R.R. Tolkien:");
        libroDAO.findByAutore("J.R.R. Tolkien")
                .forEach(l -> System.out.println("- " + l.getTitolo()));

        System.out.println("\nElementi pubblicati nel 2021:");
        elementoDAO.ricercaPerAnno(2021)
                .forEach(e -> System.out.println("- " + e.getTitolo()));

        System.out.println("\nElementi con titolo contenente 'Anelli':");
        elementoDAO.ricercaPerTitolo("Anelli")
                .forEach(e -> System.out.println("- " + e.getTitolo()));

        System.out.println("\nElementi attualmente in prestito per tessera T12345:");
        prestitoDAO.findPrestitiInCorsoByNumeroTessera("T12345")
                .forEach(p -> System.out.println("- " + p.getElemento().getTitolo()));

        System.out.println("\nPrestiti scaduti e non restituiti:");
        prestitoDAO.findPrestitiScaduti()
                .forEach(p -> System.out.println("- " + p.getElemento().getTitolo() +
                        " (Data prevista restituzione: " + p.getDataPrevistaRestituzione() + ")"));

        elementoDAO.delete("2233445566");
        System.out.println("\nLibro con ISBN '2233445566' rimosso.");

        em.close();
        emf.close();
    }
}

