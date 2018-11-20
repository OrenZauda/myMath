package myMath;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;

import javax.management.RuntimeErrorException;

import java.util.Iterator;

import myMath.Monom;
import myMath.Monom_Comperator;

/**
 * This class represents a Polynom with add, multiply functionality, it also
 * should support the following: 1. Riemann's Integral:
 * https://en.wikipedia.org/wiki/Riemann_integral 2. Finding a numerical value
 * between two values (currently support root only f(x)=0). 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able {
	// ********** add your code below ***********
	private ArrayList<Monom> PolynomArray;
	private final Monom_Comperator q = new Monom_Comperator();

	/**
	 * constructors
	 */
	public Polynom() {
		this.PolynomArray = new ArrayList<>();
	}

	/**
	 * Constructor using string
	 * 
	 * @param s is string
	 *            
	 */
	public Polynom(String s) {
		this.PolynomArray = new ArrayList<>();
		//if the polynom contain negative power throw exception
		if (s.contains("^-")) {
			throw new RuntimeErrorException(null, "Eror wrong value of power");
		}
		// create char array from the string
		char array[]=s.toCharArray();
		// initializing j to catch the last index, when the Monom started
		int j=0;
		int i=0;
		// for loop to splits the string to Monoms
		for (i=0;i<array.length;i++) {
			if (array[i]=='+'||array[i]=='-') {	
				// catch the Monom string
				String r=s.substring(j,i);
				// in case of +, disclude "+" from the next Monom
				if(array[i]=='+') {
					j=i+1;
				}
				// in case of -, include the "-" in the next Monom
				else {
					j=i;
				}
				//converting substring into Monom
				//and add it to the polynom.
				Monom temp=new Monom (r);
				this.add(temp);
			}
		}
		// insert the last Monom
		Monom temp=new Monom(""+s.substring(j));
		this.add(temp);

	}

	/**
	 * calculate function in value x
	 * 
	 * @param x
	 *            is the point in the Graph
	 * @return v the value of f(x)
	 */

	public double f(double x) {
		double v = 0;// to save the results
		Iterator<Monom> here = iteretor();
		while (here.hasNext()) {
			// reaching every Monom
			Monom t = here.next();
			v = v + t.f(x);
		}
		return v;// reurn results
	}

	/**
	 * add polynom to polynom
	 * 
	 * @param p1
	 *            is the polynom which add to our Polynom
	 * 
	 */

	@Override
	public void add(Polynom_able p1) {
		// TODO Auto-generated method stub
		Iterator<Monom> i = p1.iteretor();
		// use to go reach the Polynom
		while (i.hasNext()) {
			Monom x = new Monom(i.next());
			add(x);
		}

	}

	/**
	 * add monom to polynom
	 * 
	 * @param m1
	 *            is the Monom we added to polynom
	 */

	@Override
	public void add(Monom m1) {
		Iterator<Monom> here = iteretor();
		// to comapare all the Polynom to the Monom
		boolean t = true;
		while (here.hasNext() && t == true) {
			Monom d = here.next();
			if (d.get_power() == m1.get_power()) {
				Monom c = new Monom();// to store the add result
				c.set_power(m1.get_power());
				c.set_coefficient(m1.get_coefficient() + d.get_coefficient());
				here.remove();// remove the last Monom
				PolynomArray.add(c);
				t = false;
			}
		}
		if (t) {
			PolynomArray.add(m1);
		}
		this.PolynomArray.sort(q);// sort the Polynom by x's power

	}

	/**
	 * substrcut polynom
	 * 
	 * @param p1
	 *            is the Polynom we substruct with ours
	 */

	@Override
	public void substract(Polynom_able p1) {
		// TODO Auto-generated method stub
		Monom x = new Monom(-1, 0);
		// to Multiply the Polynom
		Polynom y = (Polynom) p1.copy();
		Iterator<Monom> i = y.iteretor();
		while (i.hasNext()) {
			i.next().Multiply(x);
			// the substruct
		}
		this.add(y);

	}

	/**
	 * multiply polynoms
	 * 
	 * @param p1
	 *            is the polynom we Multiply
	 */

	@Override
	public void multiply(Polynom_able p1) {
		// TODO Auto-generated method stub
		Iterator<Monom> a = iteretor();
		Iterator<Monom> b = p1.iteretor();// to iterator to reche the polynoms
		Polynom z = new Polynom();
		while (a.hasNext()) {
			Monom r = new Monom(a.next());
			while (b.hasNext()) {
				Monom t = new Monom(r);
				t.Multiply(b.next());// multipling any Monom
				z.add(t);
			}
			b = p1.iteretor();

		}
		Polynom p = (Polynom) z.copy();
		this.PolynomArray = p.PolynomArray;

	}

	/**
	 * checking if it ewual
	 * 
	 * @param p1
	 *            is the polynom we want to know if it equal to our polynom
	 * @return true if qeual flase if dont equal
	 */

	@Override
	public boolean equals(Polynom_able p1) {

		Polynom p = (Polynom) p1;
		// if the sizes dont equal, its not equal
		if (p.getPolynomArray().size() != this.PolynomArray.size())
			return false;
		// 2 iterators to reach the Polynoms
		Iterator<Monom> here = iteretor();
		Iterator<Monom> there = p1.iteretor();
		boolean Eq = true;
		while (here.hasNext() && Eq) {
			Eq = false;
			Monom i = here.next();
			while (there.hasNext() && !Eq) {
				Monom j = there.next();
				if (i.Equal(j)) {
					Eq = true;
				}
			}
			there = p1.iteretor();
		}

		return Eq;
	}

	/**
	 * checking if polynom is =0
	 * 
	 * @return true if zero false ig not
	 * 
	 */

	@Override
	public boolean isZero() {
		boolean z = true;
		// flag
		Iterator<Monom> here = iteretor();
		while (here.hasNext()) {
			Monom a = here.next();
			if (a.get_coefficient() != 0) {
				z = false;
			} // checking if any coefficient is 0
		}
		return z;
	}

	/**
	 * fing x which f(x)=0
	 * 
	 * @param x0
	 *            bound the line
	 * @param x1
	 *            bound the line
	 * @param eps
	 *            amount of calculation, precision volume
	 * @return root of function
	 */
	@Override
	public double root(double x0, double x1, double eps) {
		// TODO Auto-generated method stub
		double x = (x1 + x0) / 2; // get middle
		double y = f(x); // get y of middle, to realize where to go
		while (Math.abs(y) > eps) { // stop when we close enough
			double y0 = f(x0);
			double y1 = f(x1); // to realize if the function rising or fainting.
			// first case, rising function
			if (x0 < x1 && y0 < y1 || x1 < x0 && y1 < y0) {
				// y>0, get right point to the middle
				if (y > 0) {
					if (x1 < x0)
						x0 = x;
					else
						x1 = x;
				}
				// y<0, get left point to middle
				else {
					if (x1 < x0)
						x1 = x;
					else
						x0 = x;
				}
			} // second case, fainting function
			else {
				// y>0, get left point to middle
				if (y > 0) {
					if (x1 < x0)
						x1 = x;
					else
						x0 = x;
				}
				// y<0, get right point to middle
				else {
					if (x0 < x1)
						x1 = x;
					else
						x0 = x;
				}
			} // find new middle
			x = (x1 + x0) / 2;
			y = f(x);
		}

		return y;
	}

	public double root2(double x0, double x1, double eps) {
		// TODO Auto-generated method stub
		double x = (x1 + x0) / 2; // get middle
		double y = f(x); // get y of middle, to realize where to go
		while (Math.abs(y) > eps) { // stop when we close enough
			double y0 = f(x0);
			double y1 = f(x1); // to realize if the function rising or fainting.
			// first case, rising function
			if (x0 < x1 && y0 < y1 || x1 < x0 && y1 < y0) {
				// y>0, get right point to the middle
				if (y > 0) {
					if (x1 < x0)
						x0 = x;
					else
						x1 = x;
				}
				// y<0, get left point to middle
				else {
					if (x1 < x0)
						x1 = x;
					else
						x0 = x;
				}
			} // second case, fainting function
			else {
				// y>0, get left point to middle
				if (y > 0) {
					if (x1 < x0)
						x1 = x;
					else
						x0 = x;
				}
				// y<0, get right point to middle
				else {
					if (x0 < x1)
						x1 = x;
					else
						x0 = x;
				}
			} // find new middle
			x = (x1 + x0) / 2;
			y = f(x);
		}

		return x;
	}

	/**
	 * deep copy between two polynoms
	 * 
	 * @return polynon f which f(x)=g(x) to any x
	 */
	@Override
	public Polynom_able copy() {
		// TODO Auto-generated method stub
		Iterator<Monom> here = iteretor();
		Polynom b = new Polynom();
		// to store the results
		while (here.hasNext()) {
			Monom z = new Monom(here.next());
			b.add(z);
			// add Monoms one by one
		}
		return b;
	}

	/**
	 * making derivative to polynom return derivated polynom
	 */

	@Override
	public Polynom_able derivative() {
		Polynom t = (Polynom) this.copy();
		// copy the polynom
		Iterator<Monom> here = t.iteretor();
		// iterator to get all the Monoms
		while (here.hasNext()) {
			here.next().derivative();
		}
		// call derivated function from class Monom

		return t;
	}

	/**
	 * calculating area
	 * 
	 * @param x0-
	 *            left side
	 * @param x1-
	 *            right side
	 * @param eps-
	 *            amount of precision
	 * @return area of functipn
	 */

	@Override
	public double area(double x0, double x1, double eps) {
		double n = Math.abs((x1 - x0) / eps);
		// amount of calculations
		double sum = 0;
		// stoer the results
		double x = x0;
		for (int i = 0; i < n; i++) {
			x = x + eps;
			if (f(x) > 0) {
				sum = sum + f(x);
			}
		}

		return sum * eps;
	}

	public double area2(double x0, double x1, double eps) {
		double n = Math.abs((x1 - x0) / eps);
		// amount of calculations
		double sum = 0;
		// stoer the results
		double x = x0;
		for (int i = 0; i < n; i++) {
			x = x + eps;

			sum = sum + f(x);

		}

		return sum * eps;
	}

	/**
	 * functuiom to make an Iterator
	 * 
	 * @return iterator
	 *
	 */
	@Override
	public Iterator<Monom> iteretor() {

		return this.PolynomArray.iterator();

	}

	/**
	 * function which printing Polynoms
	 * 
	 * @return string which represent the function
	 */
	public String toString() {
		Iterator<Monom> here = iteretor();
		// iterator to reach the monoms
		Monom t = here.next();
		String s;
		// first Monom
		s=t.toString()+"";	

		while (here.hasNext()) {
			Monom r = here.next();
			if (r.get_coefficient() < 0) {
				s = s + r.toString() + "";
			} else {
				s = s + "+" + r.toString();
			}
		}
		return s;

	}

	public double Blockedarea(double x0, double x1, double eps) {
		double roots[] = new double[100];
		int j = 0;
		double sum = 0;
		for (double i = x0; i < x1; i = i + 0.05) {
			if (this.f(i) < 0.5 && this.f(i) > -0.5) {

				roots[j++] = i;
			}
		}
		for (int i = 0; i < j - 1; i++) {
			sum = sum + this.area2(roots[i], roots[i + 1], eps);
		}
		return sum;
	}

	/**
	 * 
	 * @return arraylist of polynom
	 */
	///////////////////
	public ArrayList<Monom> getPolynomArray() {
		return PolynomArray;
	}

	/**
	 * 
	 * @param a-
	 *            setting an polynom
	 */
	public void setA(ArrayList<Monom> a) {
		this.PolynomArray = a;
	}
	//////////////////

	public static void main(String args[]) {
		// checking add Monom method- clear!
		/*
		 * Polynom a = new Polynom(); Iterator <Monom> here = a.a.iterator(); Monom b =
		 * new Monom (2,3); Monom c = new Monom (3,3); a.add(b); a.add(c); for(int
		 * i=0;i<a.a.size();i++){ System.out.println(a.a.get(i).get_coefficient()); }
		 */

		// checking function f, value at x - clear!
		/*
		 * Polynom a = new Polynom();
		 * 
		 * Iterator <Monom> here = a.a.iterator(); Monom b = new Monom (2,3); Monom c =
		 * new Monom (3,2); a.add(b); a.add(c); System.out.println(a.f(2));
		 */

		// checking function add Polynom- clear!
		/*
		 * Polynom a = new Polynom(); Iterator <Monom> here = a.a.iterator(); Monom b =
		 * new Monom (2,3); Monom c = new Monom (3,2); a.add(b); a.add(c);
		 * 
		 * Polynom z=new Polynom(); Monom x=new Monom (2,1); Monom y= new Monom (2,2);
		 * z.add(x); z.add(y);
		 * 
		 * a.add(z); for(int i=0;i<3;i++){
		 * System.out.println(a.a.get(i).get_coefficient()+" , "+a.a.get(i).get_power())
		 * ;
		 */
		// checking function area- clear!
		/*
		 * Polynom a = new Polynom(); Iterator <Monom> here = a.a.iterator(); Monom b =
		 * new Monom (2,3); Monom c = new Monom (3,2); a.add(b); a.add(c);
		 * System.out.println(a.area(0,3,0.0001));
		 */
		// checking function copy- clear!
		/*
		 * Polynom a = new Polynom(); Iterator <Monom> here = a.a.iterator(); Monom b =
		 * new Monom (6,3); Monom c = new Monom (3,7); a.add(b); a.add(c); Polynom g=
		 * (Polynom) a.copy();
		 * 
		 * for(int i=0; i<a.a.size();i++) {
		 * System.out.println(a.a.get(i).get_coefficient()+" , "+a.a.get(i).get_power())
		 * ; } for(int i=0; i<g.a.size();i++) {
		 * System.out.println(g.a.get(i).get_coefficient()+" , "+g.a.get(i).get_power())
		 * ; } c.set_coefficient(8);
		 * 
		 * for(int i=0; i<a.a.size();i++) {
		 * System.out.println(a.a.get(i).get_coefficient()+" , "+a.a.get(i).get_power())
		 * ; } for(int i=0; i<g.a.size();i++) {
		 * System.out.println(g.a.get(i).get_coefficient()+" , "+g.a.get(i).get_power())
		 * ; }
		 */
		// checking function derivated - clear!
		/*
		 * Polynom a = new Polynom(); Iterator <Monom> here = a.a.iterator(); Monom b =
		 * new Monom (6,3); Monom c = new Monom (3,7); a.add(b); a.add(c); Polynom
		 * t=(Polynom) a.derivative(); for(int i=0; i<a.a.size();i++) {
		 * System.out.println(t.a.get(i).get_coefficient()+" , "+t.a.get(i).get_power())
		 * ; }
		 */

		// checking function isZero- clear!
		/*
		 * Polynom a = new Polynom(); Iterator <Monom> here = a.a.iterator(); Monom b =
		 * new Monom (6,3); Monom c = new Monom (3,7); a.add(b); a.add(c);
		 * System.out.println(a.isZero());
		 * 
		 * Polynom bx = new Polynom(); Iterator <Monom> dhere = bx.a.iterator(); Monom z
		 * = new Monom (0,3); Monom y = new Monom (0,7); bx.add(z); bx.add(y);
		 * 
		 * System.out.println(bx.isZero());
		 */
		// checking Multiply function- clear!
		/*
		 * Polynom a = new Polynom(); Iterator <Monom> here = a.a.iterator(); Monom t =
		 * new Monom (6,9); Monom c = new Monom (3,7); a.add(t); a.add(c);
		 * 
		 * Polynom b = new Polynom(); Iterator <Monom> dhere = b.a.iterator(); Monom z =
		 * new Monom (2,3); b.add(z); a.multiply(b); for(int i=0;i<2;i++) {
		 * System.out.println(a.a.get(i).get_coefficient()+" , "+a.a.get(i).get_power())
		 * ; }
		 */
		// checking function substruct - clear!
		/*
		 * Polynom a = new Polynom(); Monom t = new Monom (6,9); Monom c = new Monom
		 * (3,7); a.add(t); a.add(c);
		 * 
		 * Polynom b = new Polynom(); Monom z = new Monom (2,3); Monom s = new Monom
		 * (2,7); b.add(s); b.add(z);
		 * 
		 * a.substract(b); for(int i=0;i<3;i++) {
		 * System.out.println(a.a.get(i).get_coefficient()+" , "+a.a.get(i).get_power())
		 * ;
		 */
		// checking function equal - clear!
		/*
		 * Polynom a = new Polynom(); Monom t = new Monom (6,9); Monom c = new Monom
		 * (3,7); a.add(t); a.add(c);
		 * 
		 * Polynom b = new Polynom(); Monom z = new Monom (6,9); Monom s = new Monom
		 * (3,7); b.add(s); b.add(z); System.out.println(a.equals(b));
		 */

		// checking root function- clear!
		/*
		 * Polynom a = new Polynom(); Monom b = new Monom (1,3); Monom c=new
		 * Monom(-4,1); a.add(b); a.add(c); System.out.println(a.root(-1,-4, 0.5));
		 */
		// checking function toString -clear!
		/*
		 * Polynom b = new Polynom(); Monom z = new Monom (6,11); Monom s = new Monom
		 * (-3,10); b.add(s); b.add(z); System.out.println(b.toString());
		 */
		/*
		Polynom s = new Polynom();
		s.add(new Monom(1, 3));
		s.add(new Monom(-9, 1));

		System.out.println(s.Blockedarea(-4, 4, 0.01));
		 */
		Polynom a= new Polynom("-x^6+3x");
		Monom b=new Monom ("-8");
		a.add(b);
		System.out.println(a.toString());
		
		/* 	
}
		 */
}
}
