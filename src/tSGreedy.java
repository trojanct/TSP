import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.io.*;
import java.util.Random;



public class tSGreedy {
    public static int T = 10;
    static ThreadMXBean bean = ManagementFactory.getThreadMXBean( );

    static String ResultsFolderPath = "/home/cody/Time/"; // pathname to results folder
    static FileWriter resultsFile;
    static PrintWriter resultsWriter;

    static int numberOfTrials = 1000;

    public static void main( String[] args)
    {

        //calling full experiment with text file names, running the three experiments as usual for consistency
        runFullExperiment("ant-Exp1-ThrowAway.txt");
        //runFullExperiment("ant-Exp2.txt");
        //runFullExperiment("Brute-Exp3.txt");

        //testing brute force
        //for( int i = 4; i < 12; i ++) {
          //  TpsGreedy(i);
        //}


    }
    // modified timing code given to us, changed so that input sizes were reduced and went one at a time
    static void runFullExperiment(String resultsFileName) {


        String result = "";
        long inputsize = 1;
        try {

            resultsFile = new FileWriter(ResultsFolderPath + resultsFileName);
            resultsWriter = new PrintWriter(resultsFile);

        } catch (Exception e) {

            System.out.println("*****!!!!!  Had a problem opening the results file " + ResultsFolderPath + resultsFileName);
            return; // not very foolproof... but we do expect to be able to create/open the file...

        }


        ThreadCpuStopWatch BatchStopwatch = new ThreadCpuStopWatch(); // for timing an entire set of trials
        ThreadCpuStopWatch TrialStopwatch = new ThreadCpuStopWatch(); // for timing an individual trial


        resultsWriter.println("#InputSize    AverageTime      Fib(x)returned result      input x"); // # marks a comment in gnuplot data
        resultsWriter.flush();
        //uses the x instead of the input
        int input = 50;
        for (int i = 4; i < 15; i++) {

            long batchElapsedTime = 0;


            System.gc();


            // instead of timing each individual trial, we will time the entire set of trials (for a given input size)
            // and divide by the number of trials -- this reduces the impact of the amount of time it takes to call the
            // stopwatch methods themselves
            BatchStopwatch.start(); // comment this line if timing trials individually


            // run the tirals
            //for (long trial = 0; trial < numberOfTrials; trial++)
           // {

                TpsGreedy(i);

            //}

            batchElapsedTime = BatchStopwatch.elapsedTime(); // *** comment this line if timing trials individually
            double averageTimePerTrialInBatch = (double) batchElapsedTime / (double) numberOfTrials; // calculate the average time per trial in this batch

            // tying to get doubling ratio to work.
            //double doublingRatio = (double) averageTimePerTrialInBatch / (double) prevTimePerTrial;
            // prevTimePerTrial = averageTimePerTrialInBatch;


            /* print data for this size of input average time and fibonacci result*/
            if (i > 0) {
                inputsize = (long) (Math.log(i) / Math.log(2) + 1);
            }
            //resultsWriter.printf("%12d  %15.2f  %s %15d\n",inputsize, averageTimePerTrialInBatch, result,i );
            //resultsWriter.printf("%d \n",result);
            resultsWriter.printf("%15.2f\n", averageTimePerTrialInBatch);
            // might as well make the columns look nices look nice
            resultsWriter.flush();
            //System.out.println(" ....done.");

        }

    }
    public static void TpsGreedy (int N)
    {
        int [][] costMatrix = new int[N][N];
        int[][] EcMatrix = new int[N][N];
        int[][] CegMatrix = new int[N][N];
        TspBrute Brute = new TspBrute();
        TspAntColony Ant = new TspAntColony();
        int [] numArray = new int [N];

        for(int i = 0; i < N; i++)
        {

            numArray[i] = i;
        }



        //generateRandomCostMatrix(costMatrix,N);
        GenerateRandomEuclideanCostMatrix(EcMatrix,N);
        //GenerateRandomCircularGraphCostMatrix(CegMatrix,N);

/*
        //tSPGreedy(costMatrix,N);
        //Ant.TspAnt(costMatrix,N);
        Brute.TspBrute(costMatrix,numArray,1,N-1,N);
        System.out.print("the smallest cost for  Brute is "+ Brute.shortest + "\n");
        Brute.ResetShort();
*/
        //System.out.print("\n");


        tSPGreedy(EcMatrix,N);
        Ant.TspAnt(EcMatrix,N);
        Brute.TspBrute(EcMatrix,numArray,1,N-1,N);
        //System.out.print("the smallest cost for Brute is "+ Brute.shortest + "\n");
        System.out.print( Brute.shortest +"     \n");
        Brute.ResetShort();

/*
        //System.out.print("\n");

        //tSPGreedy(CegMatrix,N);
        Ant.TspAnt(CegMatrix,N);
        //Brute.TspBrute(CegMatrix,numArray,1,N-1,N);
        //System.out.print("the smallest cost for Brute is "+ Brute.shortest + "\n");
        //Brute.ResetShort();
*/







    }

    public static void tSPGreedy(int costMatrix[][], int N)
    {
        int [] path = new int[N];
        int [] track = new int [N];
        int place = 0;
        int total = 0;
        int greedytotal = 0;
        int shortest = 0;
        int g = 0;
        int temp = 0;

        track[0] = 1;
        while(total != N-1)
        {
            shortest = -1;
            for( int j = 0; j < N; j++)
            {
                // checks to see if that city has been gone too if its not the same city and if it is the shortest
                if(costMatrix[g][j] != 0 && track[j] != 1 && ((costMatrix[g][j] <= shortest) || shortest == -1))
                {
                    shortest = costMatrix[g][j];
                    place = j;
                    path[total] = place;
                    //System.out.print(place + "im in \n");

                }

            }
            //incriments it
            track[place] = 1;
            total ++;
            g = place;
        }
        //prints out chartand total
        //System.out.print("0 ");
        for(int b = 0; b < N; b++ )
        {
           // System.out.print(path[b] + " ");
            greedytotal= greedytotal + costMatrix[temp][path[b]];
            temp = path[b];

        }
        //System.out.print("\n The total for greed is " + greedytotal + "\n");
        System.out.print(greedytotal+"     ");




    }
    public static void generateRandomCostMatrix(int costArray[][],int N)
    {
        int marker = 0;
        Random rand = new Random();

        //makes a rondom chart and uses the marker to place for not being redudant
        for (int  i = 0; i < N; i ++)
        {
            for(int j = marker; j < N; j++)
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
        for (int  i = 0; i < N; i ++)
        {
            System.out.print("\n");
            for(int j = 0; j < N; j++)
            {
                System.out.print(costArray[i][j] + " ");
            }
        }
        System.out.print("\n\n");



    }
    public static void GenerateRandomEuclideanCostMatrix(int EcMatrix[][],int N)
    {
        int marker = 0;
        Random rand = new Random();
        int [][] coordinates = new int[N][2];
        for (int i = 0; i < N; i ++)
        {
            for(int j = 0; j < 2; j ++ )
            {
                coordinates[i][j] = rand.nextInt(100)+1;
            }
        }


        for (int  i = 0; i < N; i ++)
        {
            for(int j = marker; j < N; j++)
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
        for (int  i = 0; i < N; i ++)
        {
          //  System.out.print("\n");
            for(int j = 0; j < N; j++)
            {
               // System.out.print(EcMatrix[i][j] + " ");
            }
        }
        ///System.out.print("\n\n");

    }

    public static void GenerateRandomCircularGraphCostMatrix(int CgMatrix[][], int N) {
        double stepangle;
        stepangle = 2 * Math.PI / N;
        double[] x = new double[N];
        double[] y = new double[N];
        double temp = 0;
        int marker = 0;
        double radius = 100;
        int[] array = {0,5,2,9,8,7,6,1,3,4};

        for (int s = 0; s < N; s++) {
            x[s] = radius * Math.sin(s * stepangle);
            //System.out.print(x[s] + " ");
            y[s] = radius * Math.cos(s * stepangle);
            //System.out.print(y[s] + "  \n");
        }


        for (int i = 0; i < N; i++) {
            for (int j = marker; j < N; j++) {
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

           /* for ( int i = 0; i < N; i++) {
                System.out.print("\n");
                for (int j = 0; j < N; j++) {
                    System.out.print(CgMatrix[i][j] + " ");
                }
            }
            System.out.print("\n\n");
*/

        }
    public static void Randomarray(int array[],int N)
    {


        int [] Visited = new int [N];
        Visited[0] = 1;
        array[0] = 0;

    }

}
