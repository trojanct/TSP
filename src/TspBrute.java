public class TspBrute {


    static  public int shortest = 10000000;
    static  public int T = 10;
    public static void TspBrute(int CostMatrix[][], int array[], int l, int r )
    {
        int [] path = new int[T];
        permute(CostMatrix,array,1,r,path);
        System.out.print("0 ");
        for(int i = 0; i < T; i ++ )
        {
            System.out.print(path[i] + " ");
        }
        System.out.print("\n");


    }

    public static void permute(int CostMatrix[][], int array[], int l, int r ,int path[])
    {

        int temp;
        int temp2 = 0;
        int addUP = 0;

        if( l == r)
        {
            temp2 = 0;
            for(int i = 0; i < T; i++)
            {
                addUP = addUP + CostMatrix[temp2][array[i]];
                temp2 = array[i];
                //System.out.print(array[i] + "\n")
            }
            addUP = addUP + CostMatrix[array[T-1]][0];
            //System.out.print(addUP + "  \n" );
            if(shortest > addUP)
            {
                shortest = addUP;
                for(int i = 0; i < T; i ++ )
                {
                    path[T - 1 - i] = array[i];
                }
                //System.out.print( "\n");
            }

        }
        else {
            for (int j = l; j <= r; j++) {
                temp = array[l];
                array[l] = array[j];
                array[j] = temp;

                permute(CostMatrix, array, l + 1, r, path);
                temp = array[l];
                array[l] = array[j];
                array[j] = temp;
            }
        }



    }
    public static void ResetShort()
    {
        shortest = 1000000;

    }

}
