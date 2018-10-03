package pico.covoitapp.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

import pico.covoitapp.Utils.Interface.Retrofit.IUser;

public class ImageManager {

    private static ImageManager _instance;
    private StorageReference mStorageRef;
    private String TAG = "CovoitApp.ImageManager";

    private  Bitmap image;


    private ImageManager() {
        mStorageRef = FirebaseStorage.getInstance().getReference();

    }

    public static ImageManager getInstance(){
        if(_instance == null){
            _instance = new ImageManager();

        }
        return _instance;
    }


    public Bitmap getImage() {
        return image;
    }

    public void uploadImage(Bitmap img, final String fileName,  final IImage listener) {
        RetrofitHelper.me.setProfil_image(fileName);
        final StorageReference imageRef = mStorageRef.child(fileName);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        img.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = imageRef.putBytes(data);


        //upload task
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG,"FireBAse failed " + e.getMessage());
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Log.e(TAG, "photo name : " + fileName);

                Log.e(TAG, "profil name  : " + RetrofitHelper.me.getProfil_image());
                Log.e(TAG,"FireBAse success ");
               // get Url after upload it
                taskSnapshot.getMetadata().getReference().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                      Log.e(TAG, "uri : " + uri.getPath());
                    }
                });
                listener.onFirebaseResult(true);



            }
        });
    }



    public void DownloadImage(String imageName, final IImage listener){

        long size = 1024*1024;

        mStorageRef.child(imageName).getBytes(size).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                image = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                listener.onFirebaseResult( image != null );
            }
        });


    }

}
