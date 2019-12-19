public class TspBrute {

    //sets the shortest up top so it can be used in the recursivee
    static  public int shortest = 10000000;
    static  public int T = 10;
    //call to ge the correct path
    public static void TspBrute(int CostMatrix[][], int array[], int l, int r , int N)
    {
        int [] path = new int[N];
        permute(CostMatrix,array,1,r,path,N);
       // System.out.print("0 ");
        for(int i = 0; i < N; i ++ )
        {
           // System.out.print(path[i] + " ");
        }
       // System.out.print("\n");


    }

    public static void permute(int CostMatrix[][], int array[], int l, int r ,int path[],int N)
    {
        //variables to for management
        int temp;
        int temp2 = 0;
        int addUP = 0;

        if( l == r)
        {
            temp2 = 0;
            //adds up the edge cost
            for(int i = 0; i < N; i++)
            {
                addUP = addUP + CostMatrix[temp2][array[i]];
                temp2 = array[i];
                //System.out.print(array[i] + "\n")
            }
            //makes sure to get zer
            addUP = addUP + CostMatrix[array[N-1]][0];
            //System.out.print(addUP + "  \n" );
            //Gets the path of the shortest edge
            if(shortest > addUP)
            {
                shortest = addUP;
                for(int i = 0; i < N; i ++ )
                {
                    path[N - 1 - i] = array[i];
                }
                //System.out.print( "\n");
            }

        }
        //goes through and changes around the numbers in the array list
        // uses recursion to go through the list and has nodes that show where it is on the list
        else {
            for (int j = l; j <= r; j++) {
                temp = array[l];
                array[l] = array[j];
                array[j] = temp;

                permute(CostMatrix, array, l + 1, r, path,N);
                temp = array[l];
                array[l] = array[j];
                array[j] = temp;
            }
        }



    }
    // reset short or else it stays the same in subsequent go throughs
    public static void ResetShort()
    {
        shortest = 1000000;

    }

}
