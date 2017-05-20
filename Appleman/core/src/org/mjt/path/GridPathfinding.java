package org.mjt.path;

import org.mjt.astar.GridAstar;
import org.mjt.astar.GridHeuristic;

public class GridPathfinding
{
    GridAstar astar;
    GridHeuristic heuristic;

    public GridPathfinding()
    {
        heuristic = new GridHeuristic();
    }

    public GridPath getPath(GridLocation s, GridLocation e, GridMap map)
    {
        GridLocation start = (GridLocation) s;
        GridLocation end = (GridLocation) e;

        astar = new GridAstar(start, end, map, heuristic);

        return astar.getPath();
    }
}
