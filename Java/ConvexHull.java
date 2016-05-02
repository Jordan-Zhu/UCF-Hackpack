import java.util.Arrays;
import java.util.Stack;

/**
 * Convex Hull: This algorithm finds all vertices of the convex hull ordered along its boundary.
 */
public class ConvexHull {
    public static void main(String[] args) {
        int n = 5;
        pt[] pts = new pt[n];
        for (int i = 0; i < n; i++) {
            int x = 1;
            int y = 2;
            pts[i] = new pt(x, y);
        }

        // Set the reference point.
        int refIndex = getIndexMin(pts);
        pt.refX = pts[refIndex].x;
        pt.refY = pts[refIndex].y;

        // Output solution.
        System.out.printf("%.1f\n", grahamScan(pts, n));

    }

    // Returns the point in pts with minimum y breaking tie by minimum x.
    public static int getIndexMin(pt[] pts) {
        int best = 0;
        for (int i = 1; i < pts.length; i++)
            if (pts[i].y < pts[best].y || (pts[i].y == pts[best].y && pts[i].x < pts[best].x))
                best = i;
        return best;
    }

    public static double grahamScan(pt[] pts, int n) {
        // Sort the points by angle with reference point.
        Arrays.sort(pts);

        // Push first two points on to stack.
        // This stack will store the convex hull.
        Stack<pt> myStack = new Stack<pt>();
        myStack.push(pts[0]);
        myStack.push(pts[1]);

        // Go through the rest of the points.
        for (int i = 2; i < n; i++) {
            // Get last three points.
            pt cur = pts[i];
            pt mid = myStack.pop();
            pt prev = myStack.pop();

            // Pop off the left turns.
            while (!prev.isRightTurn(mid, cur)) {
                mid = prev;
                prev = myStack.pop();
            }

            // Push back the last right turn.
            myStack.push(prev);
            myStack.push(mid);
            myStack.push(cur);
        }

        // Add up distances around the hull.
        double res = 0;
        pt cur = pts[0];
        while (myStack.size() > 0) {
            pt next = myStack.pop();
            res += cur.dist(next);
            cur = next;
        }

        return res;
    }
}

class pt implements Comparable<pt> {

    // Stores reference pt
    public static int refX;
    public static int refY;

    public int x;
    public int y;
    public double angle;

    public pt(int myx, int myy) {
        x = myx;
        y = myy;
    }

    // Sets this point's angle relative to origin, from 0 to 2pi.
    public void setAngle(pt origin) {
        angle = getAngle(origin);
    }

    // Returns this point's angle relative to origin, from 0 to 2pi.
    public double getAngle(pt origin) {
        if (this.equals(origin))
            return 0;
        else {
            double tmp = Math.atan2(this.y - origin.y, this.x - origin.x);
            if (tmp <= 0)
                tmp += (2 * Math.PI);

            return tmp;
        }
    }

    // Returns the vector from this to other.
    public pt getVect(pt other) {
        return new pt(other.x - x, other.y - y);
    }

    // Returns the distance between this and other.
    public double dist(pt other) {
        return Math.sqrt((other.x - x) * (other.x - x) + (other.y - y) * (other.y - y));
    }

    // Returns the magnitude ot this cross product other.
    public int crossProductMag(pt other) {
        return this.x * other.y - other.x * this.y;
    }

    // returns true iff this to mid to next is a right turn (180 degree is considered right turn).
    public boolean isRightTurn(pt mid, pt next) {
        pt v1 = getVect(mid);
        pt v2 = mid.getVect(next);
        return v1.crossProductMag(v2) >= 0; /*** Change to > 0 to skip collinear points. ***/
    }

    // Returns true iff this pt is the origin.
    public boolean isZero() {
        return x == 0 && y == 0;
    }

    public int compareTo(pt other) {

        pt myRef = new pt(refX, refY);
        pt v1 = myRef.getVect(this);
        pt v2 = myRef.getVect(other);

        // To avoid 0 issues.
        if (v1.isZero()) return -1;
        if (v2.isZero()) return 1;

        // Angles are different, we are going counter-clockwise here.
        if (v1.crossProductMag(v2) != 0)
            return -v1.crossProductMag(v2);

        // This should work, smaller vectors come first.
        if (myRef.dist(v1) < myRef.dist(v2)) return -1;
        return 1;
    }

    public String toString() {
        return x + " " + y;
    }
}
