package com.example.cklicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
// в назвинии класса мы подключаем интерфейс для связи с фрагментом
public class RecordsActivity extends AppCompatActivity implements record_frg.OnFragmentListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        // получение данных
        Bundle arguments = getIntent().getExtras();
        // получаем массив рекордов
        String[] records = arguments.getStringArray("records");
        // передаём массив рекордов фрагменту
        if(records.length >= 1) {
            record_frg fr = (record_frg) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
            fr.init(records);
        }
    }

    // переопредёлленный метод через интерфейс для обработки нажатия на кнопку назад
    @Override
    public void record_back() {
        Intent intent = new Intent();
        intent.putExtra("fragment", 0);
        setResult(RESULT_OK,intent);
        finish();
    }
}