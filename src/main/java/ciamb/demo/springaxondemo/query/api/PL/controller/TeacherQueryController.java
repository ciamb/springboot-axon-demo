package ciamb.demo.springaxondemo.query.api.PL.controller;

import ciamb.demo.springaxondemo.core.api.rest.TeacherRest;
import ciamb.demo.springaxondemo.query.api.BLL.queries.GetTeachersQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherQueryController {
    private final QueryGateway queryGateway;

    public TeacherQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public List<TeacherRest> getAllTeachers() {
        GetTeachersQuery getTeachersQuery =
                new GetTeachersQuery();
        return queryGateway.query(getTeachersQuery, ResponseTypes.multipleInstancesOf(TeacherRest.class))
                .join();
    }
}
