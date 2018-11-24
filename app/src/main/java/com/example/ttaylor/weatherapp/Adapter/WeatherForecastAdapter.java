package com.example.ttaylor.weatherapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ttaylor.weatherapp.Common.Common;
import com.example.ttaylor.weatherapp.R;
import com.example.ttaylor.weatherapp.Modelv2.WeatherForecastResult;
import com.squareup.picasso.Picasso;


public class WeatherForecastAdapter extends RecyclerView.Adapter<WeatherForecastAdapter.MyViewHolder> {

    //Context context;
    private WeatherForecastResult weatherForecastResult;

    public WeatherForecastAdapter(WeatherForecastResult weatherForecastResult){
        this.weatherForecastResult = weatherForecastResult;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather_forecast,parent,false);
        MyViewHolder myh = new MyViewHolder(itemView);
        return myh;
    }

    public void updateInformation(WeatherForecastResult weatherForecastResult){
        this.weatherForecastResult = weatherForecastResult;
    }

    public void notifyAdapterDataSetChanged(){
        this.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        //Load image
        Picasso.get().load(new StringBuilder("https://openweathermap.org/img/w/")
                .append(weatherForecastResult.getList().get(position).getWeather().get(0).getIcon())
                .append(".png").toString()).into(holder.img_weather);

        holder.txt_date_time.setText(new StringBuilder(Common.convertUnixToDate(weatherForecastResult
        .getList().get(position).getDt())));

        holder.txt_description.setText(new StringBuilder(weatherForecastResult.getList().get(position)
        .getWeather().get(0).getDescription()));

        holder.txt_temperature.setText(new StringBuilder(String.valueOf(weatherForecastResult.getList().get(position)
                .getMain().getTemp())).append("°C"));
    }

    @Override
    public int getItemCount() {
        if(weatherForecastResult.getList() == null)
            return 0;
        int size = weatherForecastResult.getList().size();
        return size;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txt_date_time, txt_description, txt_temperature;
        ImageView img_weather;
        public MyViewHolder(View itemView){
            super(itemView);

            img_weather = (ImageView)itemView.findViewById(R.id.img_weather);
            txt_date_time = (TextView)itemView.findViewById(R.id.txt_date);
            txt_description = (TextView)itemView.findViewById(R.id.txt_description);
            txt_temperature = (TextView)itemView.findViewById(R.id.txt_temperature);

        }




    }


}
