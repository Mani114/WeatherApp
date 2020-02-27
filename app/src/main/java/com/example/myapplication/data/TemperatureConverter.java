package com.example.myapplication.data;


class TemperatureConverter {

        static int getCelsius(double temp) {

            return (int) (temp - 273.16);

        }

        static int getKelvin(double temp){

            return (int) temp;
        }


        static int getFahrenheit(double temp) {

            return (int) temp;   //convert to fahrenhit
        }



}