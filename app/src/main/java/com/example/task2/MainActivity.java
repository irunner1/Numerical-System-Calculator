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
    private Button btnConvert;
    private TextView txtRes;
    private EditText inputNum;
    public String inputSysFrom;
    private String inputSysTo;
    private EditText txt;
    public int position = 0;
    private boolean pointCounter = false;
    public String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        btnConvert = (Button) findViewById(R.id.btnConvert);
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

    public void symClick(View view) {
        Button button = (Button)view;
        if (position > 0) {
            if (!pointCounter) {
                inputNum.setText(inputNum.getText() + ".");
                pointCounter = true;
            }
            else inputNum.setText(inputNum.getText() + "");
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
            if (op.indexOf(".") != -1) {
                pointCounter = false;
            }
            op = deleteCharacters(op, op.length() - 1, op.length());
            inputNum.setText(op);
            position--;
        }
        if (inputNum.length() == 0) {
            position = 0;
        }
    }

    public int TMP(int Value, int System) {
        int a = Value, tmp = 0, b;
        while (a > System - 1) {
            b = a % System;
            tmp++;
            a /= System;
        }
        tmp++;
        return tmp;
    }

    public int SysConTo10(char str[], int sys) {
        int k, res = 0, st = 0;
        int len = str.length;
        for (int i = len - 1; i >= 0; i--) {
            switch (str[i]) {
                case 'A': k = 10;
                    break;
                case 'B': k = 11;
                    break;
                case 'C': k = 12;
                    break;
                case 'D': k = 13;
                    break;
                case 'E': k = 14;
                    break;
                case 'F': k = 15;
                    break;
                default: k = (int)str[i];
                    break;
            }
            if (k >= 48) {
                k -= 48;
            }
            if (k >= sys) {
                break;
            }
            else if (k != sys) {
                res += pow(sys, st) * k;
                st++;
            }
        }
        return res;
    }


    public static String translation(String str) {
        switch (str) {
            case "10": {
                str = "A";
                break;
            }
            case "11": {
                str = "B";
                break;
            }
            case "12": {
                str = "C";
                break;
            }
            case "13": {
                str = "D";
                break;
            }
            case "14": {
                str = "E";
                break;
            }
            case "15": {
                str = "F";
                break;
            }
            default: { break; }
        }
        return str;
    }

    public void transition(float a, int sysTo) {
        int left = (int) a;
        boolean divideOneMoreTime = left >= 1;
        String bin = "", tmp = "", res = "";
        while (divideOneMoreTime) {
            bin = left % sysTo + bin;
            if (sysTo == 16) {
                tmp += left % 16;
                res = translation(tmp) + res;
                tmp = "";
            }

            left /= sysTo;
            if (left < 1) {
                divideOneMoreTime = false;
            }
        }
        bin += ".";
        res += ".";

        float right = (float) a - (int) a;
        for (int i = 0; i < 20; i++) {
            right = right * sysTo - (int) right * sysTo;
            bin = bin + (int) right;
            if (sysTo == 16) {
                tmp += (int) right;
                res = res + translation(tmp);
                tmp = "";
            }
            if (right == 1.0) {
                break;
            }
        }
        if (bin.length() > 10) bin = bin.substring(0, 10);
        if (res.length() > 10) res = res.substring(0, 10);
        result = bin;
        txtRes.setText(bin);
        if (sysTo == 16) {
            txtRes.setText(res);
        }
    }
    public void transitionTo10(float a, int sysFrom) {
        int left = (int) a;
        int k, r = 0, st = 0;
        boolean divideOneMoreTime = left >= 1;
        String bin = "", tmp = "", res = "";

        while (divideOneMoreTime) {
            res += pow(sysFrom, st) * a;
            st++;

            if (left < 1) {
                divideOneMoreTime = false;
            }
        }
        bin += ".";

        float right = (float) a - (int) a;
        for (int i = 0; i < 20; i++) {
            res += pow(sysFrom, st) * a;
            st--;

            if (right == 1.0) {
                break;
            }
        }
        if (bin.length() > 10) bin = bin.substring(0, 10);
        result = bin;
        txtRes.setText(bin);

    }

    @Override
    public void onClick(View v) {
        int sysTo = Integer.parseInt(inputSysTo);
        int sysFrom = Integer.parseInt(inputSysFrom);

        if (inputNum.length() > 0) {
            if (inputNum.getText().toString().contains(".")) {
                if (sysFrom == sysTo) {
                    txtRes.setText(inputNum.getText().toString());
                }
                else {
                    if (sysTo == 2) {
                        float a = Float.parseFloat(inputNum.getText().toString());
                        transition(a, sysTo);
                    }///

                    if (sysTo == 8) {
                        float a = Float.parseFloat(inputNum.getText().toString());
                        transition(a, sysTo);
                    }
                    if (sysTo == 10) {
//                        if (sysFrom == 2) {
//
//                        }
                        float a = Float.parseFloat(inputNum.getText().toString());
                        transition(a, sysTo);
                    }
                    if (sysTo == 16) {
                        float a = Float.parseFloat(inputNum.getText().toString());
                        transition(a, sysTo);
                    }
                }

            }
            else {
                if (sysTo == 2) {
                    if (sysFrom == 2) {
                        result = inputNum.getText().toString();
                    }
                    else {
                        int num = Integer.parseInt(inputNum.getText().toString());
                        result = Integer.toBinaryString(num);
                    }
                    txtRes.setText(result);
                }
                else {
                    int res = 0;
                    String str = inputNum.getText().toString();
                    char[] val = str.toCharArray();
                    String tm = "";

                    res = SysConTo10(val, sysFrom);

                    int tmp2 = TMP(res, sysTo);
                    for (int i = 0; i < tmp2; i++) {
                        int b = res % sysTo;
                        switch (b) {
                            case 10: {
                                val[i] = 'A';
                                res /= sysTo;
                            }
                            break;
                            case 11: {
                                val[i] = 'B';
                                res /= sysTo;
                            }
                            break;
                            case 12: {
                                val[i] = 'C';
                                res /= sysTo;
                            }
                            break;
                            case 13: {
                                val[i] = 'D';
                                res /= sysTo;
                            }
                            break;
                            case 14: {
                                val[i] = 'E';
                                res /= sysTo;
                            }
                            break;
                            case 15: {
                                val[i] = 'F';
                                res /= sysTo;
                            }
                            break;
                            default: {
                                if (b < 48) {
                                    b += 48;
                                }
                                val[i] = (char) b;
                                res /= sysTo;
                            }
                            break;
                        }
                    }
                    for (int i = tmp2 - 1; i > -1; i--) {
                        tm += val[i];
                    }
                    txtRes.setText(tm);
                }
            }
        }
    }
}
