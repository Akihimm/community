package life.mingming.community.mapper;

import life.mingming.community.model.Question;
import life.mingming.community.model.QuestionExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
@Mapper
public interface QuestionExtMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Wed Sep 07 10:56:57 CST 2022
     */
    int incView(Question record);
    int incCommentCount(Question record);
}