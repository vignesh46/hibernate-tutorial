package com.srv.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.srv.hibernate.demo.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {

		try (SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class).buildSessionFactory()) {

			Student student1 = new Student("Deepan", "S R", "srdeepansr@gmail.com");

			// Create
			try (Session session = factory.getCurrentSession()) {

				session.beginTransaction();

				session.save(student1);

				session.getTransaction().commit();

				System.out.println("Saved Student successfully :" + student1);

			}

			// Read
			try (Session session = factory.getCurrentSession()) {

				System.out.println("Student Primary Key :" + student1.getId());

				session.beginTransaction();

				Student result = session.get(Student.class, student1.getId());

				session.getTransaction().commit();

				System.out.println("Retrived student successfully :" + result);

			}

			// Query
			try (Session session = factory.getCurrentSession()) {

				System.out.println("Display student records using Query");

				session.beginTransaction();

				List<Student> students = session.createQuery("from Student").getResultList();

				for (Student student : students) {
					System.out.println(student);
				}

				session.getTransaction().commit();
			}

			// Query
			try (Session session = factory.getCurrentSession()) {

				System.out.println("Query using where conditon");

				session.beginTransaction();

				List<Student> students = session.createQuery("from Student where lastName='Amal'").getResultList();

				for (Student student : students) {
					System.out.println(student);
				}

				session.getTransaction().commit();
			}

			// update
			try (Session session = factory.getCurrentSession()) {

				int sId = 4;

				System.out.println("Update Student using ID");

				session.beginTransaction();

				Student result = session.get(Student.class, sId);

				result.setLastName("Prasanth");

				session.getTransaction().commit();

				System.out.println("Updated Student Successfully");
			}

			// update
			try (Session session = factory.getCurrentSession()) {

				System.out.println("Update bulk records");

				session.beginTransaction();

				session.createQuery("update Student set email='test@gmail.com'").executeUpdate();

				session.getTransaction().commit();

				System.out.println("Updated bulk records Successfully");
			}

			// Delete
			try (Session session = factory.getCurrentSession()) {

				int sId = 4;

				System.out.println("Delete Student using ID");

				session.beginTransaction();

				Student result = session.get(Student.class, sId);

				session.delete(result);

				session.getTransaction().commit();

				System.out.println("Deleted Student Successfully");
			}

			// Delete using Query
			try (Session session = factory.getCurrentSession()) {

				System.out.println("Delete record using Query");

				session.beginTransaction();

				session.createQuery("delete from Student where id='5'").executeUpdate();

				session.getTransaction().commit();

				System.out.println("Deleted record using Query Successfully");
			}

		}

	}

}
