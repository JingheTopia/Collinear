import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private  ArrayList<LineSegment> segments = new ArrayList<LineSegment>();
    private Point [] pCopy;
    private int count = 0;


    public BruteCollinearPoints(Point[] points){
        nullException(points);
        pCopy = points.clone();
        final int n = pCopy.length;
        Arrays.sort(pCopy);
        for(int i = 0; i < n-3; i++)
        {
            for(int j=i+1; j < n-2;j++)
            {
                for(int k = j+1; k < n-1; k++)
                {
                    if (pCopy[i].slopeOrder().compare(pCopy[j],pCopy[k]) == 0)
                    for (int l = k+1; l < n; l++)
                    {
                        if (fourCoLine(pCopy[i],pCopy[j],pCopy[k],pCopy[l])){
                        segments.add(new LineSegment(pCopy[i],pCopy[l]));
                        count++;}
                    }

                }
            }
        }

    }    // finds all line segments containing 4 points

    private boolean fourCoLine(Point o1, Point o2, Point o3, Point o4){
        double slp1, slp2, slp3;
        slp1 = o1.slopeTo(o2);
        slp2 = o1.slopeTo(o3);
        slp3 = o1.slopeTo(o4);
        return  (slp1 == slp2 && slp1 == slp3);
    }

    private void nullException(Point [] points){
        if (points == null) throw new java.lang.IllegalArgumentException();
        for (Point p: points){
            if (p == null)
                throw new java.lang.IllegalArgumentException(); }
        Point[] ps = points.clone();
        Arrays.sort(ps);
        for(int i = 0; i < ps.length-1; i++)
        { if (ps[i].compareTo(ps[i+1]) == 0)
            throw new java.lang.IllegalArgumentException();
        }
    }

    public int numberOfSegments() { return count; }       // the number of line segments

    public LineSegment[] segments()
    { int count = 0;
        LineSegment [] result = new LineSegment[segments.size()];
    for(LineSegment l: segments)
    { result[count++] = l; }
    return result;
    }


    public static void main(String[] args) {// read the n points from a file
            In in = new In(args[0]);
            int n = in.readInt();
            Point[] points = new Point[n];
            for (int i = 0; i < n; i++) {
                int x = in.readInt();
                int y = in.readInt();
                points[i] = new Point(x, y);
            }// draw the points
            StdDraw.enableDoubleBuffering();
            StdDraw.setXscale(0, 32768);
            StdDraw.setYscale(0, 32768);
            for (Point p : points) {
                p.draw();
            }
            StdDraw.show();

            // print and draw the line segments
            BruteCollinearPoints collinear = new BruteCollinearPoints(points);
            StdOut.println(collinear.numberOfSegments());
            for (LineSegment segment : collinear.segments()) {
                StdOut.println(segment);
                segment.draw();
            }
            StdDraw.show();
        }

}

