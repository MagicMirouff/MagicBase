package com.magicmirouf.magicbase.utils.pickers;

import android.support.v4.app.Fragment;

/**
 * Created by sylva on 02/06/2016.
 */
public class ShareFragment extends Fragment {

   /* public  static final int TYPE_APP = 0;
    public  static final int TYPE_CODE = 1;
    public  static final int TYPE_EVENT = 2;
    private int type = 0;
    private String infos;
    private String url;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_share,null);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.share_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setHasFixedSize(true);

        ShareRecyclerAdapter adapter = new ShareRecyclerAdapter(buildShareList(),getActivity());
        adapter.setInfos(infos);
        adapter.setUrl(url);
        recyclerView.setAdapter(adapter);

        TextView title = (TextView) view.findViewById(R.id.title);
        TextView subtitle = (TextView) view.findViewById(R.id.subtitle);
        TextView tv_code = (TextView) view.findViewById(R.id.code);

        switch (type){
            case TYPE_APP:
                title.setText("Partagez l'app !");
                subtitle.setText("Soufflez le mot !");
                tv_code.setVisibility(View.GONE);
                break;
            case TYPE_CODE:
                title.setText("Offrez 5€ - Recevez 5€");
                subtitle.setText("Votre code");
                tv_code.setText(code);
                break;
            case TYPE_EVENT:
                title.setText(infos);
                subtitle.setVisibility(View.GONE);
                tv_code.setVisibility(View.GONE);
                break;
        }


    }

    private List<ShareRecyclerAdapter.ShareItem> buildShareList() {
        List<ShareRecyclerAdapter.ShareItem> shareItems = new ArrayList<>();

        shareItems.add(new ShareRecyclerAdapter.ShareItem(R.drawable.share_facebook, getActivity().getResources().getString(R.string.facebook),getActivity().getResources().getColor(R.color.com_facebook_blue),R.string.facebook));
        shareItems.add(new ShareRecyclerAdapter.ShareItem(R.drawable.share_messenger, getActivity().getResources().getString(R.string.messenger),getActivity().getResources().getColor(R.color.com_facebook_messenger_blue),R.string.messenger));
        shareItems.add(new ShareRecyclerAdapter.ShareItem(R.drawable.share_twitter, getActivity().getResources().getString(R.string.twitter),getActivity().getResources().getColor(R.color.color_twitter),R.string.twitter));
        shareItems.add(new ShareRecyclerAdapter.ShareItem(R.drawable.share_whatsapp, getActivity().getResources().getString(R.string.whatsapp),getActivity().getResources().getColor(R.color.color_whatapp),R.string.whatsapp));
        shareItems.add(new ShareRecyclerAdapter.ShareItem(R.drawable.share_text, getActivity().getResources().getString(R.string.sms),0,R.string.sms));
        shareItems.add(new ShareRecyclerAdapter.ShareItem(R.drawable.share_email, getActivity().getResources().getString(R.string.email),0,R.string.email));
        shareItems.add(new ShareRecyclerAdapter.ShareItem(R.drawable.share_copylink, getActivity().getResources().getString(R.string.copy_link),0,R.string.copy_link));
        return shareItems;
    }

    // TODO: 14/06/2016 from navigation
    public void setInfos(String infos) {
        this.infos = infos;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.url = url;
    }*/
}
