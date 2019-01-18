import java.util.*;
//associate an image with a mandel render
class Renderer{
    static String filename=new Date().getTime()+".png";
    static int width=8192;
    static int height=8192;
    public static void main(String[]args)throws Throwable{
        Image image=new Image(width,height);
        Mandelbrot m=new Mandelbrot(-2,2,-2,2,width,height);
        m.buddhabrot(256,4000000000l);
        image.histogramcolorbw(m.values);
        image.savetofile(filename);
    }
}
