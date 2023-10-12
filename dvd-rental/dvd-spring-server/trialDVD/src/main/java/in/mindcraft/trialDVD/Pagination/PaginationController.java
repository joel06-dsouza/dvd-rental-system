package in.mindcraft.trialDVD.Pagination;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaginationController {

   
    private final  PaginationRepository paginationRepository;

     @Autowired
    public PaginationController(PaginationRepository paginationRepository){
    this.paginationRepository=paginationRepository;
    }

    // @PostMapping("/api/pagination")
    // public ResponseEntity<PaginationInfo> getPaginatedData(@RequestBody Map<String, String> request) {
    //      String page = request.get("page");
    //     String size = request.get("size");
    //     if (page == null || size == null) {
    //         return ResponseEntity.badRequest().body(null); 
    //     }
    // PaginationInfo paginationInfo=paginationRepository.paginationByPageAndSize(page,size);
        
    //  if (paginationInfo != null) {
    //         return ResponseEntity.ok(paginationInfo);
    //     } else {
    //         // Handle the case where authentication failed
    //         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // You can customize this response as needed
    //     }
        
    // }
    
    @PostMapping("/api/pagination")
    public ResponseEntity<Page<PaginationInfo>> getPaginatedDataByRequestBody(@RequestBody Map<String, String> request) {
        String pageStr = request.get("page");
        String sizeStr = request.get("size");

        if (pageStr == null || sizeStr == null) {
            return ResponseEntity.badRequest().body(null);
        }

        try {
            int page = Integer.parseInt(pageStr);
            int size = Integer.parseInt(sizeStr);

            PageRequest pageRequest = PageRequest.of(page, size);
            Page<PaginationInfo> paginationInfoPage = paginationRepository.findAll(pageRequest);

            if (paginationInfoPage.hasContent()) {
                return ResponseEntity.ok(paginationInfoPage);
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}

