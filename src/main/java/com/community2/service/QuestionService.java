package com.community2.service;

import com.community2.dto.PaginationDTO;
import com.community2.dto.QuestionDTO;
import com.community2.exception.CustomizeErrorCodeCode;
import com.community2.exception.CustomizeException;
import com.community2.mapper.QuestionMapper;
import com.community2.mapper.UserMapper;
import com.community2.model.Question;
import com.community2.model.QuestionExample;
import com.community2.model.User;
import org.apache.ibatis.session.RowBounds;
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
        List<Question> questions = questionMapper.selectByExampleWithBLOBsWithRowbounds(new QuestionExample(),new RowBounds(offsize,size));
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO); //快速的将question的属性拷贝到questionDTO上
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        Integer totalCount = (int)questionMapper.countByExample(new QuestionExample());
        paginationDTO.setPagination(totalCount,page,size);

        return paginationDTO;
    }

    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        if(page<1) page = 1;
        Integer offsize = size*(page-1);
        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andCreatorEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithBLOBsWithRowbounds(example,new RowBounds(offsize,size));
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO); //快速的将question的属性拷贝到questionDTO上
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria()
                .andCreatorEqualTo(userId);
        Integer totalCount = (int)questionMapper.countByExample(questionExample);
        paginationDTO.setPagination(totalCount,page,size);

        return paginationDTO;
    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if(question == null) {
            throw new CustomizeException(CustomizeErrorCodeCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);

        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }
    public void createOrUpdate(Question question){
        //如果没有id则创建，有id则更新
        if(question.getId() == null){
            question.setGmtModified(System.currentTimeMillis());
            question.setGmtCreate(System.currentTimeMillis());
            questionMapper.insert(question);
        }else{
            Question updateQuestion = new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            QuestionExample example =new QuestionExample();
            example.createCriteria()
                    .andIdEqualTo(question.getId());
            int updated = questionMapper.updateByExampleSelective(updateQuestion,example);
            if(updated != 1){
                throw new CustomizeException(CustomizeErrorCodeCode.QUESTION_NOT_FOUND);
            }
        }
    }
}
