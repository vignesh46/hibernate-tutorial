package com.srv.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.srv.hibernate.demo.entity.Course;
import com.srv.hibernate.demo.entity.Instructor;
import com.srv.hibernate.demo.entity.InstructorDetail;

public class OneToManyBiDemo {

	public static void main(String[] args) {

		try (SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class).buildSessionFactory()) {

			// Create
			try (Session session = factory.getCurrentSession()) {

				session.beginTransaction();
				
				Instructor instructor = new Instructor("Vignesh", "S R", "srvigneshsr@gmail.com");

				InstructorDetail instructorDetail = new InstructorDetail("http://www.youtube.com/vignesh",
						"Love To Code");

				instructor.setInstructorDetail(instructorDetail);
				
				Course course = new Course("Java");
				instructor.addCourse(course);
				
				session.save(instructor);
				// Course not saving without below line
				session.save(course);

				session.getTransaction().commit();

			}

		}

	}

}
