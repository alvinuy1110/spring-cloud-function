import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.cloud.function.adapter.openwhisk.OpenWhiskActionRequest;
import org.springframework.cloud.function.adapter.openwhisk.OpenWhiskInitRequest;

import java.util.HashMap;
import java.util.Map;


public class MyTest {

    @Test
    public void testInit() throws Exception {
        OpenWhiskInitRequest openWhiskInitRequest = new OpenWhiskInitRequest();
     ObjectMapper objectMapper = new ObjectMapper();
        String json =objectMapper.writeValueAsString(openWhiskInitRequest);

        System.out.println(json);
    }
    @Test
    public void test1() throws Exception {
        OpenWhiskActionRequest openWhiskActionRequest = new OpenWhiskActionRequest();

        Map<String, Object> map = new HashMap<>();
        map.put("payload","hello");
        openWhiskActionRequest.setValue(map);
        ObjectMapper objectMapper = new ObjectMapper();
        String json =objectMapper.writeValueAsString(openWhiskActionRequest);

        System.out.println(json);
    }
}
