package com.ds.project01.controller;

import java.util.HashMap;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ds.project01.dto.HobDataDto;
import com.ds.project01.dto.UserDto;
import com.ds.project01.service.PT_Service;

@Controller
public class PT_Controller {
	
	@Autowired
	PT_Service service;

	@GetMapping("/user/write")
	public String user_write(Model model) {
		
		model.addAttribute("deptList", service.deptList());
		model.addAttribute("hobbyList", service.hobbyList());
		return "user/userform";
	}


	@PostMapping("/user/save")
	public String pt_save(UserDto dto, HobDataDto hdDto) {
		service.insert(dto);
		service.hobbyDataInsert(hdDto);
		
		return "redirect:/";
	}

	@GetMapping("/admin/list")
	public String admin_list(Model model, String searchKeyword) {
		model.addAttribute("adminList", service.adminList(searchKeyword));
		model.addAttribute("deptList", service.deptList());
		model.addAttribute("hobbyList", service.hobbyList());
		
		return "userList/list";
	}
	
	
	@ResponseBody
	@GetMapping("/admin/view")
	HashMap<String, Object> userView(UserDto userDto){ 
		HashMap<String, Object> map = new HashMap<>(); 
		
		String userId =userDto.getUserId();
		for (int i = 0; i < service.HobbyDataView(userId).size(); i++) {
			map.put("userHobChoice"+i, service.HobbyDataView(userId).get(i).getHobbyEntity().getHobbyCd()); 
		}
		map.put("deptList", service.deptList()); 
		map.put("getUerId",service.view(userId).getUserId());
		map.put("getUserNm",service.view(userId).getUserNm());
		map.put("getUserEmlAddr",service.view(userId).getUserEmlAddr());
		map.put("getUserTelno",service.view(userId).getUserTelno());
		map.put("getUserDeptNo",service.view(userId).getDeptEntity().getDeptNo());
		map.put("getUserAprvYn",service.view(userId).getUserAprvYn());
		
		return map;
	}
	
	@PostMapping("/admin/delete")
	public String user_delete(UserDto dto) {
		service.delete(dto);
		return "redirect:admin/list";
	}
	
}