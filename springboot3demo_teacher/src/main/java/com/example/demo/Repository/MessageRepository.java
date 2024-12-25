package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Message;


//定義一個介面 MessageRepository，繼承 JpaRepository 來進行 Message 的 CRUD 操作
public interface MessageRepository extends JpaRepository<Message, Integer> {

 // 自定義查詢方法，根據 messageId 查找對應的 Message 實體
	Message findByMessageId(Integer MessageId);
	
	List<Message> findByMessageSort(String messageSort);
}

	
