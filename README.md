# BinaryRoute
binary route algrithm
private void binarySeperateLines(List<Line> lines, List<Line> linesA, List<Line> linesB, Point pointA, Point pointB, Set<Point> pointsAB)
    {
        final int halfLineSize = lines.size() >> 1;
        Set<Line> linesToAdd = new HashSet<>();
        Map<Point, Set<Line>> point2Line = pint2Lines(lines);
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
