import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // write your code here
        int k=sc.nextInt();
        int n=sc.nextInt();
        double m=sc.nextDouble();
        while(true){
            int count=0;
            Random r=new Random(k);
            for(int i=0;i<n;i++){
                double x=r.nextGaussian();
                if(x<=m)
                    count++;
            }
            if(count==n){
                System.out.print(k);
                break;
            }
            k++;
        }
    }
}