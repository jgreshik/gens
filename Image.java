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
		Image(String filename)throws Throwable{
			image=ImageIO.read(new File(filename));
			width=image.getWidth();
			height=image.getHeight();
			red=new int[width][height];
			green=new int[width][height];
			blue=new int[width][height];
			for(int i=0;i<width;++i){
				for(int j=0;j<height;++j){
					int color=image.getRGB(i,j);
					red[i][j]=(color&0x000FF0000)>>16;
					green[i][j]=(color&0x0000FF00)>>8;
					blue[i][j]=color&0x000000FF;
				}
			}
		}
		void complement(){
			for(int i=0;i<width;++i){
				for(int j=0;j<height;++j){
					red[i][j]=255-red[i][j];
					green[i][j]=255-green[i][j];
					blue[i][j]=255-blue[i][j];	
				}
			}
		}
		void bw(){
			for(int i=0;i<width;++i){
				for(int j=0;j<height;++j){
					int avg=(red[i][j]+green[i][j]+blue[i][j])/3;
					red[i][j]=avg;
					green[i][j]=avg;
					blue[i][j]=avg;
				}
			}
		}
		void rbg(){
			int[][]temp=blue;
			blue=green;
			green=temp;
		}
		void grb(){
			int[][]temp=red;
			red=green;
			green=temp;
		}
		void gbr(){
			int[][]temp=red;
			red=green;
			green=blue;
			blue=temp;	
		}
		void brg(){
			int[][]temp=blue;
			blue=green;
			green=red;
			red=temp;
		}
		void bgr(){
			int[][]temp=blue;
			blue=red;
			red=temp;	
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
    }
		private int[][] hcolor(double[][]values){
        TreeMap<Integer,Integer>map=new TreeMap<>();
				int[][]color=new int[width][height];
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
								color[i][j]=(int)((double)map.get(key)/max*255);
            }
        }
				return color;
		}
		void histogramcolor(double[][]Red,double[][]Green,double[][]Blue){
				red=hcolor(Red);
				green=hcolor(Green);
				blue=hcolor(Blue);	
		}
		void avgx(){
				for(int i=0;i<width;++i){
					for(int j=0;j<height/2;++j){
						red[i][j]=(red[i][j]+red[i][height-j-1])/2;
						red[i][height-j-1]=red[i][j];
						green[i][j]=(green[i][j]+green[i][height-j-1])/2;
						green[i][height-j-1]=green[i][j];
						blue[i][j]=(blue[i][j]+blue[i][height-j-1])/2;
						blue[i][height-j-1]=blue[i][j];
					}
				}
		}
		void avgy(){
				for(int i=0;i<width/2;++i){
					for(int j=0;j<height;++j){
						red[i][j]=(red[i][j]+red[width-1-i][j])/2;
						red[width-1-i][j]=red[i][j];
						green[i][j]=(green[i][j]+green[width-1-i][j])/2;
						green[width-1-i][j]=green[i][j];
						blue[i][j]=(blue[i][j]+blue[width-1-i][j])/2;
						blue[width-1-i][j]=blue[i][j];
					}
				}
		}
		void avgxy(){
			for(int i=0;i<width/2;++i){
					for(int j=0;j<height/2;++j){
						red[i][j]=(red[i][j]+red[width-1-i][j]+red[i][height-j-1]+red[width-1-i][height-j-1])/4;
						red[width-1-i][j]=red[i][j];
						red[i][height-j-1]=red[i][j];
						red[width-1-i][height-j-1]=red[i][j];
						green[i][j]=(green[i][j]+green[width-1-i][j]+green[i][height-j-1]+green[width-1-i][height-j-1])/4;
						green[width-1-i][j]=green[i][j];
						green[i][height-j-1]=green[i][j];
						green[width-1-i][height-j-1]=green[i][j];
						blue[i][j]=(blue[i][j]+blue[width-1-i][j]+blue[i][height-j-1]+blue[width-1-i][height-j-1])/4;
						blue[width-1-i][j]=blue[i][j];
						blue[i][height-j-1]=blue[i][j];
						blue[width-1-i][height-j-1]=blue[i][j];
					}
				}
		}
    void savetofile(String fn)throws Throwable{
				color();
        File file=new File(fn);
        ImageIO.write(image,"png",file);
    }
}
