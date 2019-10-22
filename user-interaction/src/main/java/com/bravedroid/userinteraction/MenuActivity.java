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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.Toolbar;

public class MenuActivity extends AppCompatActivity {
    private TextView mFirstContextualMenuTextView;
    private TextView mSecondContextualMenuTextView;
  //  private ActionMode mActionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        //Contextual Menu
        mFirstContextualMenuTextView = findViewById(R.id.context_menu_text_1);
        mSecondContextualMenuTextView = findViewById(R.id.context_menu_text_2);
        registerForContextMenu(mFirstContextualMenuTextView);
        registerForContextMenu(mSecondContextualMenuTextView);
        //contextual action menu
        TextView articleContextualView = findViewById(R.id.contextual_action_menu_text);
        articleContextualView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
               //if (mActionMode != null) {
               //    return false;
               //}
               //mActionMode = startSupportActionMode(mActionModeCallback);
                startSupportActionMode(mActionModeCallback);
                v.setSelected(true);
                return true;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterForContextMenu(mFirstContextualMenuTextView);
        unregisterForContextMenu(mSecondContextualMenuTextView);
    }

    @Override
    public void onSupportActionModeStarted(@NonNull ActionMode mode) {
        super.onSupportActionModeStarted(mode);
    }

    @Override
    public void onSupportActionModeFinished(@NonNull ActionMode mode) {
        super.onSupportActionModeFinished(mode);
    }

    // region contextual action menu
    public ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            //MenuInflater inflater = mode.getMenuInflater();
            getMenuInflater().inflate(R.menu.contextual_action_bar_main_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            int itemId = item.getItemId();
            if (itemId == R.id.contextual_action_edit) {
                displayToast("contextual action edit ", MenuActivity.this);
                mode.finish();
                return true;
            } else if (itemId == R.id.contextual_action_share) {
                displayToast("contextual action share ", MenuActivity.this);
                mode.finish();
                return true;
            } else if (itemId == R.id.contextual_action_close) {
                displayToast("contextual action close ", MenuActivity.this);
                mode.finish();
                return true;
            } else if (itemId == R.id.contextual_action_back) {
                displayToast("conextual action back ", MenuActivity.this);
                mode.finish();
                return true;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            //mActionMode = null;
        }
    };
// endregion contextual action menu

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
