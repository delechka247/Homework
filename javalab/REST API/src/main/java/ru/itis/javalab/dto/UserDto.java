package ru.itis.javalab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.models.Order;

import javax.persistence.*;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;

    private List<Order> orders;

//    public static TeacherDto from(Teacher teacher) {
//        TeacherDto result = TeacherDto.builder()
//                .id(teacher.getId())
//                .firstName(teacher.getFirstName())
//                .lastName(teacher.getLastName())
//                .build();
//
//        if (teacher.getCourses() != null) {
//            result.setCourses((teacher.getCourses().stream().map(Course::getTitle).collect(Collectors.toList())));
//        }
//        return result;
//    }
//
//    public static List<TeacherDto> from(List<Teacher> teachers) {
//        return teachers.stream().map(TeacherDto::from).collect(Collectors.toList());
//    }

}