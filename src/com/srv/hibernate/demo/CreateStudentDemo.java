package com.srv.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.srv.hibernate.demo.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {
		
		try(SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory()){
			
			Student student1 = new Student("Deepan", "S R", "srdeepansr@gmail.com");
			
			//Create
			try(Session session = factory.getCurrentSession()){
				
				session.beginTransaction();
				
				session.save(student1);
				
				session.getTransaction().commit();
				
				System.out.println("Saved Student successfully :" + student1);
				
			}
			
			//Read
			try(Session session = factory.getCurrentSession()){
				
				System.out.println("Student Primary Key :" + student1.getId());
				
				session.beginTransaction();
				
				Student result = session.get(Student.class, student1.getId());
				
				session.getTransaction().commit();
				
				System.out.println("Retrived student successfully :" + result);
				
			}
			
		}
		
	}

}
