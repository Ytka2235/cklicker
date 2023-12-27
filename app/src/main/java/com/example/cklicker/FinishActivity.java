package com.example.cklicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

// в назвинии класса мы подключаем интерфейс для связи с фрагментом
public class FinishActivity extends AppCompatActivity implements finish_frg.OnFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        // получение данных
        Bundle arguments = getIntent().getExtras();
        // получаем очки
        Integer score = arguments.getInt("score");
        // отправляем очки в фрагмент
        finish_frg frg = (finish_frg) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        frg.init(score);
    }

    // переопредёлленный метод через интерфейс для обработки нажатия на кнопку назад
    @Override
    public void ref() {
        // передаём мейн активити что делать дальше
        Intent intent = new Intent();
        intent.putExtra("fragment", 1);
        setResult(RESULT_OK,intent);
        finish(); // завершаем работу активити
    }

    // переопредёлленный метод через интерфейс для обработки нажатия на кнопку рекорды
    @Override
    public void record() {
        // передаём мейн активити что делать дальше
        Intent intent = new Intent();
        intent.putExtra("fragment", 3);
        setResult(RESULT_OK,intent);
        finish(); // завершаем работу активити
    }
}