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
    
    // 提交回覆並更新狀態
    @PostMapping("/submitReply")
    public ResponseEntity<String> submitReply(@RequestParam int formId, @RequestParam String replyContent, @RequestParam int status) {
        boolean isUpdated = csfs.submitReply(formId, replyContent, status);
        if (isUpdated) {
            return ResponseEntity.ok("回覆成功");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("回覆失敗");
        }
    }
    

    // 處理表單資料更新 (PUT 請求，URL: http://localhost:8080/form_manage/json/{CSFormId})
    @PutMapping("/form_manage/json/{CSFormId}")
    @ResponseBody
    public String updateFormStatus(@PathVariable("CSFormId") Integer CSFormId, @RequestBody CSform updatedForm) {
        // 查詢資料庫中的表單
        CSform existingForm = CSformRepository.findByCSFormId(CSFormId);
        
        if (existingForm != null) {
            // 更新表單的狀態
            existingForm.setCSformChack(updatedForm.getCSformChack());
            
            // 儲存更新後的表單資料
            CSformRepository.save(existingForm);
            return "成功更新表單資料";
        } else {
            return "找不到該表單";
        }
    }
}
