package vote.backend.controllers.KommuneController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.web.bind.annotation.RestController;
import vote.backend.entities.Group.Kommune;
import vote.backend.services.KommuneService.KommuneService;
import vote.backend.services.KommuneService.KommuneServiceImp;

import java.util.List;

@RestController
public class KommuneController implements KommuneControllerInterface {

    @Autowired
    KommuneServiceImp kommuneService;

    @Override
    public List<Kommune> findAllKommunes() {
        List<Kommune> kommuneList = kommuneService.findAll();
        return kommuneList;
    }

    @Override
    public Kommune findKommuneById(Long id) {
        return kommuneService.findKommuneById(id);
    }

    @Override
    public Kommune findKommuneByKommuneCode(Long code) {
        return kommuneService.findKommuneByKommuneCode(code);
    }

    @Override
    public String findKommuneByKommuneZipCode(Long zipcode) {
       return kommuneService.findKommuneByZipCode(zipcode);
    }
}
