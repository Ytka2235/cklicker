package com.example.cklicker;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class clicker_fr extends Fragment {
    // интерфейс для связи фрагмента и активити
    private OnFragmentListener fragmentListener;

    // сдесь методы для их переопределения в активити, для отправки их фрагментом в активити
    interface OnFragmentListener
    {
        // события нажатие на кнопку
        void click();
    }

    // нужно для работы интерфейса, что-то с контекстом
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            fragmentListener = (OnFragmentListener) context;
        } catch (ClassCastException e) {}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // определяем слушатель для кнопки в фрагменте
        Button but = getView().findViewById(R.id.button);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentListener.click();
            }
        });
        return getView();
    }

    // метод для вызова его в активити, для изменения отбражения количество очков
    public void setScore(int score)
    {
        TextView txt = getView().findViewById(R.id.score);
        txt.setText("" + score);
    }

    // метод для вызова его в активити, для изменения отбражения времени
    public void setTime(int time)
    {
        TextView txt = getView().findViewById(R.id.time);
        txt.setText("" + time);
    }
}