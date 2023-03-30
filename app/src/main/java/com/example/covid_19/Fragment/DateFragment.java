package com.example.covid_19.Fragment;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.covid_19.R;

import java.util.Calendar;

public class DateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    //khi đc forcus thì nó xuất hiện 1 digalog date
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int Nam = calendar.get(Calendar.YEAR);
        int Thang = calendar.get(Calendar.MONTH);
        int Ngay = calendar.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(),this,Ngay,Thang,Nam); //fragment dc add vào activity
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        EditText edNgaySinh = getActivity().findViewById(R.id.edNgaySinhDK);
        String NgaySinh = dayOfMonth+"/"+(month+1)+"/"+year;
        edNgaySinh.setText(NgaySinh);
    }
}
