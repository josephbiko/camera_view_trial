package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.otaliastudios.cameraview.overlay.OverlayLayout;

public class OverlayLayout2 extends OverlayLayout {
    /**
     * We set {@link #setWillNotDraw(boolean)} to false even if we don't draw anything.
     * This ensures that the View system will call {@link #draw(Canvas)} on us instead
     * of short-circuiting to {@link #dispatchDraw(Canvas)}.
     * <p>
     * That would be a problem for us since we use {@link #draw(Canvas)} to understand if
     * we are currently drawing on the preview or not.
     *
     * @param context a context
     */
    public OverlayLayout2(@NonNull Context context) {
    super(context);
}

    public void invalidateRecursive(ViewGroup layout) {
        int count = getChildCount();
        View child;
        for (int i = 0; i < count; i++) {
            child = layout.getChildAt(i);
            if(child instanceof ViewGroup)
                invalidateRecursive((ViewGroup) child);
            else
                child.invalidate();
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        invalidateRecursive(this);
    super.dispatchDraw(canvas);
    }
}
