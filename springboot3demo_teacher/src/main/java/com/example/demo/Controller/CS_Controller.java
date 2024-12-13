package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CS_Controller {
	
//	客服首頁 (http://localhost:8080/CSFrontpage)
    @GetMapping("/CSFrontpage")
    public String showCSFrontpage() {
        return "Customer_Service/CSFrontpage";
    }
    
    
    
}
