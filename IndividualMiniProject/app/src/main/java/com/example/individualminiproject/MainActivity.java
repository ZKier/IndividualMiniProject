package com.example.individualminiproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private final int REQUEST_FOR_GALLERY = 1000;
    Button button;
    TextView textView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button);
        textView = (TextView)findViewById(R.id.textView);
        imageView = (ImageView)findViewById(R.id.imageView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 3); //just typed 3 not "requestCode:3"

                //ImageView imageView = (ImageView) getActivity().findViewById(R.id.imageView);
                //imageView.setImageBitmap(BitmapFactory.decodeFile(pathToPicture));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData(); //Uniform resource identifier points to the data you want to access

            //imageView.setImageURI(selectedImage);
            useImage(selectedImage);

            String txtPath = selectedImage.getPath();
            textView.setText(pickRandomText() + "\n" + txtPath);
            textView.setGravity(Gravity.CENTER);
        }
    }

    void useImage(Uri uri)
    {
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        imageView.setImageBitmap(bitmap);
    }

    String pickRandomText() {
        String[] list = {"Dogs are better than cats.",
                "Cats are better than dogs.",
                "Apple Juice or Orange Juice?",
                "What's your favorite color?",
                "Love Wins - Hetero - Hetero - Hetero",
                "Can I be Adam and you be Eve",
                "LOL I'm really out here a Computer Science Major"}; // 7 choices

        int max = 6;
        int min = 0;
        double randomNumberChoice = Math.floor(Math.random() *(max - min + 1) + min);

        return list[(int)randomNumberChoice];
    }
}