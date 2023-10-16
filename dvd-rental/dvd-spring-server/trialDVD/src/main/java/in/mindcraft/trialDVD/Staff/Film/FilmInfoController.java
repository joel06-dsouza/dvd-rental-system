package in.mindcraft.trialDVD.Staff.Film;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import java.util.List;
//import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.mindcraft.trialDVD.Staff.Staff.StaffController;

@RestController
@RequestMapping("Staff")
public class FilmInfoController {
    private final FilmInfoRepository filmInfoRepository;
    private StaffController staffController;

    @Autowired
    public FilmInfoController(FilmInfoRepository filmInfoRepository) {
        this.filmInfoRepository = filmInfoRepository;
    }

//    @PostMapping("/filmlist")
    @RequestMapping(value = "/filmlist", method = { RequestMethod.GET, RequestMethod.POST })
    public List<FilmInfo> getAllFilmInfo() {
    	
        return filmInfoRepository.findAll();
    }
    
//    @GetMapping("/filmByStoreId")
//    @RequestMapping(value = "/filmByStoreId", method = { RequestMethod.GET, RequestMethod.POST })
//    public List<FilmInfo> getAllFilmInfo(@RequestParam(name = "storeId") Long storeId) {
//        return filmInfoRepository.findByStoreId(storeId);
//    }
    
//    @PostMapping("/filmByStoreId")
//    public List<FilmInfo> getAllFilmInfoByStoreId(@RequestBody Map<Long, Object> request) {
//        Long storeId = (Long) request.get("storeId");
//        return filmInfoRepository.findByStoreId(storeId);
//    }
    
    
    @PostMapping("/filmByStoreId")
    public ResponseEntity<List<FilmInfo>> getAllFilmInfoByStoreId(@RequestBody Map<String, Object> request) {
        Object storeIdObject = request.get("storeId");
        
        if (storeIdObject == null) {
            // Handle the case where 'storeId' is not provided in the request
            return ResponseEntity.badRequest().body(null); // You can customize this response as needed
        }

        try {
            Long storeId = Long.parseLong(storeIdObject.toString());
            List<FilmInfo> filmInfoList = filmInfoRepository.findByStoreId(storeId);
            return ResponseEntity.ok(filmInfoList);
        } catch (NumberFormatException e) {
            // Handle the case where 'storeId' cannot be parsed as a Long
            return ResponseEntity.badRequest().body(null); // You can customize this response as needed
        }
    }



}
