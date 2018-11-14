
package myMath;

import java.util.Iterator;

import javax.management.RuntimeErrorException;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real
 * number and a is an integer (summed a none negative), see:
 * https://en.wikipedia.org/wiki/Monomial The class implements function and
 * support simple operations as: construction, value at x, derivative, add and
 * multiply.
 * 
 * @author oren zauda and omer zohar
 * 
 *
 */

public class Monom implements function {

	// ***************** add your code below **********************
	/**
	 * constrction
	 */
	public Monom() {
		this._coefficient = 0;
		this._power = 0;
	}

	/**
	 * constrction
	 * 
	 * @param a-
	 *            coeeficient
	 * 
	 * @param b-
	 *            power of x
	 */

	public Monom(double a, int b) {
		this.set_coefficient(a);// setting coeefficien
		this.set_power(b);// setting power
		if (b < 0)
			throw new RuntimeErrorException(null, "Err wrong value");
	}

	/**
	 * copy construction
	 * 
	 * @param ot-another
	 *            Monom we want to copy
	 */
	public Monom(Monom ot) {
		this._coefficient = ot._coefficient;
		this._power = ot._power;

	}

	/**
	 * calculating function in point x
	 * 
	 * @param x
	 *            is point in the graph
	 */

	public double f(double x) {
		return (this._coefficient * Math.pow(x, this._power));
	}

	/**
	 * adding functuion
	 * 
	 * @param a
	 *            Another Monom to add to our Monom
	 */
	public void add(Monom a) {
		if (this._power == a.get_power()) {
			this._coefficient = this._coefficient + a.get_coefficient();
		}
	}

	/**
	 * multoplyn function
	 * 
	 * @param a
	 *            another monom to multiply
	 */
	public void Multiply(Monom a) {
		this._coefficient = this._coefficient * a.get_coefficient();
		this._power = this._power + a.get_power();
	}

	/**
	 * derivating functipn
	 */

	public void derivative() {
		if (this.get_power() == 0) {
			this._coefficient = 0;
		} else {
			this._coefficient = this._coefficient * this._power;
			this._power = this._power - 1;
		}
	}

	/**
	 * 
	 * @param a
	 *            monom which is might be equal to this monom
	 * @return true if equal false if not
	 */
	public boolean Equal(Monom a) {
		if (this._coefficient == a.get_coefficient() && this._power == a.get_power()) {
			return true;
		} else
			return false;
	}

	/**
	 * stringing function
	 * 
	 * @return string repsent monom
	 */

	public String toString() {
		String t;
		if (_power > 1) {
			t = _coefficient + "x^" + _power;
		} else if (_power == 1) {
			t = _coefficient + "x";
		} else {
			t = "" + _coefficient;
		}
		return t;
	}

	/**
	 * constructor use string
	 * 
	 * @param s
	 *            is Monom wrote by string
	 */
	public Monom(String s) {
		//if the Monom contain negative power throw exception
		if(s.contains("^-")) {
			throw new RuntimeErrorException(null, "Eror wrong value of power");
		}
		//edge case, empty string
		if(s=="") {
			return;
		}
		// convert the string to char array
		char array[]=s.toCharArray();
		//first case, the string form is like "3x^2"
		if (s.contains("^")) {
			// if so, find the x index and insert value of coefficient and power
			for(int i=0;i<s.length();i++) {
				if (s.charAt(i)=='x') {
					this._coefficient=Double.parseDouble(s.substring(0, i));
					this._power=Integer.parseInt(s.substring(i+2));
				}
			}
		}    
		//second case, the string form is like "3x"
		if(s.contains("x")&&!s.contains("^")) {
			for(int i=0;i<s.length();i++) {
				if (s.charAt(i)=='x') {
					this._coefficient=Double.parseDouble(s.substring(0, i));
					this._power=1;
				}
			}

		}
		// third and last case, the string form is like "4"
		if(!s.contains("x")&&!s.equals("")) {
		this._coefficient=Double.parseDouble(s);
		}
	}




	// ****************** Private Methods and Data *****************

	public void set_coefficient(double a) {
		this._coefficient = a;
	}

	public void set_power(int p) {
		this._power = p;
	}

	int get_power() {
		return this._power;
	}

	double get_coefficient() {
		return this._coefficient;
	}

	private double _coefficient; //
	private int _power;

	public static void main(String args[]) {

		// checking initial methods, null constructor
		// parameters constructor, and copy constructor- clear!
		/*
		 * Monom a= new Monom();
		 * System.out.println("a: co= "+a._coefficient+", p= "+a._power); Monom b= new
		 * Monom (3,2); System.out.println("b: co= "+b._coefficient+", p= "+b._power);
		 * Monom c= new Monom (3,-1); Monom d= new Monom (a);
		 * System.out.println("d: co= "+d._coefficient+", p= "+d._power);
		 */

		// cheking method f, value at x - clear!
		/*
		 * Monom e= new Monom (4,4); System.out.println(e.f(1));
		 */

		// checking add function - clear!
		/*
		 * Monom f= new Monom (3,2); Monom g=new Monom (2,2); Monom h= new Monom (3,1);
		 * f.add(g); System.out.println("f+g: co= "+ f._coefficient+" p= "+f._power);
		 * f.add(h); System.out.println("f+h: co="+f._coefficient+"po= "+f._power );
		 * 
		 */

		// checking multiply function- clear!
		/*
		 * Monom i= new Monom (3,2); Monom j= new Monom (2,2); i.Multiply(j);
		 * System.out.println("i co= "+i._coefficient+"po= "+ i._power);
		 */

		// checking derivative function - clear!
		/*
		 * Monom k = new Monom (2,0); k.derivative(); System.out.println(k.toString());
		 */
		// checking equal function- clear!
		/*
		 * Monom t = new Monom (6,9); Monom c = new Monom (6,9);
		 * System.out.println(t.Equal(c));
		 */
		// checking function toString-clear!

		
	

	}
}
