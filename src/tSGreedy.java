import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.io.*;
import java.util.Random;


public class tSGreedy {
public static int T = 10;
    public static void main( String[] args) {

        int [][] costMatrix = new int[T][T];
        int[][] EcMatrix = new int[T][T];
        TspBrute Brute = new TspBrute();
        TspAntColony Ant = new TspAntColony();
        int [] numArray = new int [T];
        for(int i = 0; i < T; i++)
        {

            numArray[i] = i;
        }



        generateRandomCostMatrix(costMatrix);
        GenerateRandomEuclideanCostMatrix(EcMatrix);
        Brute.permute(costMatrix,numArray,1,T-1);
        System.out.print("the smallest cost is "+ Brute.shortest + "\n");
        tSPGreedy(costMatrix);
        Ant.TspAnt(costMatrix);



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
        for(int b = 0; b < T; b++ )
        {
            System.out.print(path[b] + " ");
            greedytotal= greedytotal + costMatrix[temp][path[b]];
            temp = path[b];

        }
        System.out.print(greedytotal + "\n");




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
                    costArray[i][j] = rand.nextInt(10)+1;
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






}
