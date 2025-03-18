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
void main(string[] args)

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

void makeDeposit(double deposit)
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
void main(String args[]):
    new CheckingsAccount()
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
    return 0.0
```

**makeDeposit**
```
void makeDeposit(double deposit):
    add deposit to balance
    print "deposit of $" + deposit as string + " made."
    call checkBalance()
```

**makeWithdrawal**
```
double makeWithdrawal(double withdrawal):
    remove withdrawal from balance
    if balance is negative:
        print "not enough money in account, please try again."
        return
    print "withdrawal of $" + withdrawal as string + " made."
    call checkBalance()
```