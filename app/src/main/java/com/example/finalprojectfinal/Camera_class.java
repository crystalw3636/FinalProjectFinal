package com.example.finalprojectfinal;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finalprojectfinal.Helper.GraphicOverlay;
import com.example.finalprojectfinal.Helper.TextGraphic;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.cloud.FirebaseVisionCloudDetectorOptions;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionCloudTextRecognizerOptions;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;
import com.wonderkiln.camerakit.CameraKitError;
import com.wonderkiln.camerakit.CameraKitEvent;
import com.wonderkiln.camerakit.CameraKitEventListener;
import com.wonderkiln.camerakit.CameraKitImage;
import com.wonderkiln.camerakit.CameraKitVideo;
import com.wonderkiln.camerakit.CameraView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import dmax.dialog.SpotsDialog;


public class Camera_class extends AppCompatActivity {

    CameraView cameraView;
    GraphicOverlay graphicOverlay;
    AlertDialog waitingDialog;
    Button btnCapture;

    @Override
    protected void onResume() {
        super.onResume();
        cameraView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        cameraView.stop();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_class);

        waitingDialog = new SpotsDialog.Builder()
                .setCancelable(false)
                .setMessage("Please wait")
                .setContext(this)
                .build();


        cameraView = (CameraView) findViewById(R.id.camera_view);
        graphicOverlay = (GraphicOverlay) findViewById(R.id.graphic_overlay);
        btnCapture = (Button) findViewById(R.id.btn_capture);
        btnCapture.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               cameraView.start();
               cameraView.captureImage();
               graphicOverlay.clear();
           }
        });

        cameraView.addCameraKitListener(new CameraKitEventListener() {
            @Override
            public void onEvent(CameraKitEvent cameraKitEvent) {

            }

            @Override
            public void onError(CameraKitError cameraKitError) {

            }

            @Override
            public void onImage(CameraKitImage cameraKitImage) {

                waitingDialog.show();
                Bitmap bitmap = cameraKitImage.getBitmap();
                bitmap = Bitmap.createScaledBitmap(bitmap, cameraView.getWidth(), cameraView.getHeight(), false);

                cameraView.stop();


                recognizeText(bitmap);

            }

            @Override
            public void onVideo(CameraKitVideo cameraKitVideo) {

            }
        });


    }

    private void recognizeText(Bitmap bitmap) {
        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(bitmap);

        FirebaseVisionCloudTextRecognizerOptions options = new FirebaseVisionCloudTextRecognizerOptions.Builder()
                .setLanguageHints(Arrays.asList("en"))
                .build();
        FirebaseVisionTextRecognizer textRecognizer = FirebaseVision.getInstance()
                .getCloudTextRecognizer(options);

        textRecognizer.processImage(image)
                .addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                    @Override
                    public void onSuccess(FirebaseVisionText firebaseVisionText) {
                        drawTextResult(firebaseVisionText);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("ERROR", e.getMessage());
            }
        });
    }

    private void drawTextResult(FirebaseVisionText firebaseVisionText) {
        List<FirebaseVisionText.TextBlock> blocks = firebaseVisionText.getTextBlocks();

        if (blocks.size() == 0) {
            Toast.makeText(this, "No Text Found", Toast.LENGTH_SHORT).show();
            return;
        }
        graphicOverlay.clear();
        HashMap<String, List<FirebaseVisionText.Element>> map = new HashMap<>();


        for (int i = 0; i < blocks.size(); i++) {
            List<FirebaseVisionText.Line> lines = blocks.get(i).getLines();

            for (int j = 0; j < lines.size(); j++) {
                List<FirebaseVisionText.Element> elements = lines.get(j).getElements();

                String name = lines.get(j).getElements().get(0).getText().toLowerCase();
                map.put(name, lines.get(j).getElements());


                for (int k = 0; k < elements.size(); k++) {
                    TextGraphic textGraphic = new TextGraphic(graphicOverlay, elements.get(k));
                    graphicOverlay.add(textGraphic);
                }

            }
        }
        waitingDialog.dismiss();
        String subtotal = map.get("subtotal").get(map.get("subtotal").size() - 1).getText();
        String tax = map.get("tax").get(map.get("tax").size() - 1).getText();
        double s = Double.parseDouble(subtotal);
        double t = Double.parseDouble(tax);
        Intent blah = new Intent(Camera_class.this, MainActivity.class);
        blah.putExtra("subtotal", s);
        blah.putExtra("tax", t);
        finish();
        startActivity(blah);


    }
}
