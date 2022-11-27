package com.example.pantry.ui.notifications;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.pantry.LoginActivity;
import com.example.pantry.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.squareup.picasso.Picasso;

public class NotificationsFragment extends Fragment {

    private com.example.pantry.ui.notifications.NotificationsViewModel notificationsViewModel;
    TextView txt_name,txt_email,txt_phone, verifyMsg, txt_verify, txt_verified;
    ImageView photo_imageView, verify_imageview, verified_imageview;
    LinearLayout btnVerifyEmail;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    String userID;
    private GoogleSignInAccount googleSignInAccount;
    private GoogleSignInClient mGoogleSignInClient;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel = ViewModelProviders.of(this).get(com.example.pantry.ui.notifications.NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);



        txt_name = root.findViewById(R.id.txt_name);
        txt_email = root.findViewById(R.id.txt_email);
        txt_phone = root.findViewById(R.id.txt_phone);
        photo_imageView = root.findViewById(R.id.photo_imageView);
        verify_imageview = root.findViewById(R.id.verify_imageview);
        verified_imageview = root.findViewById(R.id.verified_imageview);
        txt_verify = root.findViewById(R.id.txt_verify);
        txt_verified = root.findViewById(R.id.txt_verified);
        btnVerifyEmail = root.findViewById(R.id.btnVerifyEmail);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        if (firebaseAuth.getCurrentUser() == null){
            Intent intent = new Intent(getContext(), LoginActivity.class);
            String warning = "You need to Login First to unlock this feature!!";
            intent.putExtra("warning",warning);
            startActivity(intent);
        } else {
            userID = firebaseAuth.getCurrentUser().getUid();
            final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

            if (!firebaseUser.isEmailVerified()) {
                verified_imageview.setVisibility(View.GONE);
                txt_verified.setVisibility(View.GONE);

                verify_imageview.setVisibility(View.VISIBLE);
                txt_verify.setVisibility(View.VISIBLE);
            } else {
                verify_imageview.setVisibility(View.GONE);
                txt_verify.setVisibility(View.GONE);

                verified_imageview.setVisibility(View.VISIBLE);
                txt_verified.setVisibility(View.VISIBLE);
                btnVerifyEmail.setOnClickListener(null);

            }

            DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);
            documentReference.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());

                @Override
                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                    if (error != null) {
                        Log.d("TAG", "onEvent:" + error.getMessage());
                    } else if (value.getString("fullName") != null){
                        txt_name.setText(value.getString("fullName"));
                        txt_email.setText(value.getString("email"));
                        txt_phone.setText(value.getString("phone"));
                    } else if (acct!= null){
                        String personName = acct.getDisplayName();
                        String personEmail = acct.getEmail();
                        Uri personPhoto = acct.getPhotoUrl();

                        txt_name.setText(personName);
                        txt_email.setText(personEmail);
                        Picasso.get().load(personPhoto).into(photo_imageView);


                    }

                }
            });
        }
        return root;
    }


}