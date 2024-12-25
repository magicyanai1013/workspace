package com.example.demo.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.CSformRepository;
import com.example.demo.Repository.MessageRepository;
import com.example.demo.model.CSform;
import com.example.demo.model.LoginBean;
import com.example.demo.model.Message;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private CSformRepository csformRepository;

 // 獲取所有訊息
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }
    
 // 根據CSform建立新的Message
    public Message createMessageFromCSform(Integer csFormId) {
        // 加入錯誤處理和日誌記錄
        try {
            CSform csform = csformRepository.findById(csFormId)
                    .orElseThrow(() -> new RuntimeException("找不到指定的表單ID: " + csFormId));
            
            // 確保必要欄位不為空
            if (csform.getCSFormReply() == null || csform.getUserId() == null) {
                throw new RuntimeException("表單缺少必要資訊");
            }
            
            Message message = new Message();
            message.setMessageTitle("客服回覆: " + csform.getCSFormTitle());
            message.setMessageDate(new Date());
            message.setMessageUserId(csform.getUserId());
            message.setMessageContent(csform.getCSFormReply());
            
            // 在儲存前記錄資訊
            System.out.println("正在創建訊息：" + message.getMessageTitle());
            
            return messageRepository.save(message);
            
        } catch (Exception e) {
            System.err.println("創建訊息時發生錯誤：" + e.getMessage());
            throw new RuntimeException("創建訊息失敗：" + e.getMessage());
        }
    }
    
    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }
    
    public List<Message> getMessagesByCategory(String category) {
        if (category == null || category.equals("all")) {
            return getAllMessages();
        }
        return messageRepository.findByMessageSort(category);
    }


    
    // 根據MessageId查詢訊息
    public Message getMessageById(Integer messageId) {
        return messageRepository.findByMessageId(messageId);
    }
    
    //根據UserID查詢訊息
    public List<Message> getMessagesByUserId(Integer userId) {
        return messageRepository.findAll().stream()
                .filter(message -> userId.equals(message.getMessageUserId()))
                .collect(Collectors.toList());
    }
    
}
