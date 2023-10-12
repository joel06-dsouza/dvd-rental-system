// package in.mindcraft.trialDVD.Admin.Login;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import java.util.Map;

// @RestController
// @RequestMapping("/admin")
// public class LoginController {

//     private final LoginRepository loginRepository;

//     @Autowired
//     public LoginController(LoginRepository loginRepository) {
//         this.loginRepository = loginRepository;
//     }

//     @PostMapping("/login")
//     public ResponseEntity<LoginInfo> loginAdmin(@RequestBody Map<String, String> request) {
//         String username = request.get("username");
//         String password = request.get("password");

//         if (username == null || password == null) {
//             // Handle the case where 'username' or 'password' is not provided in the request
//             return ResponseEntity.badRequest().body(null); // You can customize this response as needed
//         }

//         LoginInfo loginInfo = loginRepository.findByUsernameAndPassword(username, password);

//         if (loginInfo != null) {
//             return ResponseEntity.ok(loginInfo);
//         } else {
//             // Handle the case where authentication failed
//             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // You can customize this response as needed
//         }
//     }
// }


package in.mindcraft.trialDVD.Admin.Login;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import in.mindcraft.trialDVD.Staff.JwtTokenProvider;
import in.mindcraft.trialDVD.Staff.JwtToken;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class LoginController {

    private final LoginRepository loginRepository;

    @Autowired
    public LoginController(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
@PostMapping("/login")
public ResponseEntity<Map<String, Object>> loginAdmin(@RequestBody Map<String, String> request) {
    String username = request.get("username");
    String password = request.get("password");
    long storeId=1;

    if (username == null || password == null) {
       
        return ResponseEntity.badRequest().body(null); 
    }

    LoginInfo admin = loginRepository.findByUsernameAndPassword(username, password);

    if (admin != null) {
      
        String token = jwtTokenProvider.generateToken(username,storeId);
        String adminFullName = admin.getFirst_name() + " " + admin.getLast_name();

        Map<String, Object> response = new HashMap<>();
        response.put("jwtToken", token);
        response.put("adminFullName", adminFullName);
        response.put("adminId", admin.getId()); 
        
        return ResponseEntity.ok(response);
    } else {
       
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); 
    }
}

}