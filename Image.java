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
    float sensitivity;
    Image(int W,int H){
        width=W;
        height=H;
        image=new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        red=new int[W][H];
        green=new int[W][H];
        blue=new int[W][H];
        this.sensitivity=sensitivity;
    }
    void color(){
        for(int i=0;i<width;++i){
            for(int j=0;j<height;++j){
                image.setRGB(i,j,0xFF000000+red[i][j]*65536+green[i][j]*256+blue[i][j]);
            }
        }
    }
    void histogramcolorbw(double[][]values){
        TreeMap<Integer,Integer>map=new TreeMap<>();
        for(int i=0;i<width;++i){
            for(int j=0;j<height;++j){
                int key=(int)values[i][j];
                if(!map.containsKey(key))map.put(key,0);
                map.put(key,map.get(key)+1);
            }
        }
        int sum=0;
        int max=0;
        //get percentile of the iteration value
        for(int key:map.keySet()){
            int curr=map.get(key);
            map.put(key,sum);
            max=Math.max(sum,max);
            sum+=curr;
        }
        for(int i=0;i<width;++i){
            for(int j=0;j<height;++j){
                int key=(int)values[i][j];
                red[i][j]=(int)((double)map.get(key)/max*255);
                green[i][j]=(int)((double)map.get(key)/max*255);
                blue[i][j]=(int)((double)map.get(key)/max*255);
            }
        }
        color();
    }
    void joeycolor(double[][]values){
        for(int i=0;i<width;++i){
            for(int j=0;j<height;++j){
                red[i][j]=255;
                green[i][j]=255;
                blue[i][j]=255;
                if(values[i][j]==1){
                red[i][j]=0;
                green[i][j]=0;
                blue[i][j]=0;
                }
            }
        }
    }
    void blur(int neighborDepth){
        int tempr=0,tempb=0,tempg=0;
        for(int i=neighborDepth;i<width-neighborDepth;++i){
            for(int j=neighborDepth;j<height-neighborDepth;++j){
                tempr=red[i][j];
                tempb=blue[i][j];
                tempg=green[i][j];
                for(int k=-neighborDepth;k<=neighborDepth;k++){
                    for(int b=-neighborDepth;b<=neighborDepth;b++){
                        red[i][j]=(tempr+red[i+k][j+b])/2;
                        blue[i][j]=(tempb+blue[i+k][j+b])/2;
                        green[i][j]=(tempg+green[i+k][j+b])/2;
                    }
                }
            }
        }
    }
    void avgy(){
         for(int i=0;i<width/2;++i){
            for(int j=0;j<height;++j){
                red[i][j]=(red[i][j]+red[width-i-1][j])/2;
                red[width-i-1][j]=red[i][j];
                green[i][j]=(green[i][j]+green[width-i-1][j])/2;
                green[width-i-1][j]=green[i][j];
                blue[i][j]=(blue[i][j]+blue[width-i-1][j])/2;
                blue[width-i-1][j]=green[i][j];
            }
         } 
    }
    void highContrastAvg(){
        for(int i=0;i<width/2;++i){
            for(int j=0;j<height;++j){
                if(red[i][j]==0||red[width-i-1][j]==0)red[i][j]=0;
                red[width-i-1][j]=red[i][j];               
                if(blue[i][j]==0||blue[width-i-1][j]==0)blue[i][j]=0;
                blue[width-i-1][j]=blue[i][j];
                if(green[i][j]==0||green[width-i-1][j]==0)green[i][j]=0;
                green[width-i-1][j]=green[i][j];
            }
         } 
    }
    void savetofile(String fn)throws Throwable{
        File file=new File(fn);
        ImageIO.write(image,"png",file);
    }
}
