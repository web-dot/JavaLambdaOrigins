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
		
		List<Person> roaster = Arrays.asList(
				new Person("Harish", LocalDate.parse("1993-01-18"), Sex.MALE),
				new Person("Lata", LocalDate.parse("1985-08-25"), Sex.FEMALE),
				new Person("Shishir", LocalDate.parse("1998-03-23"), Sex.MALE),
				new Person("Vrushabh", LocalDate.parse("1992-08-11"), Sex.MALE),
				new Person("Hema", LocalDate.parse("2001-04-09"), Sex.FEMALE),
				new Person("Karthik", LocalDate.parse("1980-02-27"), Sex.MALE),
				new Person("Kishan", LocalDate.parse("1983-01-05"), Sex.MALE),
				new Person("Junaid", LocalDate.parse("1997-01-02"), Sex.MALE));
		
		
		printPersonsOlderThan(roaster, 30);
		System.out.println();
		/**
		 * -> Trying to create a separate method for each possible search query can lead to brittle code
		 * 
		 * -> You can instead separate the code that specifies the criteria for which you want to search in a 
		 * 	different class -> Approach 3
		 * */
		printPersonWithinAgeRange(roaster, 25, 35);
		
		System.out.println();
		printPersons(roaster, new CheckPersonEligibleForSelectiveSrvice());
		
		
	}
}
