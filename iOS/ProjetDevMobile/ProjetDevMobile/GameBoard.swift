//
//  GameBoard.swift
//  ProjetDevMobile
//
//  Created by etudiant on 11/04/2019.
//  Copyright © 2019 etudiant. All rights reserved.
//

public class GameBoard {
    
    public class GameCell {
        var value: Int
        
        init(val: Int) {
            value = val
        }
    }
    
    var currentCellX:Int = -1;
    var currentCellY:Int = -1;
    var nbCoups:Int = 0;
    var cells:[[GameCell]];
    
    init(){
        cells = [[GameCell(val:0),GameCell(val:0),GameCell(val:0),GameCell(val:0),GameCell(val:0)],
                 [GameCell(val:0),GameCell(val:0),GameCell(val:0),GameCell(val:0),GameCell(val:0)],
                 [GameCell(val:0),GameCell(val:0),GameCell(val:0),GameCell(val:0),GameCell(val:0)],
                 [GameCell(val:0),GameCell(val:0),GameCell(val:0),GameCell(val:0),GameCell(val:0)],
                 [GameCell(val:0),GameCell(val:0),GameCell(val:0),GameCell(val:0),GameCell(val:0)]]
    }
    
    
    func swapValue(){
        
        if (currentCellX == -1){
            return
        }
        if (currentCellY == -1){
            return
        }
        
        if(cells[currentCellX][currentCellY].value==0) {
            cells[currentCellX][currentCellY].value=1
        } else {
            cells[currentCellX][currentCellY].value=0
        }
        if (currentCellY>0) {
            if (cells[currentCellX][currentCellY-1].value == 0) {
                cells[currentCellX][currentCellY-1].value = 1;
            } else {
                cells[currentCellX][currentCellY-1].value = 0;
            }
        }
        if (currentCellY<4) {
            if (cells[currentCellX][currentCellY+1].value == 0) {
                cells[currentCellX][currentCellY+1].value = 1;
            } else {
                cells[currentCellX][currentCellY+1].value = 0;
            }
        }
        if (currentCellX>0) {
            if (cells[currentCellX-1][currentCellY].value == 0) {
                cells[currentCellX-1][currentCellY].value = 1;
            } else {
                cells[currentCellX-1][currentCellY].value = 0;
            }
        }
        if (currentCellX<4) {
            if (cells[currentCellX+1][currentCellY].value == 0) {
                cells[currentCellX+1][currentCellY].value = 1;
            } else {
                cells[currentCellX+1][currentCellY].value = 0;
            }
        }
        nbCoups = nbCoups + 1
        
    }
    
    func win() -> Bool{
        for x in 0..<5 {
            for y in 0..<5 {
                if (cells[x][y].value == 0){
                    return false
                }
            }
        }
        return true
    }
    
    
    func getGameBoard() -> [[GameCell]]{
        return cells
    }
    
}
