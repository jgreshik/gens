//associate an image with a mandel render
class Renderer{
    static int width=512;
    static int height=512;
    static String filename="frac.png";
    public static void main(String[]args)throws Throwable{
        Image image=new Image(width,height);
        Mandelbrot m=new Mandelbrot(-2,2,-2,2,width,height);
        m.buddhabrot(256,20000000);
        image.histogramcolorbw(m.values);
        image.savetofile(filename);
    }
}
