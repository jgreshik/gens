import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.*;
class Image{
    int width;
    int height;
    BufferedImage image;
    int[][]red;
    int[][]green;
    int[][]blue;
    Image(int W,int H){
        width=W;
        height=H;
        image=new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        red=new int[W][H];
        green=new int[W][H];
        blue=new int[W][H];
    }
    void color(){
        for(int i=0;i<width;++i){
            for(int j=0;j<height;++j){
                image.setRGB(i,j,0xFF000000+red[i][j]*65536+green[i][j]*256+blue[i][j]);
            }
        }
    }
    void histogramcolor(double[][]values){
        TreeMap<Integer,Integer>map=new TreeMap<>();
        for(int i=0;i<width;++i){
            for(int j=0;j<height;++j){
                int key=(int)values[i][j];
                if(!map.containsKey(key))map.put(key,0);
                map.put(key,map.get(key)+1);
            }
        }
        int sum=0;
        for(int key:map.keySet()){
            int curr=map.get(key);
            map.put(key,curr+sum);
            sum+=curr;
        }
        for(int i=0;i<width;++i){
            for(int j=0;j<height;++j){
                int key=(int)values[i][j];
                red[i][j]=(int)(map.get(key)/sum*256);
                green[i][j]=(int)(map.get(key)/sum*256);
                blue[i][j]=(int)(map.get(key)/sum*256);
            }
        }
        color();
    }
    void savetofile(String fn)throws Throwable{
        File file=new File(fn);
        ImageIO.write(image,"png",file);
    }
}