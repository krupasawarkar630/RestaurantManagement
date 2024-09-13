package com.android.restaurant_management;

import static android.app.Activity.RESULT_OK;
import static android.widget.ImageView.*;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Locale;

public class HomeFragment extends Fragment {

    VideoView videoView;
    CardView cvTea,cvCoffee,cvJuice,cvBreakFast,cvLunch,cvDinner;
    ImageSlider imageSlider;
    LinearLayout llDesserts;
    ImageView ivMic;
    EditText etSearch;
    TextToSpeech textToSpeech;
    private static final int REQUEST_CODE_SPEECH_INPUT=1;

    @SuppressLint("MissingInflatedId")
    @Override
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater,@NotNull ViewGroup container,
                            @NotNull Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.fragment_home, container, false);

        llDesserts=view.findViewById(R.id.llDesserts);

        videoView = view.findViewById(R.id.videoview);
        imageSlider=view.findViewById(R.id.ImageSlider);
        cvTea=view.findViewById(R.id.cvTea);
        cvCoffee=view.findViewById(R.id.cvCoffee);
        cvJuice=view.findViewById(R.id.cvJuice);
        cvBreakFast=view.findViewById(R.id.cvBreakFast);
        cvLunch=view.findViewById(R.id.cvLunch);
        cvDinner=view.findViewById(R.id.cvDinner);

        ivMic=view.findViewById(R.id.ivMic);
        etSearch=view.findViewById(R.id.etSearch);

        ivMic.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                i.putExtra(RecognizerIntent.EXTRA_PROMPT,"Search Your Desired Foods");
                try {
                    startActivityForResult(i, REQUEST_CODE_SPEECH_INPUT);
                }catch (Exception e){
                    Toast.makeText(getActivity(),""+e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
        String videoPath = "android.resource://" + getActivity().getPackageName()
                + "/" + R.raw.videooffer;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(getActivity());
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        videoView.start();

        ArrayList<SlideModel> slideModels=new ArrayList<>();
        boolean add = slideModels.add(new SlideModel(R.drawable.indianfoodoffer, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.burgeroffer, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.pizzaoffer, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.offer, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels,ScaleTypes.FIT);
        imageSlider.setImageList(slideModels);

        imageSlider.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemSelected(int i) {
                Toast.makeText(getActivity(), "Clicked On "+(i+1), Toast.LENGTH_SHORT).show();
                Intent i1 = new Intent(getActivity(), OfferActivity.class);
                startActivity(i1);
            }
            @Override
            public void doubleClick(int i) {
            }
        });

        cvTea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getActivity(), TeaActivity.class);
                startActivity(i);
            }
        });

        cvCoffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getActivity(), CoffeeActivity.class);
                startActivity(i);
            }
        });

        cvJuice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getActivity(), JuiceActivity.class);
                startActivity(i);
            }
        });

        cvBreakFast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getActivity(), BreakFastActivity.class);
                startActivity(i);
            }
        });

        cvLunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getActivity(),LunchActivity.class);
                startActivity(i);
            }
        });

        cvDinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getActivity(), DinnerActivity.class);
                startActivity(i);
            }
        });

        llDesserts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getActivity(), DessertsActivity.class);
                startActivity(i);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE_SPEECH_INPUT) {
            if(resultCode==RESULT_OK && data!=null){
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                etSearch.setText(result.get(0));

        textToSpeech = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    textToSpeech.setLanguage(Locale.ENGLISH);
                    textToSpeech.speak(result.get(0),TextToSpeech.QUEUE_FLUSH,null,null);
                }
            }
        });

            }
        }
    }
}