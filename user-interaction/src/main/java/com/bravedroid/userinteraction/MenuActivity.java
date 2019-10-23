package com.bravedroid.userinteraction;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.PopupMenu;

import java.util.Random;

public class MenuActivity extends AppCompatActivity {
    private TextView mFirstContextualMenuTextView;
    private TextView mSecondContextualMenuTextView;
    private ImageButton mPopupMenuButton;

    private ActionMode mActionMode;

    private int[] iconArray = {R.drawable.ic_shop, R.drawable.ic_shop_two, R.drawable.ic_shopping_basket, R.drawable.ic_order_name};
    private int oldIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //Contextual Menu
        mFirstContextualMenuTextView = findViewById(R.id.textView_main_contextualMenu_one);
        mSecondContextualMenuTextView = findViewById(R.id.textView_main_contextualMenu_two);
        registerForContextMenu(mFirstContextualMenuTextView);
        registerForContextMenu(mSecondContextualMenuTextView);
        //contextual action menu
        TextView articleContextualView = findViewById(R.id.textView_main_contextualActionMenu);
        articleContextualView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mActionMode != null) {
                    return false;
                }
                mActionMode = startSupportActionMode(mActionModeCallback);
                v.setSelected(true);
                return true;
            }
        });
        // region popup menu
        mPopupMenuButton = findViewById(R.id.imageButton_main_Popup);
        mPopupMenuButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(MenuActivity.this, mPopupMenuButton);
                popup.getMenuInflater().inflate(R.menu.menu_popup, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.item1) {
                            displayToast("item1", MenuActivity.this);
                        } else if (item.getItemId() == R.id.item2) {
                            displayToast("item2", MenuActivity.this);
                        } else if (item.getItemId() == R.id.item3) {
                            displayToast("item3", MenuActivity.this);
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });
        // endregion popup menu

        TextView mMoveToNextOptionItemText = findViewById(R.id.textView_main_random);
        mMoveToNextOptionItemText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                invalidateOptionsMenu();
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
            mActionMode = null;
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
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_order);
        int index = getRandomIndex();
        item.setIcon(iconArray[index]);
        return true;
    }

    private int getRandomIndex() {
        Random random = new Random();
        int newIndex = random.nextInt(iconArray.length);
        while (true) {
            if (newIndex != oldIndex) {
                break;
            }
            newIndex = random.nextInt(iconArray.length);
        }

//        for (; oldIndex == newIndex; ) {
//            newIndex = random.nextInt(iconArray.length);
//        }
//        for (; oldIndex != newIndex; newIndex = random.nextInt(iconArray.length))
//
//        while (oldIndex == newIndex) {
//            newIndex = random.nextInt(iconArray.length);
//        }
//        do {
//            newIndex = random.nextInt(iconArray.length);
//        } while (oldIndex == newIndex);

        oldIndex = newIndex;
        return newIndex;
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
        if (v.getId() == R.id.textView_main_contextualMenu_one) {
            inflater.inflate(R.menu.contextual_menu_main_first, menu);
        } else if (v.getId() == R.id.textView_main_contextualMenu_two) {
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
