package com.belajar.cruddemo;

import com.belajar.cruddemo.dao.AppDAO;
import com.belajar.cruddemo.dao.AppDAOImpl;
import com.belajar.cruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner ->{
			//createCourseAndStudents(appDAO);

			//findCourseAndStudents(appDAO);

			//findStudentAndCourses(appDAO);

			//addMoreCoursesForStudent(appDAO);

			//deleteCourse(appDAO);

			//deleteStudent(appDAO);
		};
	}

	private void deleteStudent(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Deleting student id: " + theId);

		appDAO.deleteStudentById(theId);

		System.out.println("Done");
	}

	private void addMoreCoursesForStudent(AppDAO appDAO) {
		int theId = 2;
		Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);

		// create more courses
		Course tempCourse1 = new Course("Rubik's cube - How to speed cube");
		Course tempCourse2 = new Course("Atari 2600 - Game Development");

		// add courses to student
		tempStudent.addCourse(tempCourse1);
		tempStudent.addCourse(tempCourse2);

		System.out.println("Updating student: " +tempStudent);
		System.out.println("associated courses: " +tempStudent.getCourses());

		appDAO.update(tempStudent);

		System.out.println("Done");
	}

	private void findStudentAndCourses(AppDAO appDAO) {
		int theId = 2;
		Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);

		System.out.println("Loaded student: " + tempStudent);
		System.out.println("Courses: " + tempStudent.getCourses());

		System.out.println("Done");
	}

	private void findCourseAndStudents(AppDAO appDAO) {

		int theId = 10;
		Course tempCourse = appDAO.findCourseAndStudentByCourseId(theId);

		System.out.println("Loaded course: " + tempCourse);
		System.out.println("Students: " + tempCourse.getStudents());

		System.out.println("Done");

	}

	private void createCourseAndStudents(AppDAO appDAO) {
		// create a course
		Course tempCourse = new Course("Pacman - How to score one million points");

		// create the students
		Student tempStudent1 = new Student("John", "Doe", "john@gmail.com");
		Student tempStudent2 = new Student("Mary", "Pom", "mary@gmail.com");

		// add students to the course
		tempCourse.addStudent(tempStudent1);
		tempCourse.addStudent(tempStudent2);

		// save the course and associated students
		System.out.println("Saving the course: " +tempCourse);
		System.out.println("associated students: " + tempCourse.getStudents());

		appDAO.save(tempCourse);

		System.out.println("Done!");
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int theId = 10;
		System.out.println("Deleting course id: "+theId);
		appDAO.deleteCourseById(theId);
		System.out.println("Done");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {
		// get the course and reviews
		int theId = 10;
		Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);

		// print the course
		System.out.println(tempCourse);

		// print the reviews
		System.out.println(tempCourse.getReviews());

		System.out.println("Done!!");
	}

	private void createCourseAndReviews(AppDAO appDAO) {
		// create a course
		Course tempCourse = new Course("Pacman - How to score one million Points");

		// add some reviews
		tempCourse.addReview(new Review("Great course ... loved it!"));
		tempCourse.addReview(new Review("Cool course, job well done"));
		tempCourse.addReview(new Review("What a dumb course, you are an idiot!"));

		// save the course
		System.out.println("Saving the course");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());

		appDAO.save(tempCourse);

	}

	private void deleteCourse(AppDAO appDAO) {
		int theId = 10;
		System.out.println("Deleting course id: " + theId);

		appDAO.deleteCourseById(theId);
		System.out.println("Done!");
	}

	private void updateCourse(AppDAO appDAO) {
		int theID = 10;

		System.out.println("Finding course id: " + theID);
		Course tempCourse = appDAO.findCourseById(theID);

		System.out.println("Updating course id: " + theID);
		tempCourse.setTitle("Enjoy the simple things!");

		appDAO.Update(tempCourse);

		System.out.println("Done");
	}

	private void updateInstructor(AppDAO appDAO) {

		int theId = 1;

		//find the instructor
		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor =  appDAO.findInstructorById(theId);

		//update the instructor
		System.out.println("Updating instructor id: " + theId);
		tempInstructor.setLastName("TESTER");

		appDAO.Update(tempInstructor);
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int theId = 1;

		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated courses: " + tempInstructor.getCourses());

		System.out.println("done");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: " + tempInstructor);

		// find courses for instructor
		System.out.println("Finding courses for instructor id: " + theId);

		List<Course> courses = appDAO.findCoursesByInstructorId(theId);

		tempInstructor.setCourses(courses);

		System.out.println("the associated courses: " + tempInstructor.getCourses());

		System.out.println("Done");

	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated courses: " +tempInstructor.getCourses());

		System.out.println("Done");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		// create the instructor
		Instructor tempInstructor =
				new Instructor("Susan", "Public", "susan@gmail.com");

		InstructorDetail tempInstructorDetail=
				new InstructorDetail("http://www.youtube.com",
						"Video Games");

		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// create some courses
		Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
		Course tempCourse2 = new Course("The Pinball Masterclass");
		Course tempCourse3 = new Course("Billiard Masterclass");

		// add courses to instructor
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);
		tempInstructor.add(tempCourse3);

		// save the instructor
		//
		System.out.println("Saving instructor: "+ tempInstructor);
		System.out.println("The courses: "+ tempInstructor.getCourses());
		appDAO.save(tempInstructor);
		System.out.println("Done!");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int theId = 3;
		System.out.println("Deleting instructor detail id: " + theId);
		appDAO.deleteInstructorDetailById(theId);
		System.out.println("Done !");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		int theId = 2;
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);
		System.out.println("tempInstructorDetail: " + tempInstructorDetail);
		System.out.println("Associated instructor: " +tempInstructorDetail.getInstructor());
	}

	private void deleteInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Deleting instructor id: "+theId);
		appDAO.deleteInstructorById(theId);
		System.out.println("Done!");
	}

	private void findInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associate instructorDetail only: "+tempInstructor.getInstructorDetail());
	}


	private void createInstructor(AppDAO appDAO) {

		/*// create the instructor
		Instructor tempInstructor =
				new Instructor("Chad", "Darby", "darby@gmail.com");

		InstructorDetail tempInstructorDetail=
				new InstructorDetail("http://www.luv2code.com/youtube",
						"Luv 2 code!!");*/

		// create the instructor
		Instructor tempInstructor =
				new Instructor("Madu", "Patel", "madu@gmail.com");

		InstructorDetail tempInstructorDetail=
				new InstructorDetail("http://www.luv2code.com/youtube",
						"Playing guitar !!");

		tempInstructor.setInstructorDetail(tempInstructorDetail);

		System.out.println("Saving instructor: " +tempInstructor);
		appDAO.save(tempInstructor);

		System.out.println("Done!");
	}


}
























