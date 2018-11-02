package com.ixinhoo.shopping.controller.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ixinhoo.api.client.StatusDto;
import com.ixinhoo.api.shiro.annotation.ProhibitedEntry;
import com.ixinhoo.shopping.dto.question.QuestionDto;
import com.ixinhoo.shopping.service.question.QuestionService;

@Controller
@RequestMapping(value="api/bg/question")
public class QuestionController {

	@Autowired
	private QuestionService service;
	/**
	 * 保存提问
	 * @param dto
	 * @return
	 */
	 
	@RequestMapping(value="save",method=RequestMethod.POST)
	@ResponseBody
	public StatusDto insert(QuestionDto dto) {
		return service.saveQuestion(dto);
	}
}
