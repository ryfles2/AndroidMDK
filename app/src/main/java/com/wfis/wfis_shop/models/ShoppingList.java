package com.wfis.wfis_shop.models;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.wfis.wfis_shop.db.AppDatabase;

import java.util.List;

@Table(database = AppDatabase.class)
public class ShoppingList extends BaseModel {

    @PrimaryKey(autoincrement = true)
    @Column
    private int id;

    @Column
    private String name;

    @Column
    private String description;

    private List<Product> productList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany
    public List<Product> getProductList() {
        return SQLite.select().from(Product.class).where(Product_Table.shoppingList_id.eq(id)).queryList();
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
