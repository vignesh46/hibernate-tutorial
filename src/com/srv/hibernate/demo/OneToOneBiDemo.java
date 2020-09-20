package com.srv.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.srv.hibernate.demo.entity.Instructor;
import com.srv.hibernate.demo.entity.InstructorDetail;

public class OneToOneBiDemo {

	public static void main(String[] args) {

		try (SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).buildSessionFactory()) {

			// Fetch Instructor using InstructorDetail - BiDirectional
			try (Session session = factory.getCurrentSession()) {

				session.beginTransaction();

				int theId = 4;
				/**
				 * Below line with fetch Instructor along with InstructorDetail Since we mapped
				 * Instructor reference in InstructorDetail
				 */
				InstructorDetail instructorDetail = session.get(InstructorDetail.class, theId);

				System.out.println(instructorDetail);

				System.out.println(instructorDetail.getInstructor());

				session.getTransaction().commit();
			}

			// Delete Instructor and InstructorDetail using InstructorDetail object -
			// BiDirectional
			try (Session session = factory.getCurrentSession()) {

				session.beginTransaction();

				int theId = 2;
				/**
				 * Below line with fetch Instructor along with InstructorDetail Since we mapped
				 * Instructor reference in InstructorDetail
				 */
				InstructorDetail instructorDetail = session.get(InstructorDetail.class, theId);

				System.out.println(instructorDetail);

				System.out.println(instructorDetail.getInstructor());

				/**
				 * Below line will delete both InstructorDetail and Instructor
				 */
				session.delete(instructorDetail);

				session.getTransaction().commit();
			}

			// Delete only InstructorDetail - BiDirectional
			/**
			 * NOTE: We have to modify the Cascade options in InstructorDetail class by
			 * removing ALL option and set each options except REMOVE
			 */
			try (Session session = factory.getCurrentSession()) {

				session.beginTransaction();

				int theId = 3;
				/**
				 * Below line with fetch Instructor along with InstructorDetail Since we mapped
				 * Instructor reference in InstructorDetail
				 */
				InstructorDetail instructorDetail = session.get(InstructorDetail.class, theId);

				System.out.println(instructorDetail);

				System.out.println(instructorDetail.getInstructor());

				/**
				 * Below line will break the link between Instructor and InstructorDetail
				 */
				instructorDetail.getInstructor().setInstructorDetail(null);
				// Now it will delete only InstructorDetail
				session.delete(instructorDetail);

				session.getTransaction().commit();
			}

			// Delete Instructor and InstructorDetail using Instructor object -
			// BiDirectional
			try (Session session = factory.getCurrentSession()) {

				session.beginTransaction();

				int theId = 4;
				/**
				 * Below line with fetch Instructor along with InstructorDetail Since we mapped
				 * Instructor reference in InstructorDetail
				 */
				Instructor instructor = session.get(Instructor.class, theId);

				System.out.println(instructor);

				/**
				 * Below line will delete both Instructor and InstructorDetail
				 */
				session.delete(instructor);

				session.getTransaction().commit();
			}
		}
	}

}
