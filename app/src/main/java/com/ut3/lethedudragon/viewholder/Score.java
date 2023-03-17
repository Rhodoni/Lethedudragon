package com.ut3.lethedudragon.viewholder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ut3.lethedudragon.R;

public class Score extends Activity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        setContentView(R.layout.score);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    @Override
    public void onBackPressed(){
        Intent mainAct = new Intent(this, Opening.class);
        finish();
        startActivity(mainAct);
    }

    class ScoreAdapter extends RecyclerView.Adapter{
        Context c;

        ScoreAdapter(Context c){
            this.c=c;
        }
        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new RecyclerView.ViewHolder(new TextView(c)){};
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            //scored = sharedPref.getInt("score",0);
            ((TextView) holder.itemView).setText("score " + 30+" s");
        }

        @Override
        public int getItemCount() {
            return 1;
        }
    }
}
