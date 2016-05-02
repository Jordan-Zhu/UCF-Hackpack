/**
 * 2D Geometry functions: Tests line intersection, parallel line intersection,
 * area of triangle, and area between vectors.
 * <p>
 * TO-DO: Circle-line segment intersection.
 */
public class geo2d {
    public static void main(String[] args) {
        pt2D a = new pt2D(3.0, 7.0);
        pt2D b = new pt2D(-4.0, 6.0);
        pt2D c = new pt2D(10.0, 2.0);
        pt2D d = new pt2D(1.0, -5.0);

        // find area of triangle abc, via vectors.
        vect2D v1 = new vect2D(a, b);
        vect2D v2 = new vect2D(a, c);
        System.out.printf("The area of triangle ABC is %.3f.\n", v1.crossMag(v2) / 2);

        // Find the angle between the two vectors.
        System.out.printf("The angle between the vectors is %.3f radians.\n", v1.angle(v2));

        // Test typical line intersection.
        line first = new line(a, b);
        line second = new line(c, d);
        pt2D meet = first.intersect(second);
        System.out.printf("The two lines meet at point (%.3f, %.3f).\n", meet.x, meet.y);

        // Test parallel line intersection call.
        pt2D e = new pt2D(24.0, 4.0);
        line third = new line(c, e);
        pt2D never = first.intersect(third);
        if (never == null) System.out.println("These lines never meet.");
    }
}

class vect2D {
    public double x;
    public double y;

    // Our constructor.
    public vect2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Calculates the vector of a line segment.
    public vect2D(pt2D start, pt2D end) {
        x = end.x - start.x;
        y = end.y - start.y;
    }

    // Returns the dot product of two vectors.
    public double dot(vect2D other) {
        return this.x * other.x + this.y * other.y;
    }

    // Returns the magnitude of the vector.
    public double mag() {
        return Math.sqrt(x * x + y * y);
    }

    // This calculates the angle between two vectors
    // using the relationship between the arccos of the dot product.
    // The formula for this is cos = v*w / (mag(v) x mag(w)).
    public double angle(vect2D other) {
        return Math.acos(this.dot(other) / mag() / other.mag());
    }

    public double signedCrossMag(vect2D other) {
        return this.x * other.y - other.x * this.y;
    }

    public double crossMag(vect2D other) {
        return Math.abs(signedCrossMag(other));
    }
}

class line {

    final public static double EPSILON = 1e-9;

    public pt2D p;
    public vect2D dir;

    public line(pt2D start, pt2D end) {
        p = start;
        dir = new vect2D(start, end);
    }

    public pt2D intersect(line other) {
        double den = det(dir.x, -other.dir.x, dir.y, -other.dir.y);
        if (Math.abs(den) < EPSILON) {
            return null;
        }

        double numLambda = det(other.p.x - p.x, -other.dir.x, other.p.y - p.y, -other.dir.y);
        return eval(numLambda / den);

    }

    // Returns the shortest distance from other to this line. Sets a vector from the starting
    // point of this line to other and uses the cross product with that vector and the direction
    // vector of the line.
    public double distance(pt2D other) {
        vect2D toPt = new vect2D(p, other);
        return dir.crossMag(toPt) / dir.mag();
    }

    // Returns the point on this line corresponding to parameter lambda.
    public pt2D eval(double lambda) {
        return new pt2D(p.x + lambda * dir.x, p.y + lambda * dir.y);
    }

    public static double det(double a, double b, double c, double d) {
        return a * d - b * c;
    }

}

class pt2D {

    public double x;
    public double y;

    public pt2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
