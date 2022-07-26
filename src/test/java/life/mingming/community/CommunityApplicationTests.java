package life.mingming.community;

import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class CommunityApplicationTests {

    @Test
    void contextLoads() {
        String s = UUID.randomUUID().toString();
        String s1 = UUID.randomUUID().toString();
        System.out.println(s);
        System.out.println(s1);
    }

}
