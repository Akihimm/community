package life.mingming.community.mapper;

import life.mingming.community.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("select * from github_user where token=#{token}")
     public User findByToken(@Param("token") String token);


    @Insert("insert into github_user (ACCOUNT_ID,NAME,TOKEN,GMT_CREATE,GMT_MODIFIED,AVATAR_URL) values (#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
        public void insert(User user);
    @Select("select * from github_user where id=#{id}")
    User findById(@Param("id")  Integer id);
    @Select("select * from github_user where account_id=#{accountId}")
    User findByAccountId(@Param("accountId") String accountId);
    @Update("update user set name=#{name},token=#{token},gmt_modified=#{gmtModified},avatar_url=#{avatarUrl}where id=#{id}")
    void update(User dbUser);
}

