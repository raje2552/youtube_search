package com.example.youtube_video_search3;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.youtube_video_search3.databinding.InformationPageBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link information_page#newInstance} factory method to
 * create an instance of this fragment.
 */
public class information_page extends Fragment {

    InformationPageBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public information_page() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment information_page.
     */
    // TODO: Rename and change types and number of parameters
    public static information_page newInstance(String param1, String param2) {
        information_page fragment = new information_page();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = InformationPageBinding.inflate(inflater , container , false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.URLEdText.getText().toString().isEmpty()
                        && !binding.channelIdEdText.getText().toString().isEmpty()) {
                        if (!binding.maxRESULTSEdText.getText().toString().isEmpty()) {
                            int max_Res = Integer.parseInt(binding.maxRESULTSEdText.getText().toString());
                            if (max_Res != 0 ) {
                                display_result DR = display_result.newInstance(
                                        binding.URLEdText.getText().toString(),
                                        binding.channelIdEdText.getText().toString() ,
                                        binding.maxRESULTSEdText.getText().toString());

                                getActivity().getSupportFragmentManager()
                                        .beginTransaction().replace(R.id.fragment56, DR).addToBackStack(null).commit();
                            }
                        }
                }else {
                    Toast.makeText(getActivity().getApplicationContext(),
                                    "check then inputs the existing ",
                                    Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }
    ;
}