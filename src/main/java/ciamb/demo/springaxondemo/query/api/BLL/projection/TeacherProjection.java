package ciamb.demo.springaxondemo.query.api.BLL.projection;

import ciamb.demo.springaxondemo.core.api.entity.Teacher;
import ciamb.demo.springaxondemo.core.api.repository.TeacherRepository;
import ciamb.demo.springaxondemo.core.api.rest.TeacherRest;
import ciamb.demo.springaxondemo.query.api.BLL.queries.GetTeachersQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeacherProjection {
    private final TeacherRepository teacherRepository;

    public TeacherProjection(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @QueryHandler
    public List<TeacherRest> handle(GetTeachersQuery getTeachersQuery) {
        List<Teacher> teacherList =
                teacherRepository.findAll();
        return teacherList.stream()
                .map(teacher -> TeacherRest
                        .builder()
                        .name(teacher.getName())
                        .lastName(teacher.getLastName())
                        .build())
                .collect(Collectors.toList());
    }
}
