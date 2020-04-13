package com.cridacamo.cetus.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.cridacamo.cetus.R;
import com.cridacamo.cetus.utilities.ActionBarUtil;
import com.cridacamo.cetus.utilities.DatePickerFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterEventActivity extends AppCompatActivity {

    private ActionBarUtil actionBarUtil;
    @BindView(R.id.register_event_txt_date)
    public EditText txtDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_event);
        ButterKnife.bind(this);
        initComponents();
    }
    private void initComponents() {
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.RegisterEvent));
    }

    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                txtDate.setText(day + "/" + (month+1) + "/" + year);

            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void goToEvenstListActivity(View view) {
        Intent intent = new Intent(this, EventsActivity.class);
        startActivity(intent);
    }
}
