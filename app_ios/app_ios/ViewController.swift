//
//  ViewController.swift
//  app_ios
//
//  Created by Andreas on 22.08.18.
//  Copyright © 2018 Andreas. All rights reserved.
//

import UIKit
import Kalkulator

class ViewController: UIViewController, KalkulatorCalculatorObserver  {
    
    @IBOutlet weak var resultPreviewLabel: UILabel!
    @IBOutlet weak var inputLineLabel: UILabel!
    
    let calc = KalkulatorCalculator()

    override func viewDidLoad() {
        super.viewDidLoad()
        calc.registerObserver(observer: self)
    }
    
    @IBAction func digitButtonClicked(_ sender: UIButton) {
        switch sender.tag {
        case 0:
            calc.addInput(input: KalkulatorInputNumberZERO())
            break
        case 1:
            calc.addInput(input: KalkulatorInputNumberONE())
            break
        case 2:
            calc.addInput(input: KalkulatorInputNumberTWO())
            break
        case 3:
            calc.addInput(input: KalkulatorInputNumberTHREE())
            break
        case 4:
            calc.addInput(input: KalkulatorInputNumberFOUR())
            break
        case 5:
            calc.addInput(input: KalkulatorInputNumberFIVE())
            break
        case 6:
            calc.addInput(input: KalkulatorInputNumberSIX())
            break
        case 7:
            calc.addInput(input: KalkulatorInputNumberSEVEN())
            break
        case 8:
            calc.addInput(input: KalkulatorInputNumberEIGHT())
            break
        case 9:
            calc.addInput(input: KalkulatorInputNumberNINE())
            break
        default:
            break
        }
    }
    
    @IBAction func plusButtonClicked(_ sender: UIButton) {
        calc.addInput(input: KalkulatorInputOperatorPLUS())
    }
    
    @IBAction func minusButtonClicked(_ sender: UIButton) {
        calc.addInput(input: KalkulatorInputOperatorMINUS())
    }

    @IBAction func clearButtonClicked(_ sender: UIButton) {
        calc.addInput(input: KalkulatorInputRESET())
    }
    
    @IBAction func enterButtonClicked(_ sender: UIButton) {
        calc.addInput(input: KalkulatorInputEQUALS())
    }
    
    //KalkulatorCalculatorObserver conformance
    func onChange(state: KalkulatorState) {
        resultPreviewLabel.text = state.resultPreview
        inputLineLabel.text = state.inputLine
    }
}

