public class TspBrute {


    static  public int shortest = 100;
    static  public int T = 10;
    public static void  permute(int CostMatrix[][], int array[], int l, int r )
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
                //System.out.print(array[i] + "\n");

            }
            addUP = addUP + CostMatrix[array[T-1]][0];
            if(shortest > addUP)
            {
                shortest = addUP;
            }

        }
        else {
            for (int j = l; j <= r; j++) {
                temp = array[l];
                array[l] = array[j];
                array[j] = temp;

                permute(CostMatrix, array, l + 1, r);
                temp = array[l];
                array[l] = array[j];
                array[j] = temp;
            }
        }


    }

}
