package com.biryanistudio.udacityapi.Models;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Aakaash on 24/07/16 at 12:27 PM.
 */

public class EmptyRecyclerView extends RecyclerView {

    private View emptyView;

    final private AdapterDataObserver observer = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeChanged(final int positionStart, final int itemCount) {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeChanged(final int positionStart, final int itemCount, Object payload) {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeInserted(final int positionStart, final int itemCount) {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeRemoved(final int positionStart, final int itemCount) {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeMoved(final int fromPosition, final int toPosition, final int itemCount) {
            checkIfEmpty();
        }
    };

    public EmptyRecyclerView(final Context context) {
        super(context);
    }

    public EmptyRecyclerView(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
    }

    public EmptyRecyclerView(final Context context, @Nullable final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
    }

    private void checkIfEmpty() {
        if (emptyView != null && getAdapter() != null) {
            if (getAdapter().getItemCount() == 0) {
                emptyView.setVisibility(VISIBLE);
                EmptyRecyclerView.this.setVisibility(GONE);
            } else {
                emptyView.setVisibility(GONE);
                EmptyRecyclerView.this.setVisibility(VISIBLE);
            }
        }
    }

    @Override
    public void setAdapter(final Adapter adapter) {
        final Adapter oldAdapter = getAdapter();
        if (oldAdapter != null) {
            oldAdapter.unregisterAdapterDataObserver(observer);
        }
        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(observer);
        }
        checkIfEmpty();
    }

    public void setEmptyView(final View emptyView) {
        this.emptyView = emptyView;
        checkIfEmpty();
    }
}
