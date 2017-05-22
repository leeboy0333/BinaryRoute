package implement;

import module.Line;
import module.Point;

import java.util.*;

/**
 * Created by Administrator on 2017/5/21.
 */
public class BinaryRoute {
    private Map<Point, Set<Line>> point2Lines =null;
    private List<Line> route(List<Line> lines, Point pIn, Point pOut, boolean init)
    {
        if(init){
            initPoint2Lines(lines);
        }
        if(pIn.equals(pOut))
        {
            return new ArrayList<>(0);
        }
        if(!isLinesContainsPoint(lines, pIn) || !isLinesContainsPoint(lines, pOut))
        {
            return null;
        }
        if(lines.size() == 1)
        {
            return lines;
        }
        for(Line line : lines)
        {
            if((line.getP1().equals(pIn) && line.getP2().equals(pOut)) ||
                    (line.getP1().equals(pOut) && line.getP2().equals(pIn)))
            {
                return Arrays.asList(line);
            }
        }

        Set<Point> pointsAB = new HashSet<>();
        List<Line> linesA = new ArrayList<>();
        List<Line> linesB = new ArrayList<>();

        binarySeperateLines(lines, linesA, linesB, pIn, pOut, pointsAB);

        List<Line> minRouteLines = null;
        if(pointsAB.isEmpty())
        {
            if(linesA.isEmpty())
            {
                return route(linesB, pIn, pOut, false);
            }
            else
            {
                return route(linesA, pIn, pOut,false);
            }
        }
        for(Point pointAB : pointsAB)
        {
            List<Line> linesInA = route(linesA, pIn, pointAB,false);
            List<Line> linesInB = route(linesB, pointAB, pOut,false);
            if(linesInA == null || linesInB == null)
            {
                continue;
            }
            List<Line> result = new ArrayList<>(linesInA);
            result.addAll(linesInB);
            if(minRouteLines == null || compareRoute(minRouteLines, result) > 0)
            {
                minRouteLines = result;
            }
        }
        return minRouteLines;
    }

    private void initPoint2Lines(List<Line> lines) {
        for(Line line :lines)
        {
            mapAppend(point2Lines, line.getP1(), line);
            mapAppend(point2Lines, line.getP2(), line);
        }
    }

    private <K,V> void mapAppend(Map<K, Set<V>> mapSet, K key, V val)
    {
        Set<V> valSet = mapSet.get(key);
        if(valSet == null)
        {
            valSet = new HashSet<>();
            mapSet.put(key, valSet);
        }
        valSet.add(val);
    }

    public List<Line> route(List<Line> lines, Point pIn, Point pOut)
    {
        return route(lines, pIn, pOut, true);
    }

    private int compareRoute(List<Line> minRouteLines, List<Line> result) {
        return minRouteLines.size() - result.size();
    }

    private void binarySeperateLines(List<Line> lines, List<Line> linesA, List<Line> linesB, Point pointA, Point pointB, Set<Point> pointsAB)
    {
        boolean toA = true;
        Set<Point> pointsA = new HashSet<>();
        Set<Point> pointsB = new HashSet<>();
         
        for(Point point : pointsA)
        {
            if(pointsB.contains(point))
            {
                pointsAB.add(point);
            }
        }
    }

    private boolean isLinesContainsPoint(List<Line> lines,  Point point)
    {
        for(Line line : lines)
        {
            if(point.equals(line.getP1()))
            {
                return true;
            }
            if(point.equals(line.getP2()))
            {
                return true;
            }
        }
        return false;
    }

}
