package com.example.dumika.bitsandpizzas2;


import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;


public class InfoFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RelativeLayout layout = (RelativeLayout)inflater
                .inflate(R.layout.fragment_pizza_material, container, false);
        RecyclerView pizzaRecycler = (RecyclerView)layout.findViewById(R.id.pizza_recycler);

        String[] pizzaNames = new String[Pizza.pizzas.size()];
        for (int i = 0; i < Pizza.pizzas.size(); i++) {
            pizzaNames[i] = Pizza.pizzas.get(i).getName();
            (Pizza.pizzas.get(i)).setImageResourceId(R.drawable.images);
        }

        int[] pizzaImages = new int[Pizza.pizzas.size()];
        for (int i = 0; i < Pizza.pizzas.size(); i++) {
            pizzaImages[i] = Pizza.pizzas.get(i).getImageResourceId();
        }

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        pizzaRecycler.setLayoutManager(layoutManager);
        CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(pizzaNames, pizzaImages);
        pizzaRecycler.setAdapter(adapter);
        adapter.setListener(new CaptionedImagesAdapter.Listener() {
            public void onClick(final int position) {

                AlertDialog ad = new AlertDialog.Builder(getActivity())
                        .create();
                ad.setCancelable(true);
                ad.setTitle("Válassza ki a paramétereket!");
                ad.setMessage("Mennyiség: ");
                ad.setButton(AlertDialog.BUTTON_NEUTRAL, "Kosárba",
                        new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        String mailID = "message";

                        Rendeles r = new Rendeles(Pizza.pizzas.get(position).getAr(),
                                Pizza.pizzas.get(position).getName());
                        Rendeles.rendelesek.add(r);

                    }
                });

                ad.show();
            }
        });

        return layout;
    }
}
