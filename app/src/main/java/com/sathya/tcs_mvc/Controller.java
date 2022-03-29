package com.sathya.tcs_mvc;

/*

    Note : Controller should know about the model and view..
         : reg. all the progress bar handlers here PB1 ... 4
 */

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

// get the dependency
public class Controller  extends ViewModel {


    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    private  Model model ;
    private  View view ;

    private MutableLiveData<Model> mModel =  new MutableLiveData<Model>() ;


    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
        Log.d("tag"," About to call setValue  "+model.toString());
        mModel.setValue( model );
    }

    public Controller() {
    }


    public void setName( String name){ model.setName(name);}
    public void setAge( String  age){ model.setAge(age); }

    public String getName() { return model.getName();}
    public String getAge() { return model.getAge();}

    // When ever the data changes in the model controller in turn will call the view..

    public  void  updateView() {

        view.printUserLoginDetails( model.getName(),model.getAge());

    }

    // return the live data here

    public LiveData<Model> getNewUser(){
       Log.d("tag"," getNeUser "+mModel.toString());
        return mModel ;
    }


}
