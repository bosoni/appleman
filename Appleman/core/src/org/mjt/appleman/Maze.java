// orig code: http://jonathanzong.com/blog/2012/11/06/maze-generation-with-prims-algorithm
// modified by mjt
package org.mjt.appleman;

import java.util.ArrayList;

public class Maze
{
    /**
     * luo random labyrintin. r, c: dimensions of generated maze
     */
    public static char[][] makeMaze(int r, int c, boolean setStart, boolean setEnd)
    {
        // v�hennet��n 2 koska metodin lopussa lis�t��n finalMazeen 2 (reunat) 
        r -= 2;
        c -= 2;

        // build maze and initialize with only walls
        StringBuilder s = new StringBuilder(c);
        for (int x = 0; x < c; x++)
        {
            s.append('#');
        }
        char[][] maz = new char[r][c];
        for (int x = 0; x < r; x++)
        {
            maz[x] = s.toString().toCharArray();
        }

        // select random point and open as start node
        Point st = new Point((int) (Math.random() * r), (int) (Math.random() * c), null);
        if (setStart)
        {
            maz[st.r][st.c] = 'S';
        } else
        {
            maz[st.r][st.c] = ' ';
        }

        // iterate through direct neighbors of node
        ArrayList<Point> frontier = new ArrayList<Point>();
        for (int x = -1; x <= 1; x++)
        {
            for (int y = -1; y <= 1; y++)
            {
                if (x == 0 && y == 0 || x != 0 && y != 0)
                {
                    continue;
                }
                try
                {
                    if (maz[st.r + x][st.c + y] == ' ')
                    {
                        continue;
                    }
                } catch (Exception e)
                { // ignore ArrayIndexOutOfBounds
                    continue;
                }
                // add eligible points to frontier
                frontier.add(new Point(st.r + x, st.c + y, st));
            }
        }

        Point last = null;
        while (!frontier.isEmpty())
        {

            // pick current node at random
            Point cu = frontier.remove((int) (Math.random() * frontier.size()));
            Point op = cu.opposite();
            try
            {
                // if both node and its opposite are walls
                if (maz[cu.r][cu.c] == '#')
                {
                    if (maz[op.r][op.c] == '#')
                    {

                        // open path between the nodes
                        maz[cu.r][cu.c] = ' ';
                        maz[op.r][op.c] = ' ';

                        // store last node in order to mark it later
                        last = op;

                        // iterate through direct neighbors of node, same as earlier
                        for (int x = -1; x <= 1; x++)
                        {
                            for (int y = -1; y <= 1; y++)
                            {
                                if (x == 0 && y == 0 || x != 0 && y != 0)
                                {
                                    continue;
                                }
                                try
                                {
                                    if (maz[op.r + x][op.c + y] == ' ')
                                    {
                                        continue;
                                    }
                                } catch (Exception e)
                                {
                                    continue;
                                }
                                frontier.add(new Point(op.r + x, op.c + y, op));
                            }
                        }
                    }
                }
            } catch (Exception e)
            { // ignore NullPointer and ArrayIndexOutOfBounds
            }

            // if algorithm has resolved, mark end node
            if (frontier.isEmpty())
            {
                if (setEnd)
                {
                    maz[last.r][last.c] = 'E';
                } else
                {
                    maz[last.r][last.c] = ' ';
                }
            }
        }

        // tehd��n mazen "reunat"
        char[][] finalMaz = new char[r + 2][c + 2];
        for (int i = 0; i < r + 2; i++)
        {
            for (int j = 0; j < c + 2; j++)
            {
                if (i == 0 || j == 0 || i == r + 1 || j == c + 1)
                {
                    finalMaz[i][j] = '#';
                } else
                {
                    finalMaz[i][j] = maz[i - 1][j - 1];
                }
            }
        }

        return finalMaz;
    }

    static class Point
    {
        Integer r;
        Integer c;
        Point parent;

        public Point(int x, int y, Point p)
        {
            r = x;
            c = y;
            parent = p;
        }

        // compute opposite node given that it is in the other direction from the parent
        public Point opposite()
        {
            if (this.r.compareTo(parent.r) != 0)
            {
                return new Point(this.r + this.r.compareTo(parent.r), this.c, this);
            }
            if (this.c.compareTo(parent.c) != 0)
            {
                return new Point(this.r, this.c + this.c.compareTo(parent.c), this);
            }
            return null;
        }
    }

    /**
     * luo huoneita. huoneiden koko randomilla [minSX, maxSX], [minSY, maxSY]
     * material on joku merkki (esim ' ' on floor)
     *
     * @param roomCount
     * @param maze
     * @param mapWidth
     * @param mapHeight
     * @param minSX
     * @param minSY
     * @param maxSX
     * @param maxSY
     * @param material
     */
    static void makeRooms(int roomCount, char[][] maze, int mapWidth, int mapHeight, int minSX, int minSY, int maxSX,
            int maxSY, char material)
    {
        int x, y, sx, sy;
        for (int c = 0; c < roomCount; c++)
        {
            // looppaa kunnes huone on kartalla (ei mene reunoista yli)
            while (true)
            {
                x = Globals.random.nextInt(mapWidth) + 1;
                y = Globals.random.nextInt(mapHeight) + 1; // random pos
                sx = Globals.random.nextInt(maxSX) + minSX;
                sy = Globals.random.nextInt(maxSY) + minSY; // random size
                if (x + sx < mapWidth - 2 && y + sy < mapHeight - 2)
                {
                    break;
                }
            }

            for (int xx = 0; xx < sx; xx++)
            {
                for (int yy = 0; yy < sy; yy++)
                {
                    maze[x + xx][y + yy] = material;
                }
            }
        }
    }

    public static void printMaze(char[][] maze, int mapWidth, int mapHeight)
    {
        // DEBUG: print final maze
        for (int j = 0; j < mapHeight; j++)
        {
            for (int i = 0; i < mapWidth; i++)
            {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }
}
