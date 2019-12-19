import java.util.Arrays;
import java.util.Random;

public class TspAntColony {


    static public int T = 10;
    public static void  TspAnt(int Dist[][],int N)
    {
        // controls the different factors of the anthill problem
        double PheromoneFactor = 2;
        double DecayFactor = .90;
        int maxUnchangedTimeSteps = 200;

        //ants
        int M = 10; // ants
        double [][] Phero = new double[N][N];
        double [][] NewPhero = new double[N][N];
        int [] Visited = new int[N];
        int [] Path = new int[N];
        int step;
        int k;
        int h = 0;
        double pathCost;
        double Q;
        double totalA;
        double cumProb;
        double minPathCostSoFar = -1;
        int [] miniPath = new int[N];
        double eSP;
        double miniCostLastTime = -1;
        int StaySame = 0;
        //starting up a new ant
        while(maxUnchangedTimeSteps > StaySame ) {
            for (int p = 0; p < N; p++) {
                Arrays.fill(NewPhero[p], 0);
            }
            //ant loop making sure everthings default
            for (int i = 0; i < M; i++) {
                pathCost = 0;
                Path[0] = 0;
                Arrays.fill(Visited, 0);
                Visited[0] = 1;
                for (step = 1; step < N ;step++) {
                    k = Path[step - 1];
                    totalA = 0;
                    //takes the start of calculations to be used in probability in taking paths
                    for (h = 0; h < N ; h++) {
                        if (Visited[h] != 1 && h != k) {
                            totalA = totalA + (1 + Phero[k][h]) / Dist[k][h];
                        }
                    }
                    Q = Math.random();
                    cumProb = 0;
                    //goes through and looks for next path with probability
                    for (h = 0; h < N; h++) {
                        if (Visited[h] != 1 && h != k) {
                            eSP = ((1 + Phero[k][h]) / Dist[k][h]) / totalA;
                            cumProb = cumProb + eSP;
                            if (Q < cumProb) {
                                break;
                            }
                        }
                    }
                    //takes in ants information
                    Path[step] = h;
                    Visited[h] = 1;
                    pathCost = pathCost + Dist[k][h];
                }
                //Sees if this is the smallest path that has been found
                pathCost = pathCost + Dist[h][0];
                if (pathCost < minPathCostSoFar || minPathCostSoFar == -1) {
                    minPathCostSoFar = pathCost;
                    for(int a = 0; a < N; a++) {
                        miniPath[a] = Path[a];
                    }
                }
                //takes in information on where pheromones will be but makes sure not to put that
                //info in while there is still ants
                for (step = 0; step < N ; step++) {
                    k = Path[step];
                    h = Path[(step + 1) % N];
                    NewPhero[k][h] = NewPhero[k][h] + PheromoneFactor / pathCost;
                }
                //puts in new pheromenes but also looks at the decay factor of the pheromones
                for (k = 0; k < N-1; k++) {
                    for (h = 0; h < N; h++) {
                        Phero[k][h] = Phero[k][h] * DecayFactor;
                        Phero[k][h] = Phero[k][h] + NewPhero[k][h];
                    }

                }
            }
            //path calculations
            if( minPathCostSoFar == miniCostLastTime)
            {
                StaySame++;
                //System.out.print(StaySame + " ");
            }
            else
            {
                miniCostLastTime = minPathCostSoFar;
                StaySame = 0;
            }
        }
        /*for(int i = 0; i < N; i ++ )
        {
            System.out.print(miniPath[i] + " ");
        }
        System.out.print("0\n"+"ant lowest found " +miniCostLastTime + " \n");*/
        System.out.print(miniCostLastTime + "     ");

    }


}
