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

}
