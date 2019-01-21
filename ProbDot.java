import java.util.*;
class ProbDot{
    Random rand=new Random();
    double[] rs=new double[6];
    int xloc,yloc,max;
    double R;
    // size will be between 1 and 4 for particles of decreasing influence
    ProbDot(int W,int H,int x,int y,int size,double max_size){
        R=max_size;
        for(int i=1;i<7;i++){
            rs[i-1]=Math.random()*R/(size*i);
        }
        xloc=x;
        yloc=y;
    }
}
