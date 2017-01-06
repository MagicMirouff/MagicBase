package com.magicmirouf.magicbase.webservices.google.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.magicmirouf.beewhizz.webservices.google.RetroFitGoogle;
import com.magicmirouf.beewhizz.webservices.google.models.Predictions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlacesAutoCompleteAdapter extends ArrayAdapter<String> implements Filterable {

    public Context context;
    private List<Predictions.Prediction> resultList;
    private List<String> suggestions;

    private Double lat,lng;

    public PlacesAutoCompleteAdapter(Context context, int layout) {
        super(context, layout);
        this.context = context;
        resultList = new ArrayList<>();
        suggestions = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return resultList.size();
    }

    @Override
    public String getItem(int index) {
        return suggestions.get(index);
    }


    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(final CharSequence constraint) {
                final FilterResults filterResults = new FilterResults();
                if (constraint != null) {
                    Callback<Predictions> callback = new Callback<Predictions>() {
                        @Override
                        public void onResponse(Call<Predictions> call, Response<Predictions> response) {
                            if (response.body() !=null) {
                                // Assign the data to the FilterResults
                                resultList.clear();
                                suggestions.clear();
                                for (Predictions.Prediction prediction : response.body().getPredictions()) {
                                    suggestions.add(prediction.getDescription());
                                    resultList.add(prediction);
                                }

                                filterResults.values = suggestions;
                                filterResults.count = suggestions.size();
                                publishResults(constraint.toString(), filterResults);
                            }
                        }

                        @Override
                        public void onFailure(Call<Predictions> call, Throwable t) {
                            filterResults.values =null;
                        }
                    };

                    // Retrieve the autocomplete results.
                    RetroFitGoogle.getService().autocomplete(constraint.toString()).enqueue(callback);
                }

                return null;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    notifyDataSetChanged();
                }
                else {
                    notifyDataSetInvalidated();
                }
            }};
        return filter;
    }



    public String getPlaceId(String selected) {
        int positon = suggestions.indexOf(selected);
        if (positon != -1){
            return resultList.get(positon).place_id;
        }
        return null;
    }
}