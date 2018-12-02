package com.example.ttaylor.weatherapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ttaylor.weatherapp.Adapter.WeatherForecastAdapter;
import com.example.ttaylor.weatherapp.Common.Common;
import com.example.ttaylor.weatherapp.Modelv2.WeatherForecastResult;
import com.example.ttaylor.weatherapp.Retrofit.IOpenWeatherMap;
import com.example.ttaylor.weatherapp.Retrofit.RetrofitClient;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class ForecastFragment extends Fragment {

    CompositeDisposable compositeDisposable;
    IOpenWeatherMap nServices;

    WeatherForecastAdapter adapter;

    boolean data = false;

    TextView txt_city_name, txt_geo_coord;
    RecyclerView recycler_forecast;

    static ForecastFragment instance;

    public static ForecastFragment getInstance(){
        if(instance == null){
            instance = new ForecastFragment();
        }
        return instance;
    }

    public ForecastFragment() {
        compositeDisposable = new CompositeDisposable();
        Retrofit retrofit = RetrofitClient.getInstance();
        nServices = retrofit.create(IOpenWeatherMap.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View itemView = inflater.inflate(R.layout.fragment_forecast, container, false);



        txt_city_name = (TextView)itemView.findViewById(R.id.txt_city_name);
        txt_geo_coord = (TextView)itemView.findViewById(R.id.txt_geo_coord);

        //
        WeatherForecastResult weatherForecastResult = new WeatherForecastResult();
        adapter = new WeatherForecastAdapter(weatherForecastResult);
        //

        recycler_forecast = (RecyclerView)itemView.findViewById(R.id.recycler_forecast);
        recycler_forecast.setHasFixedSize(true);
        recycler_forecast.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        //Empty Set
        recycler_forecast.setAdapter(adapter);
        //

        getForecastWeatherInformation();

        return itemView;
    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    public void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    private void getForecastWeatherInformation() {
        compositeDisposable.add(nServices.getForecastWeatherByLatLng(
                String.valueOf(Common.current_location.getLatitude()),
                String.valueOf(Common.current_location.getLongitude()),
                Common.APP_ID,
                "metric")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<WeatherForecastResult>() {
                            @Override
                            public void accept(WeatherForecastResult weatherForecastResult) throws Exception {
                                displayForecastWeather(weatherForecastResult);
                                data = true;
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.d("ERROR",""+throwable.getMessage());
                            }
                        })
        );


    }

    private void displayForecastWeather (WeatherForecastResult weatherForecastResult){
        txt_city_name.setText(new StringBuilder(weatherForecastResult.getCity().getName()));
        txt_geo_coord.setText(new StringBuilder(weatherForecastResult.getCity().getCoord().toString()));


        //WeatherForecastAdapter adapter = new WeatherForecastAdapter(getContext(),weatherForecastResult);
        adapter.updateInformation(weatherForecastResult);
        adapter.notifyAdapterDataSetChanged();

        //recycler_forecast.setAdapter(adapter);
    }

}
