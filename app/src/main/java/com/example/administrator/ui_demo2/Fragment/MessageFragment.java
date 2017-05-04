package com.example.administrator.ui_demo2.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.ui_demo2.R;
import com.example.administrator.ui_demo2.adapter.SwipeAdapter;
import com.example.administrator.ui_demo2.entity.WXMessage;
import com.example.administrator.ui_demo2.widget.SwipeListView;

import java.util.ArrayList;
import java.util.List;

public class MessageFragment extends Fragment {
    private List<WXMessage> data = new ArrayList<>();
    private SwipeListView mListView;
    private OnFragmentInteractionListener mListener;
    private View view;
    private boolean isInit = false;

    public MessageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_message,container,false);
        findView();
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void findView() {

        mListView = (SwipeListView)view.findViewById(R.id.mListView);
        SwipeAdapter mAdapter = new SwipeAdapter(getActivity(),data);
        mAdapter.setOnRightItemClickListener(new SwipeAdapter.onRightItemClickListener() {

            @Override
            public void onRightItemClick(View v, int position) {

                Toast.makeText(getContext(), "删除第  " + (position+1)+" 对话记录",
                        Toast.LENGTH_SHORT).show();
                data.remove(position);
                findView();
            }
        });

        mListView.setAdapter(mAdapter);
    }

    private void initData() {

        if (!isInit) {
            for(int i=0;i<3;i++){
                WXMessage msg;
                if(i%3==0){
                    msg = new WXMessage("腾讯新闻", "人民日报刊文：习近平对评价毛泽东提6个重要观点", "早上8:44");
                    msg.setIcon_id(R.drawable.qq_icon);
                }else if(i%3==1){
                    msg = new WXMessage("订阅号", "CSDN：2013年国内最具技术影响力公司","早上8:49");
                    msg.setIcon_id(R.drawable.wechat_icon);
                }else{
                    msg = new WXMessage("微博阅读", "美女演各款妹子跟男朋友打电话","昨天晚上");
                    msg.setIcon_id(R.drawable.qq_icon);
                }

                data.add(msg);
            }
        }
        isInit = true;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
