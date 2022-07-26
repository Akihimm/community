package life.mingming.community.mapper;

import life.mingming.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
        @Insert("insert into github_user (ACCOUNT_ID,NAME,TOKEN,GMT_CREATE,GMT_MODIFIED) values (#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified})")
        public void insert(User user);
    }

