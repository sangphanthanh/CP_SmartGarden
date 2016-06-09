

#define SensorPin 1          //pH meter Analog output to Arduino Analog Input 0
unsigned long int avgValue;  //Store the average value of the sensor feedback
float b;
int buf[10],temp;
#define analogPin 0     // potentiometer wiper (middle terminal) connected to analog pin 0


int val = 0;           // variable to store the value 
void setup()

{

  Serial.begin(9600);          //  setup serial

}



void loop()

{
   for(int i=0;i<10;i++)       //Get 10 sample value from the sensor for smooth the value
  { 
    buf[i]=analogRead(SensorPin);
    delay(10);
  }
  for(int i=0;i<9;i++)        //sort the analog from small to large
  {
    for(int j=i+1;j<10;j++)
    {
      if(buf[i]>buf[j])
      {
        temp=buf[i];
        buf[i]=buf[j];
        buf[j]=temp;
      }
    }
  }
  avgValue=0;
  for(int i=2;i<8;i++)                      //take the average value of 6 center sample
    avgValue+=buf[i];
  float phValue=(float)avgValue*5.0/1024/6; //convert the analog into millivolt
  phValue=3.5*phValue;                      //convert the millivolt into pH value

  val = analogRead(analogPin);    // read the input pin

  
  Serial.print(val);             // debug value
  Serial.print(",");
  Serial.println(phValue);
  delay(1000);
}

  
