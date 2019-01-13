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
    // convert from from position x bounded by |a & b| to y bounded by |c & d| 
    double maptorange(double a, double b, double c, double d, double x){
        return (x-a)/(b-a)*(d-c)+c;
    }
    int escapetime(int i,int j,int iterations){
        double real=maptorange(0,width,left,right,i);
        double imag=maptorange(0,height,down,up,j);
        Complex c=new Complex(real,imag);
        Complex z=new Complex(0,0);
        int count=0;
        // here assuming square image
        // using z^2 iterative formula
        while(z.r*z.r+z.i*z.i<=right*right&&count<iterations){
            z=z.multiply(z);
            z=z.add(c);
            count++;
        }
        return count;
    }
    void renderescapetime(int iterations){
        int i,j;
        for(i=0;i<width;i++){
            for(j=0;j<height;j++){
                values[i][j]=escapetime(i,j,iterations);
            }
        }
    }

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
