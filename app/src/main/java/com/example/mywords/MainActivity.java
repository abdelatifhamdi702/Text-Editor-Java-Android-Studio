package com.example.mywords;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {

    EditText txtWords ;
    TextView txtSize;
    Button btnBig;
    Button btnSmall;
    CheckBox cbxBold;
    CheckBox cbxUnder;
    Spinner sColor;
    Spinner sFont;
    RadioButton rdLTR;
    RadioButton rdRTL;
    EditText txtFileName;
    Button btnNew;
    Button btnSave;
    Button btnGetFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtWords = (EditText)findViewById(R.id.TxtMultiLine);
        txtSize = (TextView)findViewById(R.id.txtSize);
        btnBig = (Button)findViewById(R.id.btnBig);
        btnSmall = (Button)findViewById(R.id.btnSmall);
        cbxBold = (CheckBox)findViewById(R.id.checkBoxBold);
        cbxUnder = (CheckBox)findViewById(R.id.checkBoxUnder);
        sColor = (Spinner)findViewById(R.id.spinnerColor);
        sFont = (Spinner)findViewById(R.id.spinnerFont);
        rdLTR = (RadioButton)findViewById(R.id.radioButtonL);
        rdRTL = (RadioButton)findViewById(R.id.radioButtonR);
        txtFileName = (EditText)findViewById(R.id.txtFileName);
        btnNew = (Button)findViewById(R.id.btnNew);
        btnSave = (Button)findViewById(R.id.btnSave);
        btnGetFile = (Button)findViewById(R.id.btnGetFile);
        fillColors();
        fillFonts();
        cbxBold.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setBold();
            }
        });
        cbxUnder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setUnder();
            }
        });
      sColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              setTxtColor();
          }

          @Override
          public void onNothingSelected(AdapterView<?> parent) {
              setTxtColor();
          }
      });
      sFont.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              setTxtFont();
          }

          @Override
          public void onNothingSelected(AdapterView<?> parent) {
              setTxtFont();
          }
      });
      rdRTL.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              setAlign();
          }
      });
      rdLTR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              setAlign();
          }
      });
      btnNew.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              newFile();
          }
      });
      btnSave.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              saveFile();
          }
      });
      btnGetFile.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              getFile();
          }
      });
    }
    protected void fillColors(){
        String[] colors = {
                "black","red","blue","green","gray","orange","navy",
                "brown","pink","yellow"
        };
        ArrayAdapter<String> tt = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,colors);
        sColor.setAdapter(tt);
    }
    protected void fillFonts(){
        String[] fonts = {
                "sans-serif",
                "VLADIMIR",
                "arial",
                "ITCBLKAD",
                "SNAP____",
                "STENCIL",
                "VINERITC",
                "ALGER"
        };
        ArrayAdapter<String> tt = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,fonts);
        sFont.setAdapter(tt);
    }
   public void bigText(View v){
        int size = Integer.parseInt(txtSize.getText().toString());
        size++;
        txtSize.setText(size+"");
        txtWords.setTextSize(size);
   }
    public void smallText(View v){
        int size = Integer.parseInt(txtSize.getText().toString());
        size--;
        txtSize.setText(size+"");
        txtWords.setTextSize(size);
    }
    protected void setBold(){
setTxtFont();
    }
    protected void setUnder(){
        if(cbxUnder.isChecked()) txtWords.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        else txtWords.setPaintFlags(Paint.LINEAR_TEXT_FLAG);
        setBold();
    }
    protected void setTxtColor(){
        String couleur = sColor.getSelectedItem().toString();
        switch (couleur) {
            case "black" :
                txtWords.setTextColor(getResources().getColor(R.color.black));
                break;
                case "red" :
                    txtWords.setTextColor(getResources().getColor(R.color.red));
                break; case "blue" :
                txtWords.setTextColor(getResources().getColor(R.color.blue));
                break; case "green" :
                txtWords.setTextColor(getResources().getColor(R.color.green));
                break; case "gray" :
                txtWords.setTextColor(getResources().getColor(R.color.gray));
                break; case "orange" :
                txtWords.setTextColor(getResources().getColor(R.color.orange));
                break; case "navy" :
                txtWords.setTextColor(getResources().getColor(R.color.navy));
                break;
                case "brown" :
                    txtWords.setTextColor(getResources().getColor(R.color.brown));
                    break; case "pink" :
                txtWords.setTextColor(getResources().getColor(R.color.pink));
                break; case "yellow" :
                txtWords.setTextColor(getResources().getColor(R.color.yellow));
                break;
            default :
                txtWords.setTextColor(getResources().getColor(R.color.black));
                break;
        }
    }
    protected void setTxtFont(){
        String font = sFont.getSelectedItem().toString(); Typeface tf = Typeface.SANS_SERIF;
        switch (font) {
            case "sans-serif" :
                tf = Typeface.SANS_SERIF;
                break;
            case "VLADIMIR" :
                tf = Typeface.createFromAsset(getAssets(),"VLADIMIR.TTF");
                break;
            case "arial" :
                tf = Typeface.createFromAsset(getAssets(),"arial.ttf");
                break; case "ITCBLKAD" :
                tf = Typeface.createFromAsset(getAssets(),"ITCBLKAD.TTF");
                break; case "SNAP____" :
                tf = Typeface.createFromAsset(getAssets(),"SNAP____.TTF");
                break; case "STENCIL" :
                tf = Typeface.createFromAsset(getAssets(),"STENCIL.TTF");
                break; case "VINERITC" :
                tf = Typeface.createFromAsset(getAssets(),"VINERITC.TTF");
                break; case "ALGER" :
                tf = Typeface.createFromAsset(getAssets(),"ALGER.TTF");
                break;

        }
        if(cbxBold.isChecked())
        txtWords.setTypeface(tf,Typeface.BOLD);
        else
            txtWords.setTypeface(tf,Typeface.NORMAL);
    }
    protected void setAlign(){
        if (rdRTL.isChecked()) txtWords.setGravity(Gravity.RIGHT);
        else txtWords.setGravity(Gravity.LEFT);
    }
    protected void newFile(){
        txtWords.setText("");
        txtSize.setText("22");
        txtWords.setTextSize(22);
        cbxBold.setChecked(false);
        cbxUnder.setChecked(false);
        sColor.setSelection(0);
        sFont.setSelection(0);
        rdLTR.setChecked(true);
        txtFileName.setText("FileName");
        txtWords.requestFocus();
    }
    protected void saveFile(){
        if("".equals(txtFileName.getText().toString().trim())){
            Toast.makeText(this, "File name is empty !", Toast.LENGTH_SHORT).show();
            txtFileName.requestFocus();
        } else {try {
            String strPath =getExternalFilesDir(null).getAbsolutePath()+"/MyWords";
            File f = new File(strPath); f.mkdir();
            PrintWriter pw = new PrintWriter(strPath + "/" + txtFileName.getText() + ".txt");
                pw.write(txtWords.getText().toString()); pw.close();
                PrintWriter pwSet = new PrintWriter(strPath+"/"+txtFileName.getText()+"Set.txt");
                String strSet = txtSize.getText() +"\n"
                        +cbxBold.isChecked() +"\n"
                        +cbxUnder.isChecked() +"\n"
                        +sColor.getSelectedItem() +"\n"
                        +sFont.getSelectedItem() +"\n"
                        +rdLTR.isChecked() +"\n"
                        +rdRTL.isChecked();
                pwSet.write(strSet);
                pwSet.close();
            Toast.makeText(this, "File is saved", Toast.LENGTH_SHORT).show();

                       } catch (Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
            
        }
    }
    protected  void getFile(){
        try{
            String strPath = getExternalFilesDir(null).getAbsolutePath()+"/MyWords";
            FileReader fr = new FileReader(strPath+"/"+txtFileName.getText()+".txt");
            BufferedReader br = new BufferedReader(fr);
            String strCont ="" ;
            String strLine ="";
            while ((strLine=br.readLine()) != null){
                strCont+=strLine+"\n";
            }
            fr = new FileReader(strPath+"/"+txtFileName.getText()+"Set.txt");
            br = new BufferedReader(fr);
            String[] strSet = new String[7];
            int indice = 0;
            while ((strLine=br.readLine()) != null){
                strSet[indice]=strLine;
                indice++;
            }
            fr.close(); br.close();
            txtWords.setText(strCont);
            txtSize.setText(strSet[0]);
            txtWords.setTextSize(Integer.parseInt(strSet[0]));
            cbxBold.setChecked(Boolean.parseBoolean(strSet[1]));
            cbxUnder.setChecked(Boolean.parseBoolean(strSet[2]));
            sColor.setSelection(((ArrayAdapter<String>)(sColor.getAdapter())).getPosition(strSet[3]));
            sFont.setSelection(((ArrayAdapter<String>)(sFont.getAdapter())).getPosition(strSet[4]));
            if("true".equals(strSet[5])){
                rdLTR.setChecked(true);
            } else {rdRTL.setChecked(true);}




        }
        catch(Exception ex){
            Toast.makeText(this, ""+ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}