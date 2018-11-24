package com.example.ttaylor.weatherapp.Retrofit;



import com.example.ttaylor.weatherapp.Modelv2.WeatherForecastResult;
import com.example.ttaylor.weatherapp.Model.WeatherResult;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IOpenWeatherMap {
    @GET ("weather")
    Observable<WeatherResult> getWeatherByLatLng(@Query("lat") String lat,
                                                 @Query("lon") String lng,
                                                 @Query("appid") String appid,
                                                 @Query("units") String units);
    @GET ("weather")
    Observable<WeatherResult> getWeatherByCityName(@Query("q") String cityName,
                                                 @Query("appid") String appid,
                                                 @Query("units") String units);


    @GET ("forecast")
    Observable<WeatherForecastResult> getForecastWeatherByLatLng(@Query("lat") String lat,
                                                         @Query("lon") String lng,
                                                         @Query("appid") String appid,
                                                         @Query("units") String units);

}
