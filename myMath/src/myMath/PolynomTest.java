package myMath;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PolynomTest {

	
	@Test
	void testPolynomString() { 
		
		Polynom a= new Polynom("-x^6+3x-8");
		System.out.println(a.toString());
		Polynom b= new Polynom("77");
		System.out.println(b.toString());
		Polynom c= new Polynom("3x");
		System.out.println(c.toString());
		Polynom d= new Polynom("x^6");
		System.out.println(d.toString());
		Polynom e= new Polynom("x");
		System.out.println(e.toString());
		Polynom f= new Polynom("-x");
		System.out.println(f.toString());
		Polynom g=new Polynom("0");
		System.out.println(g.toString());
		Polynom h= new Polynom("x^6+3x-8");
		System.out.println(h.toString());

	}




	@Test
	void testF() {
		Polynom a= new Polynom("-x^6-3x-8");
		System.out.println(a.f(1));
		System.out.println(a.f(0));
		Polynom b= new Polynom("x");
		System.out.println(b.f(0));

	}

		 
		@Test
		void testAddPolynom_able() {
			Polynom a= new Polynom("-x^6-8");
			Polynom b= new Polynom("x+6");
			a.add(b);
			System.out.println(a.toString());
		}


		
	@Test
	void testAddMonom() {
		Monom a=new Monom (3,6);
		Polynom b= new Polynom("-x^6-3x");
		b.add(a);
		System.out.println(b.toString());
	}

	@Test
	void testSubstract() {
		Polynom a= new Polynom("-x^6-3x");
		Monom b=new Monom (-8,3);
		Polynom d=new Polynom();
		d.add(b);
		a.substract(d);
		System.out.println(a.toString());
	}

	@Test
	void testMultiply() {
		Polynom a=new Polynom ("3x^7");
		Polynom b= new Polynom ("x+9");
		a.multiply(b);
		System.out.println(a.toString());
	}

	@Test
	void testEqualsPolynom_able() {
		Polynom a=new Polynom ("4x+2");
		Polynom b=new Polynom ("4x+2");
		Polynom c=new Polynom ("4x");
		System.out.println(a.equals(b));
		System.out.println(a.equals(c));



	}

	@Test
	void testIsZero() {
		Polynom a= new Polynom();
		Polynom b= new Polynom("x");
		System.out.println(a.isZero());
		System.out.println(b.isZero());
	}

	@Test
	void testRoot() {
		Polynom a=new Polynom("x^2");
		System.out.println(a.root(-2, 2, 0.001));

	}


	@Test
	void testRoot2() {
		Polynom a=new Polynom("x^2-1");
		System.out.println(a.root2(-2, 2, 0.001));
	}

	@Test
	void testCopy() {
		Polynom a = new Polynom();
		Monom b = new Monom (6,3);
		Monom c = new Monom (3,7); 
		a.add(b);
		Polynom g= (Polynom) a.copy();
		a.add(c);
		System.out.println(g.toString());
		System.out.println(a.toString());
	}

	@Test
	void testDerivative() {
		Polynom a=new Polynom("4x^2");
		Polynom b=(Polynom)a.derivative();
		System.out.println(b.toString());
	}

	@Test
	void testArea() {
		Polynom a=new Polynom("x^2");
		System.out.println(a.area(0, 1, 0.001));
	}

	@Test
	void testArea2() {
		Polynom a=new Polynom("-x^2");
		System.out.println(a.area2(0, 1, 0.001));
	}



	@Test
	void testToString() {
		Polynom a=new Polynom ("3x^2");
		System.out.println(a.toString());
	}

	@Test 
	void testBlockedarea() {
		Polynom s = new Polynom();
		s.add(new Monom(1, 3));
		s.add(new Monom(-9, 1));
		System.out.println(s.Blockedarea(-4, 4, 0.01));

	 }

}
	

