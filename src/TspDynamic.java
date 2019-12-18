public class TspDynamic {

    static  public int shortest = 1000;
    static  public int T = 5;
    public static void  permute(int Matrix[][], int array[], int l, int r )
    {
        int temp;
        int temp2 = 0;
        int addUP = 0;
        if( l == r)
        {
            temp2 = 0;
            for(int i = 0; i < 5; i++)
            {
                addUP = addUP + Matrix[temp2][array[i]];
                temp2 = array[i];
                //System.out.print(array[i] + "\n");

            }
            addUP = addUP + Matrix[array[T-1]][0];
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

                permute(Matrix, array, l + 1, r);
                temp = array[l];
                array[l] = array[j];
                array[j] = temp;
            }
        }


    }

    public static void makeBit()
    {


    }



}

