package com.example.demo.Service;

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

	public CSform addform(String CSFormSort,String CSFormTitle,String CSFormContent) {
	CSform csf=new CSform(); 
	csf.setCSFormSort(CSFormSort);
	csf.setCSFormTitle(CSFormTitle);
	csf.setCSFormContent(CSFormContent);
	
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
	
}
