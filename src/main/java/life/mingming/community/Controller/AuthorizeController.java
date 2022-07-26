package life.mingming.community.Controller;

import life.mingming.community.dto.AccessTokenDTO;
import life.mingming.community.dto.GithubUser;
import life.mingming.community.mapper.UserMapper;
import life.mingming.community.model.User;
import life.mingming.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GitHubProvider gitHubProvider;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state")String state,
                           HttpServletRequest request) throws IOException {

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_secret(clientSecret);
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = gitHubProvider.getUser(accessToken);
        if(githubUser!=null){
            User user=new User();
            user.setToken(UUID.randomUUID().toString());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setName(githubUser.getName());
            user.setGmtModified(System.currentTimeMillis());
            user.setGmtCreate(user.getGmtModified());
            userMapper.insert(user);
            //登录成功写cookie和session
                request.getSession().setAttribute("user",githubUser);
                return "redirect:/";
        }else {
            //登陆失败重新定向到首页
                return "redirect:/";
        }

    }
}
