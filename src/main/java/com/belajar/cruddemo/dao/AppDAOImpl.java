package com.belajar.cruddemo.dao;

import com.belajar.cruddemo.entity.Course;
import com.belajar.cruddemo.entity.Instructor;
import com.belajar.cruddemo.entity.InstructorDetail;
import com.belajar.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{

    EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        // retrieve the instructor
        Instructor tempInstructor = entityManager.find(Instructor.class, theId);

        //
        List<Course> courses = findCoursesByInstructorId(theId);

        for (Course tempCourse : courses){
            tempCourse.setInstructor(null);
        }

        // delete the instructor
        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, theId);

        // remove the associated object reference
        // break bi-directional link
        //
        instructorDetail.getInstructor().setInstructorDetail(null);

        entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {

        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id = :data", Course.class);

        query.setParameter("data", theId);
        // execute query

        List<Course> courses = query.getResultList();

        return courses;
    }

    /*@Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i "
                        + "JOIN FETCH i.courses "
                        + "JOIN FETCH i.instructorDetail "
                        + "where i.id = :data", Instructor.class);
        query.setParameter("data", theId);
        List<Instructor> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }*/

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i "
                        + "LEFT JOIN FETCH i.courses "
                        + "LEFT JOIN FETCH i.instructorDetail "
                        + "where i.id = :data", Instructor.class);
        query.setParameter("data", theId);
        List<Instructor> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    @Transactional
    public void Update(Instructor tempInstructor) {
        entityManager.merge(tempInstructor);
    }

    @Override
    @Transactional
    public void Update(Course tempCourse) {
        entityManager.merge(tempCourse);
    }

    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class, theId);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        Course tempCourse = entityManager.find(Course.class, theId);
        entityManager.remove(tempCourse);
    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        entityManager.persist(theCourse);
    }


    /*@Override
    public Course findCourseAndReviewsByCourseId(int theId) {
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                        + "JOIN FETCH c.reviews "
                        + "where c.id = :data", Course.class);
        query.setParameter("data", theId);
        List<Course> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }*/

    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                        + "LEFT JOIN FETCH c.reviews "
                        + "where c.id = :data", Course.class);
        query.setParameter("data", theId);
        List<Course> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    /*@Override
    public Course findCourseAndStudentByCourseId(int theId) {
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                        + "JOIN FETCH c.students "
                        + "where c.id = :data", Course.class);
        query.setParameter("data", theId);
        List<Course> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }*/
    @Override
    public Course findCourseAndStudentByCourseId(int theId) {
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                        + "LEFT JOIN FETCH c.students "
                        + "where c.id = :data", Course.class);
        query.setParameter("data", theId);
        List<Course> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }


    /*@Override
    public Student findStudentAndCoursesByStudentId(int theId) {
        TypedQuery<Student> query = entityManager.createQuery(
                "select s from Student s "
                        + "JOIN FETCH s.courses "
                        + "where s.id = :data", Student.class);
        query.setParameter("data", theId);
        List<Student> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }*/

    @Override
    public Student findStudentAndCoursesByStudentId(int theId) {
        TypedQuery<Student> query = entityManager.createQuery(
                "select s from Student s "
                        + "LEFT JOIN FETCH s.courses "
                        + "where s.id = :data", Student.class);
        query.setParameter("data", theId);
        List<Student> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    @Transactional
    public void update(Student tempStudent) {
        entityManager.merge(tempStudent);
    }

    @Override
    @Transactional
    public void deleteStudentById(int theId) {

        // retrieve the student
        Student tempStudent = entityManager.find(Student.class, theId);

        if (tempStudent != null){
            // get the courses
            List<Course> courses = tempStudent.getCourses();

            // break association of all courses for the student
            for (Course tempCourse : courses){
                tempCourse.getStudents().remove(tempStudent);
            }

            // now delete the student
            entityManager.remove(tempStudent);

        }

    }

    @Override
    public List<Instructor> findAllInstructors() {
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i", Instructor.class);
        return query.getResultList();
    }

    @Override
    public List<Course> findAllCourses() {
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c", Course.class);
        return query.getResultList();
    }

    @Override
    public List<Student> findAllStudents() {
        TypedQuery<Student> query = entityManager.createQuery(
                "select s from Student s", Student.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }
}









































