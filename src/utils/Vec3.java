package utils;

/**
 * Class representing a vector.
 * 
 * @author Mazen
 */
public class Vec3 {

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
	public Vec3(final double x, final double y, final double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Constructor by default.
	 */
	public Vec3() {
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
	public Vec3 add(Vec3 v) {
		return new Vec3(this.x + v.x, this.y + v.y, this.z + v.z);
	}

	/**
	 * Subtract a vector from this vector.
	 * 
	 * @param c the vector to subtract
	 * @return the result of the subtraction
	 */
	public Vec3 sub(Vec3 c) {
		return new Vec3(this.x - c.x, this.y - c.y, this.z - c.z);
	}

	/**
	 * Multiply this vector by a scalar.
	 * 
	 * @param s the scalar
	 * @return the result of the multiplication
	 */
	public Vec3 mul(double s) {
		return new Vec3(this.x * s, this.y * s, this.z * s);
	}

	/**
	 * Compute the dot product of this vector and another vector.
	 * 
	 * @param v the vector to compute the dot product with
	 * @return the dot product
	 */
	public double dotProduct(Vec3 v) {
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

}
