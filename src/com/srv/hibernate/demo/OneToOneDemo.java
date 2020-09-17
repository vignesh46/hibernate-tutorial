package com.srv.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.srv.hibernate.demo.entity.Instructor;
import com.srv.hibernate.demo.entity.InstructorDetail;

public class OneToOneDemo {

	public static void main(String[] args) {

		try (SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).buildSessionFactory()) {

			// Create
			try (Session session = factory.getCurrentSession()) {

				session.beginTransaction();

				Instructor instructor = new Instructor("Vignesh", "S R", "srvigneshsr@gmail.com");

				InstructorDetail instructorDetail = new InstructorDetail("http://www.youtube.com/vignesh",
						"Love To Code");

				instructor.setInstructorDetail(instructorDetail);

				session.save(instructor);

				session.getTransaction().commit();

			}

			// Delete
			try (Session session = factory.getCurrentSession()) {

				session.beginTransaction();

				int theId = 1;
				Instructor instructor = session.get(Instructor.class, theId);

				if (instructor != null) {
					/*
					 * Will Delete Instructor and InstructorDetail both since we have set Cascade as
					 * ALL
					 */
					session.delete(instructor);
				}

				session.getTransaction().commit();

			}

		}
	}

}
