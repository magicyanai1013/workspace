package com.example.demo.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Repository.CSformRepository;
import com.example.demo.Service.CSformService;
import com.example.demo.model.CSform;

@Controller
public class CSformController {

    @Autowired
    private CSformService csfs;
    
    @Autowired
    private CSformRepository CSformRepository;

 // 顯示會員資料總覽頁面 (http://localhost:8080/form_manage)
    @GetMapping("/form_manage")
    public String showform_manage() {
        return "Customer_Service/form_manage";
    }    
    
 // 顯示填寫表單頁面 (http://localhost:8080/csform)
    @GetMapping("/csform")
    public String csform() {
        return "Customer_Service/form";
    }
     
//  新增表單資料 (http://localhost:8080/form_manage/formadd)
    @PostMapping("/form_manage/formadd")
    public String addform(String CSFormSort,String CSFormTitle,String CSFormContent,Date CSFormDate) {
    	csfs.addform(CSFormSort,CSFormTitle,CSFormContent,CSFormDate);
    	return "Customer_Service/form";
    }
    
    

    // 取得所有表單資料，回傳 JSON 格式 (http://localhost:8080/form_manage/json)
    @GetMapping("/form_manage/json")
    @ResponseBody
    public List<CSform> getform_manageJson() {
        return CSformRepository.findAll();
    }
      
    @PostMapping("/submitReply")
    @ResponseBody
    public ResponseEntity<String> submitReply(@RequestParam Integer CSFormId, @RequestParam String CSFormReply, @RequestParam Integer CSFormChack) {
        // 處理邏輯
        System.out.println("收到的 formId：" + CSFormId);
        System.out.println("回覆內容：" + CSFormReply);
        System.out.println("狀態：" + CSFormChack);
        
        // 假設你這裡是根據表單 ID 更新表單的回覆和狀態
        CSform existingForm = CSformRepository.findByCSFormId(CSFormId);
        
        if (existingForm != null) {
            existingForm.setCSFormReply(CSFormReply);
            existingForm.setCSFormChack(CSFormChack);
            CSformRepository.save(existingForm);
            return ResponseEntity.ok("回覆成功");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("找不到該表單");
        }
    }

    

}


