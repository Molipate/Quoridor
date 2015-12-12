package Model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;





public class AssetsImage {

    protected Model model;


    public ImageIcon  IconJ1, IconJ2, WallH, WallV,intersection,Icon,movePossible;



    //gifs
    //public final ImageIcon PawnMove = new ImageIcon(this.getClass().getResource("/image/TutoGif/Pion.gif"));


    public AssetsImage(Model m) {

        this.model = m;



        IconJ1 = new ImageIcon(ImageLoader.loadImage("/Assets/iconJ1.png"));
        IconJ2 = new ImageIcon(ImageLoader.loadImage("/Assets/iconJ2.png"));
        WallH = new ImageIcon(ImageLoader.loadImage("/Assets/MH.png"));
        WallV = new ImageIcon(ImageLoader.loadImage("/Assets/MV.png"));
        intersection = new ImageIcon(ImageLoader.loadImage("/Assets/Intersection.png"));
        Icon = new ImageIcon(ImageLoader.loadImage("/Assets/icon.png"));
        movePossible = new ImageIcon(ImageLoader.loadImage("/Assets/movePossible.png"));

    }

    public ImageIcon getIconJ1() {
        return IconJ1;
    }

    public void setIconJ1(ImageIcon iconJ1) {
        IconJ1 = iconJ1;
    }

    public ImageIcon getIconJ2() {
        return IconJ2;
    }

    public void setIconJ2(ImageIcon iconJ2) {
        IconJ2 = iconJ2;
    }

    public ImageIcon getWallH() {
        return WallH;
    }

    public void setWallH(ImageIcon wallH) {
        WallH = wallH;
    }

    public ImageIcon getWallV() {
        return WallV;
    }

    public void setWallV(ImageIcon wallV) {
        WallV = wallV;
    }

    public ImageIcon getIntersection() {
        return intersection;
    }

    public void setIntersection(ImageIcon intersection) {
        this.intersection = intersection;
    }

    public ImageIcon getIcon() {
        return Icon;
    }

    public void setIcon(ImageIcon icon) {
        Icon = icon;
    }

    public ImageIcon getMovePossible() {
        return movePossible;
    }

    public void setMovePossible(ImageIcon movePossible) {
        this.movePossible = movePossible;
    }
}