package com.example.achraf.mydiaries;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.achraf.mydiaries.DB.AccesData;
import com.google.android.gms.ads.AdView;

import java.sql.RowId;
import java.util.List;

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.ViewHolder> {

    private Activity activity = null;
    private List<Diary> diaries = null;
    private int itemLayout;

    private AccesData accesData = null;


    public DiaryAdapter(Activity activity, List<Diary> diaries, int itemLayout){
        this.activity = activity;
        this.diaries = diaries;
        this.itemLayout = itemLayout;
        this.accesData = new AccesData(activity);
    }


    @NonNull
    @Override
    public DiaryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.diary_element, viewGroup, false);
        return new ViewHolder(v);
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull DiaryAdapter.ViewHolder viewHolder, int i) {
        // to change color of drawable diary shape


        //ThemeChangeHelper.changeTheme(viewHolder.diary_1,viewHolder.textCadre_1,activity);




        final int i_to_sort = diaries.size() -i - 1;

        viewHolder.date_1.setText(getFormatDate(diaries.get(i_to_sort).getDate()));

        String story = diaries.get(i_to_sort).getStory();


        if(story.length() > 7){
            story = story.substring(0,6) + "...";
        }

        viewHolder.description_1.setText(story);

        viewHolder.imageViewRemove_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("Confirm delete a diary");
                builder.setMessage("Are you sure you want delete it ? ");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("diary","click on remove button for the id :"+diaries.get(i_to_sort).getId());
                        accesData.deleteDiary(diaries.get(i_to_sort).getId());
                        diaries.remove(i_to_sort);
                        notifyDataSetChanged();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(activity, "cool :)", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.show();






            }
        });

        final Intent i_edit = new Intent(activity,DiaryEditing.class);


        viewHolder.editButton_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dateToShow = getFormatDate(diaries.get(i_to_sort).getDate());
                String storyToEdit = diaries.get(i_to_sort).getStory();
                i_edit.putExtra("story",storyToEdit);
                i_edit.putExtra("id",diaries.get(i_to_sort).getId());
                i_edit.putExtra("date",dateToShow);
                activity.startActivity(i_edit);
            }
        });

    }


    @Override
    public int getItemCount() {
        return diaries.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView date_1;
        private TextView description_1;
        private ImageView imageViewRemove_1;

        private ImageView showButton_1;
        private ImageView editButton_1;

        private LinearLayout diary_1;
        private LinearLayout textCadre_1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date_1 = itemView.findViewById(R.id.dateElement);
            description_1 = itemView.findViewById(R.id.descriptionElement);
            imageViewRemove_1 = itemView.findViewById(R.id.removeButton);
            //showButton_1 = itemView.findViewById(R.id.showButton);
            editButton_1  = itemView.findViewById(R.id.editButton);

            diary_1 = itemView.findViewById(R.id.diary);
            textCadre_1 = itemView.findViewById(R.id.textCadre);

        }
    }

    public String getFormatDate(String date){
        String[] date_1 = date.split("/");
        String month = "";
        switch(date_1[1]){
            case "01":
                month = "January";
                break;
            case "02":
                month = "February";
                break;
            case "03":
                month = "March";
                break;
            case "04":
                month = "April";
                break;
            case "05":
                month = "May";
                break;
            case "06":
                month = "June";
                break;
            case "07":
                month = "July";
                break;
            case "08":
                month = "August";
                break;
            case "09":
                month = "September";
                break;
            case "10":
                month = "October";
                break;
            case "11":
                month = "November";
                break;
            case "12":
                month = "December";
                break;
        }
        String retDate = Integer.parseInt(date_1[0])+"th "+month+" "+date_1[2];

        return retDate;
    }
}
