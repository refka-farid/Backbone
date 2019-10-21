package com.bravedroid.userinteraction;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MenuActivity extends AppCompatActivity {
    private TextView mFirstContextualMenuTextView;
    private TextView mSecondContextualMenuTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Contextual Menu
        mFirstContextualMenuTextView = findViewById(R.id.context_menu_text_1);
        mSecondContextualMenuTextView = findViewById(R.id.context_menu_text_2);
        registerForContextMenu(mFirstContextualMenuTextView);
        registerForContextMenu(mSecondContextualMenuTextView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterForContextMenu(mFirstContextualMenuTextView);
        unregisterForContextMenu(mSecondContextualMenuTextView);
    }

    // region OptionsMenu callbacks
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_order) {
            displayToast("action order", this);
            return true;
        } else if (itemId == R.id.action_settings) {
            displayToast("action status", this);
            return true;
        } else if (itemId == R.id.action_contact) {
            displayToast("action contact", this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
// endregion OptionsMenu callbacks

    //region contextMenu callbacks
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        if (v.getId() == R.id.context_menu_text_1) {
            inflater.inflate(R.menu.contextual_menu_main_first, menu);
        } else if (v.getId() == R.id.context_menu_text_2) {
            inflater.inflate(R.menu.contextual_menu_main_second, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.contextual_remove) {
            displayToast("contextual remove", this);
            return true;
        } else if (itemId == R.id.contextual_update) {
            displayToast("contextual update", this);
            return true;
        } else if (itemId == R.id.contextual_share) {
            displayToast("contextual share", this);
            return true;
        } else if (itemId == R.id.contextual_edit) {
            displayToast("contextual edit", this);
            return true;
        }
        return super.onContextItemSelected(item);
    }
    //endregion contextMenu callbacks

    //region utilities
    static void displayToast(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
    //endregion utilities
}
