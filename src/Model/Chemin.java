package Model;

/**
 * Created by lfbarreto on 02/02/16.
 */
public class Chemin {

    private int i1,j1,i2,j2;


    public Chemin(int i1,int j1, int i2, int j2){
        this.i1=i1;
        this.j1=j1;
        this.i2=i2;
        this.j2=j2;
    }

    public int getI1() {
        return i1;
    }

    public void setI1(int i1) {
        this.i1 = i1;
    }

    public int getJ1() {
        return j1;
    }

    public void setJ1(int j1) {
        this.j1 = j1;
    }

    public int getI2() {
        return i2;
    }

    public void setI2(int i2) {
        this.i2 = i2;
    }

    public int getJ2() {
        return j2;
    }

    public void setJ2(int j2) {
        this.j2 = j2;
    }

    public boolean equals(Object obj){
        if(obj == null)return false;
        if(obj == this)return true;
        if(!(obj instanceof Chemin ))return false;
        return (((Chemin) obj).getI1()==this.i1 &&
                ((Chemin) obj).getI2()==this.i2 &&
                ((Chemin) obj).getJ1()==this.j1 &&
                ((Chemin) obj).getJ2()==this.j2);
    }
}
