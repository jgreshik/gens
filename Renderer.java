import java.util.*;
//associate an image with a mandel render
class Renderer{
    static String filename=new Date().getTime()+".png";
    static String input="gg.jpg";
    static int width=1024;
    static int height=1024;
    public static void main(String[]args)throws Throwable{
        Image image=new Image(width,height);
        
        // Rorschach
        // 
        Image imageR=new Image(width,height);
        int num_dots=10000;
        double dots_max_size=10.0;
        double sensitivity=0.1;
        int clusters=1;
        int blur=0;
        Rorschach ror=new Rorschach(width,height,num_dots,dots_max_size,sensitivity,clusters);
        ror.run();
        imageR.joeycolor(ror.values);
        imageR.highContrastAvg();
        imageR.blur(blur);
        imageR.color();
        filename=new String(num_dots+"_"+dots_max_size+"_"+sensitivity+"_"+clusters+".png");
        imageR.savetofile(filename);
        // end Rorschach
        //
        /*	
        Image image=new Image(input);
	image.brg();
        image.savetofile(filename);
        */
    }
}
