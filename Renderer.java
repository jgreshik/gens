//associate an image with a mandel render
class Renderer{
    static int width=1024;
    static int height=1024;
    static String filename="frac.png";
    public static void main(String[]args){
        Image image=new Image(width,height);
        Mandelbrot m=new Mandelbrot(-2,2,-2,2,width,height);
        m.buddhabrot(128,1000000);
        image.histogramcolorbw(m.values);
        image.savetofile(filename);
    }
}
