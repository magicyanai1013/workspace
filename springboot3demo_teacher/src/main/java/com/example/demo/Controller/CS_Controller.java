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
    
//	常見問題-帳號相關 (http://localhost:8080/CSQAData/account)
    @GetMapping("/CSQAData/account")
    public String showCSQAaccount() {
        return "Customer_Service/QA_account";
    }
    
//	常見問題-平台守則 (http://localhost:8080/CSQAData/rules)
    @GetMapping("/CSQAData/rules")
    public String showCSQArules() {
        return "Customer_Service/QA_rules";
    }
    
//	常見問題-買家相關 (http://localhost:8080/CSQAData/buyer)
    @GetMapping("/CSQAData/buyer")
    public String showCSQAbuyer() {
        return "Customer_Service/QA_buyer";
    }
    
//	常見問題-賣家相關 (http://localhost:8080/CSQAData/seller)
    @GetMapping("/CSQAData/seller")
    public String showCSQAseller() {
        return "Customer_Service/QA_seller";
    }
    
//	常見問題-其他問題 (http://localhost:8080/CSQAData/other)
    @GetMapping("/CSQAData/other")
    public String showCSQAother() {
        return "Customer_Service/QA_other";
    }
    
}
