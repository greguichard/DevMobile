//
//  GameView.swift
//  ProjetDevMobile
//
//  Created by etudiant on 10/04/2019.
//  Copyright © 2019 etudiant. All rights reserved.
//

import UIKit

class GameView: UIView  {
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        self.becomeFirstResponder()
        backgroundColor = UIColor.clear
    }
    
    var gb:GameBoard = GameBoard.init()
    
   
    override func draw(_ rect: CGRect) {
        
        let context = UIGraphicsGetCurrentContext()
        
        let w = Int(rect.size.width)
        let cellwidth = w/5
        //Dessiner les cellules
        
        for x in 0..<5 {
            for y in 0..<5 {
                if (gb.getGameBoard()[x][y].value==0){
                    context?.setFillColor(red: 0, green: 0, blue: 0, alpha: 0.5)
                } else {
                    context?.setFillColor(red: 0, green: 0, blue: 0, alpha: 100)
                }
                let rectangle = CGRect(x: x*cellwidth, y: y*cellwidth, width: cellwidth, height: cellwidth)
                context?.fill(rectangle)
            }
        }
        
        //Dessiner les lignes
        context?.setLineWidth(2)
        UIColor.black.setStroke()
        
        for i in 0..<6 {
            context?.move(to: CGPoint(x: i*cellwidth, y: 0))
            context?.addLine(to: CGPoint(x: i*cellwidth, y: cellwidth*5))
            context!.strokePath()
            
            context?.move(to: CGPoint(x: 0, y: i*cellwidth))
            context?.addLine(to: CGPoint(x: cellwidth*5, y: i*cellwidth))
            context!.strokePath()
            
        }
    
    }
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        
        let touch = touches.first
        let location = touch?.location(in: self)
        let xcoord = location?.x
        let ycoord = location?.y
        let screenSize: CGRect = UIScreen.main.bounds
        let w = screenSize.width
        //let h = screenSize.height
        let cellwidth = w/5
        
        
        if ( Int(xcoord!) > 0 && xcoord! < cellwidth*5 && Int(ycoord!) > 0 && ycoord! < cellwidth*5) {
            let cellX = Int(xcoord!/cellwidth)
            let cellY = Int(ycoord!/cellwidth)
            
            gb.currentCellX = cellX
            gb.currentCellY = cellY
            gb.swapValue()
            
            self.setNeedsDisplay()
            
//            if(gb.win()){
//            }
        }
    }
    
    override func motionBegan(_ motion: UIEventSubtype, with event: UIEvent?) {
        self.setNeedsDisplay()
    }
    
    override var canBecomeFirstResponder: Bool {
        get {
            return true
        }
    }
    
}
