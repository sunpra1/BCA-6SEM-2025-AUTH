package com.sunpra.auth.ui;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunpra.auth.R;
import com.sunpra.auth.model.Country;

import java.util.Arrays;
import java.util.List;


public class PracticeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_practice, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeView(view);
    }

    private void initializeView(View view) {
        List<Country> countriesWithFlag = Arrays.asList(
                new Country(
                        R.drawable.flag_of_nepal,
                        R.string.nepal
                ),
                new Country(
                        R.drawable.flag_of_india,
                        R.string.india
                )
        );
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(countriesWithFlag);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }
}

class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>{

    private List<Country> countries;


    public MyRecyclerViewAdapter(List<Country> countries) {
        this.countries = countries;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Country country = countries.get(position);
        holder.updateView(holder.itemView.getContext(), country);

    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView flagImage;
        private TextView countryName;


        public ViewHolder(View root) {
            super(root);
            flagImage = root.findViewById(R.id.flag);
            countryName = root.findViewById(R.id.country);
        }

        private void updateView(Context context, Country country){
            flagImage.setImageDrawable(
                    ContextCompat.getDrawable(context, country.getFlag())
            );
            countryName.setText(context.getString(country.getName()));
        }
    }

}