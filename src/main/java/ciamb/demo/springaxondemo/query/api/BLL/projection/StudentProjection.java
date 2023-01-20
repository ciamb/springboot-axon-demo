package ciamb.demo.springaxondemo.query.api.BLL.projection;

import ciamb.demo.springaxondemo.core.api.entity.Student;
import ciamb.demo.springaxondemo.core.api.repository.StudentRepository;
import ciamb.demo.springaxondemo.core.api.rest.StudentRest;
import ciamb.demo.springaxondemo.query.api.BLL.queries.GetStudentsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentProjection {

    private final StudentRepository studentRepository;

    public StudentProjection(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @QueryHandler
    public List<StudentRest> handle(GetStudentsQuery getStudentsQuery) {
        List<Student> studentList =
                studentRepository.findAll();
        return studentList.stream()
                .map(student -> StudentRest
                        .builder()
                        .name(student.getName())
                        .lastName(student.getLastName())
                        .birthDate(student.getBirthDate())
                        .build())
                .collect(Collectors.toList());
    }
}
