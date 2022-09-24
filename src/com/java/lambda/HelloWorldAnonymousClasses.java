package com.java.lambda;

public class HelloWorldAnonymousClasses {
	
	
	interface HelloWorld {
		public void greet();
		public void greetSomeone(String someone);
	}
	
	
	public void sayHello() {
		
		class EnglishGreeting implements HelloWorld{

			String name = "world";
			@Override
			public void greet() {
				greetSomeone("tout le monde");
			}

			@Override
			public void greetSomeone(String someone) {
				name = someone;
				System.out.println("Salut " + name);
			}
			
		}
		
		HelloWorld englishGreeting = new EnglishGreeting();
		
		HelloWorld frenchGreeting = new HelloWorld() {
			
			String name = "tout le monde";
			@Override
			public void greet() {
				greetSomeone(name);
			}
			@Override
			public void greetSomeone(String someone) {
				name = someone;
				System.out.println("Salut " + name);
			}
		};
		
		HelloWorld spanishGreeting = new HelloWorld() {
			String name = "mundo";
			@Override
			public void greet() {
				greetSomeone(name);
			}
			@Override
			public void greetSomeone(String someone) {
				name = someone;
				System.out.println("Hola " + name);
			}
		};
		
		englishGreeting.greet();
		frenchGreeting.greetSomeone("Fred");
		spanishGreeting.greet();
		
	}
	
	public static void main(String args[]) {
		
		HelloWorldAnonymousClasses myApp = new HelloWorldAnonymousClasses();
		myApp.sayHello();
	}
	
	
	
	/* an anonymous class is an expression
	 * 
	 * The anonymous class expression consists the following
	 * 
	 * *The new operator
	 * *The name of an interface to implement or class to extend
	 * *Parenthesis that contains arguments to a constructor, just like normal class instance creation expression.
	 * When you implement an interface, there is no constructor, so an emty pair of parenthesis is used.
	 * *A body which is a class declaration body. More specifically, in the body method declarations are allowed but 
	 * statements are not.
	 * 
	 * 
	 * 
	 * 
	 * **/ 
	
}
