package DemoApi.controller;


import DemoApi.service.WorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(path = "/chegg")
public class MainController {
//    TransactionService transactionService;
    WorkflowService workflowService;

    @Autowired
    public MainController(WorkflowService workflowService){
        this.workflowService = workflowService;
    }

    @GetMapping("/{personId}")
    public ResponseEntity chegg(@PathVariable("personId") String personId) {
        if (personId == null | personId.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("person Id must be provided");
        }

        if (!personId.matches("[0-9]+")) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("person Id is digits only");
        }

        String res;
        try {
            res = workflowService.processGetRequest(personId);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing person id");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(res);
    }



}
