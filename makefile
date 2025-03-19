Customer.class: Customer.java AbstractUser.class CheckingsAccount.class SavingsAccount.class
	javac -g Customer.java

AbstractUser.class: AbstractUser.java IHasMenu.class
	javac -g AbstractUser.java

CheckingsAccount.class: CheckingsAccount.java IHasMenu.class
	javac -g CheckingsAccount.java

SavingsAccount.class: SavingsAccount.java CheckingsAccount.class
	javac -g SavingsAccount.java

IHasMenu.class: IHasMenu.java
	javac -g IHasMenu.java

testAdmin: Admin.class
	java Admin

testCustomer: Customer.class
	java Customer

testCheckings: CheckingsAccount.class
	java CheckingsAccount

testSavings: SavingsAccount.class
	java SavingsAccount

clean:
	rm *.class