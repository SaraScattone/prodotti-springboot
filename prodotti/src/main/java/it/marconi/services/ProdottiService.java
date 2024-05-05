package it.marconi.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.marconi.domains.Prodotti;
import it.marconi.domains.ProdottiForm;
import it.marconi.repositories.ProdottiRepository;

@Service
public class ProdottiService {
    
    @Autowired
    private ProdottiRepository prodottiRepo;

    public Prodotti save(ProdottiForm prodottiForm) {

        Prodotti p = mapProdotti(prodottiForm);
        return prodottiRepo.save(p);
    }

    private Prodotti mapProdotti(ProdottiForm prodottiForm) {

        Prodotti p = new Prodotti();
        p.setNome(prodottiForm.getNome());
        p.setCategoria(prodottiForm.getCategoria());
        p.setAnnoProduzione(prodottiForm.getAnnoProduzione());
        p.setQuantita(prodottiForm.getQuantita());

        return p;
    }

    public Optional<Prodotti> get(UUID id) {
        return prodottiRepo.findById(id);
    }

    public List<Prodotti> findAll() {
        return prodottiRepo.findAll();
    }

    public void deleteById(UUID id) {
        prodottiRepo.deleteById(id);
    }

    public void deleteAll(ProdottiRepository prodottiRepo) {
        prodottiRepo.deleteAll();
    }
}
