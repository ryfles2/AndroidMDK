package com.wfis.wfis_shop.dialogs;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.wfis.wfis_shop.R;
import com.wfis.wfis_shop.models.Product;
import com.wfis.wfis_shop.models.Product_Table;

public class ProductEditingDialog extends DialogFragment{

    public static ProductEditingDialog newInstance(int id) {

        Bundle args = new Bundle();
        args.putInt("ID", id);
        ProductEditingDialog fragment = new ProductEditingDialog();
        fragment.setArguments(args);
        return fragment;
    }

    private EditText name, value;
    private Button save;
    private Product product;
    private DialogInteraction interaction;

    public void setInteraction(DialogInteraction interaction) {
        this.interaction = interaction;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int idProduct = getArguments().getInt("ID");
        product = SQLite.select().from(Product.class).where(Product_Table.id.eq(idProduct)).querySingle();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_edit_product, container, false);
        name = view.findViewById(R.id.name);
        value = view.findViewById(R.id.value);
        save = view.findViewById(R.id.update);
        setProduct();

        save.setOnClickListener(view1 -> {
            product.setName(name.getText().toString());
            product.setValue(value.getText().toString());
            product.update();
            if (interaction != null) {
                interaction.onDismiss();
            }
            dismiss();
        });

        return view;
    }

    private void setProduct() {
        name.setText(product.getName());
        value.setText(product.getValue());
    }


    public interface DialogInteraction {
        void onDismiss();
    }
}