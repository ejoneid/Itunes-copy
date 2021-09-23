package no.noroff.Itunes.controllers;

import no.noroff.Itunes.repositories.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for the MusicController, used with Thymeleaf.
 * Covers GET
 */
@Controller
public class MusicController {

    @Autowired
    private MusicRepository musicRepository;

    /**
     * GET 5 random tracks, artists and genres.
     * @param model = model to be used in the returned view.
     * @return the index view.
     */
    @GetMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("tracks", musicRepository.getRandomTracks(5));
        model.addAttribute("artists", musicRepository.getRandomArtist(5));
        model.addAttribute("genres", musicRepository.getRandomGenres(5));
        return "index";
    }

    /**
     * GET all tracks that corresponds to the param trackName with additional info of album, artist and genre of that song.
     * @param trackName = the string to filter tracks by.
     * @param model = model to be used in the returned view.
     * @return the view-tracks view with the matching songs.
     */
    @GetMapping(value = "/tracks", params = "trackName")
    public String getTrackByName(@RequestParam String trackName, Model model){
        model.addAttribute("tracks", musicRepository.getTrackByName(trackName));
        return "view-tracks";
    }


}
