package in.mindcraft.trialDVD.Staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("Staff")
public class FilmInfoController {
    private final FilmInfoRepository filmInfoRepository;
    private StaffController staffController;

    @Autowired
    public FilmInfoController(FilmInfoRepository filmInfoRepository) {
        this.filmInfoRepository = filmInfoRepository;
    }

    @PostMapping("/filmlist") 
    public List<FilmInfo> getAllFilmInfo() {
    	
        return filmInfoRepository.findAll();
    }
    
    @GetMapping("/filmByStoreId")
    public List<FilmInfo> getAllFilmInfo(@RequestParam(name = "storeId") Long storeId) {
        return filmInfoRepository.findByStoreId(storeId);
    }

}
