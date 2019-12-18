import java.util.Arrays;
import java.util.Random;

public class TspAntColony {


    static public int T = 10;
    public static void  TspAnt(int Dist[][])
    {
        double PheromoneFactor = 1;
        double DecayFactor = .9;
        int maxUnchangedTimeSteps = 20;
        int N = T;
        //ants
        int M = 5; // ants
        double [][] Phero = new double[T][T];
        double [][] NewPhero = new double[T][T];
        int [] Visited = new int[T];
        int [] Path = new int[T];
        int step;
        int k;
        int h = 0;
        double pathCost;
        double Q;
        double totalA;
        double cumProb;
        double minPathCostSoFar = -1;
        int [] miniPath = new int[T];
        double eSP;
        double miniCostLastTime = -1;
        int StaySame = 0;

        while(maxUnchangedTimeSteps > StaySame ) {
            for (int p = 0; p < T; p++) {
                Arrays.fill(NewPhero[p], 0);
            }
            for (int i = 0; i < M; i++) {
                pathCost = 0;
                Path[0] = 0;
                Arrays.fill(Visited, 0);
                Visited[0] = 1;
                for (step = 1; step < N ;step++) {
                    k = Path[step - 1];
                    totalA = 0;
                    for (h = 0; h < N ; h++) {
                        if (Visited[h] != 1 && h != k) {
                            totalA = totalA + (1 + Phero[k][h]) / Dist[k][h];
                        }
                    }
                    Q = Math.random();
                    cumProb = 0;
                    for (h = 0; h < N; h++) {
                        if (Visited[h] != 1 && h != k) {
                            eSP = ((1 + Phero[k][h]) / Dist[k][h]) / totalA;
                            cumProb = cumProb + eSP;
                            if (Q < cumProb) {
                                break;
                            }
                        }
                    }
                    Path[step] = h;
                    Visited[h] = 1;
                    pathCost = pathCost + Dist[k][h];
                }
                pathCost = pathCost + Dist[h][0];
                if (pathCost < minPathCostSoFar || minPathCostSoFar == -1) {
                    minPathCostSoFar = pathCost;
                    miniPath = Path;
                }
                for (step = 0; step < N ; step++) {
                    k = Path[step];
                    h = Path[(step + 1) % N];
                    NewPhero[k][h] = NewPhero[k][h] + PheromoneFactor / pathCost;
                }
                for (k = 0; k < N-1; k++) {
                    for (h = 0; h < N; h++) {
                        Phero[k][h] = Phero[k][h] * DecayFactor;
                        Phero[k][h] = Phero[k][h] + NewPhero[k][h];
                    }

                }
            }

            if( minPathCostSoFar == miniCostLastTime)
            {
                StaySame++;
                System.out.print(StaySame + " ");
            }
            else
            {
                miniCostLastTime = minPathCostSoFar;
                StaySame = 0;
            }
        }
        for(int i = 0; i < T; i ++ )
        {
            System.out.print(miniPath[i] + " ");
        }
        System.out.print("\n"+"ant lowest found " +miniCostLastTime + " \n");

    }


}
