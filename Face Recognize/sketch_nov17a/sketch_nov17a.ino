unsigned long tepTimer ;    

const int red = 11;
const int green = 10;
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
