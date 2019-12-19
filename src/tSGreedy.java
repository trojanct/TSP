import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.io.*;
import java.util.Random;



public class tSGreedy {
public static int T = 10;
    public static void main( String[] args) {

        int [][] costMatrix = new int[T][T];
        int[][] EcMatrix = new int[T][T];
        int[][] CegMatrix = new int[T][T];
        TspBrute Brute = new TspBrute();
        TspAntColony Ant = new TspAntColony();
        int [] numArray = new int [T];
        int [] bruteArray = new int [T];
        for(int i = 0; i < T; i++)
        {

            numArray[i] = i;
        }



        generateRandomCostMatrix(costMatrix);
        GenerateRandomEuclideanCostMatrix(EcMatrix);
        GenerateRandomCircularGraphCostMatrix(CegMatrix);


        tSPGreedy(costMatrix);
        Ant.TspAnt(costMatrix);
        Brute.TspBrute(costMatrix,numArray,1,T-1);
        System.out.print("the smallest cost is "+ Brute.shortest + "\n");
        Brute.ResetShort();

        System.out.print("\n");


        tSPGreedy(EcMatrix);
        Ant.TspAnt(EcMatrix);
        Brute.TspBrute(EcMatrix,numArray,1,T-1);
        System.out.print("the smallest cost for Brute is "+ Brute.shortest + "\n");
        Brute.ResetShort();

        System.out.print("\n");

        tSPGreedy(CegMatrix);
        Ant.TspAnt(CegMatrix);
        Brute.TspBrute(CegMatrix,numArray,1,T-1);
        System.out.print("the smallest cost for Brute is "+ Brute.shortest + "\n");








    }
    public static void tSPGreedy(int costMatrix[][])
    {
        int [] path = new int[T];
        int [] track = new int [T];
        int place = 0;
        int total = 0;
        int greedytotal = 0;
        int shortest = 0;
        int g = 0;
        int temp = 0;

        track[0] = 1;
        while(total != T-1)
        {
            shortest = 100;
            for( int j = 0; j < T; j++)
            {
                if(costMatrix[g][j] != 0 && track[j] != 1 && costMatrix[g][j] <= shortest)
                {
                    shortest = costMatrix[g][j];
                    place = j;
                    path[total] = place;
                    //System.out.print(place + "im in \n");

                }

            }
            track[place] = 1;
            total ++;
            g = place;
        }
        System.out.print("0 ");
        for(int b = 0; b < T; b++ )
        {
            System.out.print(path[b] + " ");
            greedytotal= greedytotal + costMatrix[temp][path[b]];
            temp = path[b];

        }
        System.out.print("\n The total for greed is " + greedytotal + "\n");




    }
    public static void generateRandomCostMatrix(int costArray[][])
    {
        int marker = 0;
        Random rand = new Random();

        for (int  i = 0; i < T; i ++)
        {
            for(int j = marker; j < T; j++)
            {
                if(i == j)
                {
                    costArray[i][j] = 0;
                }
                else
                {
                    costArray[i][j] = rand.nextInt(20)+1;
                    costArray[j][i] = costArray[i][j];
                }

            }
            marker ++;
        }
        for (int  i = 0; i < T; i ++)
        {
            System.out.print("\n");
            for(int j = 0; j < T; j++)
            {
                System.out.print(costArray[i][j] + " ");
            }
        }
        System.out.print("\n\n");



    }
    public static void GenerateRandomEuclideanCostMatrix(int EcMatrix[][])
    {
        int marker = 0;
        Random rand = new Random();
        int [][] coordinates = new int[T][2];
        for (int i = 0; i < T; i ++)
        {
            for(int j = 0; j < 2; j ++ )
            {
                coordinates[i][j] = rand.nextInt(100)+1;
            }
        }


        for (int  i = 0; i < T; i ++)
        {
            for(int j = marker; j < T; j++)
            {
                if(i == j)
                {
                    EcMatrix[i][j] = 0;
                }
                else
                {
                    double temp = (Math.pow((coordinates[i][0]- coordinates[j][0]),2 )+ Math.pow((coordinates[i][1] - coordinates[j][1]),2));
                    temp = Math.pow(temp,.5);
                    EcMatrix[i][j] = (int)temp;
                    EcMatrix[j][i] = EcMatrix[i][j];
                }

            }
            marker ++;
        }
        for (int  i = 0; i < T; i ++)
        {
            System.out.print("\n");
            for(int j = 0; j < T; j++)
            {
                System.out.print(EcMatrix[i][j] + " ");
            }
        }
        System.out.print("\n\n");

    }

    public static void GenerateRandomCircularGraphCostMatrix(int CgMatrix[][]) {
        double stepangle;
        stepangle = 2 * Math.PI / T;
        double[] x = new double[T];
        double[] y = new double[T];
        double temp = 0;
        int marker = 0;
        double radius = 100;
        int[] array = {0,5,2,9,8,7,6,1,3,4};

        for (int s = 0; s < T; s++) {
            x[s] = radius * Math.sin(s * stepangle);
            System.out.print(x[s] + " ");
            y[s] = radius * Math.cos(s * stepangle);
            System.out.print(y[s] + "  \n");
        }


        for (int i = 0; i < T; i++) {
            for (int j = marker; j < T; j++) {
                if (i == j) {
                    CgMatrix[i][j] = 0;
                } else {
                    temp = (Math.pow((x[i] - x[j]), 2) + Math.pow((y[i] - y[j]), 2));
                    temp = Math.pow(temp, .5);
                    CgMatrix[i][j] =  (int) temp;
                    CgMatrix[j][i] = CgMatrix[i][j];
                }

            }
            marker++;
        }

            for ( int i = 0; i < T; i++) {
                System.out.print("\n");
                for (int j = 0; j < T; j++) {
                    System.out.print(CgMatrix[i][j] + " ");
                }
            }
            System.out.print("\n\n");


        }
    public static void Randomarray(int array[])
    {


        int [] Visited = new int [T];
        Visited[0] = 1;
        array[0] = 0;

    }

}
