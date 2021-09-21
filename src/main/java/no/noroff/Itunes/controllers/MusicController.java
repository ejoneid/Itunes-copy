
package no.noroff.Itunes.controllers;

import no.noroff.Itunes.repositories.CustomerRepositoryImpl;
import no.noroff.Itunes.repositories.MusicRepository;
import no.noroff.Itunes.repositories.MusicRepositoryImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
public class MusicController {

    MusicRepositoryImpl musicRepository = new MusicRepositoryImpl();

    @RequestMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("tracks", musicRepository.getRandomTracks(5));
        model.addAttribute("artist", musicRepository.getRandomArtist(5));
        model.addAttribute("genre", musicRepository.getRandomGenres(5));
        return "index";
    }


}
