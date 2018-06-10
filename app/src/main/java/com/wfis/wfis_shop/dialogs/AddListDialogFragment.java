package com.wfis.wfis_shop.dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.wfis.wfis_shop.R;
import com.wfis.wfis_shop.models.ShoppingList;

/**
 * Created by kacper on 27.03.2018.
 */

public class AddListDialogFragment extends DialogFragment {

    public static AddListDialogFragment newInstance() {

        Bundle args = new Bundle();

        AddListDialogFragment fragment = new AddListDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    EditText name, desc;
    private DialogInteraction interaction;

    public void setInteraction(DialogInteraction interaction) {
        this.interaction = interaction;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_list, container, false);
        Button add = view.findViewById(R.id.add);
        name = view.findViewById(R.id.dialog_add_list_name);
        desc = view.findViewById(R.id.dialog_add_list_description);
        add.setOnClickListener(view1 -> {

            ShoppingList shoppingList = new ShoppingList();
            shoppingList.setName(name.getText().toString());
            shoppingList.setDescription(desc.getText().toString());

            shoppingList.save();
            interaction.onDismiss();
            dismiss();
        });

        return view;
    }

    public interface DialogInteraction {
        void onDismiss();
    }
}
