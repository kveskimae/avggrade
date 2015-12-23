package com.foo.webapp.xml;

import java.io.File;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.foo.logic.gen.Course;
import com.foo.logic.gen.Student;
import com.foo.logic.gen.Students;

public class XMLSampleFileCreator {

	public static void main(String[] args) {
		Students students = new Students();

		Student student2 = new Student();
		students.getStudent().add(student2);
		student2.setName("Tarmo Tamm");

		Course student2course3 = new Course();
		student2course3.setCode("S2C3");
		student2course3.setCredits(6);
		student2course3.setGrade(2);
		student2.getCourse().add(student2course3);

		Course student2course2 = new Course();
		student2course2.setCode("S2C2");
		student2course2.setCredits(6);
		student2course2.setGrade(5);
		student2.getCourse().add(student2course2);

		Course student2course1 = new Course();
		student2course1.setCode("S2C1");
		student2course1.setCredits(12);
		student2course1.setGrade(4);
		student2.getCourse().add(student2course1);

		Student student1 = new Student();
		students.getStudent().add(student1);
		student1.setName("Mari Maasikas");

		Course student1course3 = new Course();
		student1course3.setCode("S1C3");
		student1course3.setCredits(6);
		student1course3.setGrade(2);
		student1.getCourse().add(student1course3);

		Course student1course2 = new Course();
		student1course2.setCode("S1C2");
		student1course2.setCredits(6);
		student1course2.setGrade(5);
		student1.getCourse().add(student1course2);

		Course student1course1 = new Course();
		student1course1.setCode("S1C1");
		student1course1.setCredits(12);
		student1course1.setGrade(4);
		student1.getCourse().add(student1course1);

		try {
			File file = new File("students.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Students.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(students, file);
			jaxbMarshaller.marshal(students, System.out);

			// just testing
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Students unmarshaledStudents = (Students) jaxbUnmarshaller.unmarshal(file);
			System.out.println(unmarshaledStudents);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

}
