package com.example.demo.Controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Repository.CSQADataRepository;
import com.example.demo.Service.QADataService;
import com.example.demo.model.CSQAData;

@Controller  // 使用 @Controller 來渲染視圖
public class CSQADataController {

    @Autowired
    private CSQADataRepository CSQADataRepository;
    
    @Autowired
    private QADataService qads;
    
    // 顯示常見問題總覽頁面 (http://localhost:8080/QA_manage)
    @GetMapping("/QA_manage")
    public String showQA_manage() {
        return "Customer_Service/QA_manage";
    }

    // 取得所有常見問題內容，回傳 JSON 格式 (http://localhost:8080/QA_manage/json)
    @GetMapping("/QA_manage/json")
    @ResponseBody
    public List<CSQAData> getQA_manageJson() {
        return CSQADataRepository.findAll();
    }
    
    //新增常見問題(http://localhost:8080/QA_manage/addQA)
    @PostMapping("/QA_manage/addQA")
    @ResponseBody
    public String addQA(String CSQADataSort,String CSQADataTitle,String CSQADataContent,Date CSQADataDATE) {
    	qads.addQA(CSQADataSort,CSQADataTitle,CSQADataContent,CSQADataDATE);
    	return "QA_manage/addQA";
    }

    // 根據ID找出對應資料至修改窗口中(http://localhost:8080/QA_manage/json/QADataId/3)
    @GetMapping("/QA_manage/json/QADataId/{CSQADataId}")
    @ResponseBody
    public CSQAData getQAById(@PathVariable Integer CSQADataId) {
        return CSQADataRepository.findById(CSQADataId)
                 .orElseThrow(() -> new RuntimeException("找不到指定的資料"));
    }

    @PutMapping("/QA_manage/json/{CSQADataId}")
    @ResponseBody  // 重要：確保直接返回 JSON
    public CSQAData updateQAData(
        @PathVariable Integer CSQADataId, 
        @RequestBody CSQAData updatedData
    ) {
        // 直接調用服務層的更新方法
        return qads.updateQAData(CSQADataId, updatedData);
    }
    
}
    
    

