package life.mingming.community.service;

import life.mingming.community.enums.CommentTypeEnum;
import life.mingming.community.exception.CustomizeErrorCode;
import life.mingming.community.exception.CustomizeException;
import life.mingming.community.mapper.CommentMapper;
import life.mingming.community.mapper.QuestionExtMapper;
import life.mingming.community.mapper.QuestionMapper;
import life.mingming.community.model.Comment;
import life.mingming.community.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    QuestionExtMapper questionExtMapper;
    @Transactional //添加事务如果那个增加评论数的操作没有成功那么全部回滚掉
    public void insert(Comment comment) {
        if(comment.getParentId()==null||comment.getParentId()==0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if(comment.getType()==null|| !CommentTypeEnum.isExist(comment.getType())){
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if(comment.getType()==CommentTypeEnum.COMMENT.getType()){
            //回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if(dbComment==null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insertSelective(comment);
        }else {
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            //回复问题
            if(question==null){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insertSelective(comment);
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);
        }
    }
}
