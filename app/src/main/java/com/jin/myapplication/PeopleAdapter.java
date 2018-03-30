package com.jin.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jinx9 on 2018-03-27.
 */

public class PeopleAdapter extends BaseAdapter {

    private static final String TAG = PeopleAdapter.class.getSimpleName();
    private final List<People> mData;
    private final Context mContext;

    public PeopleAdapter(Context context, List<People> data) {
        mContext = context;
        mData = data;
    }

    // 데이터 갯수
    @Override
    public int getCount() {
        return mData.size();
    }

    // position번째에 어떤 데이터가 있는지 알려줘야 함
    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    // 데이터베이스의 id
    @Override
    public long getItemId(int position) {
        return position;
    }

    // position번째의 레이아웃 완성해서 알려줘야 함
    // convertView - position번째의 레이아웃의 레퍼런스
    // parent 이 어댑터가 붙을 부모의 레퍼런스 (ListView나 GridView)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            Log.d(TAG, "getView: getView 최초 : " +position);
            // 최초
            convertView = LayoutInflater.from(mContext)
                    .inflate(R.layout.item_exam, parent, false);
            holder = new ViewHolder();

            // 레이아웃 가지고 오기
            ImageView imageView = convertView.findViewById(R.id.image_view);
            TextView nameTextView = convertView.findViewById(R.id.name_text);
            TextView phoneTextView = convertView.findViewById(R.id.phone_text);
            holder.image = imageView;
            holder.name = nameTextView;
            holder.phone = phoneTextView;

            convertView.setTag(holder);
        } else {
            // 재사용
            Log.d(TAG, "getView: 재사용"+ position);
            holder = (ViewHolder) convertView.getTag();
        }

        // data
        People people = (People) getItem(position);

        // 뿌리기
        holder.image.setImageResource(people.getPicture());
        holder.name.setText(people.getName());
        holder.phone.setText(people.getPhone());

        return convertView;
    }

    private static class ViewHolder {
        ImageView image;
        TextView name;
        TextView phone;
    }
}

