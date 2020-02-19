package com.community2.service;

import com.community2.dto.PaginationDTO;
import com.community2.dto.QuestionDTO;
import com.community2.mapper.QuestionMapper;
import com.community2.mapper.UserMapper;
import com.community2.model.Question;
import com.community2.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {
        if(page<1) page = 1;
        Integer offsize = size*(page-1);
        List<Question> questions = questionMapper.list(offsize,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO); //快速的将question的属性拷贝到questionDTO上
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        Integer totalCount = questionMapper.count();
        paginationDTO.setPagination(totalCount,page,size);

        return paginationDTO;
    }

}
