package com.example.cklicker;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Size;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

// в назвинии класса мы подключаем интерфейс для связи с фрагментом
public class MainActivity extends AppCompatActivity implements  clicker_fr.OnFragmentListener {

    // лист где храняться рекорды
    public List<Integer> records = new ArrayList<>();
    // время для кликера
    public static Integer Time;
    // набранные очки
    public Integer score;
    // настройки
    public SharedPreferences setting;
    // таймер
    CountDownTimer timer;

    // этод код будет выполняться пры возврате с дочерней активити
    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    // принимаем из дочерней активити информацию что дальше должно делать приложение
                    if(result.getResultCode() == Activity.RESULT_OK)
                    {
                        Intent intent = result.getData();
                        Integer frg = intent.getIntExtra("fragment",0);
                        if(frg == 0)
                        {
                            // перейти в начальную активити
                            frg_start();
                        }
                        if(frg == 1)
                        {
                            //запустить кликер
                            start();
                        }
                        if(frg == 3)
                        {
                            // перейти в активити с рекордами
                            frg_records();
                        }
                    }
                }
            });



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Инициализируем настройки
        setting = getSharedPreferences("setting", Context.MODE_PRIVATE);
        frg_start(); // вызываем метод для запуска дочерней активити с начальным экраном
    }

    // сохраняем рекорды в массиве каждый раз когда активити ставиться на паузу
    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor editor = setting.edit();
        editor.putInt("size", records.size()); //пишем размер массива
        for(int i=0; i < records.size(); i++)
            editor.putInt("record"+i, records.get(i)); //складываем элементы массива
        editor.commit();

    }

    // полуаем массив рекрдов из настроек каждый раз когда активити возобновляет свою работу
    @Override
    protected void onResume() {
        records.clear();
        if(setting != null) {
            int size = setting.getInt("size", 0);
            if (size != 0) {
                for (int i = 0; i < size; i++) {
                    records.add(setting.getInt("record" + 1, 0));
                }
            }
            else records.add(0);
        }
        super.onResume();
    }

    // метод для старта кликера
    public void start() {
        // сбрасываем значения в активити и в фрагменте
        clicker_fr frg = (clicker_fr) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        score = 0;
        frg.setScore(score);
        Time = 0;
        frg.setTime(Time);
        // создаем свой класс потока он нужен для перехода в дочернию активити после завершения таймера
        // если не использовать поток, а вызвать метод для перехода на дочернию активити в таймере то вылезает исключение
        class myThread extends Thread
        {
            @Override
            public void run() {
                super.run();
                while (true) // бесконечный цикл для ожидания определ времени
                {
                    // поток проверяет закончилось ли время
                    if(Time == 30) {
                        timer.cancel(); // закрываем таймер
                        frg_finish();  // и вызываем методьдля перехода в дочернию активити
                        break; // выходим из бесконечнико цикла и завершаем поток
                    }
                }
            }
        }
        // создаём таймер вводим врем окончания и время тика
        timer = new CountDownTimer(30000, 1000) {
            // метод выполняется каждый тик
            @Override
            public void onTick(long time) {
                // каждый тик изменяем время на один в активити и фрагменте
                clicker_fr frg = (clicker_fr) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
                Time++;
                frg.setTime(Time);
            }
            // метод выполняется один раз когда кончается время, но у нас он пустой
            @Override
            public void onFinish() {

            }
        }.start(); // запуск таймера
        myThread th = new myThread(); // создаем экземпляр своего потока
        th.start(); // запускаем  свой поток
    }

    // переопредёлленный метод через интерфейс для обработки нажатия на кнопку
    @Override
    public void click() {
        score++;
        clicker_fr frg = (clicker_fr) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        frg.setScore(score);
    }

    // метод для запуска дочерней активити где выводиться результат
    public void frg_finish()
    {
        // ставим набранный результат в массиве на своё место по убыванию
        if(records.size() != 0 )
        {
            for (int i = 0; i < records.size(); i++) {
                if (score > records.get(i)) {
                    records.add(i, score);
                    break;
                }
            }
        }
        else {records.add(score);}
        // создаём намерение для перехода в дочернию активити
        Intent intent = new Intent(this, FinishActivity.class);
        intent.putExtra("score",score); // через намерение отправляем набранный результат
        mStartForResult.launch(intent);
    }

    // метод для запуска дочернего активити с начальным экраном
    public void frg_start()
    {
        Intent intent = new Intent(this, second_activity.class);
        mStartForResult.launch(intent);
    }

    // метод для запуска дочернего активити с рекордами
    public void frg_records()
    {
        Intent intent = new Intent(this, RecordsActivity.class);
        if(records.size()!=0) {
            // с помощью метода toArray переводим из листа в массив чисел
            Integer[] reci = records.toArray(new Integer[records.size()]);
            // и переводим из масива чисел в массив строк
            String[] rec = new String[reci.length];
            for(int i = 0; i < records.size();i++)
                rec[i] = "" + reci[i];
            intent.putExtra("records", rec); // отправляем массив строк с рекордами
        }
        mStartForResult.launch(intent);
    }
}