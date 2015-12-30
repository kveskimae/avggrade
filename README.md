# Weighted average grade calculator parent project

## About

This application calculates weighted average grade for students

## Running

Web application can be built with Maven into web archive (WAR) file and deployed into a Java web container like Tomcat, or any other.

Alternative is to use Maven Jetty plugin from command line inside web application project, for example:

	/workspace/avggrade/avggrade-webapp $ mvn jetty:run

## Design

This is a Maven project with outside dependencies, written using Spring and Angular frameworks together with some additional libraries. 

It has two modules: 
 * logic module;
 * web application.

### Logic module

Logic module does the heavy lifting of calculating the weighted average mean for a list of students. 

At first the web application unmarshals a list of students in the format *com.foo.logic.gen.Students* recognisable to logic module. Then *AvgCalculator* instance can do the actual calculations.

#### A note about JAXB classes in logic module

Java classes in package "com.foo.logic.gen" are generated from *[parent folder]/avggrade-logic/resources/studentslist.xsd* , do not manually change them!

### Web application

Web application is a Spring Web MVC project. 

User provides students list as an XML file. This file gets posted to *FileUploadController* , located at [domain]/rest/upload, e.g. http://localhost:8080/avggrade-webapp/rest/upload . 

The uploading of students list file is done by Angular application that runs inside web browser. The state of upload controller can be tested by querying this same file upload controller location with GET operation. The result should be JSON object containing text "Upload controller is up and running".

Web application, once started, can serve web page for uploading students XML file. Usage is explained at landing page.

Students list gets actually ordered by Angular in front-end.