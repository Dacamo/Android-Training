package com.cridacamo.cetus.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cridacamo.cetus.R;
import com.cridacamo.cetus.utilities.ActionBarUtil;

public class RegisterEventActivity extends AppCompatActivity {

    private ActionBarUtil actionBarUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_event);
        initComponents();
    }
    private void initComponents() {
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.RegisterEvent));
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
