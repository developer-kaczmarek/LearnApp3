package com.example.learnapp3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<ListItem> consolidatedList;
    private View cardTask;
    private View headlineTasks;

    public Adapter(Context context, List<ListItem> consolidatedList) {

        this.consolidatedList = consolidatedList;
        this.mContext = context;
    }

    public void removeItem(int position){

        consolidatedList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ListItem.TYPE_GENERAL:
                cardTask = inflater.inflate(R.layout.items, parent, false);
                viewHolder = new GeneralViewHolder(cardTask);

                break;

            case ListItem.TYPE_DATE:
                headlineTasks = inflater.inflate(R.layout.headline, parent, false);
                viewHolder = new DateViewHolder(headlineTasks);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        switch (viewHolder.getItemViewType()) {
            case ListItem.TYPE_GENERAL:
                GeneralItem generalItem   = (GeneralItem) consolidatedList.get(position);
                GeneralViewHolder generalViewHolder = (GeneralViewHolder) viewHolder;
                generalViewHolder.titleTask.setText(generalItem.getPojoOfJsonArray().getName());
                generalViewHolder.time.setText(generalItem.getPojoOfJsonArray().getTime());

                break;

            case ListItem.TYPE_DATE:
                DateItem dateItem = (DateItem) consolidatedList.get(position);
                DateViewHolder dateViewHolder = (DateViewHolder) viewHolder;

                SimpleDateFormat oldDateFormat =
                        new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
                SimpleDateFormat newDateFormat =
                        new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());

                try {
                    dateViewHolder.txtTitle.setText(newDateFormat.format(
                            oldDateFormat.parse(dateItem.getDate())
                            )
                    );

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                break;
        }
    }

    // ViewHolder для Заголовка (Дата)
    class DateViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtTitle;
        public DateViewHolder(View v) {
            super(v);
            this.txtTitle = v.findViewById(R.id.txt);
        }
    }

    // ViewHolder для карточки задачи (Название задачи и время)
    class GeneralViewHolder extends RecyclerView.ViewHolder {
        protected TextView titleTask;
        protected TextView time;
        protected RelativeLayout viewBackground;
        protected LinearLayout viewForeground;
        public GeneralViewHolder(View v) {
            super(v);
            this.titleTask = v.findViewById(R.id.title);
            this.time = v.findViewById(R.id.time);
            this.viewBackground = v.findViewById(R.id.viewBackground);
            this.viewForeground = v.findViewById(R.id.viewForeground);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return consolidatedList.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return consolidatedList != null ? consolidatedList.size() : 0;
    }
}
