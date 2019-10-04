package com.example.mdbpurchases2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class AddActivity extends AppCompatActivity {

    EditText etCost;
    EditText etSupplier;
    EditText etDesc;
    Button btnDone;
    ImageView photo;
    Button btnPhoto;
    Uri photoUri;
    Purchase p;
    String photoByte;

    public final static int PICK_PHOTO_CODE = 1046;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        etCost = findViewById(R.id.etCost);
        etSupplier = findViewById(R.id.etSupplier);
        etDesc = findViewById(R.id.etDesc);
        btnDone = findViewById(R.id.btnDone);
        btnPhoto = findViewById(R.id.btnPhoto);
        photo = findViewById(R.id.ivPhoto);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (photoByte == null || etCost.getText().toString() == "" || etSupplier.getText().toString() == "" || etDesc.getText().toString() == "" ) {
                    Toast.makeText(AddActivity.this, "Please attach a photo and fill out all the boxes!", Toast.LENGTH_LONG).show();
                } else {
                    p = new Purchase(etCost.getText().toString(), etDesc.getText().toString(), etSupplier.getText().toString(), photoByte, System.currentTimeMillis());
                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                    mDatabase.child("purchases").child(((Integer) p.hashCode()).toString()).setValue(p).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(AddActivity.this, "success!", Toast.LENGTH_LONG).show();
                        }
                    });
                    Intent intent = new Intent(AddActivity.this, PurchaseCards.class);
                    startActivity(intent);
                }
            }
        });

        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPickPhoto(view);
            }
        });

    }

    public void onPickPhoto(View view) {
        // Create intent for picking a photo from the gallery
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
        // So as long as the result is not null, it's safe to use the intent.
        if (intent.resolveActivity(getApplicationContext().getPackageManager()) != null) {
            // Bring up gallery to select a photo
            startActivityForResult(intent, PICK_PHOTO_CODE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            photoUri = data.getData();
            // Do something with the photo based on Uri
            Bitmap selectedImage = null;
            try {
                selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoUri);
//                selectedImage = getCorrectlyOrientedImage(getContext(), photoUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Load the selected image into a preview
            Glide.with(getApplicationContext()).load(photoUri).into(photo);
            photoByte = photoUri.toString();
        }
    }

    public static int getOrientation(Context context, Uri photoUri) {
        /* it's on the external media. */
        Cursor cursor = context.getContentResolver().query(photoUri,
                new String[] { MediaStore.Images.ImageColumns.ORIENTATION }, null, null, null);

        if (cursor.getCount() != 1) {
            return -1;
        }

        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    public static Bitmap getCorrectlyOrientedImage(Context context, Uri photoUri) throws IOException {
        InputStream is = context.getContentResolver().openInputStream(photoUri);
        BitmapFactory.Options dbo = new BitmapFactory.Options();
        dbo.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(is, null, dbo);
        Bitmap srcBitmap = BitmapFactory.decodeStream(is);
        is.close();

        int orientation = getOrientation(context, photoUri);
        if (orientation > 0) {
            Matrix matrix = new Matrix();
            matrix.postRotate(orientation);

            srcBitmap = Bitmap.createBitmap(srcBitmap, 0, 0, srcBitmap.getWidth(),
                    srcBitmap.getHeight(), matrix, true);
        }
        return srcBitmap;
    }
}
