package com.example.lenovo_pc.monashfriendfinder;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.lenovo_pc.monashfriendfinder.util.SQLUtil;
import com.example.lenovo_pc.monashfriendfinder.util.Studentpr;
import com.example.lenovo_pc.monashfriendfinder.util.URLConfigUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FriendsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FriendsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FriendsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View  view;
    private OnFragmentInteractionListener mListener;

    public FriendsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FriendsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FriendsFragment newInstance(String param1, String param2) {
        FriendsFragment fragment = new FriendsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_friends,container,false);
        SharedPreferencesUtils helper = new SharedPreferencesUtils(FriendsFragment.this.getContext(),"setting");
        String id = helper.getString("name");
        MatchData data = new MatchData();
        System.out.println(id);
        data.execute(id);
        return inflater.inflate(R.layout.fragment_friends, container, false);
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

    public void detail(){
        SharedPreferencesUtils helper = new SharedPreferencesUtils(FriendsFragment.this.getContext(),"setting");

    }
    public void addrows(final List<Studentpr> result){
        System.out.println(result.size());
        LinearLayout context = (LinearLayout)view.findViewById(R.id.context);
        for(int i = 0;i < result.size();i++){
            LinearLayout tem = new LinearLayout(FriendsFragment.this.getActivity());
            tem.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,70));
            tem.setOrientation(LinearLayout.HORIZONTAL);

            ViewGroup.LayoutParams llp = new ViewGroup.LayoutParams(140, 70);
            final String t = result.get(i).getStuid();
            Button add = new Button(getContext());
            add.setLayoutParams(llp);
            add.setText("Delete");
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(deletefriend(t)){
                        Toast.makeText(getActivity(),"Success",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(),"Fail",Toast.LENGTH_SHORT).show();
                    }
                }
            });
            tem.addView(add);
            TextView id = new TextView(FriendsFragment.this.getActivity());
            id.setText(result.get(i).getStuid());
            id.setLayoutParams(llp);
            id.setBackgroundResource(R.color.blue);
            tem.addView(id);

            TextView firname = new TextView(FriendsFragment.this.getActivity());
            firname.setText(result.get(i).getFirname());
            firname.setLayoutParams(llp);
            firname.setBackgroundResource(R.color.blue);
            tem.addView(firname);

            TextView surname = new TextView(FriendsFragment.this.getActivity());
            surname.setText(result.get(i).getSurname());
            surname.setLayoutParams(llp);
            surname.setBackgroundResource(R.color.blue);
            tem.addView(surname);

            TextView gender = new TextView(FriendsFragment.this.getActivity());
            gender.setText(result.get(i).getGender());
            gender.setLayoutParams(llp);
            gender.setBackgroundResource(R.color.blue);
            tem.addView(gender);

            TextView email = new TextView(FriendsFragment.this.getActivity());
            email.setText(result.get(i).getDob());
            email.setLayoutParams(llp);
            email.setBackgroundResource(R.color.blue);
            tem.addView(email);
            context.addView(tem);
        }
    }
    public ArrayList<Studentpr> receivefriends(String id){
        ArrayList<Studentpr>  res = new ArrayList<Studentpr>();

        return res;
    }
    public boolean deletefriend(String id){
        boolean flag = false;
        try{
            OkHttpClient client = new OkHttpClient();

            String url = SQLUtil.serverhost+ URLConfigUtil.getURLdeletefriend()+ Integer.valueOf(id);
            Log.d("TestActivity", url);
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();

            System.out.println(responseData);
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }


    private class MatchData extends AsyncTask<String, Void ,String> {
        @Override
        protected void onPostExecute(String value){
            super.onPostExecute(value);
            ArrayList<Studentpr> result = new ArrayList<Studentpr>();
            try {
                JSONArray arr = JSONArray.parseArray(value);
                for(int i = 0;i < arr.size();i++){
                    JSONObject tem = arr.getJSONObject(i);
                    Studentpr stu = new Studentpr();

                    stu.setStuid(tem.getString("stuid"));
                    stu.setFirname(tem.getString("firname"));
                    stu.setSurname(tem.getString("surname"));
                    stu.setDob(tem.getString("dob"));
                    stu.setGender(tem.getString("gender"));
                    stu.setCourse(tem.getString("course"));
                    stu.setStumode(tem.getString("stumode"));
                    stu.setMoemail(tem.getString("moemail"));
                    stu.setSubdate(tem.getString("subdate"));
                    stu.setSubtime(tem.getString("subtime"));
                    stu.setSuburb(tem.getString("suburb"));
                    stu.setNationality(tem.getString("nationality"));
                    stu.setNalanguage(tem.getString("nalanguage"));
                    stu.setFsport(tem.getString("fsport"));
                    stu.setFmovie(tem.getString("fmovie"));
                    stu.setFunit(tem.getString("funit"));
                    stu.setCurjob(tem.getString("curjob"));
                    stu.setAddress(tem.getString("address"));

                    result.add(stu);
                }
                addrows(result);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        @Override
        protected String doInBackground(String... strings) {
            String totalbyte = null;
            try {
                OkHttpClient client = new OkHttpClient();

                String url = SQLUtil.serverhost+ URLConfigUtil.getURLfriends()+"/"+ strings[0];
                Log.d("TestActivity", url);
                Request request = new Request.Builder().url(url).header("User-Agent", "OkHttp Headers.java")
                        .addHeader("Accept", "application/json; q=0.5")
                        .addHeader("Accept", "application/vnd.github.v3+json").build();
                Response response = client.newCall(request).execute();
                String responseData = response.body().string();
                totalbyte = responseData;
                System.out.println(totalbyte+"            lalalala");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return totalbyte;
        }
    }

}
