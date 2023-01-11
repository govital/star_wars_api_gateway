package DemoApi.service;

import DemoApi.handler.MappingHandler;
import DemoApi.model.ApiMainModel;
import DemoApi.handler.RestCallHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;

@Service
public class WorkflowService {
    private RestCallHandler restCallHandler;
    private MappingHandler mapper;

    @Value("${chegg.starwarsapi.path}")
    private String starwarsPath;

    @Autowired
    public WorkflowService(RestCallHandler restCallHandler, MappingHandler mapper) {
        this.restCallHandler = restCallHandler;
        this.mapper = mapper;
    }

    private String getDataById(String id) throws IOException {
        URL url = new URL(starwarsPath+id);
        return restCallHandler.stringContentCall(url, "GET");
    }

    public String processGetRequest(String id) throws IOException, ClassNotFoundException {
        ApiMainModel swm = (ApiMainModel)mapper.jsonToObj(getDataById(id), "DemoApi.model.ApiMainModel");
        return swm.toString();
    }



}
