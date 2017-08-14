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
package org.amahi.anywhere.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.amahi.anywhere.R;

import java.util.ArrayList;

public class NonAdminUserAdapter  extends RecyclerView.Adapter<NonAdminUserAdapter.NonAdminUserViewHolder>{

    private ArrayList<String> nonAdminUsers = new ArrayList<>();

    class NonAdminUserViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        NonAdminUserViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.text);
        }
    }

    @Override
    public NonAdminUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NonAdminUserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_server_share_item, parent, false));
    }

    @Override
    public void onBindViewHolder(NonAdminUserViewHolder holder, int position) {
        holder.textView.setText(nonAdminUsers.get(position));
    }

    @Override
    public int getItemCount() {
        return nonAdminUsers.size();
    }

    public void replaceWith(ArrayList<String> nonAdminUsers){
        this.nonAdminUsers = nonAdminUsers;
    }
}