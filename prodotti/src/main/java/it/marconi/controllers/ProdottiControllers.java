package it.marconi.controllers;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.marconi.domains.Prodotti;
import it.marconi.domains.ProdottiForm;
import it.marconi.repositories.ProdottiRepository;
import it.marconi.services.ProdottiService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/prodotti")
public class ProdottiControllers {
    
    @Autowired
    private ProdottiService prodottiService;

    @PostMapping("")
    public ModelAndView vediProdotto(Prodotti prodotti) {

        return new ModelAndView("redirect:/prodotti?=" + prodotti.getId());
    }

    @GetMapping("/new")
    public ModelAndView nuovoProdotto() {
        return new ModelAndView("prodotti-form").addObject(new ProdottiForm());
    }

    @PostMapping("/new")
    public ModelAndView gestisciNuovoProdotto(@ModelAttribute @Valid ProdottiForm prodottiForm, BindingResult br) {

        if(br.hasErrors())
            return new ModelAndView("prodotti-form");

        Prodotti prodotti = prodottiService.save(prodottiForm);

        return new ModelAndView("redirect:/prodotti?=" + prodotti.getId());
    }

    @GetMapping(params = "id")
    public ModelAndView mostraProdotti(@RequestParam("id") UUID prodottoId) {

        Optional<Prodotti> opProdotto = prodottiService.get(prodottoId);

        if(opProdotto.isPresent())
            return new ModelAndView("prodotti-details").addObject("prodotto", opProdotto.get());
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prodotto non trovato");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteProdotto(@PathVariable("id") UUID prodottoId, RedirectAttributes attr) {

        prodottiService.deleteById(prodottoId);

        attr.addFlashAttribute("deleted", true);

        return new ModelAndView("redirect:/prodotti");
    }

    @GetMapping("deleteAll")
    public ModelAndView deleteAll(RedirectAttributes attr, ProdottiRepository prodottiRepo) {

        prodottiService.deleteAll(prodottiRepo);

        attr.addFlashAttribute("deleted", true);

        return new ModelAndView("redirect:/prodotti");
    }
}
