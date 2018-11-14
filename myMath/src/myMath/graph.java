package myMath;
import java.awt.Color;
import javax.swing.JFrame;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.points.PointRenderer;
import de.erichseifert.gral.ui.InteractivePanel;

public class graph extends JFrame {
    public graph() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        Polynom s= new Polynom();
        s.add(new Monom(1,3));
        s.add(new Monom(-9,1));
//        s.add(new Monom(0.4,2));
//        s.add(new Monom(3,1));
//        s.add(new Monom(-4.5,0));
        DataTable data = new DataTable(Double.class, Double.class);
        DataTable data2 = new DataTable(Double.class, Double.class);
        for (double x = -4.0; x <= 4.0; x+=0.01) {
        	double y=s.f(x);
        	double y0=s.f(x-0.01);
        	double y1=s.f(x+0.01);
            data.add(x, y);
            if(y<y0&&y<y1||y>y0&&y>y1)data2.add(x,y);  
        }
        XYPlot plot = new XYPlot(data);
        plot.add(data2);
        getContentPane().add(new InteractivePanel(plot));
        LineRenderer lines = new DefaultLineRenderer2D();
        plot.setLineRenderers(data, lines);
        Color color = new Color(0.0f, 0.3f, 1.0f);
        Color color2 = new Color(0.6400f, 0.3300f, 0.0300f);
        plot.getPointRenderers(data2).get(0).setColor(color2);
        plot.getPointRenderers(data).get(0).setColor(color);
        plot.getLineRenderers(data).get(0).setColor(color);
    }

    public static void main(String[] args) {
        graph frame = new graph();
        frame.setVisible(true);
        Polynom s= new Polynom();
        s.add(new Monom(0.2,4));
        s.add(new Monom(-1.5,3));
        s.add(new Monom(3,2));
        s.add(new Monom(-1,1));
        s.add(new Monom(-5,0));
        
        // roots of s
        double x0=s.root2(-2,0,0.01);
        double x1=s.root2(4, 6, 0.01);
        System.out.println("x0 = "+x0);
        System.out.println("x1 = "+x1);
        System.out.println( s.area2(x0, x1, 0.01));

        
    }
}