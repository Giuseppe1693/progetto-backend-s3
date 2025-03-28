package it.epicode.catalogobibliotecario.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
public class Prestito {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private Utente utente;

    @ManyToOne
    private ElementoCatalogo elemento;

    private LocalDate dataInizio;
    private LocalDate dataPrevistaRestituzione;
    private LocalDate dataEffettivaRestituzione;

    public Prestito() {}

    public Prestito(Utente utente, ElementoCatalogo elemento, LocalDate dataInizio) {
        this.utente = utente;
        this.elemento = elemento;
        this.dataInizio = dataInizio;
        this.dataPrevistaRestituzione = dataInizio.plusDays(30);
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public Utente getUtente() { return utente; }
    public void setUtente(Utente utente) { this.utente = utente; }

    public ElementoCatalogo getElemento() { return elemento; }
    public void setElemento(ElementoCatalogo elemento) { this.elemento = elemento; }

    public LocalDate getDataInizio() { return dataInizio; }
    public void setDataInizio(LocalDate dataInizio) { this.dataInizio = dataInizio; }

    public LocalDate getDataPrevistaRestituzione() { return dataPrevistaRestituzione; }
    public void setDataPrevistaRestituzione(LocalDate dataPrevistaRestituzione) { this.dataPrevistaRestituzione = dataPrevistaRestituzione; }

    public LocalDate getDataEffettivaRestituzione() { return dataEffettivaRestituzione; }
    public void setDataEffettivaRestituzione(LocalDate dataEffettivaRestituzione) { this.dataEffettivaRestituzione = dataEffettivaRestituzione; }
}
