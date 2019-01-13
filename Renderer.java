//associate an image with a mandel render
class Renderer{
    int width=1024;
    int height=1024;
    String filename="frac.png";
    public static void main(String[]args){
        Image image=new Image(width,height);
        Mandelbrot m=new Mandelbrot(-2,2,-2,2,width,height);
        m.buddhabrot();
        image.histogramcolorbw(m.values);
        image.savetofile(filename);
    }
}
