package com.example.demo.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.CSformRepository;
import com.example.demo.model.CSform;

@Service
public class CSformService {
	
	@Autowired
	private CSformRepository csfr;

	public CSform addform(String CSFormSort, String CSFormTitle, String CSFormContent,Date CSFormDate) {
        CSform csf = new CSform();
        csf.setCSFormSort(CSFormSort);
        csf.setCSFormTitle(CSFormTitle);
        csf.setCSFormContent(CSFormContent);
        csf.setCSFormDate(new Date());  // 設置當前時間
        // 儲存並返回結果
        return csfr.save(csf);
    }
	
	public CSform findcsfById(Integer CSFormId) {
		Optional<CSform> optional = csfr.findById(CSFormId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	public List<CSform> findAllcsf(){
		return csfr.findAll();
	}
	
	 // 提交回覆並更新狀態
    public boolean submitReply(int CSFormId, String CSFormReply, int CSFormChack) {
        // 查詢指定的表單
        CSform form = csfr.findByCSFormId(CSFormId);
        
        // 若表單存在，則進行更新
        if (form != null) {
            form.setCSFormReply(CSFormReply);  // 更新回覆內容
            form.setCSFormChack(CSFormChack);  // 更新狀態
            csfr.save(form);  // 儲存更新的表單
            return true;
        }
        return false;
    }
	
}
