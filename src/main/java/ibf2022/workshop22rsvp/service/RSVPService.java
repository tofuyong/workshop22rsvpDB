package ibf2022.workshop22rsvp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibf2022.workshop22rsvp.model.RSVP;
import ibf2022.workshop22rsvp.repository.RSVPRepository;

@Service
public class RSVPService {

    @Autowired
    RSVPRepository rsvpRepo;

    // #1
    public Integer countAll() {
        return rsvpRepo.countAll();
    }

    // #2 
    public List<RSVP> findAll() {
        return rsvpRepo.findAll();
    }

    // #3
    public RSVP selectById(Integer id) {
        return rsvpRepo.selectById(id);
    }

    // #4
    public RSVP findByName(String fullname) {
        return rsvpRepo.findByName(fullname);
    }

    // #5
    public Boolean save(RSVP rsvp) {
        return rsvpRepo.save(rsvp);
    }

    // #6 Single update
    public Boolean update(RSVP rsvp) {
        return rsvpRepo.update(rsvp);
    }

    // #7 Batch update - take note of return which is the size of the update / an integer array
    public int[] batchUpdate(List<RSVP> rsvp) {
        return rsvpRepo.batchUpdate(rsvp);
    }
}
