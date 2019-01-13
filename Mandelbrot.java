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
    void renderescapetime(int count){

    }
    
}
