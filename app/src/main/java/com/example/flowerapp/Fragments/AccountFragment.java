package com.example.flowerapp.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.flowerapp.Activites.AboutActivity;
import com.example.flowerapp.Activites.LoginActivity;
import com.example.flowerapp.Activites.NotificationActivity;
import com.example.flowerapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountFragment extends Fragment {
    private  static final int GALLERY_INTENT=1000;

    public FirebaseUser currentUser;
    public FirebaseAuth mAuth;
    TextView about , security , support , notification ,log_out , next;

    public AccountFragment() {
        // Required empty public constructor
    }

    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_account, container, false);
        // Inflate the layout for this fragment


        next = view.findViewById(R.id.storage);
        log_out = view.findViewById(R.id.text_logout);
        about = view.findViewById(R.id.text_about);
        security = view.findViewById(R.id.text_security);
        support = view.findViewById(R.id.text_support);
        notification = view.findViewById(R.id.notification);

        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();

                Intent i = new Intent(getContext(), LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);



            }
        });
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NotificationActivity.class);
                startActivity(intent);

            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AboutActivity.class);
                startActivity(intent);
            }
        });


        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://support.google.com/android/?hl=ar#topic=7313011"));
                startActivity(intent);
            }
        });

        security.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://developer.android.com/training/articles/security-tips"));
                startActivity(intent);
            }
        });






        mAuth=FirebaseAuth.getInstance();
        currentUser=mAuth.getCurrentUser();



//        imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(Intent.ACTION_PICK);
//                intent.setType("*/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Select file"), GALLERY_INTENT);
//            }
//        });


//        ImageView image_notification = view.findViewById(R.id.image_notification);
//       image_notification.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               Intent intent = new Intent(getContext(),NotificationActivity.class);
//               startActivity(intent);
//
//           }
//       });


//        ImageView image_about_next = view.findViewById(R.id.image_about_next);
//        image_about_next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(),AboutActivity.class);
//                startActivity(intent);
//
//            }
//        });


//        ImageView image_support_next = view.findViewById(R.id.image_next_support);
//        image_support_next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("https://www.merriam-webster.com/dictionary/support"));
//                startActivity(intent);
//
//            }
//        });



//        ImageView image_security_next = view.findViewById(R.id.image_next_security);
//        image_security_next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("https://www.techtarget.com/searchsecurity/definition/security"));
//                startActivity(intent);
//
//            }
//        });





        return view;
    }
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            mainImageURI = data.getData();
//            Glide.with(this).load(currentUser.getPhotoUrl()).into(imageButton);
//
//            if (mainImageURI.getPath() != null)
//                updateProfile();
//        }
//    }
//    private void updateProfile() {
//
//        UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder()
////                .setDisplayName(nameEd.getText().toString())
//                .setPhotoUri(mainImageURI)
//                .build();
//        currentUser.updateProfile(userProfileChangeRequest)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//
//                        if (task.isSuccessful()) {
//                            currentUser.reload();
//                            Toast.makeText(getActivity(), "Profile Update for" , Toast.LENGTH_SHORT).show();
//                        } else {
//                            task.getException().printStackTrace();
//                            Toast.makeText(getActivity(), "null", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                })
//
//        ;
//
//
//    }
}