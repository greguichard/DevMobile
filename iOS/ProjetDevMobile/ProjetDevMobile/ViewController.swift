//
//  ViewController.swift
//  ProjetDevMobile
//
//  Created by etudiant on 10/04/2019.
//  Copyright © 2019 etudiant. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    let maVue = GameView(frame: CGRect(x: 0, y: 0, width: 1500, height: 1500))
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

}

