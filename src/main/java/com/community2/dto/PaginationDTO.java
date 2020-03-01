package com.community2.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious=false;
    private boolean showFirstPage=false;
    private boolean showNext=true;
    private boolean showEndPage=false;
    //当前页的页码
    private Integer currentPage;
    //当前显示的所有页码，存放在数组中
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage=1;


    public void setPagination(Integer totalCount, Integer page, Integer size) {

        if(page<1) page = 1;

        Integer totalPage = 0;
        if(totalCount%size == 0 ){
            totalPage = totalCount/size;
        }else{
            totalPage = totalCount/size + 1;
        }
        if(totalCount == 0) totalPage = 1;
        this.totalPage = totalPage;
        if(page>totalPage) page = totalPage;
        this.currentPage = page;

        pages.add(page);
        for(int i=1;i<=3;i++){
            if(page-i>0) pages.add(0,page-i);
            if(page+i<=totalPage) pages.add(page+i);
        }
        //是否展示上一页
        if(page > 1) showPrevious = true; else showPrevious = false;
        //是否展示下一页
        if(page < totalPage) showNext = true; else showNext = false;
        //是否展示回到第一页
        if(!pages.contains(1)) showFirstPage = true; else showFirstPage = false;
        //是否展示跳转到最后一页
        if(!pages.contains(totalPage)) showEndPage = true; else showEndPage = false;
    }
}
