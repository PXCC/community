package com.community2.dto;

import com.community2.model.User;
import lombok.Data;

@Data
public class QuestionDTO {
    private Long id;
    private  String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private String tag;
    private Long creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;

}
