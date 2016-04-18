import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Created by Jordan on 4/15/2016.
 */
public class intersect {
    static class Vector3 {
        public float x, y, z;

        public Vector3(float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public Vector3 add(Vector3 other) {
            return new Vector3(x + other.x, y + other.y, z + other.z);
        }

        public Vector3 sub(Vector3 other) {
            return new Vector3(x - other.x, y - other.y, z - other.z);
        }

        public Vector3 scale(float f) {
            return new Vector3(x * f, y * f, z * f);
        }

        public Vector3 cross(Vector3 other) {
            return new Vector3(y * other.z - z * other.y,
                               z - other.x - x * other.z,
                               x - other.y - y * other.x);
        }

        public float dot(Vector3 other) {
            return x * other.x + y * other.y + z * other.z;
        }
    }

    public static boolean intersectRayWithPlane(Vector3 R1, Vector3 R2,
                                                Vector3 S1, Vector3 S2, Vector3 S3) {
        // Step 1. Find the normal to the plane.
        Vector3 dS21 = S2.sub(S1);
        Vector3 dS31 = S3.sub(S1);
        Vector3 n = dS21.cross(dS31);

        // Step 2. Compute the ray / plane intersection.
        Vector3 dR = R1.sub(R2);

        float ndotdR = n.dot(dR);

        if (Math.abs(ndotdR) < 1e-6f) { // Choose your tolerance
            System.out.println("There is no intersection.");
            return false;
        }

        // Else we can find the 3D coordinates of the point of intersection M.
        float t = -n.dot(R1.sub(S1)) / ndotdR;
        Vector3 M = R1.add(dR.scale(t));

        // Step 3. Project the vector M - S1 onto the two vectors S2 - S1 and S3 - S1.
        Vector3 dMS1 = M.sub(S1);
        float u = dMS1.dot(dS21);
        float v = dMS1.dot(dS31);

        // Step 4. If these pass then the point of intersection M lies inside the plane,
        // else it's outside.
        return (u >= 0.0f && u <= dS21.dot(dS21)
                && v >= 0.0f && v <= dS31.dot(dS31));

    }
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int numCases = Integer.parseInt(in.nextLine());

        for(int loop = 1; loop <= numCases; loop++)
        {
            StringTokenizer tok1 = new StringTokenizer(in.nextLine());
            float x1 = Float.parseFloat(tok1.nextToken());
            float y1 = Float.parseFloat(tok1.nextToken());
            float z1 = Float.parseFloat(tok1.nextToken());

            float x2 = Float.parseFloat(tok1.nextToken());
            float y2 = Float.parseFloat(tok1.nextToken());
            float z2 = Float.parseFloat(tok1.nextToken());

            Vector3 R1 = new Vector3(x1, y1, z1);
            Vector3 R2 = new Vector3(x2, y2, z2);

            StringTokenizer tok2 = new StringTokenizer(in.nextLine());
            float x3 = Float.parseFloat(tok2.nextToken());
            float y3 = Float.parseFloat(tok2.nextToken());
            float z3 = Float.parseFloat(tok2.nextToken());

            float x4 = Float.parseFloat(tok2.nextToken());
            float y4 = Float.parseFloat(tok2.nextToken());
            float z4 = Float.parseFloat(tok2.nextToken());

            float x5 = Float.parseFloat(tok2.nextToken());
            float y5 = Float.parseFloat(tok2.nextToken());
            float z5 = Float.parseFloat(tok2.nextToken());

            Vector3 S1 = new Vector3(x3, y3, z3);
            Vector3 S2 = new Vector3(x4, y4, z4);
            Vector3 S3 = new Vector3(x5, y5, z5);

            boolean b = intersectRayWithPlane(R1, R2, S1, S2, S3);
//            assert b;
            System.out.printf("Data Set #%d: %s\n", loop, b);

        }
    }
}
