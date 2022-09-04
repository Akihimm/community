package life.mingming.community.dto;

import lombok.Data;

import javax.naming.ldap.PagedResultsControl;
@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatar_url;
}
