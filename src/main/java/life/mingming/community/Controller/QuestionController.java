package life.mingming.community.Controller;

import life.mingming.community.dto.QuestionDTO;
import life.mingming.community.mapper.QuestionMapper;
import life.mingming.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.criteria.CriteriaBuilder;

@Controller
public class QuestionController {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionService questionService;
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name="id") Integer id,
                           Model model){
          QuestionDTO questionDTO= questionService.getById(id);
          model.addAttribute("question",questionDTO);
        return "question";
    }
}
