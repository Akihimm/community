package life.mingming.community;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommunityApplicationTests {

    @Test
    void contextLoads() {
        String string="access_token=ghu_3RPyIi8XMTnyxe8o54Oe2IAo25ai6j45VdXI&expires_in=28800&refresh_token=ghr_ryXC1jyV8hlFyRzyHjivR5eP6PxGkBEpyj83H0lkB6OAZsG3UQ2eG8aJMRiU5OKeopiyYf02Pcnf&refresh_token_expires_in=15897599&scope=&token_type=bearer\n";
        String[] split = string.split("&");
        String tokenstr=split[0]+"&"+split[1]+"&"+split[2]+"&"+split[3];
        String[] split1 = tokenstr.split("=");
        String token=split1[1]+"="+split1[2]+"="+split1[3]+"="+split1[4];
        System.out.println(token);
    }

}
