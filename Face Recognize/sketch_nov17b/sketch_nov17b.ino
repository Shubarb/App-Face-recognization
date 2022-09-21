const int relay = 12;
const int red = 11;
const int green = 10;
void setup(){ 
    pinMode(5, OUTPUT);    
    pinMode(red, OUTPUT);
    pinMode(green, OUTPUT);
    pinMode(relay, OUTPUT);
    Serial.begin(9600);        
}

void lock()
{
  digitalWrite(red,HIGH);
  digitalWrite(green,LOW);
  digitalWrite(relay,LOW);
}
void loop(){ 
    int num;
    num = digitalRead(A0);
    if(num == HIGH){
    Serial.println("Face access!");
    delay(400);
    int val;               
    double data;          
    val = analogRead(A2);  //đọc dữ liệu từ lối vào A2 (LM35)
    data = (double) val * (5/10.24); //chuyển từ V từ Vout của LM35 sang nhiệt độ C 
             lock();
             Serial.print("temperature: ");     
             Serial.print(data);              
             Serial.println("C");
             delay(100);  
    if(data <= 37.5 && data > 36) 
    {
            digitalWrite(red,LOW);
            digitalWrite(green,HIGH);
            digitalWrite(relay,HIGH);
            Serial.println("DOOR OPEN");
            delay(4000);
            lock();
    }
    if(data >37.5)
    {
            Serial.println("temperature is too high, try again later!");
            delay(1000);
    }
    }
    else {
    Serial.println("Face denied!");
    delay(400);
  
  }
}
