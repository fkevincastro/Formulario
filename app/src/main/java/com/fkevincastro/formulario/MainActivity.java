package com.fkevincastro.formulario;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    Spinner LugarNacimiento;
    Button Fecha;
    int year,month,day;
    static final int DILOG_ID=0;
    EditText Usuario,Contra1,Contra2,Correo;
    String R_usuario,R_contra1,R_contra2,R_sexo,R_ciudad,R_correo; //variables internas
    boolean FaltaDato,PsswDiff;
    String[] Hobbies=new String[4];

    CheckBox B_nadar,B_leer,B_viajar,B_pintar;
    TextView TextoSalida;
    String[] ciudades={"Ciudades","Bogotá","Medellin","Cali","Barranquilla","Cartagena","Ibagué","Bucaramanga","Pereira","Manizales","Armenia"};
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LugarNacimiento= findViewById(R.id.sPlace);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,ciudades);
        showialogOnbuttonClicked();
        LugarNacimiento.setAdapter(adapter);
        LugarNacimiento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               FaltaDato=false;
               switch (i){
                   case 1: R_ciudad="Bogotá";
                       break;
                   case 2: R_ciudad="Medellin";
                       break;
                   case 3: R_ciudad="Cali";
                       break;
                   case 4: R_ciudad="Barranquilla";
                       break;
                   case 5: R_ciudad="Cartagena";
                       break;
                   case 6: R_ciudad="Ibagué";
                       break;
                   case 7: R_ciudad="Bucaramanga";
                       break;
                   case 8: R_ciudad="Pereira";
                       break;
                   case 9: R_ciudad="Manizales";
                       break;
                   case 10: R_ciudad="Armenia";
                       break;
                   default:FaltaDato=true;
                   break;

               }
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {
                    FaltaDato=true;
           }
       });

        final Calendar calendario= Calendar.getInstance();
        year=calendario.get(Calendar.YEAR);
        month=calendario.get(Calendar.MONTH)+1;
        day=calendario.get(Calendar.DAY_OF_MONTH);

        Usuario=findViewById(R.id.eUser);
        Contra1=findViewById(R.id.ePsw1);
        Contra2=findViewById(R.id.epsw2);
        B_nadar=findViewById(R.id.cNadar);
        B_leer=findViewById(R.id.cLeer);
        B_pintar=findViewById(R.id.cPintar);
        B_viajar=findViewById(R.id.cViajar);
        TextoSalida=findViewById(R.id.tSalida);
        Correo=findViewById(R.id.eMail);
        Hobbies[0] = "";
        Hobbies[1] = "";
        Hobbies[2] = "";
        Hobbies[3] = "";





    }

    public void OnRadioButtonClicked(View view) {
        int id=view.getId();
        if(id==R.id.rMale){
          R_sexo="Masculino";
        }
        else {
            R_sexo="Femenino";
        }
    }

    public  void showialogOnbuttonClicked(){
        Fecha=findViewById(R.id.bfecha);

        Fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DILOG_ID);

            }
        });
    }

    protected Dialog onCreateDialog(int id){
        if (id==DILOG_ID)
            return  new DatePickerDialog(this, dpickerListener, year,month,day);
        return null;
    }

    private  DatePickerDialog.OnDateSetListener dpickerListener=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int Year_Local, int monthofyear_Local, int dayofmonth_Local) {
            year=Year_Local;
            month=monthofyear_Local+1;
            day=dayofmonth_Local;

        }
    };

    public void OnEnviarClicked(View view) {

        FaltaDato=false;
        PsswDiff=false;

        if(!Usuario.getText().toString().isEmpty()){
            R_usuario=Usuario.getText().toString();
        }
        else FaltaDato=true;

        if(!Correo.getText().toString().isEmpty()){
            R_correo=Correo.getText().toString();
        }
        else FaltaDato=true;

        if(R_sexo==null){
            FaltaDato=true;
        }
      if(R_ciudad==null){
           FaltaDato=true;
       }
       if(Hobbies[0]==""&&Hobbies[1]=="" &&Hobbies[2]=="" &&Hobbies[3]=="" ){
           FaltaDato=true;
       }

        if(!Contra1.getText().toString().isEmpty() ) {
            R_contra1 = Contra1.getText().toString();

       }
       else {
            R_contra1 ="";
           FaltaDato=true;
        }

        if(  !Contra2.getText().toString().isEmpty()) {

            R_contra2=Contra2.getText().toString();
       }else {
            R_contra2 = "";
            FaltaDato=true;
        }

       if (!R_contra1.equals(R_contra2) &&  R_contra1!=null &&  R_contra2!=null){
                PsswDiff=true;
            }
            else PsswDiff=false;

        PonerDatosTextView();

    }

    public void onCheckBoxClicked(View view) {
        int id=view.getId();




        switch(id){
            case R.id.cNadar:
                if(B_nadar.isChecked()){

                    Hobbies[0]="Nadar";

                }
                else  Hobbies[0] = "";


                break;
            case R.id.cPintar:
                if(B_pintar.isChecked()) {
                    Hobbies[1] = " Pintar";

                }
                else  Hobbies[1] = "";



                break;
            case R.id.cViajar:
                if(B_viajar.isChecked()){
                    Hobbies[2]=" Viajar";

                }
                else  Hobbies[2] = "";



                break;
            case R.id.cLeer:
                if(B_leer.isChecked()){
                    Hobbies[3]=" Leer";

                }
                else  Hobbies[3] = "";

                break;
        }



    }

    public void PonerDatosTextView(){
        Log.d("PssDiff 2:",String.valueOf(PsswDiff));
        if(FaltaDato){
            TextoSalida.setText("Registro incompleto: Faltan Datos");
        }
        else if(PsswDiff){
            TextoSalida.setText("Las constraseñas no coinciden");
        }
        else{
            TextoSalida.setText("USUARIO: " + R_usuario + "\n" + "CONTRASEÑA: " + R_contra1 + "\n" + "CORREO: " + R_correo + "\n" + "SEXO: " + R_sexo + "\nFECHA NAC: " + year + "/" + month + "/" + day + "\nCIUDAD: " + R_ciudad + "\nHOBBIES: ");

            if(Hobbies[0]!="")
                TextoSalida.append("Nadar ");
            if (Hobbies[1]!="")
                TextoSalida.append("Pintar ");
            if (Hobbies[2]!="")
                TextoSalida.append("Viajar ");
            if (Hobbies[3]!="")
                TextoSalida.append("Leer ");

            TextoSalida.append("\nREGISTRO COMPLETO.");








        }

    }
}

