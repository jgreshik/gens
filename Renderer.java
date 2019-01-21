import java.util.*;
//associate an image with a mandel render
class Renderer{
    static String filename=new Date().getTime()+".png";
		static String input="gg.jpg";
    static int width=4096;
    static int height=4096;
    public static void main(String[]args)throws Throwable{
				Image image=new Image(input);
				image.brg();
        image.savetofile(filename);
    }
}
