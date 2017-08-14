/*
 * Copyright (c) 2014 Amahi
 *
 * This file is part of Amahi.
 *
 * Amahi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Amahi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Amahi. If not, see <http ://www.gnu.org/licenses/>.
 */
package org.amahi.anywhere.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.amahi.anywhere.R;
import org.amahi.anywhere.adapter.NonAdminUserAdapter;
import org.amahi.anywhere.util.RecyclerItemClickListener;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class NonAdminUserFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private NonAdminUserAdapter nonAdminUserAdapter;
    private ArrayList<String> mNonAdminUsers;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = layoutInflater.inflate(R.layout.fragment_non_admin_users, container, false);
        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.non_admin_recv);
        nonAdminUserAdapter = new NonAdminUserAdapter();
        mNonAdminUsers = new ArrayList<>();
        mNonAdminUsers.add("okatticus");
        mNonAdminUsers.add("codingblazer");
        mNonAdminUsers.add("opticod");
        nonAdminUserAdapter.replaceWith(mNonAdminUsers);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        mRecyclerView.addItemDecoration(new
                DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(nonAdminUserAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
            askForPin(mNonAdminUsers.get(position));
            }
        }));

        return rootView;
    }

    private void askForPin(String userName){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setTitle("Enter password for "+userName);
        builder.setView(inflater.inflate(R.layout.alert_custom_dialog, null))
                .setPositiveButton("Authenticate", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        showAlert();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.create();
        builder.show();
    }

    private void showAlert(){
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
        }
        else {
            builder = new AlertDialog.Builder(getContext());
        }
        builder.setTitle("Lock to a non admin user")
                .setMessage("Are you sure you want to lock to the particular user. To access the admin files you'll have to log out and then login again?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }
}
