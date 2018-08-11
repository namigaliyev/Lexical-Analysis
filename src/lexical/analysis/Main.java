/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexical.analysis;

import java.io.IOException;

/**
 *
 * @author Namig
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Transactions is=new Transactions();
        is.File("Program.java");
        is.Calculate();
        is.Print();
        
    }
    
}
