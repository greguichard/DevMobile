package com.example.projetdevmobile;

public class GameBoard {

    public static class GameCell {
        public int Value;

        public GameCell( int Value ) {
            this.Value = Value;
        }
    }


    public int currentCellX = -1;
    public int currentCellY = -1;
    public int nbCoups = 0;
    public GameCell [][] cells;



    public GameBoard() {
        this.cells = new GameCell[][]{
                {new GameCell(1), new GameCell(1), new GameCell(1), new GameCell(0), new GameCell(0)},
                {new GameCell(1), new GameCell(1), new GameCell(1), new GameCell(1), new GameCell(0)},
                {new GameCell(1), new GameCell(1), new GameCell(1), new GameCell(1), new GameCell(1)},
                {new GameCell(1), new GameCell(1), new GameCell(1), new GameCell(1), new GameCell(1)},
                {new GameCell(1), new GameCell(1), new GameCell(1), new GameCell(1), new GameCell(1)}
        };
    }

    public void pushValue() {
        if ( this.currentCellX == -1 ) return;
        if ( this.currentCellY == -1 ) return;

        GameCell currentCell = this.cells[ this.currentCellY ][ this.currentCellX ];
        if ( currentCell.Value == 0 ) {
            currentCell.Value = 1;
        } else {
            currentCell.Value = 0;
        }

        if (this.currentCellY>0) {
            GameCell CellHaut = this.cells[this.currentCellY-1][this.currentCellX];
            if (CellHaut.Value == 0) {
                CellHaut.Value = 1;
            } else {
                CellHaut.Value = 0;
            }
        }
        if (this.currentCellY<4) {
            GameCell CellBas = this.cells[this.currentCellY+1][this.currentCellX];
            if (CellBas.Value == 0) {
                CellBas.Value = 1;
            } else {
                CellBas.Value = 0;
            }
        }
        if (this.currentCellX>0) {
            GameCell CellGauche = this.cells[this.currentCellY][this.currentCellX-1];
            if (CellGauche.Value == 0) {
                CellGauche.Value = 1;
            } else {
                CellGauche.Value = 0;
            }
        }
        if (this.currentCellX<4) {
            GameCell CellDroite = this.cells[this.currentCellY][this.currentCellX+1];
            if (CellDroite.Value == 0) {
                CellDroite.Value = 1;
            } else {
                CellDroite.Value = 0;
            }
        }
        nbCoups = nbCoups +1;
        if (this.win()){

        }
    }

    public boolean win(){
        for( int y=0; y<5; y++ ) {
            for (int x = 0; x < 5; x++) {
                if (this.cells[x][y].Value == 0){
                    return false;
                }
            }
        }
        return true;
    }

}
