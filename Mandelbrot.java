import java.util.*;
class Mandelbrot{
    //what part of the plane are we viewing?
    double left;
    double right;
    double up;
    double down;
    //scale of the image
    int width;
    int height;
    //values of the data
    double[][]values;
    Mandelbrot(double L,double R,double U,double D,int W,int H){
        left=L;
        right=R;
        up=U;
        down=D;
        width=W;
        height=H;
        values=new double[W][H];
    }
    //get the escape time for each mapped point
    void renderescapetime(int count){

    }
    //get the value for each point
    void renderbuddhabrot(int count,int points){
        Random random=new Random();
        for(int p=0;p<points;++p){ 
            double x=(right-left)*random.nextDouble()+left;
            double y=(up-down)*random.nextDouble()+right;
            HashSet<Complex>set=new HashSet<>();
            int c=0;
            Complex a=new Complex(x,y);
            Copmlex z=new Complex(0,0);
            while(c<count&&z.abs()<2){
                set.add(z);
                z=z.multiply(z).add(a);
                ++c;
            }
            if(c>=count)continue;
            for(Complex n:set){
                int i=(int)maptorange(left,right,0,i,n.r);
                int j=(int)maptorange(down,up,0,j,n.i);
                values[i][j]=values[i][j]+1;
            }
        }    
    }
}
