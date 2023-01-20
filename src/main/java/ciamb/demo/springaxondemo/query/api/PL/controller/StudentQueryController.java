package ciamb.demo.springaxondemo.query.api.PL.controller;

import ciamb.demo.springaxondemo.core.api.rest.StudentRest;
import ciamb.demo.springaxondemo.query.api.BLL.queries.GetStudentsQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentQueryController {
    private final QueryGateway queryGateway;

    public StudentQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public List<StudentRest> getAllStudents() {
        GetStudentsQuery getStudentsQuery =
                new GetStudentsQuery();
        return queryGateway.query(getStudentsQuery, ResponseTypes.multipleInstancesOf(StudentRest.class))
                .join();
    }
}
