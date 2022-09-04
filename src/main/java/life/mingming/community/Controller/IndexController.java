package life.mingming.community.Controller;

import life.mingming.community.dto.PaginationDTO;
import life.mingming.community.dto.QuestionDTO;
import life.mingming.community.mapper.QuestionMapper;
import life.mingming.community.mapper.UserMapper;
import life.mingming.community.model.Question;
import life.mingming.community.model.User;
import life.mingming.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
     private UserMapper userMapper;
    private Cookie[] cookies;

    @GetMapping("/")
       public String index(HttpServletRequest request
            ,Model model
            ,@RequestParam(name = "page",defaultValue = "1") Integer page
            ,@RequestParam(name = "size",defaultValue = "5") Integer size){

        PaginationDTO pagination=questionService.list(page,size);
        model.addAttribute("pagination",pagination);
        return "index";}
}
