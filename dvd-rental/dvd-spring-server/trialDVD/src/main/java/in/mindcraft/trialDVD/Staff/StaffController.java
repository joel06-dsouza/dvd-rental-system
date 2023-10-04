package in.mindcraft.trialDVD.Staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/staff")
public class StaffController {
	public long S_id;
    @Autowired
    private StaffRepository staffRepository;

    @GetMapping("/allStaff")
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Staff> getStaffById(@PathVariable Long id) {
        Optional<Staff> staff = staffRepository.findById(id);

        if (staff.isPresent()) {
            return ResponseEntity.ok(staff.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
//   
//
//  
    
    @GetMapping("/login")
    public ResponseEntity<Long> login(@RequestParam String username, @RequestParam String password) {
        // Find all Staff with the given username
        List<Staff> staffList = staffRepository.findAllByUsername(username);
        String msg = "login success";

        for (Staff staff : staffList) {
            if (staff.getPassword().equals(password)) {
                long S_id = staff.getStoreId(); // Assuming getStoreId() is the getter method for storeId
                System.out.println("Store ID "+S_id);
                return ResponseEntity.ok(S_id);
            }
        }



        // If no matching user was found, return an error response
        return ResponseEntity.notFound().build();
    }
    

//    @GetMapping("/allFilmInfo")
//    public ResponseEntity<List<FilmInfo>> getAllFilmInfo() {
//        List<FilmInfo> filmInfoList = staffRepository.findAllFilmInfo();
//
//        if (!filmInfoList.isEmpty()) {
//            return ResponseEntity.ok(filmInfoList);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

}


    
