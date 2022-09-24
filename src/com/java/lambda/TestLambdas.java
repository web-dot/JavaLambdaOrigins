package com.java.lambda;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

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
	/**
	 * *Although this approach is less brittle--you don't have to rewrite methods if you change the structure of Person--
	 * you still have additional code: a new interface and a local class for each search you plan to perform in your 
	 * application.
	 * 
	 * *Because checkPersonEligibleForSelectiveService implements an interface, you can use an anonymous class
	 * instead of a local class and bypass the need to declare a new class for each search
	 * */
	
	
	//Approach 6: use standard function interface with lambda
	//printPersonsWithPredicate
	public static void printPersonsWithPredicate(List<Person> roster, Predicate<Person> tester) {
		for(Person p : roster) {
			if(tester.test(p)) {
				p.printPerson();
			}
		}
	}
	
	
	//Approach 7: using Lambda Expressions throughout your application
	/**
	 * Reconsider the method printPersonWithPredicate to see where else you could use lambda expressions
	 * Consider the method printPersonsWithPredicate, instead of invoking printPerson, you can specify a different
	 * action to perform on those Person instances that satisfy the criteria specified by the tester.
	 * 
	 * The following method replaces the invocation p.printPerson(), with an instance of Consumer<Person> that
	 * invokes the method accept.
	 * */
	
	public static void processPersons(List<Person> roster, Predicate<Person> tester, Consumer<Person> block) {
		for(Person p : roster) {
			if(tester.test(p)) {
				block.accept(p);
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
				new Person("Kavitha", LocalDate.parse("2001-01-05"), Sex.FEMALE),
				new Person("Junaid", LocalDate.parse("1997-01-02"), Sex.MALE),
				new Person("Jaya", LocalDate.parse("2003-08-25"), Sex.FEMALE),
				new Person("Shishir", LocalDate.parse("1998-03-23"), Sex.MALE),
				new Person("Deepika", LocalDate.parse("1992-08-11"), Sex.FEMALE),
				new Person("Hrisitha", LocalDate.parse("1998-02-18"), Sex.FEMALE),
				new Person("Kapil", LocalDate.parse("1985-02-12"), Sex.MALE),
				new Person("Shruthi", LocalDate.parse("1995-01-05"), Sex.FEMALE),
				new Person("Gaurav", LocalDate.parse("1989-05-02"), Sex.MALE));
				
		
		printPersonsOlderThan(roster, 30);
		System.out.println();
		/**
		 * -> Trying to create a separate method for each possible search query can lead to brittle code
		 * 
		 * -> You can instead separate the code that specifies the criteria for which you want to search in a 
		 * 	different class -> Approach 3
		 * */
		printPersonWithinAgeRange(roster, 18, 25);
		
		System.out.println();
		printPersons(roster, new CheckPersonEligibleForSelectiveSrvice());
	
		System.out.println();
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
						return p.getGender() == Person.Sex.MALE && p.getAge() >=18 && p.getAge() < 25; 
					}
				});
		
		System.out.println();
		//Approach 5: Specify Search Criteria code with Lambda Expression
		/**
		 * The CheckPerson interface is a functional interface. A functional interface is any interface that contains
		 * only one abstract method.(A functional interface may contain one or more default methods or static methods)
		 * 
		 * Because a functional interface contains only one abstract method, you can omit the name of that method when you 
		 * implement it. To do this, instead of using an anonymous class expression, you have a Lambda Expression. 
		 * */
		
		printPersons(roster, p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() < 25);
		
		System.out.println();
		/**
		 * you can use a standard functional interface in place of interface CheckPerson, which reduces even further the
		 * amount of code required
		 * 
		 * JDK defines several functional interfaces, which you can find in java.util.function
		 * 
		 * For example you can use the Predicate<T> interface in place of CheckPerson. This interface contains the
		 * method boolean test(T t)
		 * 
		 * */
		
		//Approach 6: use Standard Functional Interface with Lambda expressions
		printPersonsWithPredicate(
				roster,
				new Predicate<Person>() {
					@Override
					public boolean test(Person t) {
						return t.getGender() == Person.Sex.FEMALE && t.getAge() >= 18 && t.getAge() < 25;
					}
				});
		
		System.out.println();
		/*
		 * The above anonymous class that creates Predicate<T> object can be written as Lambda expression as below
		 * where the method name can be omitted
		**/
		printPersonsWithPredicate(
				roster, 
				p -> p.getGender() == Person.Sex.FEMALE & p.getAge() >= 18 && p.getAge() < 25);
		
		
		System.out.println();
		//Approach 7: 
		processPersons(
				roster,
				new Predicate<Person>() {
					@Override
					public boolean test(Person t) {
						return t.getGender() == Person.Sex.FEMALE && t.getAge() >= 18 && t.getAge() < 25;
					}
				},
				new Consumer<Person>() {
					@Override
					public void accept(Person t) {
						t.printPerson();
					}
				});
		
		System.out.println();
		//The above expression can be rewritten using lambda expression as
		
		processPersons(
				roster, 
				p -> p.getGender() == Person.Sex.FEMALE && p.getAge() >= 18 && p.getAge() < 25, 
				p -> p.printPerson());
		
	}
}
