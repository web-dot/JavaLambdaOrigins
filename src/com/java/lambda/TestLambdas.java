package com.java.lambda;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.java.lambda.Person.Sex;

public class TestLambdas {

	//Approach 1: Create methods that search for members thst match one characteristic
	public static void printPersonsOlderThan(List<Person> roster, int age) {
		for(Person p : roster) {
			if(p.getAge() >= age) {
				p.printPerson();
			}
		}
	}
	
	//Approach 2: Create more generalized search methods
	public static void printPersonWithinAgeRange(List<Person> roster, int low, int high) {
		for(Person p : roster) {
			if(low <= p.getAge() && p.getAge() < high) {
				p.printPerson();
			}
		}
	}
	
	//Approach 3: Specify search criteria code in a local class
	/**
	 * *Although this approach is less brittle--you don't have to rewrite methods if you change the structure of Person--
	 * you still have additional code: a new interface and a local class for each search you plan to perform in your 
	 * application.
	 * 
	 * *Because checkPersonEligibleForSelectiveService implements an interface, you can use an anonymous class
	 * instead of a local class and bypass the need to declare a new class for each search
	 * */
	interface CheckPerson{
		boolean test(Person p);
	}
	
	static class CheckPersonEligibleForSelectiveSrvice implements CheckPerson {
		@Override
		public boolean test(Person p) {
			return p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25;
		}
	}
	
	
	
	public static void printPersons(List<Person> roster, CheckPerson tester) {
		for(Person p : roster) {
			if(tester.test(p)) {
				p.printPerson();
			}
		}
	}
	
	
	public static void main(String args[]) {
		
		List<Person> roster = Arrays.asList(
				new Person("Harish", LocalDate.parse("1993-01-18"), Sex.MALE),
				new Person("Lata", LocalDate.parse("1985-08-25"), Sex.FEMALE),
				new Person("Shishir", LocalDate.parse("1998-03-23"), Sex.MALE),
				new Person("Vrushabh", LocalDate.parse("1992-08-11"), Sex.MALE),
				new Person("Hema", LocalDate.parse("2001-04-09"), Sex.FEMALE),
				new Person("Karthik", LocalDate.parse("1980-02-27"), Sex.MALE),
				new Person("Kishan", LocalDate.parse("1983-01-05"), Sex.MALE),
				new Person("Junaid", LocalDate.parse("1997-01-02"), Sex.MALE));
		
		
		printPersonsOlderThan(roster, 30);
		System.out.println();
		/**
		 * -> Trying to create a separate method for each possible search query can lead to brittle code
		 * 
		 * -> You can instead separate the code that specifies the criteria for which you want to search in a 
		 * 	different class -> Approach 3
		 * */
		printPersonWithinAgeRange(roster, 25, 35);
		
		System.out.println();
		printPersons(roster, new CheckPersonEligibleForSelectiveSrvice());
	
		
		//Approach 4: Specify Search Criteria code in an Anonymous Class
		/**
		 * This approach reduces the amount of code required because you don't have to create a new class for 
		 * each search that you want to perform. However the syntax of the anonymous class is bulky considering that the
		 * CheckPerson interface contains only one method. In this case use a lambda expression instead of an anonymous class. 
		 * */
		
		printPersons(
				roster, 
				new CheckPerson() {
					@Override
					public boolean test(Person p) {
						return p.getGender() == Person.Sex.MALE && p.getAge() >=18 && p.getAge() < 35; 
					}
				});
		
		
		//Approach 5: Specify Search Criteria code with Lambda Expression
		/**
		 * The CheckPerson interface is a functional interface. A functional interface is any interface that contains
		 * only one abstract method.(A functional interface may contain one or more default methods or static methods)
		 * 
		 * Because a functional interface contains only one abstract method, you can omit the name of that method when you 
		 * implement it. To do this, instead of using an anonymous class expression, you have a Lambda Expression. 
		 * */
		
		printPersons(roster, p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() < 25);
		
		
		
		
		
		
		
	}
}
