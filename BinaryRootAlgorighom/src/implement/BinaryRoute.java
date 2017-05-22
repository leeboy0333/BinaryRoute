package implement;

import module.Line;
import module.Point;

import java.util.*;

/**
 * Created by Administrator on 2017/5/21.
 */
public class BinaryRoute {
    private Map<Point, Set<Line>> point2Lines =null;
    public List<Line> route(List<Line> lines, Point pIn, Point pOut)
    {
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
                return route(linesB, pIn, pOut);
            }
            else
            {
                return route(linesA, pIn, pOut);
            }
        }
        for(Point pointAB : pointsAB)
        {
            List<Line> linesInA = route(linesA, pIn, pointAB);
            List<Line> linesInB = route(linesB, pointAB, pOut);
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

    private int compareRoute(List<Line> minRouteLines, List<Line> result) {
        return minRouteLines.size() - result.size();
    }

    private void binarySeperateLines(List<Line> lines, List<Line> linesA, List<Line> linesB, Point pointA, Point pointB, Set<Point> pointsAB)
    {
        final int halfLineSize = lines.size() >> 1;
        Set<Line> linesToAdd = new HashSet<>();
        Map<Point, Set<Line>> point2Line = piont2LineSetMap(lines);
        Set<Point> nextPoints = new HashSet<>(Arrays.asList(pointA));

        addLineToA:
        while(!nextPoints.isEmpty())
        {
            Set<Point> nextPointsTmp = new HashSet<>();
            for(Point point : nextPoints)
            {
                Set<Line> nextLines = point2Line.remove(point);
                if(nextLines == null)
                {
                    continue;
                }
                for(Line line : nextLines)
                {
                    if(linesToAdd.contains(line) || line.getP1().equals(pointB) || line.getP2().equals(pointB))
                    {
                        continue;
                    }
                    linesToAdd.add(line);
                    if(linesToAdd.size() >= halfLineSize)
                    {
                        break addLineToA;
                    }
                    if(line.getP1().equals(point))
                    {
                        nextPointsTmp.add(line.getP2());
                    }
                    else if(line.getP2().equals(point))
                    {
                        nextPointsTmp.add(line.getP1());
                    }
                }
            }
            nextPoints = nextPointsTmp;
        }

        Set<Point> pointsA = new HashSet<>();
        Set<Point> pointsB = new HashSet<>();

        for(Line line : lines)
        {
            if(linesToAdd.contains(line))
            {
                linesA.add(line);
                pointsA.add(line.getP1());
                pointsA.add(line.getP2());
            }
            else
            {
                linesB.add(line);
                pointsB.add(line.getP1());
                pointsB.add(line.getP2());
            }
        }

        for(Point point : pointsA)
        {
            if(pointsB.contains(point))
            {
                pointsAB.add(point);
            }
        }
    }

    private Map<Point, Set<Line>> piont2LineSetMap(List<Line> lines) {
        Map<Point, Set<Line>> point2LineSetMap = new HashMap<>();
        for(Line line : lines)
        {
            mapAppend(point2LineSetMap, line.getP1(), line);
            mapAppend(point2LineSetMap, line.getP2(), line);
        }
        return point2LineSetMap;
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
