package pico.covoitapp.Utils;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class ImageManager {

    private static ImageManager _instance;
    private StorageReference mStorageRef;


    private ImageManager() {
        mStorageRef = FirebaseStorage.getInstance().getReference();

    }

    public static ImageManager getInstance(){
        if(_instance == null){
            _instance = new ImageManager();
        }
        return _instance;
    }



    public void uploadImage(String path, String fileName) {
        Uri file = Uri.fromFile(new File(path + fileName));
        final StorageReference imageRef = mStorageRef.child("images/" + fileName);
        UploadTask uploadTask = imageRef.putFile(file);

        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }
                return imageRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();

                }
            }
        });
    }



    public File DownloadImage(String imageName) throws IOException {
        File localFile = File.createTempFile(imageName, "jpg");
        mStorageRef.child("images/" + imageName).getFile(localFile);
        return localFile;

    }

}
