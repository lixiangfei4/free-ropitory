package com.ixinhoo.shopping.service.question;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ixinhoo.api.client.StatusDto;
import com.ixinhoo.api.dao.BaseDao;
import com.ixinhoo.api.service.BaseService;
import com.ixinhoo.crumbs.code.util.mapper.BeanMapper;
import com.ixinhoo.shopping.dao.question.QuestionDao;
import com.ixinhoo.shopping.dto.question.QuestionDto;
import com.ixinhoo.shopping.entity.question.Question;

@Service
public class QuestionService extends BaseService<Question> {
    @Autowired
    private QuestionDao dao;


    @Override
    protected BaseDao<Question> getBaseDao() {
        return dao;
    }
    /**
     * 插入一条提问
     * lf
     * @param rDto
     * @return
     */
    @Transactional(readOnly=false)
    public StatusDto saveQuestion(QuestionDto reqdto) {
    	StatusDto dto = new StatusDto(false);
    	if(reqdto==null) {
    		dto.setMsg("参数不能为空");
    	}else {
    	Question q = BeanMapper.map(reqdto, Question.class);
    	String date = DateFormatUtils.format(new Date(System.currentTimeMillis()), "yyMMdd"); 
    	q.setCreateTime(Long.parseLong(date));
    	q.setUpdateTime(q.getCreateTime());
    	dao.insert(q);
    	dto.setStatus(true);
    	return dto;
    	}
    	return dto;
    }
    	
   }