# 1 "C:\\Users\\Tran Viet Quang\\Downloads\\sketch_nov17a\\sketch_nov17a.ino"
unsigned long tepTimer ;

const int red = 11;
const int green = 10;
void setup(){
    pinMode(red, 0x1);
    pinMode(green, 0x1);
    Serial.begin(9600);
}

void lock()
{
  digitalWrite(red,0x1);
  digitalWrite(green,0x0);
}
void loop(){
  if(Serial.available() > 0){
    int num;
    num = Serial.read();
    if(num == '1'){
            digitalWrite(red,0x0);
            digitalWrite(green,0x1);
    }
    else {
    lock();
    }

  }
}
