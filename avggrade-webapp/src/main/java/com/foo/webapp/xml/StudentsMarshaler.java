package com.foo.webapp.xml;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.foo.logic.gen.Students;

public class StudentsMarshaler {

	public Students unmarshal(final InputStream inputStream) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Students.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Students unmarshaledStudents = (Students) jaxbUnmarshaller.unmarshal(inputStream);
		return unmarshaledStudents;
	}

}
