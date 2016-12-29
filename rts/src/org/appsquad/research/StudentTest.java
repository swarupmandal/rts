package org.appsquad.research;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StudentTest {
	public static void main(String[] args) {
		double i = 0.0;
		System.out.println("Int 2 Str " + String.valueOf(i));
		ArrayList<Student> studentList = new ArrayList<Student>();
		
		studentList.add(new Student(1, "a", 10));
		studentList.add(new Student(1, "b", 20));
		studentList.add(new Student(1, "c", 10));
		studentList.add(new Student(1, "a", 20));
		studentList.add(new Student(1, "a", 30));
		studentList.add(new Student(1, "b", 10));
		studentList.add(new Student(1, "c", 10));
		
		studentList.add(new Student(2, "a", 10));
		studentList.add(new Student(2, "b", 20));
		studentList.add(new Student(2, "c", 10));
		studentList.add(new Student(2, "a", 20));
		studentList.add(new Student(2, "a", 30));
		studentList.add(new Student(2, "b", 10));
		studentList.add(new Student(2, "d", 10));
		
		studentList.add(new Student(3, "a", 10));
		studentList.add(new Student(3, "b", 20));
		studentList.add(new Student(3, "c", 10));
		studentList.add(new Student(3, "a", 20));
		studentList.add(new Student(3, "a", 30));
		studentList.add(new Student(3, "b", 10));
		studentList.add(new Student(3, "d", 10));
		
		
		Map<Integer, Object> parentMap = new HashMap<Integer, Object>();
		for (Student st : studentList) {
			Map<String, Integer> childMap = new HashMap<String, Integer>();	
			childMap.put(st.name, st.marks);
			parentMap.put(st.id, childMap);
		}
		
		for (Map.Entry<Integer, Object> entry : parentMap.entrySet())
		{
		     System.out.println(entry.getKey() + "/" + entry.getValue());
		}
	}
}

class Student{
	public int id;
	public String name;
	public int marks;
	
	public Student(int i,String n, int m ) {
		this.id = i;
		this.name = n;
		this.marks = m;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	
}