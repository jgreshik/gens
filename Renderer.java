import java.util.*;
//associate an image with a mandel render
class Renderer{
    static String filename=new Date().getTime()+".png";
    static int width=1024;
    static int height=1024;
    public static void main(String[]args)throws Throwable{
        Image image=new Image(width,height);
        
        // Rorschach
        // 
        int num_dots=10000;
        double dots_max_size=10.0;
        double sensitivity=0.1;
        int clusters=1;
        int blur=0;
        Rorschach ror=new Rorschach(width,height,num_dots,dots_max_size,sensitivity,clusters);
        ror.run();
        image.joeycolor(ror.values);
        image.highContrastAvg();
        image.blur(blur);
        image.color();
        filename=new String(num_dots+"_"+dots_max_size+"_"+sensitivity+"_"+clusters+".png");
        // end Rorschach
        //
        image.savetofile(filename);
    }
}
