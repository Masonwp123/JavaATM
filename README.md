# JavaATM
Withdraw ALL THE MONEY

## Classes

### interface IHasMenu
```
String menu()
void start()
```

### public class CheckingsAccount implements IHasMenu

**CheckingsAccount() && CheckingsAccount(double balance)**
```
CheckingsAccount(double balance):
    this.balance = balance

# default balance is always 0.0
CheckingsAccount():
    CheckingsAccount(0.0)
```

**main**
```
public static void main(String[] args):
    Account = new CheckingsAccount()
    call Account.start()
```

**menu**
```
String main():
    print "Checkings Account"
    print empty line
    print "0) Exit"
    print "1) Check Balance"
    print "2) Make Deposit"
    print "3) Make Withdrawal"
    print empty line
    print "Action (0-3): "

    return input as string
```

**start**
```
void start():
    keepGoing = true
    while keepGoing:
        String response = call menu()
        if response is "0":
            keepGoing = false
        else if reponse is "1":
            call checkBalance()
        else if response is "2":
            call makeDeposit()
        else if response is "3":
            call makeWithdrawal()
```

**getBalance**
```
double getBalance():
    return balance
```

**getBalanceString**
```
String getBalanceString():
    return balance converted to string
```

**setBalance**
```
void setBalance(double balance):
    if (balance < 0.0):
        print "Balance cannot be negative"
        return

    this.balance = balance
    call checkBalance()
```

**checkBalance**
```
void checkBalance():
    print "Account has $" + call getBalanceString() + "."
```

**getDouble**
```
# test function
private double getDouble():
    return get double input with exception handling
```

**makeDeposit**
```
void makeDeposit():

    depositString = input as string
    deposit = convert depositString to double

    if deposit is negative:
        print "Deposit cannot be negative."
        return;

    add deposit to balance
    print "deposit of $" + depositString + " made."
    call checkBalance()
```

**makeWithdrawal**
```
double makeWithdrawal():

    withdrawalString = input as string
    withdrawal = convert withdrawalString to double

    if withdrawal is greater than balance:
        print "not enough money in account, please try again."
        return
    else if withdrawal is less than 0:
        print "withdrawal cannot be negative."
        return
    remove withdrawal from balance
    print "withdrawal of $" + withdrawalString + " made."
    call checkBalance()
```

### public class SavingsAccount extends CheckingsAcount

**SavingsAccount() && SavingsAccount(double balance)**
```
SavingsAccount(double balance):
    CheckingsAccount(balance)

# default balance is always 0.0
SavingsAccount():
    SavingsAccount(0.0)
```

**main**
```
public static void main(String[] args):
    Account = new SavingsAccount()
    call Account.start()
```

**calcInterest**
```
# Interest Rate should be as a decimal, so adding one will apply the original balance
void calcInterest():
    setBalance(getBalance() * InterestRate + 1)
```

**setInterestRate**
```
void setInterestRate():
    rate = getDouble()
    interestRate = rate
```

**getInterestRate**
```
void getInterestRate():
    print interestRate as string
```

### public abstract class AbstractUser implements IHasMenu

**AbstractUser() && AbstractUser(String userName, String PIN)**
```
AbstractUser(String userName, String PIN):
    this.userName = userName
    this.PIN = PIN

# default PIN is always 0000
AbstractUser():
    AbstractUser("NULL", "0000")
```

**login**
```
boolean login(String userName, String PIN):
    if userName equals this.userName and PIN equals this.PIN:
        print "Login Successful!"
        return true
    return false
```

**getUserName**
```
String getUserName():
    return userName
```

**setUserName**
```
void setUserName(userName):
    this.userName = userName
```

**getPIN**
```
String getPIN():
    return PIN
```

**setPIN**
```
void setPIN(PIN):
    this.PIN = PIN
```

**abstract getReport**

### public class Customer extends AbstractUser

**main**
```
public static void main(String[] args):
    customer = new Customer()
    call customer.start()
```

**menu**
```
print "Checkings Account"
    print empty line
    print "0) Exit"
    print "1) Change PIN"
    print "2) View Checkings Account"
    print "3) View Savings Account"
    print empty line
    print "Action (0-3): "

    return input as string
```

**start**
```
void start():
    keepGoing = true
    while keepGoing:
        String response = call menu()
        if response is "0":
            keepGoing = false
        else if reponse is "1":
            call changePIN()
        else if reponse is "2":
            call checkingsAccount.start()
        else if response is "3":
            call savingsAccount.start()
```

**changePIN**
```
void changePIN():
    prompt user to change pin
    check if pin is 4 numberic digits
    tell user pin was set successfully
    call setPIN(PIN)
```

**getReport**
```
```