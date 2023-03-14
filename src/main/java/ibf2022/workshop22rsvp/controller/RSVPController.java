package ibf2022.workshop22rsvp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ibf2022.workshop22rsvp.model.RSVP;
import jakarta.validation.Valid;

@Controller
@RequestMapping
public class RSVPController {

    @GetMapping("/")
    public String getIndex(RSVP rsvp, Model model) {
        model.addAttribute("rsvp", rsvp);
        return "index";
    }

    // @PostMapping("/confirm")
    // public String postRSVP(@Valid RSVP rsvp, Model model, BindingResult results) {
    //     if (results.hasErrors())
	// 		return "index";
    //     rsvpSvc.save(rsvp);
    //     return "index";
    // }
}
