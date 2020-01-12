package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Cacheable;
import java.util.List;
import java.util.Optional;


@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {

//    @Query("SELECT Course FROM course c  WHERE c.name = :courseName")
//    Course findCourseByCourseName(@Param("courseName") String courseName);

    /*@Query("SELECT new com.mycompany.myapp.domain.dto.CourseDto(c.courseName, c.courseLocation, c.courseContent, c.teacherId) from Course c")
    List<Ship> findAllCoursesDto();

    @Query("SELECT new com.mycompany.myapp.domain.dto.CourseWithTNDto(c.courseName, c.courseLocation, c.courseContent, u.login) from Course c left join User u on c.teacherId = u.id")
    List<CourseWithTNDto> findAllCoursesDtoWithTeacherName();

    //@Query("SELECT new com.mycompany.myapp.domain.dto.CourseWithTNDto(c.courseName, c.courseLocation, c.courseContent, u.login) from Course c left join User u on c.teacherId = u.id")
    Optional<Course> findCourseByCourseName(String courseName);

    @Query("SELECT new com.mycompany.myapp.domain.dto.CourseDto(c.courseName, c.courseLocation, c.courseContent, c.teacherId) from Course c where c.courseName = ?1")
    List<CourseDto> findByName(String targetName);

    //@Query("INSERT INTO course (course.id, course_name, course_location, course_content, teacher_id) VALUES (1, \"name_test2\", \"location_test2\", \"content_test2\", 1)")
    //Optional<Course> saveSqlDirect(Course course);
    */
    @Query("SELECT new com.mycompany.myapp.domain.Ship(s.id, s.shipName, s.country, s.type, s.freqVeryLow, s.freqLow,\n" +
        "s.freqMed, s.freqHigh, s.freqVeryHigh, s.freqActive, s.tpk, s.numBlades) from Ship s where s.shipName = ?1")
    List<Ship> findByName(String targetName);

    @Query("SELECT new com.mycompany.myapp.domain.Ship(s.id, s.shipName, s.country, s.type, s.freqVeryLow, s.freqLow,\n" +
        "s.freqMed, s.freqHigh, s.freqVeryHigh, s.freqActive, s.tpk, s.numBlades) from Ship s where " +
        "((least(s.freqVeryLow / ?1, ?1 / s.freqVeryLow ) >= ?6) or (?1 = -1)) and ((least(s.freqLow / ?2, ?2/s.freqLow) >= ?6) or (?2 = -1))" +
        "and ((least(s.freqMed / ?3, ?3/s.freqMed) >= ?6) or (?3 = -1)) and ((least(s.freqHigh / ?4, ?4/s.freqHigh) >= ?6) or (?4 = -1))" +
        "and ((least(s.freqVeryHigh / ?5, ?5/s.freqVeryHigh) >= ?6) or (?5 = -1)) and ((s.freqActive = ?7) or (?7 = -1))" +
        " and ((s.country = ?8) or (?8 = 'NA'))")
    List<Ship> findByFreq(long vlf, long lf, long mf, long hf, long vhf, double thres, long af, String count);


}
