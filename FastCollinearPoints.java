import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {

    private final int n;
    private ArrayList<LineSegment> segments;


    public FastCollinearPoints(Point[] points) {
        nullException(points);
        segments = new ArrayList<LineSegment>();
        Point [] ps = points.clone();
        Arrays.sort(ps);
        Point [] pCopy = points.clone();
        Arrays.sort(pCopy);
        n = points.length;

        for (int i = 0; i < n-1; i++) {
            Point p = ps[i];
            Arrays.sort(pCopy, p.slopeOrder());
            int count = 0;

            for (int j=1; j< n-1;j++){
                if (p.slopeOrder().compare(pCopy[j],pCopy[j+1])==0)
                {
                    count++;
                    if ((j == n-2) && (count >=2))
                    {
                        Point[] line = new Point[count+2];
                        line[0] = p;
                        for (int k = 1; k<=count+1;k++)
                            line[k]= pCopy[j-k+2];
                        Arrays.sort(line);
                        if (line[0]==p)
                            segments.add(new LineSegment(line[0],line[line.length-1]));
                            break;
                    }
                }
                else {
                    if (count >= 2 ) {
                        Point[] line = new Point[count+2];
                        line[0] = p;
                        for (int k = 1; k<count+2;k++)
                            line[k]= pCopy[j-k+1];
                        Arrays.sort(line);
                        if (line[0]==p)
                            segments.add(new LineSegment(line[0],line[line.length-1]));
                    }
                    count = 0;
                }
            }
        }
    }

        // finds all line segments containing 4 or more points

        public int numberOfSegments ()
        {
        return segments.size();
        }       // the number of line segments

        public LineSegment[] segments () {
            int count = 0;
            int size = segments.size();
            LineSegment[] seg = new LineSegment[size];
            for (LineSegment l : segments) {
                seg[count++] = l;
            }
            return seg;
        }                // the line segments

    private void nullException (Point[]points){
            if (points == null) throw new IllegalArgumentException();
            for (Point p : points) {
                if (p == null)
                    throw new IllegalArgumentException(); }
            Point[] ps = points.clone();
            Arrays.sort(ps);
            for(int i = 0; i < ps.length-1; i++){
                if (ps[i].compareTo(ps[i + 1]) == 0)
                    throw new IllegalArgumentException(); }
        }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        StdDraw.show();
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        FastCollinearPoints collinear = new FastCollinearPoints(points);
        StdOut.println(collinear.numberOfSegments());

        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
    }
}

