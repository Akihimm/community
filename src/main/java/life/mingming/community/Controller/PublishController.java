package life.mingming.community.Controller;
import life.mingming.community.mapper.QuestionMapper;
import life.mingming.community.mapper.UserMapper;
import life.mingming.community.model.Question;
import life.mingming.community.model.User;
import life.mingming.community.service.QuestionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    QuestionService questionService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;
    private Question question;
    private Cookie[] cookies;
    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Long id,
                       Model model){

        Question question1 = questionMapper.selectByPrimaryKey(id);
        model.addAttribute("title",question1.getTitle());
        model.addAttribute("description",question1.getDescription());
        model.addAttribute("tag",question1.getTag());
        model.addAttribute("id",question1.getId());
        return "publish";
    }
    @GetMapping("/publish")
    public String publish(){

        return "publish";
    }
    @PostMapping("/publish")
    public String doPublish(
            @RequestParam ("title") String title,
            @RequestParam ("description")String description,
            @RequestParam ("tag")   String tag,
            @RequestParam(value = "id", required = false) Long id,
            HttpServletRequest request,
            Model model
    ){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        if(title==null||title==""){
            model.addAttribute("error","标题不能为空");
            return"publish";
        }
        if(description==null||title==""){
            model.addAttribute("error","描述不能为空");
            return"publish";
        }
        User user=(User)request.getSession().getAttribute("user");
        if(user==null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }
        question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setId(id);
        questionService.createOrUpdate(question);
        return "redirect:/";
    }
}
