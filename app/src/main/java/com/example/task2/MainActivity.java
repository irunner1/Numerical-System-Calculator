package com.example.task2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.math.BigInteger;

import static java.lang.Math.pow;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txtRes;
    private EditText inputNum;
    public String inputSysFrom;
    private String inputSysTo;
    public int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        Button btnConvert = (Button) findViewById(R.id.btnConvert);
        txtRes = (TextView) findViewById(R.id.txtRes);
        inputNum = (EditText) findViewById(R.id.inputNum);

        Spinner firstSpinner = (Spinner) findViewById(R.id.spinner1);
        Spinner secondSpinner = (Spinner) findViewById(R.id.spinner2);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        firstSpinner.setAdapter(myAdapter);
        secondSpinner.setAdapter(myAdapter);

        firstSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                inputSysFrom = adapterView.getSelectedItem().toString();
                int sysFrom = Integer.parseInt(inputSysFrom);

                findViewById(R.id.btnD).setEnabled(true);
                findViewById(R.id.btnE).setEnabled(true);
                findViewById(R.id.btnF).setEnabled(true);
                findViewById(R.id.btnA).setEnabled(true);
                findViewById(R.id.btnB).setEnabled(true);
                findViewById(R.id.btnC).setEnabled(true);
                findViewById(R.id.btn2).setEnabled(true);
                findViewById(R.id.btn3).setEnabled(true);
                findViewById(R.id.btn4).setEnabled(true);
                findViewById(R.id.btn5).setEnabled(true);
                findViewById(R.id.btn6).setEnabled(true);
                findViewById(R.id.btn7).setEnabled(true);
                findViewById(R.id.btn8).setEnabled(true);
                findViewById(R.id.btn9).setEnabled(true);
                
                if (sysFrom == 2) {
                    findViewById(R.id.btnD).setEnabled(false);
                    findViewById(R.id.btnE).setEnabled(false);
                    findViewById(R.id.btnF).setEnabled(false);
                    findViewById(R.id.btnA).setEnabled(false);
                    findViewById(R.id.btnB).setEnabled(false);
                    findViewById(R.id.btnC).setEnabled(false);
                    findViewById(R.id.btn2).setEnabled(false);
                    findViewById(R.id.btn3).setEnabled(false);
                    findViewById(R.id.btn4).setEnabled(false);
                    findViewById(R.id.btn5).setEnabled(false);
                    findViewById(R.id.btn6).setEnabled(false);
                    findViewById(R.id.btn7).setEnabled(false);
                    findViewById(R.id.btn8).setEnabled(false);
                    findViewById(R.id.btn9).setEnabled(false);
                }
                else if (sysFrom == 8) {
                    findViewById(R.id.btnD).setEnabled(false);
                    findViewById(R.id.btnE).setEnabled(false);
                    findViewById(R.id.btnF).setEnabled(false);
                    findViewById(R.id.btnA).setEnabled(false);
                    findViewById(R.id.btnB).setEnabled(false);
                    findViewById(R.id.btnC).setEnabled(false);
                    findViewById(R.id.btn8).setEnabled(false);
                    findViewById(R.id.btn9).setEnabled(false);

                }
                else if (sysFrom == 10) {
                    findViewById(R.id.btnD).setEnabled(false);
                    findViewById(R.id.btnE).setEnabled(false);
                    findViewById(R.id.btnF).setEnabled(false);
                    findViewById(R.id.btnA).setEnabled(false);
                    findViewById(R.id.btnB).setEnabled(false);
                    findViewById(R.id.btnC).setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        secondSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                inputSysTo = adapterView.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        btnConvert.setOnClickListener(this);
    }

    public void onNumberClick(View view){
        Button button = (Button)view;

        if (inputNum.length() == 0) { //проверка на 0 позицию
            position = 0;
        }
        if (position > 1) { //для всех позиций
            inputNum.append(button.getText());
            position++;
        }
        if (position == 1 && button.getText().toString().equals("0")) { //если второй элемент и кнопка 0
            if (inputNum.getText().toString().equals("0")) { // если первый элемент 0
                inputNum.setText("0");
            }
            else { // если первый элемент не 0
                inputNum.append(button.getText());
            }
        }
        if (position == 1 && !button.getText().toString().equals("0")) { //если второй элемент и кнопка не 0
            if (inputNum.getText().toString().equals("0")) {
                inputNum.setText(button.getText().toString());
            }
            else {
                inputNum.append(button.getText());
                position++;
            }
        }
        if (position == 0) {
            inputNum.append(button.getText());
            position++;
        }
    }

    public void onSymbolClick(View view) {
        Button button = (Button)view;
        String op = button.getText().toString();
        String number = inputNum.getText().toString();
        String r = number + op;
        inputNum.setText(r);
    }

    String deleteCharacters(String str, int from, int to) {
        return str.substring(0,from)+str.substring(to);
    }

    public void onDeleteClick(View view) {
        if (inputNum.length() > 0) {
            String op = inputNum.getText().toString();
            op = deleteCharacters(op, op.length() - 1, op.length());
            inputNum.setText(op);
            position--;
        }
        if (inputNum.length() == 0) {
            position = 0;
        }
    }

    public String twoToTen(int from, int to, String num) {
        long number;
        number = Long.parseLong(num, from);
        return Long.toString(number, to);
    }

    @Override
    public void onClick(View v) {
        int sysTo = Integer.parseInt(inputSysTo);
        int sysFrom = Integer.parseInt(inputSysFrom);
        txtRes.setText(twoToTen(sysFrom, sysTo, inputNum.getText().toString()));
    }
}
