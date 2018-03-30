package com.jin.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AdapterViewExamActivity extends AppCompatActivity {

    private ArrayList<People> mPeopleData;
    private String TAG = AdapterViewExamActivity.class.getSimpleName();
    private PeopleAdapter mAdapter;
    private ListView mListView;
    private EditText mWeatherEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_view_exam);

        // View
        mListView = findViewById(R.id.list_view);
        GridView gridView = findViewById(R.id.grid_view);
        Spinner spinner = findViewById(R.id.spinner);

        // Data
        mPeopleData = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int picture;
            if (i % 2 == 0) {
                picture = R.drawable.miku;
            } else {
                picture = R.mipmap.ic_launcher;
            }
            People people = new People("아무개 " + i, "전화번호 " + i, picture);
            mPeopleData.add(people);
        }

        // Adapter
        mAdapter = new PeopleAdapter(AdapterViewExamActivity.this, mPeopleData);

        mListView.setAdapter(mAdapter);

//        gridView.setAdapter(adapter);
//        spinner.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            People people = mPeopleData.get(position);
//            mPeopleData.get(position);
                People people = (People) parent.getAdapter().getItem(position);
                Toast.makeText(AdapterViewExamActivity.this, "그냥 클릭", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onItemClick: " + people.toString()); // people의 정보를 본다

                Intent intent = new Intent(AdapterViewExamActivity.this, DetailAdressActivity.class);
                intent.putExtra("name", people.getName());
                intent.putExtra("phone", people.getPhone());
                intent.putExtra("picture", people.getPicture());
                startActivity(intent);
            }
        });

//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
//                Toast.makeText(AdapterViewExamActivity.this, "롱 클릭" + position, Toast.LENGTH_SHORT).show();
//                return true; // 이벤트 소비 OK
//
//            }
//        });

        // Context 메뉴 연결
        registerForContextMenu(mListView);

        // SharedPreference 데이터 복원
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        String weather = settings.getString("weather", "맑음");

        mWeatherEditText = findViewById(R.id.weather_edit);
        mWeatherEditText.setText(weather);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // 메뉴버튼이 처음 눌러졌을 때 실행되는 콜백메서드
        // 메뉴버튼을 눌렀을 때 보여줄 menu 에 대해서 정의
        getMenuInflater().inflate(R.menu.menu_address, menu);
        Log.d("test", "onCreateOptionsMenu - 최초 메뉴키를 눌렀을 때 호출됨");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 메뉴의 항목을 선택(클릭)했을 때 호출되는 콜백메서드
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Log.d("test", "onOptionsItemSelected - 메뉴항목을 클릭했을 때 호출됨");

        int id = item.getItemId();
        switch (id) {
            case R.id.action_add:
                Intent intent = new Intent(AdapterViewExamActivity.this, DetailAdressActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_coffee, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.action_item1:
                Toast.makeText(this, "액션 1", Toast.LENGTH_SHORT).show();

                // 물어보자 AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(AdapterViewExamActivity.this);
                builder.setTitle("삭제");
                builder.setMessage("정말로 삭제하시겠습니까?");
                // 바깥 부분 클릭했을 때 막기
                builder.setCancelable(false); // 바깥클릭해도 안닫히게
                builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // dialog : 눌린객체,  i : 아이템 클릭 position번째
                        Log.d(TAG, "onClick: " + i);
                        // 삭제
                        mPeopleData.remove(info.position);
                        // 업데이트
                        mAdapter.notifyDataSetChanged(); // Best!!!
//                mListView.setAdapter(new PeopleAdapter(this, mPeopleData));
//                mListView.setAdapter(mAdapter);
                    }
                });

                builder.setNegativeButton("아니오", null); // 보통 아무처리안해도됨.
                builder.setIcon(R.drawable.miku);

                builder.create().show();

                return true;
            case R.id.action_item2:
                Toast.makeText(this, "액션 2", Toast.LENGTH_SHORT).

                        show();
                return true;
            default:
                return super.

                        onContextItemSelected(item);
        }

    }

    // 뒤로 가기 onBackPressed 하기전에 저장
    @Override
    public void onBackPressed() {
        // 저장
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("weather", mWeatherEditText.getText().toString());

        // 비동기
        editor.apply();

        // 뒤로 가기
        super.onBackPressed();
    }
}