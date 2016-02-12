package Model;

/**
 * Created by lfbarreto on 02/02/16.
 */
public class Chemin {

    public Case target;

    public final double weight=1.0;

    public Chemin(Case target){
        this.target=target;
    }

    public boolean equals(Object obj){
        if(obj == null)return false;
        if(obj == this)return true;
        if(!(obj instanceof Chemin ))return false;
        return (((Chemin) obj).target==this.target);
    }
}
