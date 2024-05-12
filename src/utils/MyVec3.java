package utils;

/**
 * Class representing a vector.
 * 
 * @author Mazen
 */
public class MyVec3 {

	private double x;
	private double y;
	private double z;

	/**
	 * Constructor.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param z the z coordinate
	 */
	public MyVec3(final double x, final double y, final double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Constructor by default.
	 */
	public MyVec3() {
		this(0.0D, 0.0D, 0.0D);
	}

	/**
	 * 
	 * @return the x coordinate
	 */
	public double getX() {
		return this.x;
	}

	/**
	 * 
	 * @return the y coordinate
	 */
	public double getY() {
		return this.y;
	}

	/**
	 * 
	 * @return the z coordinate
	 */
	public double getZ() {
		return this.z;
	}

	/**
	 * Add a vector to this vector.
	 * 
	 * @param v the vector to add
	 * @return the result of the addition
	 */
	public MyVec3 add(MyVec3 v) {
		return new MyVec3(this.x + v.x, this.y + v.y, this.z + v.z);
	}

	/**
	 * Subtract a vector from this vector.
	 * 
	 * @param c the vector to subtract
	 * @return the result of the subtraction
	 */
	public MyVec3 sub(MyVec3 c) {
		return new MyVec3(this.x - c.x, this.y - c.y, this.z - c.z);
	}

	/**
	 * Multiply this vector by a scalar.
	 * 
	 * @param s the scalar
	 * @return the result of the multiplication
	 */
	public MyVec3 mul(double s) {
		return new MyVec3(this.x * s, this.y * s, this.z * s);
	}

	/**
	 * Compute the dot product of this vector and another vector.
	 * 
	 * @param v the vector to compute the dot product with
	 * @return the dot product
	 */
	public double dotProduct(MyVec3 v) {
		return this.x * v.x + this.y * v.y + this.z * v.z;
	}

	/**
	 * Normalize this vector.
	 * 
	 */
	public void normalize() {
		double lengthSquare = this.dotProduct(this);
		if (lengthSquare > 0.0D) {
			double length = Math.sqrt(lengthSquare);
			this.x /= length;
			this.y /= length;
			this.z /= length;
		}
	}

	/**
	 * Compute the cross product of this vector and another vector.
	 *
	 * @param v the vector to compute the cross product with
	 * @return the cross product
	 */
	public MyVec3 crossProduct(MyVec3 v) {
		double newX = this.y * v.z - this.z * v.y;
		double newY = this.z * v.x - this.x * v.z;
		double newZ = this.x * v.y - this.y * v.x;
		return new MyVec3(newX, newY, newZ);
	}

	/**
	 *
	 * @return the length of the vector
	 */
	public double length() {
		return Math.sqrt(this.dotProduct(this));
	}

	/**
	 *
	 * @param v the vector to compare
	 * @return the angle between the two vectors
	 */
	public double angle(MyVec3 v) {
		return Math.acos(this.dotProduct(v) / (this.length() * v.length()));
	}

	/**
	 *
	 * @param v the vector to compare
	 * @return the distance between the two vectors
	 */
	public double distance(MyVec3 v) {
		return Math.sqrt(Math.pow(this.x - v.x, 2) + Math.pow(this.y - v.y, 2) + Math.pow(this.z - v.z, 2));
	}

	/**
	 *
	 * @param v the vector to compare
	 * @return the projection of this vector on the other vector
	 */
	public MyVec3 projection(MyVec3 v) {
		return v.mul(this.dotProduct(v) / v.dotProduct(v));
	}

	/**
	 *
	 * @return the string representation of the vector
	 */
	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ", " + this.z + ")";
	}

	/**
	 *
	 * @param v the vector to compare
	 * @return true if the vectors are equal, false otherwise
	 */
	public boolean equals(MyVec3 v) {
		return this.x == v.x && this.y == v.y && this.z == v.z;
	}
}
