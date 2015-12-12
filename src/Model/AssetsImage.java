package Model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;





public class AssetsImage {

    protected Model model;


    public ImageIcon  IconJ1, IconJ2, WallH, WallV;



    //gifs
    //public final ImageIcon PawnMove = new ImageIcon(this.getClass().getResource("/image/TutoGif/Pion.gif"));


    public AssetsImage(Model m) {

        this.model = m;



        IconJ1 = new ImageIcon(ImageLoader.loadImage("/Assets/iconJ1.png"));
        IconJ2 = new ImageIcon(ImageLoader.loadImage("/Assets/iconJ2.png"));
        WallH = new ImageIcon(ImageLoader.loadImage("/Assets/MH.png"));
        WallV = new ImageIcon(ImageLoader.loadImage("/Assets/MV.png"));

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
}