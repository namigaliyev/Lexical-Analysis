package paket; 
 
/**
 * 
 * @author Namig
 */
public class Number {
  private int value;     
  private boolean negative;     
  String aa;
  float bb;
  
  public Number(int value){
    this.value = value;
    negative = value < 0;
  }
  
  public Number(String aa,float bb){
	  this.aa=aa;
	  this.bb=bb;
  }

  public void setDeger(int value){
    this.deger = value;
    negative = this.value < 0;
  }
  public boolean Negative(){
    return negative;
  } 
 
  @Override
  public String toString() {
    return String.valueOf(deger);
  }
}