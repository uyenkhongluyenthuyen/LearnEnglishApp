package com.example.learnenglishapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.learnenglishapp.activity.AddWordActivity;
import com.example.learnenglishapp.R;
import com.example.learnenglishapp.activity.HocTuVungActivity;
import com.example.learnenglishapp.activity.SapXepCauActivity;
import com.example.learnenglishapp.activity.TracNghiemActivity;
import com.example.learnenglishapp.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private CardView cardViewTracNghiem, cardViewHocTuVung, cardViewSapXepCau, cardViewThemTuMoi;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        //anh xa
        cardViewHocTuVung= root.findViewById(R.id.cardViewHocTuVung);
        cardViewTracNghiem= root.findViewById(R.id.cardViewTracNghiem);
        cardViewSapXepCau= root.findViewById(R.id.cardViewSapXepCau);
        cardViewThemTuMoi= root.findViewById(R.id.cardViewThemTuMoi);

        //xu li su kien
        cardViewHocTuVung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HocTuVungActivity.class);
                startActivity(intent);
            }
        });
        cardViewTracNghiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TracNghiemActivity.class);
                startActivity(intent);

            }
        });
        cardViewSapXepCau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SapXepCauActivity.class);
                startActivity(intent);

            }
        });
        cardViewThemTuMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddWordActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}