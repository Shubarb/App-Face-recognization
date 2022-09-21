#include <Arduino.h>
#line 1 "C:\\Users\\Tran Viet Quang\\Downloads\\sketch_nov17a\\sketch_nov17a.ino"
unsigned long tepTimer ;    

const int red = 11;
const int green = 10;
#line 5 "C:\\Users\\Tran Viet Quang\\Downloads\\sketch_nov17a\\sketch_nov17a.ino"
void setup();
#line 11 "C:\\Users\\Tran Viet Quang\\Downloads\\sketch_nov17a\\sketch_nov17a.ino"
void lock();
#line 16 "C:\\Users\\Tran Viet Quang\\Downloads\\sketch_nov17a\\sketch_nov17a.ino"
void loop();
#line 5 "C:\\Users\\Tran Viet Quang\\Downloads\\sketch_nov17a\\sketch_nov17a.ino"
void setup(){ 
    pinMode(red, OUTPUT);
    pinMode(green, OUTPUT);
    Serial.begin(9600);        
}

void lock()
{
  digitalWrite(red,HIGH);
  digitalWrite(green,LOW);
}
void loop(){ 
  if(Serial.available() > 0){
    int num;
    num = Serial.read();
    if(num == '1'){
            digitalWrite(red,LOW);
            digitalWrite(green,HIGH);
    }
    else {
    lock();
    }
  
  }
}

