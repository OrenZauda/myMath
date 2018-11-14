package myMath;

import java.util.Iterator;


public interface Polynom_able extends cont_function{
	
	public void add(Polynom_able p1);
	
	public void add(Monom m1);
	
	public void substract(Polynom_able p1);
	
	public void multiply(Polynom_able p1);
	
	public boolean equals (Polynom_able p1);
	
	public boolean isZero();
	
	public double root(double x0, double x1, double eps);
	
	public Polynom_able copy();

	public Polynom_able derivative();
	
	public double area(double x0,double x1, double eps);
	
	public Iterator<Monom> iteretor();
}
