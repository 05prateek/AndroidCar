# AndroidCar
Android Controlled Car Robot over wifi(ESP8266)


There are a already a lot of RC car projects using a smartphone to control the car using ESP8266.
this is just a fun college project, with a much better speed control.


# How to:

+ Firstly build your car using esp8266 motor driver,motors and chassis
+ upload the [AndroidCarServer.ino](https://github.com/05prateek/AndroidCar/blob/main/AndroidCarServer.ino) into the esp8266 using ArduionoIde
+ import the [android code folder](https://github.com/05prateek/AndroidCar/tree/main/Android) in Android Studio and build the apk or install the app over adb.
+ once the app is installed check connect the Esp8266 to your PC and open Serial Monitor on Arduino Ide
+ Check the printed Ip Address of the ESP8266 in the serial monitor and insert it into the app on smartphone device with port as 80.

# Working

This Project uses HTTP Requests.
To handle the request on the android app [Volley](https://github.com/google/volley) library is used.
The server here is the ESP8266 and the client is the smartphone. The client will send HTTP POST requests to the ESP8266 to send commands to control the car.

The Speed Control SeekBar is Made using [code by tankery](https://github.com/tankery/CircularSeekBar)

# Screenshots

![](https://github.com/05prateek/AndroidCar/blob/main/Screenshots/AndroidCar.png)
