package com.magicmirouf.magicbase.ui.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.magicmirouf.magicbase.R;
import com.magicmirouf.magicbase.utils.config.Config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by sylva on 26/07/2016.
 */
public class RetrofitListRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int NOT_EMPTY = 0;
    private static final int EMPTY = 1;
    private Context context;
    protected List<T> datas;
    protected Call<List<T>> call ;
    protected Callback callback;
    protected int layout_item;
    protected AdapterListener adapterListener;
    protected int type;

    public RetrofitListRecyclerAdapter(Context context) {
        this.context = context;
        this.datas = new ArrayList<>();
    }

    public void requestDatas() {
        callback = new Callback<List<T>>() {
            @Override
            public void onResponse(Call<List<T>> call, Response<List<T>> response) {
                stopProgress();
                datas.clear();

                if (response.body() !=null) {
                    datas.addAll(response.body());
                    notifyDataSetChanged();
                }

                if (response.body() != null)
                    Config.Log("Call response ok",response.body().toString());
                else {
                    try {
                        Config.Log("Call response nok",response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (adapterListener !=null){
                    adapterListener.listUpdated();
                }
            }

            @Override
            public void onFailure(Call<List<T>> call, Throwable t) {
                Config.Log("Failure retrofit list",t.getMessage());
            }
        };

        if (call !=null) {
            call.enqueue(callback);
            startProgress();
        }
    }

    private void startProgress() {
        //((NavigationActivity)context).setProgress(true);
    }
    private void stopProgress() {
        //((NavigationActivity)context).setProgress(false);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == NOT_EMPTY) {
            view = LayoutInflater.from(context).inflate(layout_item, parent, false);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.layout_item_text, parent, false);
        }
        return onCreateView(view);
    }

    @Override
    public int getItemViewType(int position) {
        return type;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (type == NOT_EMPTY) {
            onBindView(holder, datas.get(position));
        } else {
            ((ViewHolderText)holder).setData("Pas d'evenements");
        }
    }

    @Override
    public int getItemCount() {
        if (datas.size() >0) {
            type = NOT_EMPTY;
            return datas.size();
        } else {
            type = EMPTY;
            return 1;
        }
    }

    protected void onBindView(RecyclerView.ViewHolder holder, T data){
    }

    protected RecyclerView.ViewHolder onCreateView(View view) {
        return new ViewHolderText(view);
    }

    public void setJsonObjectCall(JsonObject jsonObjectCall) {
    }

    public void setAdapterListener(AdapterListener adapterListener) {
        this.adapterListener = adapterListener;
    }

    public interface AdapterListener {
        void listUpdated();
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setEmptyText(){

    }

    public class ViewHolderText extends RecyclerView.ViewHolder {

        private TextView textView;

        public ViewHolderText(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
        }

        public void setData(String text) {
            textView.setText(text);
        }
    }
}
