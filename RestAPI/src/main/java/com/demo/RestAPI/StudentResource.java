package com.demo.RestAPI;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("students")
public class StudentResource {

	StudentRepository repo = new StudentRepository();

	//Fetching records from Student table
	@GET
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<Student> getStudent(){
		return repo.getStdlist();
	}

	//Fetching records by Id from Student table.
	@GET
	@Path("student/{id}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Student getStudentbyID(@PathParam("id")int id){
		return repo.getStudentId(id);
	}

	// Creating Student table
	@POST
	@Path("student")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Student createStudent(Student s){
		System.out.println(s);
		repo.createStudent(s);
		return s;
	}

	//Performing Update for Student table
	@PUT
	@Path("student")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Student updateStudent(Student s){
		System.out.println(s);
		if(repo.getStudentId(s.getId()).getId()==0){
			repo.createStudent(s);

		}else{
			repo.updateStudent(s);
		}
		return s;
	}


	@DELETE
	@Path("student/{id}")
	//@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Student deleteStudent(@PathParam("id")int id){
		System.out.println(id);

		Student std = repo.getStudentId(id);

		if(std.getId()!=0){

			repo.deleteStudentData(id);
		}
		return std;
	}


}
