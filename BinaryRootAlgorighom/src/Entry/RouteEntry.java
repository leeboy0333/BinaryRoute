package Entry;

import implement.BinaryRoute;
import module.Line;
import module.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/21.
 */
public class RouteEntry {
    public static void main(String[] args) {
        BinaryRoute route = new BinaryRoute();

        List<Line> lines = initLines();

        List<Line> routeResult = route.route(lines, new Point("3"), new Point("11"));

        printLineList(routeResult);
    }

    private static void printLineList(List<Line> lines) {
        if(lines == null)
        {
            System.out.printf("lines is null.");
        }
        else
        {
            for(Line line:lines)
            {
                System.out.printf(line.getP1().getName() + "->" + line.getP2().getName() + ",");
            }
        }

        System.out.printf("\n---------------------");
    }

    private static List<Line> initLines() {
        List<Line> lines = new ArrayList<>();

        lines.add(makeLine("1","2"));
        lines.add(makeLine("1","3"));
        lines.add(makeLine("1","5"));
        lines.add(makeLine("1","10"));
        lines.add(makeLine("2","3"));
        lines.add(makeLine("3","4"));
        lines.add(makeLine("3","9"));
        lines.add(makeLine("4","5"));
        lines.add(makeLine("4","6"));
        lines.add(makeLine("4","7"));
        lines.add(makeLine("4","8"));
        lines.add(makeLine("4","9"));
        lines.add(makeLine("5","6"));
        lines.add(makeLine("5","10"));
        lines.add(makeLine("6","9"));
        lines.add(makeLine("6","7"));
        lines.add(makeLine("7","8"));
        lines.add(makeLine("7","11"));
        lines.add(makeLine("7","10"));
        lines.add(makeLine("8","9"));
        lines.add(makeLine("8","11"));
        lines.add(makeLine("10","11"));
        return lines;
    }

    private static Line makeLine(String p1, String p2)
    {
        return new Line(new Point(p1), new Point(p2));
    }


}
