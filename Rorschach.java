import java.util.*;
class Rorschach{
    Random rand=new Random();
    //scale of the image
    int width;
    int height;
    //dots for probability superposition
    ArrayList<ProbDot> dots;
    //values of the data
    double[][]values;
    // number of dots to be created and their maximum |r|
    int num_dots;
    double dots_max_size;
    // set boundary for dot locations so things do not break
    int upperw;
    int lowerw;
    int upperh;
    int lowerh;
    // set value to color pixels at
    double sensitivity;
    // number of times to cluster
    int clusters;
    Rorschach(int width,int height,int num_dots,double dots_max_size,double sensitivity,int clusters){
        this.width=width;
        this.height=height;
        dots=new ArrayList<ProbDot>();
        zeroValues(width,height);
        this.num_dots=num_dots;
        this.dots_max_size=dots_max_size;
        upperw=(int)(Math.floor(width-2*dots_max_size));//*6/8));
        lowerw=(int)(Math.floor(dots_max_size));//width/8));
        upperh=(int)(Math.floor(height-2*dots_max_size));//*6/8));
        lowerh=(int)(Math.floor(dots_max_size));//height/8));
        this.sensitivity=sensitivity;
        this.clusters=clusters;
    }
    // create dots with random x,y position corresponding to locations in values[][]
    void randomDots(boolean destroyDots){
        if(destroyDots) destroyDots();
        int i=0,j=0,gens,num=0;
        // currently making num_dots dots
        gens=num_dots;//rand.nextInt(num_dots/2)+num_dots/2;
        for(int k=0;k<gens;k++){
            i=rand.nextInt(upperw)+lowerw;
            j=rand.nextInt(upperh)+lowerh; 
            num=rand.nextInt(4)+1;
            dots.add(new ProbDot(width,height,i,j,num,dots_max_size));
        }
    }
    
    // remove dots from dots array
    void destroyDots(){
        dots.clear();
    }

    // fill values with zeros
    void zeroValues(int W,int H){
        values=new double[W][H];
    }

    // map values of dots to more probable areas in values
    void clusterDots(){
        destroyDots();
        int i=0,j=0,num=0;
        for(i=lowerw;i<width-lowerw;i++){
            for(j=lowerh;j<height-lowerh;j++){
                if(values[i][j]>=sensitivity){
                    num=rand.nextInt(4)+1;
                    dots.add(new ProbDot(width,height,i,j,num,dots_max_size));
                }
            }
        }
    }

    // edit values according to dots in ArrayList<ProbDot> dots
    void conceiveValues(){
        int i=0,j=0;
        for(ProbDot dot:dots){
            for(double r:dot.rs){
                for(i=(int)(Math.floor(dot.xloc-r));i<(int)(Math.floor(dot.xloc+r));i++){
                    for(j=(int)(Math.floor(dot.yloc-r));j<(int)(Math.floor(dot.yloc+r));j++){
                        if(Math.hypot(Math.abs(i-dot.xloc),Math.abs(j-dot.yloc))>r)continue;
                        values[i][j]+=rand.nextInt(10)+1;
                    }
                }
            }
        }
    }

    void normalizeValues(){
        int i=0,j=0;
        double max=0;
        for(i=0;i<width;i++){
            for(j=0;j<height;j++){
                if(values[i][j]>max)max=values[i][j];
            }
        }
        for(i=0;i<width;i++){
            for(j=0;j<height;j++){
                values[i][j]/=max;
            }
        }
    }
    void finalizeValues(){
        int i=0,j=0;
        for(i=0;i<width;i++){
            for(j=0;j<height;j++){
                if(values[i][j]>sensitivity)values[i][j]=1;
                else values[i][j]=0;
            }
        }
    }    
    void run(){
        randomDots(false);
        conceiveValues();
        normalizeValues();
        for(int i=0;i<clusters;i++){
            clusterDots();
            zeroValues(width,height);
            conceiveValues();
            normalizeValues();
        }
        finalizeValues();
    }
}
