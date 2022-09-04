package life.mingming.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer currentPage;
    private List<Integer> pages=new ArrayList<>();//给前端展示的页面的集合
    private Integer totalPage;
    public void setPagination(Integer totalCount, Integer page, Integer size) {
        this.currentPage=page;
         totalPage=0;
        if(totalCount%size==0){
        totalPage=totalCount/size;
        }else {
            totalPage=totalCount/size+1;
        }
        if(page<1){
            page=1;
        }
        if(page>totalPage){
            page=totalPage;
        }
        this.currentPage=page;
        pages.add(page);
        for (int i=1;i<=3;i++){
            if(page-i>0){
                pages.add(0,page-i);
            }
            if (page+i<=totalPage){
                pages.add(page+i);
            }
        }
        //是否展示上一页
        if(page==1){
            showPrevious=false;
        }else {
            showPrevious=true;
        }
        if(page==totalPage){
            showNext=false;
        }else {
            showNext=true;
        }
        //是否展示第一页那个按钮
        if(currentPage==1){
            showFirstPage=false;
        }else {
            showFirstPage=true;
        }
        //是否展示最后一页按钮
        if(currentPage==totalPage){
            showEndPage=false;
        }else{
            showEndPage=true;   
        }
    }
}
