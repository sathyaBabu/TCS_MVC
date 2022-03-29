package com.sathya.tcs_mvc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

/*

     sathyahelp123@gmail.com

     Thread App..
     UI handler
     Worker Thread : Progress BAr..

     Assignment :

     ******  Fix The LiveData PArt in this code...   ******

      create 4 Progress BAr.. That in turn should communicate to the UI.. When UI completes the task
      it will report back to PB1,2,3,4

      Discuss about the Design Aspect...



    // TCS_Thread documentation
    ArrayList< Handler > outBoxHandler = new ArrayList<>();
    // we will be creating 4 PB all the 4 PB gets re. to the OutBoxHandler...
    // When the PB_X job is completed.. then the UI will report the PB about the completion state..

    // Its not a good idea to reg.. right in UI..
    // What next?

    // MVC this supports us with a basic frame work..
    // What is MVC   Model view Controller
    // Model holds PB1, 2 , 3  , 4
    // View( Activity )  displays the progress of all the PB1... 4
    // Controller : Core part of our App .. dose all the registration and deligation Job..





 */
public class MainActivity extends AppCompatActivity {

    // we can also bring in Livedata here
    EditText name,age ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.editPersonName) ;
        age = (EditText) findViewById(R.id.editPersonAge) ;

        // Imagine user is typing iy in or we are featching it from a server..
        Model model = getLoginDetails(); // Model has Login Details NEW we can also bring in Livedata here
        // live data has to publish it to the observers..
        View view = new View();

        // Model and view has to be delt with the controller...

//        Controller controller = new Controller(model,view);
        Controller controller = new Controller();
        controller = new ViewModelProvider(this).get(Controller.class);


        // Changing the model and view so that observers will get a notification via live data to watch changes

        controller.setModel( model);
        controller.setView( view);


                    // ***** Manual..
                    // So far we have not implimented Our Live data here so we nned to take care of care of it manually

                  //  controller.updateView(); // since the model has NEW data ( model has changed )

                    // Following has to be held by the model...
                    // YES the data is ported on to model in the controller

//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//                    controller.setName("Sathya Sai");
//                    controller.setAge("24");
//
//                    controller.updateView(); // since the model has NEW data ( model has changed )


        // This is how our code should be to deal with thr live data..
        // Since we are already in the view lets display the changed data


        //  ViewModelProvider has a built in factory which in turn takes class arg as paramaters
        // in this case model view could be considered as factory parm..


      controller.getNewUser().observe(this, new Observer<Model>() {
          @Override
          public void onChanged(Model model) {
              // Use textView or ListView
              Log.d("Tag","Name : "+model.getName()+" Age : "+model.getAge());

          }
      });



    }



    // pretending to be as getting to the server to featch the user login..

    private  static  Model getLoginDetails() {



        Model login = new Model();
        login.setName("Farzana");
        login.setAge("23");

        return  login ;
    }
}

/*


    private    Model getLoginDetails() {


        name = (EditText) findViewById(R.id.editPersonName) ;
        age = (EditText) findViewById(R.id.editPersonAge) ;


        Model login = new Model();
        login.setName(name.getText().toString());
        login.setAge(age.getText().toString());

        return  login ;
    }

    public void loginlive(View view1) {
        controller.setModel( model);
        controller.setView( view );
        Log.d("Tag","Name login live : "+model.getName()+" Age : "+model.getAge());

    }
 */