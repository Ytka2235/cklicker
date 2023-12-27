package com.example.cklicker;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class start_frg extends Fragment {
    // интерфейс для связи фрагмента и активити
    private OnFragmentListener fragmentListener;
    // сдесь методы для их переопределения в активити, для отправки их фрагментом в активити
    interface OnFragmentListener
    {
        // события нажатие на кнопку старт
        void start_start();
        // события нажатие на кнопку рекорды
        void start_records();
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
        Button but = getView().findViewById(R.id.start);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentListener.start_start();
            }
        });
        but = getView().findViewById(R.id.records);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentListener.start_records();
            }
        });

        return getView();
    }
}