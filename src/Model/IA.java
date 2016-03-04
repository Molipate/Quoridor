package Model;

public class IA {
    Model model ;
    Board board ;
    public IA(Model m, Board b){
        this.model=m;
        this.board=b;
    }
// Important : l'ia suppose que le joueur ordi est en haut

    /* Important : l'ia suppose qu'il y a une fonction shortest_path dans model qui renvoie un tableau de tableaux
     *    sous la forme [nb de déplacements pour gagner][couple x,y du déplacement en question]
     *    Il suffira de faire un :
     *    switch player     pour que ce soit l'IA qui joue
     *    IA.getMove()      pour que le joueur 2 joue
     *    check win
     *    switch player     pour retourner sur le tour du joueur humain
     */
    public void getMove() {


        if (model.shortest_path(model.J2).size() < model.shortest_path(model.J1).size() || model.getNbWall_J2() == 0)
            board.move_Player(model.J2, model.shortest_path(model.J2).get(0).getJ(), model.shortest_path(model.J2).get(0).getI());
            // si le j2 peut finir avant le j1 ou si j2 n'a plus de murs
            //  move_player fait bouger le joueur 2 à la première case de son chemin le plus court / else
        else if (model.shortest_path(model.J1).get(0).getJ() < board.getJ1X()){
            board.placeWall(board.getJ1X() - 1, board.getJ1Y() - 1, Board.VERTICAL);// place un mur en vertical en haut à gauche de J1
            // pas besoin de tester, si j1y==0, la partie est finie
        }
        else if (model.shortest_path(model.J1).get(0).getJ() > board.getJ1X()) {
            board.placeWall(board.getJ1X() + 1, board.getJ1Y() - 1, Board.VERTICAL);// place un mur en vertical en haut à droite de J1
            // pas besoin de tester, si j1y==0, la partie est finie
        }
        else if (model.shortest_path(model.J1).get(0).getI() < board.getJ1Y()){
            if(board.getJ1X()<8)
                board.placeWall(board.getJ1X() + 1, board.getJ1Y() + 1, Board.HORIZONTAL);// place un mur en horizontal en bas à droite de J1
            else
                board.placeWall(board.getJ1X() - 1, board.getJ1Y() + 1, Board.HORIZONTAL);// place un mur en horizontal en bas à gauche de J1
        }
        else if(model.shortest_path(model.J1).get(0).getI()>board.getJ1Y()) {
            if(board.getJ1X()<8)
                board.placeWall(board.getJ1X() + 1, board.getJ1Y() - 1, Board.HORIZONTAL);// place un mur en hotizontal en haut à droite de J1
            else
                board.placeWall(board.getJ1X() - 1, board.getJ1Y() - 1, Board.HORIZONTAL);// place un mur en horizontal en haut à gauche de J1

        }
    }
}
