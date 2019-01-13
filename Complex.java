class Complex{
    double r;
    double i;
    Complex(double R,double I){
        r=R;
        i=I;
    }
    Complex add(Complex a){
        return new Complex(r+a.r,i+a.i);
    }
    Complex subtract(Complex a){
        return new Complex(r-a.r,i-a.i);
    }
    Complex multiply(Complex a){
        return new Complex(r*a.r-i*a.i,r*a.i+i*a.r);
    }
}
