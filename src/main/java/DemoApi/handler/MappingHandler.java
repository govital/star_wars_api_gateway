package DemoApi.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class MappingHandler {
    private ObjectMapper mapper;

    public MappingHandler() {
        this.mapper = new ObjectMapper();
        this.mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public <T> Object jsonToObj(String json, String classNema) throws JsonProcessingException, ClassNotFoundException {
        return mapper.readValue(json, Class.forName(classNema));
    }



}
