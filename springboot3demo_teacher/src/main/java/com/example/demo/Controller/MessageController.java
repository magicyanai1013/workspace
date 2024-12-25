package com.example.demo.Controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CSform;
import com.example.demo.model.LoginBean;
import com.example.demo.model.Message;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;

import com.example.demo.Repository.CSformRepository;
import com.example.demo.Service.MessageService;

@RestController
@RequestMapping("/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;
    
    @Autowired
    private CSformRepository cSformRepository;

    // 根據ID獲取訊息
    @GetMapping("/{messageId}")
    public ResponseEntity<Message> getMessageById(@PathVariable Integer messageId) {
        Message message = messageService.getMessageById(messageId);
        if (message != null) {
            return ResponseEntity.ok(message);
        }
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        try {
            message.setMessageDate(new Date());
            Message savedMessage = messageService.createMessage(message);
            return ResponseEntity.ok(savedMessage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //處理分類篩選功能
    @GetMapping
    public List<Message> getAllMessages(@RequestParam(required = false) String category) {
        if (category != null && !category.equals("all")) {
            return messageService.getMessagesByCategory(category);
        }
        return messageService.getAllMessages();
    }
    
    @PostMapping("/create/{csFormId}")
    public ResponseEntity<?> createMessageFromCSform(@PathVariable Integer csFormId) {
        try {
            Message message = messageService.createMessageFromCSform(csFormId);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("建立訊息失敗：" + e.getMessage());
        }
    }

    @GetMapping("/checkLogin")
    public ResponseEntity<?> checkLoginStatus(HttpSession session) {
        try {
            // 從 session 中取得 LoginBean
            LoginBean user = (LoginBean) session.getAttribute("user");
            
            // 檢查使用者是否登入
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("使用者尚未登入，無法取得 userId");
            }
            
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("檢查登入狀態時發生錯誤：" + e.getMessage());
        }
    }
    
    // 處理使用者特定的訊息
    @GetMapping("/messages/user/{userId}")
    public ResponseEntity<?> getUserMessages(@PathVariable Integer userId, HttpSession session) {
        try {
            // 從 session 中取得 LoginBean
            LoginBean user = (LoginBean) session.getAttribute("user");
            
            // 檢查使用者是否登入
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("未登入，請先登入後再嘗試");
            }

            // 將 session 中的 userId 轉換為 Integer 以進行比較
            Integer sessionUserId = user.getUserId();

            // 驗證請求的 userId 與登入用戶是否相符
            if (!sessionUserId.equals(userId)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("無權限查看其他用戶的訊息");
            }

            List<Message> userMessages = messageService.getMessagesByUserId(userId);
            return ResponseEntity.ok(userMessages);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("載入訊息失敗：" + e.getMessage());
        }
    }

    @GetMapping("/messages/center")
    public String showMessageCenter(Model model, HttpSession session) {
        LoginBean user = (LoginBean) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("userId", user.getUserId());
            return "Customer_Service/messageCenter";
        } else {
            return "redirect:/login";
        }
    }
    
}
