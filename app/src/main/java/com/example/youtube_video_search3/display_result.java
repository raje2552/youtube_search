package com.example.youtube_video_search3;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.youtube_video_search3.databinding.FragmentDisplayResultBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link display_result#newInstance} factory method to
 * create an instance of this fragment.
 */
public class display_result extends Fragment {
        FragmentDisplayResultBinding binding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private  static final   String ARG_URL = "URL" ;
    private static final String ARG_Channel = "Channel";
    private  static final String ARG_MaxResult = "MaxResult" ;

    // TODO: Rename and change types of parameters
    private String mURL;
    private String mChannel;
    private String mMaxResult;

    public display_result() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static display_result newInstance(String URL, String Channel_ID, String max_result ) {
        display_result fragment = new display_result();
        Bundle args = new Bundle();
        System.out.println("/////////66776776 : "+URL + " " + Channel_ID + " " +max_result);
        args.putString(ARG_URL, URL);
        args.putString(ARG_Channel, Channel_ID);
        args.putString(ARG_MaxResult, max_result);
        System.out.println("/////234453 : "+args.getString(ARG_URL));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mURL = getArguments().getString(ARG_URL);
            mChannel = getArguments().getString(ARG_Channel);
            mMaxResult = getArguments().getString(ARG_MaxResult);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDisplayResultBinding.inflate(inflater , container ,false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.button.setOnClickListener(v -> {
            if(isConnectionNetwork()){
                System.out.println("result ERWERE : "+binding.serarch.getText().toString());
                RecyclerView recyclerView = view.findViewById(R.id.recyclerView);


                  new handleAPI_Youtube(mURL,  // URL
                        binding.serarch.getText().toString().trim(),                    // query
                        mMaxResult,                                                     // max result
                        getActivity().getApplicationContext(),                          //context
                        recyclerView )                                                  //recyclerView
                        .execute();



            }else{
                Toast.makeText(getActivity().getApplicationContext(),
                        "message Network0453 : not connect network , please connection internet",
                        Toast.LENGTH_SHORT).show();
            }
        });




    }

    /**
     * this code work check if the device is connection in network if the device is connection
     * with internet return true ,otherwise return false and not receive parameter
     * */

    private boolean isConnectionNetwork (){
        ConnectivityManager Cm = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo NI = null;
        if (Cm != null)
            NI = Cm.getActiveNetworkInfo();

        return NI != null && NI.isConnected();
    }

}