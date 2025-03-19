# JavaATM
Withdraw ALL THE MONEY

## Classes

### interface IHasMenu
```
String menu()
void start()
```

### class CheckingsAccount implements IHasMenu
```
CheckingsAccount()
CheckingsAccount(double balance)
static void main(string[] args)

# begin IHasMenu implementation
String menu()
void start()
# end IHasMenu implementation

# balance functions

double getBalance()
String getBalanceString()
void setBalance(double balance)
void checkBalance()
private double getDouble()

# modifiers

void makeDeposit()
double makeWithdrawal()
====================
double balance
```

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
    print empty line
    print "0) Exit"
    print "1) Do things"
```

**start**
```
void start():
    print "hello"
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

### class SavingsAccount extends CheckingsAcount

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