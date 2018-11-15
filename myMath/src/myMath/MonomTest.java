package myMath;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MonomTest {

	@Test
	void testF() {
		Monom a=new Monom(3,3);
		System.out.println(a.f(1));
	}

	@Test
	void testAdd() {
		Monom a=new Monom(3,3);
		Monom b=new Monom (4,3);
		Monom c=new Monom(2,2);
		a.add(c);
		System.out.println(a);
		a.add(b);
		System.out.println(a);
}

	@Test
	void testMultiply() {
		Monom a=new Monom(3,3);
		Monom b=new Monom (4,3);
        a.Multiply(b);
        System.out.println(a);
        
	}

	@Test
	void testDerivative() {
    Monom a=new Monom (4,2);
    Monom b=new Monom (2,0);
    a.derivative();
    b.derivative();
    System.out.println(a);
    System.out.println(b);
	}
	
	@Test
	void testEqual() {
		Monom b=new Monom(0,0);
		Monom a=new Monom (0,0);
		System.out.println(a.Equal(b));
		Monom c=new Monom (3,6);
		System.out.println(a.Equal(c));
	}

	
	@Test
	void testToString() {
		Monom a=new Monom (3,2);
		Monom b=new Monom (0,0);
		System.out.println(a.toString());
		System.out.println(b.toString());
	}

	@Test
	void testMonomString() {
		Monom a=new Monom("3x^2");
		System.out.println(a.toString());
	}
}
