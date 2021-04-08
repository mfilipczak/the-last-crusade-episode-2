import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    static class Piece {
        int type;
        boolean moveable;
        boolean isExit;

        public Piece(int type) {
            this.type = Math.abs(type);
            moveable = type > 0;
        }

        public boolean canRotateMany() {
            return type >=6 && type <=13;
        }

         public String exit(String site) {
            switch(type) {
                case 1: return "TOP";
                case 2: return "RIGHT".equals(site) ? "RIGHT" : "LEFT";
                case 3: return "TOP";
                case 4: return "TOP".equals(site) ? "RIGHT" : "TOP";
                case 5: return "TOP".equals(site) ? "LEFT" : "TOP";
                case 6: return "RIGHT".equals(site) ? "RIGHT" : "LEFT";
                case 7: return "TOP";
                case 8: return "TOP";
                case 9: return "TOP";
                case 10:  return "RIGHT";
                case 11:  return "LEFT";
                case 12: return "TOP";
                case 13: return "TOP";
            }
            return "";
         }

        public boolean canEnter(String site) {
            boolean can = false;
            switch(type) {
                case 1: can = true;
                break;
                case 2: can = "LEFT".equals(site) || "RIGHT".equals(site);
                break;
                case 3: can = "TOP".equals(site);
                break;
                case 4: can = "TOP".equals(site) || "RIGHT".equals(site);
                break;
                case 5:can = "LEFT".equals(site) || "TOP".equals(site);
                break;
                case 6: can = "LEFT".equals(site) || "RIGHT".equals(site);
                break;
                case 7: can = "TOP".equals(site) || "RIGHT".equals(site);
                break;
                case 8: can = "LEFT".equals(site) || "RIGHT".equals(site);
                break;
                case 9: can = "LEFT".equals(site) || "TOP".equals(site);
                break;
                case 10:  can = "TOP".equals(site);
                break;
                case 11:  can = "TOP".equals(site);
                break;
                case 12:  can = "RIGHT".equals(site);
                break;
                case 13: can = "LEFT".equals(site);
                break;
            }
            return can;
        }
 public Piece rotateClockwise(int times) {
     for(int i=0;i<times;i++) {
         rotateClockwise();
     }
     return this;
 }

        public Piece rotateClockwise() {
            switch(type) {
                case 1: 
                break;
                case 2: type=3;
                break;
                case 3:  type = 2;
                break;
                case 4: type = 5;
                break;
                case 5: type = 4;
                break;
                case 6: type = 7;
                break;
                case 7: type = 8;
                break;
                case 8: type = 9;
                break;
                case 9: type = 6;
                break;
                case 10: type = 11;
                break;
                case 11: type = 12;
                break;
                case 12: type = 13;
                break;
                case 13: type = 10;
                break;
            }
            return this;
        }
 public Piece rotateCounterClockwise(int times) {
     for(int i=0;i<times;i++) {
         rotateCounterClockwise();
     }
          return this;
 }
        public Piece rotateCounterClockwise() {
            switch(type) {
                case 1: 
                break;
                case 2: type=3;
                break;
                case 3:  type = 2;
                break;
                case 4: type = 5;
                break;
                case 5: type = 4;
                break;
                case 6: type = 9;
                break;
                case 7: type = 6;
                break;
                case 8: type = 7;
                break;
                case 9: type = 8;
                break;
                case 10: type = 13;
                break;
                case 11: type = 10;
                break;
                case 12: type = 11;
                break;
                case 13: type = 12;
                break;
            }
            return this;
        }

        public String toString() {
            return ""+type;
        }
    }

    static class Move implements Comparable{
        int x;
        int y;
        String move;
        int steps = 0;

        public Move(int x, int y, String move) {
            this.x = x;
            this.y = y;
            this.move = move;
            countSteps(x, y, move);
        }

        public void countSteps(int x, int y, String POS) {
            steps++;
            int nextX =x;
            int nextY =y;
            Piece type = matrix[y][x];
            switch(type.type) {
                case 1:  nextY++;
                break;
                case 2:if("LEFT".equals(POS))  nextX++;
                else nextX--;
                break;
                case 3:  nextY++;
                break;
                case 4:if("TOP".equals(POS))  nextX--;
                else nextY++;
                break;
                case 5:if("TOP".equals(POS))  nextX++;
                else nextY++;
                break;
                case 6:if("LEFT".equals(POS))  nextX++;
                else nextX--;
                break;
                case 7:nextY++;
                break;
                case 8:nextY++;
                break;
                case 9:nextY++;
                break;
                case 10:nextX--;
                break;
                case 11:nextX++;
                break;
                case 12:nextY++;
                break;
                case 13:nextY++;
                break;
            } 
            if(nextX>=0 && nextX <W && nextY >= 0 && nextY <H) {
            Piece nextType = matrix[nextY][nextX];
            if(nextType.canEnter(type.exit(POS)) && !nextType.moveable){
                countSteps(nextX, nextY, type.exit(POS));
            }
            }
        }

        public String toString() {
            return x +" " +y +" " +move;
        }

        public int compareTo(Object o) {
            int v = Integer.valueOf(this.steps).compareTo(((Move)o).steps);
            if(v == 0) {
                v = Integer.valueOf(((Move)o).y).compareTo(this.y);
            }
            return v;
        }
    }


    public static int W = 0;
    public static int H = 0;
    public static Piece[][] matrix;
    public static int freeMoves =0;
    public static LinkedList<Move> moves = new LinkedList<>();
    public static List<Integer> movedRows = new ArrayList<>();

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        W = in.nextInt(); // number of columns.
        H = in.nextInt(); // number of rows.
        if (in.hasNextLine()) {
            in.nextLine();
        }
        matrix = new Piece[H][W];
        for (int i = 0; i < H; i++) {
            for(int j=0 ;j< W;j++) {
                int v = in.nextInt();
                matrix[i][j] = new Piece(v);

                 System.err.print(v +" ");
            }
            System.err.println('\n');
            //String LINE = in.nextLine(); // represents a line in the grid and contains W integers. Each integer represents one room of a given type.
        }
        int EX = in.nextInt(); // the coordinate along the X axis of the exit.
        matrix[H-1][EX].isExit=true;
        // game loop
        while (true) {
            int XI = in.nextInt();
            int YI = in.nextInt();
            String POS = in.next();
            Piece type = matrix[YI][XI];
          //  System.err.println(XI+";"+YI +";"+POS+";"+type);
            List<Move> rocks = new ArrayList<>();
             System.err.println(movedRows);
            int R = in.nextInt(); // the number of rocks currently in the grid.

            for (int i = 0; i < R; i++) {
                int XR = in.nextInt();
                int YR = in.nextInt();
                String POSR = in.next();
                System.err.println("ROCK->"+XR+";"+YR +";"+POSR);
                if(YR > YI && !movedRows.contains(YR))
                rocks.add(new Move(XR, YR, POSR));
            }

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");


            // One line containing on of three commands: 'X Y LEFT', 'X Y RIGHT' or 'WAIT'
            int nextX =XI;
            int nextY =YI;
            switch(type.type) {
                case 1:  nextY++;
                break;
                case 2:if("LEFT".equals(POS))  nextX++;
                else nextX--;
                break;
                case 3:  nextY++;
                break;
                case 4:if("TOP".equals(POS))  nextX--;
                else nextY++;
                break;
                case 5:if("TOP".equals(POS))  nextX++;
                else nextY++;
                break;
                case 6:if("LEFT".equals(POS))  nextX++;
                else nextX--;
                break;
                case 7:nextY++;
                break;
                case 8:nextY++;
                break;
                case 9:nextY++;
                break;
                case 10:nextX--;
                break;
                case 11:nextX++;
                break;
                case 12:nextY++;
                break;
                case 13:nextY++;
                break;
            }
            String doRotate = rotate(nextX, nextY, POS, type);
            //System.err.println(XI+";"+YI +";"+POS+";"+type+";"+nextX +";"+nextY);
             System.err.println("EXIT->" + doRotate + ";" +EX);
            if(doRotate != null) {
                       System.out.println(nextX +" " + nextY + " " + doRotate);


            }else {
                System.err.println(moves);
                if(!moves.isEmpty()){
                    Move m = moves.poll();
                    if("RIGHT".equals(m.move)) {
                        matrix[m.y][m.x].rotateClockwise();
                    }else {
                        matrix[m.y][m.x].rotateCounterClockwise();
                    }
                    System.out.println(m);
                }else if(!rocks.isEmpty()){
                  //  Collections.sort(rocks);
                    for(int i = 0 ;i <rocks.size();i++) {
                        Move r = rocks.get(i);
                         System.err.println("rock->"+r.x +" "+ r.y +" " + r.steps);
                    }
                    //try block rock
                    boolean moved = false;
                    for(int i = 0 ;i <rocks.size();i++) {
                        Move r = rocks.get(i);
                    Piece curr = matrix[r.y][r.x];
            int nextXR =r.x;
            int nextYR =r.y;
            switch(curr.type) {
                case 1:  nextYR++;
                break;
                case 2:if("LEFT".equals(r.move))  nextXR++;
                else nextXR--;
                break;
                case 3:  nextYR++;
                break;
                case 4:if("TOP".equals(r.move))  nextXR--;
                else nextYR++;
                break;
                case 5:if("TOP".equals(r.move))  nextXR++;
                else nextYR++;
                break;
                case 6:if("LEFT".equals(r.move))  nextXR++;
                else nextXR--;
                break;
                case 7:nextYR++;
                break;
                case 8:nextYR++;
                break;
                case 9:nextYR++;
                break;
                case 10:nextXR--;
                break;
                case 11:nextXR++;
                break;
                case 12:nextYR++;
                break;
                case 13:nextYR++;
                break;
            }
            Piece next = matrix[nextYR][nextXR];
            System.err.println("1->"+r.x +" "+ r.y +" " + r.steps+" " +next +" " + next.moveable +" "+nextYR  +" " + YI +" "+ nextXR +" " +XI);
            if(next.canEnter(curr.exit(r.move)) && next.moveable && !(nextYR == YI && nextXR ==XI) && !(nextYR == nextY && nextXR ==nextX)) {
                next.rotateClockwise();
                 System.out.println(nextXR +" " + nextYR + " " + "RIGHT");
                 moved = true;
                 movedRows.add(nextYR);
                 break;
            }else if(next.canEnter(curr.exit(r.move)) && !next.moveable) {
                          switch(next.type) {
                case 1:  nextYR++;
                break;
                case 2:if("LEFT".equals(curr.exit(r.move)))  nextXR++;
                else nextXR--;
                break;
                case 3:  nextYR++;
                break;
                case 4:if("TOP".equals(curr.exit(r.move)))  nextXR--;
                else nextYR++;
                break;
                case 5:if("TOP".equals(curr.exit(r.move)))  nextXR++;
                else nextYR++;
                break;
                case 6:if("LEFT".equals(curr.exit(r.move)))  nextXR++;
                else nextXR--;
                break;
                case 7:nextYR++;
                break;
                case 8:nextYR++;
                break;
                case 9:nextYR++;
                break;
                case 10:nextXR--;
                break;
                case 11:nextXR++;
                break;
                case 12:nextYR++;
                break;
                case 13:nextYR++;
                break;
            }
            curr = next;
            next = matrix[nextYR][nextXR];
            System.err.println("2->"+r.x +" "+ r.y +" " + r.move+" " +next +" " + next.moveable +" "+nextYR  +" " + YI +" "+ nextXR +" " +XI);
            
            if(next.canEnter(curr.exit(r.move)) && next.moveable && !(nextYR == YI && nextXR ==XI)&& !(nextYR == nextY && nextXR ==nextX)) {
                next.rotateClockwise();
                 System.out.println(nextXR +" " + nextYR + " " + "RIGHT");
                 moved = true;
                 movedRows.add(nextYR);
                 break;
            }
            }
            }
            if(!moved)
                System.out.println("WAIT");
            

                }else {
                    System.out.println("WAIT");
                }
            }
            
        }
    }

        public static String rotate(int x, int y, String POS, Piece type) {
             String rotate = null;
             Piece nextType = matrix[y][x];
           //  System.err.println("1>" +nextType.canEnter(type.exit(POS)));
             if(nextType.canEnter(type.exit(POS)) && isSafe(nextType, type.exit(POS), x, y, 1)) {
                return rotate;
             }
             if(nextType.moveable) {
                nextType.rotateClockwise();
              //  System.err.println("2>" +nextType.canEnter(type.exit(POS))+";" +nextType+ ";" +type.exit(POS));
                boolean canEnter = nextType.canEnter(type.exit(POS));
                if(canEnter && isSafe(nextType, type.exit(POS), x, y, 0)) {
                    return "RIGHT";
                }
                nextType.rotateCounterClockwise();
                nextType.rotateCounterClockwise();
              //  System.err.println("3>" +nextType.canEnter(type.exit(POS))+";"+nextType+ ";" +type.exit(POS));
                canEnter = nextType.canEnter(type.exit(POS));
                if(canEnter && isSafe(nextType, type.exit(POS), x, y, 0)) {
                    return "LEFT";
                }
                nextType.rotateClockwise();
                 System.err.println("BOOO");
             }
             return rotate;
        }


        
                public static boolean isSafe(Piece p, String POS, int XI, int YI, int freeMoves) {
                   //  System.err.println("isSafe(1)->" +p +" " +POS + " " +XI + " " +YI + " " +freeMoves);
                    if(p.isExit) {
                        return true;
                    }
           //          System.err.println("isSafe(2)->" +p.isExit);
            int nextX =XI;
            int nextY =YI;
            switch(p.type) {
                case 1:  nextY++;
                break;
                case 2:if("LEFT".equals(POS))  nextX++;
                else nextX--;
                break;
                case 3:  nextY++;
                break;
                case 4:if("TOP".equals(POS))  nextX--;
                else nextY++;
                break;
                case 5:if("TOP".equals(POS))  nextX++;
                else nextY++;
                break;
                case 6:if("LEFT".equals(POS))  nextX++;
                else nextX--;
                break;
                case 7:nextY++;
                break;
                case 8:nextY++;
                break;
                case 9:nextY++;
                break;
                case 10:nextX--;
                break;
                case 11:nextX++;
                break;
                case 12:nextY++;
                break;
                case 13:nextY++;
                break;
            }
          //   System.err.println("isSafe(4)->" +nextX +" " +nextY);
            if(nextX>=0 && nextX <W && nextY >= 0 && nextY <H) {
             Piece nextType = matrix[nextY][nextX];
             boolean canEnter = nextType.canEnter(p.exit(POS));
           //  System.err.println("isSafe(3)->" +nextType +" " +canEnter);
             if(canEnter && isSafe(nextType, p.exit(POS), nextX, nextY, freeMoves+1)) {
                 return true;
             }
            if(nextType.moveable) {
             nextType.rotateClockwise();
             canEnter = nextType.canEnter(p.exit(POS));
             if(canEnter && isSafe(nextType, p.exit(POS), nextX, nextY, freeMoves - 1)) {
                 nextType.rotateCounterClockwise();

                 return true;
             }
             nextType.rotateCounterClockwise();
             nextType.rotateCounterClockwise();
             canEnter = nextType.canEnter(p.exit(POS));
             if(canEnter && isSafe(nextType, p.exit(POS), nextX, nextY, freeMoves -1 )) {
                 nextType.rotateClockwise();
                 return true;
             }
             nextType.rotateClockwise();
             if(nextType.canRotateMany() && freeMoves >1) {
                nextType.rotateClockwise(2);
                canEnter = nextType.canEnter(p.exit(POS));
                if(canEnter && isSafe(nextType, p.exit(POS), nextX, nextY, freeMoves - 2)) {
                    nextType.rotateCounterClockwise(2);
                    moves.add(new Move(nextX, nextY, "RIGHT"));
                    return true;
                }
                nextType.rotateCounterClockwise(2);
                nextType.rotateCounterClockwise(2);
                canEnter = nextType.canEnter(p.exit(POS));
                if(canEnter && isSafe(nextType, p.exit(POS), nextX, nextY, freeMoves -2 )) {
                    nextType.rotateClockwise(2);
                    moves.add(new Move(nextX, nextY, "LEFT"));
                    return true;
                }
                nextType.rotateClockwise(2);
                if(freeMoves >2) {
                                   nextType.rotateClockwise(3);
                canEnter = nextType.canEnter(p.exit(POS));
                if(canEnter && isSafe(nextType, p.exit(POS), nextX, nextY, freeMoves - 3)) {
                    nextType.rotateCounterClockwise(3);
                    return true;
                }
                nextType.rotateCounterClockwise(3);
                nextType.rotateCounterClockwise(3);
                canEnter = nextType.canEnter(p.exit(POS));
                if(canEnter && isSafe(nextType, p.exit(POS), nextX, nextY, freeMoves -3 )) {
                    nextType.rotateClockwise(3);
                    return true;
                }
                nextType.rotateClockwise(3);
                }
             }
            }
            }else{
                return false;
            }
            return false;
    }
}
