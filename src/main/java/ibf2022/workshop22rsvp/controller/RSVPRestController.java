package ibf2022.workshop22rsvp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.workshop22rsvp.model.RSVP;
import ibf2022.workshop22rsvp.service.RSVPService;

@RestController
@RequestMapping("/api/rsvp")
public class RSVPRestController {

    @Autowired
    RSVPService rsvpSvc;
    
    // #1 Get number of RSVPs
    @GetMapping("/count")
    public ResponseEntity<Integer> getRSVPCount() {
        Integer rsvpCount = rsvpSvc.countAll();
        return ResponseEntity.ok().body(rsvpCount);
    }

    // #2 Get All RSVPs
    @GetMapping("/rsvps")
    public ResponseEntity<List<RSVP>> getAll() {
        List<RSVP> rsvpList = rsvpSvc.findAll();
        return ResponseEntity.ok().body(rsvpList);
    }

    // #3 Get RSVP by ID
    @GetMapping("/{id}")
    public ResponseEntity<RSVP> getRSVPById(@PathVariable("id") Integer id) {
        RSVP rsvp = rsvpSvc.selectById(id);
        if (rsvp == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(rsvp);
        }
    }

    // #4 Get RSVP by Name
    @GetMapping("")
    public ResponseEntity<RSVP> getRSVPByName(@RequestParam("full_name") String fullname) {
        RSVP rsvp = rsvpSvc.findByName(fullname);
        if (rsvp == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(rsvp);
        }
    }

    // #5 Save a new RSVP
    @PostMapping("/save")
    public ResponseEntity<Boolean> save(@RequestBody RSVP rsvp) {
        Boolean saved = rsvpSvc.save(rsvp);
        if (!saved) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok().body(saved);
        }
    }

    // #6 Single update existing RSVP
    @PutMapping("/update")
    public ResponseEntity<Boolean> update(@RequestBody RSVP rsvp) {
        Boolean updated = rsvpSvc.update(rsvp);
        if (!updated) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(updated);
        }
    }

    // #7 Batch update
    @PutMapping("/batchupdate")
    public ResponseEntity<int[]> batchUpdate(@RequestBody List<RSVP> rsvp) {
        int[] batchSize = rsvpSvc.batchUpdate(rsvp);
        if (batchSize == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(batchSize);
        }
    }
    
}
