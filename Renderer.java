import java.util.*;
//associate an image with a mandel render
class Renderer{
    static String filename=new Date().getTime()+".png";
    static int width=4096;
    static int height=4096;
    public static void main(String[]args)throws Throwable{
        Image image=new Image(width,height);
				Mandelbrot m=new Mandelbrot(-2,2,-2,2,width,height);
				m.buddhabrot(2048,4000000l);
				System.out.println("Finished phase 1");
				Mandelbrot m0=new Mandelbrot(-2,2,-2,2,width,height);
				m0.buddhabrot(256,4000000l);
				Mandelbrot m1=new Mandelbrot(-2,2,-2,2,width,height);
				m1.buddhabrot(32,4000000l);
				image.histogramcolor(m.values,m0.values,m1.values);	
        image.savetofile(filename);
    }
}
