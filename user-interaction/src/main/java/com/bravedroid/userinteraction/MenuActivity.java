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
    private TextView mContextualMenuTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Contextual Menu
        mContextualMenuTextView = findViewById(R.id.context_menu_text_1);
        registerForContextMenu(mContextualMenuTextView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterForContextMenu(mContextualMenuTextView);
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
        inflater.inflate(R.menu.context_menu_main, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.contextual_edit) {
            displayToast("contextual edit", this);
            return true;
        } else if (itemId == R.id.contextual_share) {
            displayToast("contextual share", this);
            return true;
        }
        return super.onContextItemSelected(item);
    }
    //endregion contextMenu callbacks

    //region utilities
    static void displayToast(String message, Context context) {
        Toast.makeText(context, "action contact", Toast.LENGTH_SHORT).show();
    }
    //endregion utilities
}
