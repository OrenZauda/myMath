# myMath
myMath is a projact that we get on class to solve math problems.

## Usage
You can use our [Java](https://he.wikipedia.org/wiki/Java_(%D7%A4%D7%9C%D7%98%D7%A4%D7%95%D7%A8%D7%9E%D7%AA_%D7%AA%D7%95%D7%9B%D7%A0%D7%94)) project to solve math problems and calculate things like derivative,multiply,area and etc. 
Also you can see a graph of the function you create.

Test:
```java
Polynom s = new Polynom();
		s.add(new Monom(1, 3));
		s.add(new Monom(-9, 1));
		System.out.println(s.Blockedarea(-4, 4, 0.01));
```
Return:
```java
2.8421709430404007E-14
```

## FunctionList

1. **Polynom**
   - add
     - make an + between two Polynom's
   - multyply
      - make an * between two Polynom's
   - area
      - calculate the area between function's
   - f
     - calculate the sum of Polynom
   - equals
     - check a posible of two equals Polynom's
   - isZero
      - check if the Polynom is 0
   - root
      - find the min and max points of Polynom
   - toString
      - return the polynom itself
   
2. **Monom**
   - add
     - make an + between two Monom's
   - multyply
      - make an * between two Monom's
   - derivative
      - make a derivative to the Monom
   - f
     - calculate the sum of Monom
   - equal
      - check a posible of two equals Monom's
   - toString
     - return the polynom itself


## Contributing
Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.
