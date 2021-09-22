
package no.noroff.Itunes.controllers;

import no.noroff.Itunes.repositories.MusicRepositoryImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class MusicController {

    MusicRepositoryImpl musicRepository = new MusicRepositoryImpl();

    @GetMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("tracks", musicRepository.getRandomTracks(5));
        model.addAttribute("artists", musicRepository.getRandomArtist(5));
        model.addAttribute("genres", musicRepository.getRandomGenres(5));
        return "index";
    }

    @GetMapping(value = "/tracks", params = "trackName")
    public String getTrackByName(@RequestParam String trackName, Model model){
        model.addAttribute("tracks", musicRepository.getTrackByName(trackName));
        return "view-tracks";
    }


}
