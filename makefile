Admin.class: Admin.java AbstractUser.class
	javac -g Admin.java

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

Bank.class: Bank.java IHasMenu.class Admin.class Customer.class
	javac -g Bank.java

run: Bank.class
	java Bank

clean:
	rm *.class