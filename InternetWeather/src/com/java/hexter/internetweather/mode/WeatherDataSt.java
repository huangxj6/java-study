package com.java.hexter.internetweather.mode;

import java.util.ArrayList;

import com.java.hexter.internetweather.observer.Observer;
import com.java.hexter.internetweather.observer.Subject;

public class WeatherDataSt implements Subject{
	
	private float mTemperatrue;
	private float mPressure;
	private float mHumidity;
	private ArrayList<Observer> mObservers;
	public WeatherDataSt()
	{
		mObservers=new ArrayList<Observer>();
	}
	
	public float getTemperature()
	{
		return mTemperatrue;
		
	}
	
	public float getPressure()
	{
		return mPressure;
		
	}
	
	public float getHumidity()
	{
		return mHumidity;
		
	}
	public void dataChange()
	{
		notifyObservers();
	}

	

	
	
	
	public void setData(float mTemperatrue,float mPressure,float mHumidity)
	{
		this.mTemperatrue=mTemperatrue;
		this.mPressure=mPressure;
		this.mHumidity=mHumidity;
		dataChange();
	}

	@Override
	public void registerObserver(Observer o) {
		// TODO Auto-generated method stub
		mObservers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		// TODO Auto-generated method stub
		if(mObservers.contains(o))
		{mObservers.remove(o);}
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		for(int i=0,len=mObservers.size();i<len;i++)
		{
			mObservers.get(i).update(getTemperature(), getPressure(), getHumidity());
		}
	}

}
