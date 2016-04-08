import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Jordan on 4/8/2016.
 */
class circle {
    public int x;
    public int y;
    public int r;

    public circle(int x, int y, int r) {
        // set the class's variables to the input variables
        this.x = x;
        this.y = y;
        this.r = r;
    }

}

public class segment {
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        while(in.hasNext())
        {
            StringTokenizer circleTok = new StringTokenizer(in.nextLine());
            double x = Double.parseDouble(circleTok.nextToken());
            double y = Double.parseDouble(circleTok.nextToken());
            double r = Double.parseDouble(circleTok.nextToken());

            // center of the circle
            Point2D center = new Point2D.Double(x, y);

            StringTokenizer lineTok = new StringTokenizer(in.nextLine());
            double x1 = Double.parseDouble(lineTok.nextToken());
            double y1 = Double.parseDouble(lineTok.nextToken());
            double x2 = Double.parseDouble(lineTok.nextToken());
            double y2 = Double.parseDouble(lineTok.nextToken());

            // the line segment
            Line2D seg = new Line2D.Double(x1, y1, x2, y2);

            // Check if distance from the line segment to this point lies within radius
            if(seg.ptSegDist(center) <= r)
            {
                System.out.println("The line segment intersects the circle.");
            }
            else
            {
                System.out.println("The line segment does not intersect the circle.");
            }
        }
    }
}
