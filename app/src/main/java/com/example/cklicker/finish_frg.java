package com.example.cklicker;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;




public class finish_frg extends Fragment {
    // интерфейс для связи фрагмента и активити
    private OnFragmentListener fragmentListener;

    // сдесь методы для их переопределения в активити, для отправки их фрагментом в активити
    interface OnFragmentListener
    {
        // событие нажатие на кнопку для открытия начального экрана
        void ref();
        // событие нажатие на кнопку для открытия таблицы рекордов
        void record();
    }
    // нужно для работы интерфейса, что-то с контекстом
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            fragmentListener = (OnFragmentListener) context;
        } catch (ClassCastException e) {}
    }

    // нужно для работы интерфейса, что-то с контекстом
    public finish_frg()
    {
        super(R.layout.fragment_finish_frg);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // определение слушателей для кнопок
        Button but = getView().findViewById(R.id.ref);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentListener.ref();
            }
        });
        but = getView().findViewById(R.id.record);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentListener.record();
            }
        });
        return getView();
    }

    // метод для вызова его в активити, для отображение очков
    public void init(Integer score)
    {
        TextView txt = getView().findViewById(R.id.score);
        txt.setText("" + score);
    }

}