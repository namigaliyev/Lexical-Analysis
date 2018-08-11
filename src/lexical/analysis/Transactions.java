/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexical.analysis;

/**
 *
 * @author Namig
 */

import static com.sun.org.apache.xalan.internal.lib.ExsltStrings.split;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.reflect.*;

public class Transactions {
    private String satir;
    private String sinifadi;
    private int altElemanSayi=0;
    private String[] altElemanAdTur=new String[100];
    
    private int uyeFonkSayi=0;
    private String[] uyeFonkDonusTur=new String[100];
    private String[] uyeFonkAd=new String[100];
    private int[] uyeFonkParaSayi=new int[100];
    private String[] uyeFonkParaAdTur=new String[100];
    private String[] Degiskenler={"int","float","double","char",
                                            "String","boolean"};
    private String[] Donusler={"int","float","double","char",
                                            "String","boolean",
                                            "void"};
    private String[] Parametre=null;
    
    //Dosyadan okuma islemleri bu method'da bulunuyor
    public void File(String yol) throws IOException 
    {
        FileReader fileReader = new FileReader(yol);
        String line;

        BufferedReader br = new BufferedReader(fileReader);

        while ((line = br.readLine()) != null) {
            satir+=line;
            satir+="\n";
        }

        br.close();
        System.out.println(satir);
    }
    
    //Hesaplama islemleri
    public void Calculate()
    {
        int i = satir.indexOf("public class");//harfin konumunu getirme
        int baslangic = i;
        char kontrol = ' ';
        while(kontrol != '{')
        {
            i++;
            kontrol = satir.charAt(i);//harf harf atlama
            
        }
        
        String k = satir.substring(baslangic,i);//
        
        k = k.replace("public class ", "");//silme
        
        sinifadi=k;
        i+=4;
        baslangic=i;
 
        while(kontrol!='}')
        {
            i++;
            kontrol = satir.charAt(i);
            
            if(kontrol==';')
            {
                k = satir.substring(baslangic,i);//
                
                
                for(int j=0;j<6;j++)
                {
                    if(k.contains(Degiskenler[j]))
                    {
                        k=k.substring(k.indexOf(Degiskenler[j]),k.length());
                        k = k.replace(Degiskenler[j]+" ", "");
                        altElemanAdTur[altElemanSayi]=k+" - ";
                        altElemanAdTur[altElemanSayi]+=Degiskenler[j];
                    }
                }
                i+=4;
                baslangic=i;
                kontrol = ' ';
                altElemanSayi++;
            }
            
            if(kontrol=='(')
            {
                k = satir.substring(baslangic,i);
                
        
                for(int j=0;j<7;j++)
                {
                    if(k.contains(sinifadi.trim()))
                    {
                        uyeFonkAd[uyeFonkSayi]=sinifadi;
                        uyeFonkDonusTur[uyeFonkSayi]="No";
                        break;
                    }
                    if(k.contains(Donusler[j])||k.contains(sinifadi))
                    {
                        k=k.substring(k.indexOf(Donusler[j]),k.length());
                        k = k.replace(Donusler[j]+" ", "");
                
                        uyeFonkAd[uyeFonkSayi]=k;
                        uyeFonkDonusTur[uyeFonkSayi]=Donusler[j];
                
                        break;
                    }
            
                }
                i++;
                kontrol = satir.charAt(i);
                if(kontrol!=')')
                {
                    baslangic=i;
                    kontrol = ' ';
                    while(kontrol != ')')
                    {
                        i++;
                        kontrol = satir.charAt(i);//harf harf atlama
                    }
                    k = satir.substring(baslangic,i);
        
                    Parametre=k.split(",");
        
        
                    for(int n=0;n<Parametre.length;n++)
                    {
                        for(int j=0;j<6;j++)
                        {
                            if(Parametre[n].contains(Degiskenler[j]))
                            {
                                Parametre[n] = Parametre[n].replace(Degiskenler[j]+" ", "");
                                if(uyeFonkParaAdTur[uyeFonkSayi]==null)
                                    uyeFonkParaAdTur[uyeFonkSayi]=Parametre[n]+" - ";
                                else
                                    uyeFonkParaAdTur[uyeFonkSayi]+=Parametre[n]+" - ";
                                uyeFonkParaAdTur[uyeFonkSayi]+=Degiskenler[j]+"\n";
                            }
                        }
                    }   
                    uyeFonkParaSayi[uyeFonkSayi]=Parametre.length;        
                }
        
        
                while(kontrol != '}')
                {
                    i++;
                    kontrol = satir.charAt(i);//harf harf atlama
                }
        
                baslangic=i;
                kontrol = ' ';
                uyeFonkSayi++;
            }   
        }
    }
    
    public void Print()
    {
        System.out.println("-----------------");
        System.out.println("Class name : "+sinifadi);
        System.out.println("Subelements :"+altElemanSayi+"\n");
        for(int i=0;i<altElemanSayi;i++)
        {
            System.out.println(altElemanAdTur[i]);
        }
        System.out.println("\nMember functions : "+uyeFonkSayi);
        for(int i=0;i<uyeFonkSayi;i++)
        {
            System.out.println(uyeFonkAd[i]);
            System.out.println("Return type : "+uyeFonkDonusTur[i]);
            if(uyeFonkParaAdTur[i]!=null)
            {
                System.out.println("Parameter : "+uyeFonkParaSayi[i]);
                System.out.print(uyeFonkParaAdTur[i]);
            }
            else
                System.out.println("Parameter : 0");
            System.out.println("---------");
        }

    }
 
}
