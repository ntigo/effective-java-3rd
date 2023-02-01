package com.coopnc.effectivejava3rd.item16.exam2;

public class Point {
	private double x;
	private double y;

	public Point(double x, double y){
		this.x = x;
		this.y = y;
	}

	public double getx(){
		return this.x;
	}

	public double gety(){
		return this.y;
	}

	public void setx(double x){
		this.x = x;
	}

	public void sety(double y){
		this.y = y;
	}

}
