package com.example.cklicker;

import static android.R.*;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class record_frg extends Fragment {
    // интерфейс для связи фрагмента и активити
    private OnFragmentListener fragmentListener;
    // сдесь методы для их переопределения в активити, для отправки их фрагментом в активити
    interface OnFragmentListener
    {
        // события нажатие на кнопку назад
        void record_back();
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
        Button but = getView().findViewById(R.id.but);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentListener.record_back();
            }
        });

        return getView();
    }

    // метод для вызова его в активити, для отбражения рекордов
    void init(String[] records)
    {
        // следующие 4 строчки для вывод в ListView знасений заголовков
        ListView listView = getView().findViewById(R.id.List);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(),
                android.R.layout.simple_list_item_1, records);
        listView.setAdapter(adapter);
    }

}