# Lexical-Analysis
**It was developed to analyze any "java" program.**

**For example, you enter such a file.**
```
package Number; 

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

```
**As a result, we are taking this output.**
```
-----------------
Class name : Number 
Subelements :4

value - int
negative - boolean
aa - String
bb - float

Member functions : 5
Number 
Return type : No
Parameter : 1
value - int
---------
Number 
Return type : No
Parameter : 2
aa - String
bb - float
---------
setDeger
Return type : void
Parameter : 1
value - int
---------
Negative
Return type : boolean
Parameter : 0
---------
toString
Return type : String
Parameter : 0
---------
```
