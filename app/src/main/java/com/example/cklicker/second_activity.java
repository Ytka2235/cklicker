package com.example.cklicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
// это активити начального окна
// в назвинии класса мы подключаем интерфейс для связи с фрагментом
public class second_activity extends AppCompatActivity implements start_frg.OnFragmentListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }
    // переопредёлленный метод через интерфейс для обработки нажатия на кнопку старт
    @Override
    public void start_start() {
        Intent intent = new Intent();
        intent.putExtra("fragment", 1);
        setResult(RESULT_OK,intent);
        finish();
    }
    // переопредёлленный метод через интерфейс для обработки нажатия на кнопку рекорды
    @Override
    public void start_records() {
        Intent intent = new Intent();
        intent.putExtra("fragment", 3);
        setResult(RESULT_OK,intent);
        finish();
    }
}