package com.tcl.common.mediaplayer.video.UI;

import com.tcl.common.mediaplayer.utils.TypefaceTextView;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

public class ScrollingTextView extends TypefaceTextView {   
    
    public ScrollingTextView(Context context, AttributeSet attrs, int defStyle) {   
        super(context, attrs, defStyle);   
    }   
    
    public ScrollingTextView(Context context, AttributeSet attrs) {   
        super(context, attrs);   
    }   
    
    public ScrollingTextView(Context context) {   
        super(context);   
    }   
    @Override  
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {   
        if(focused)   
            super.onFocusChanged(focused, direction, previouslyFocusedRect);   
    }   
    
    @Override  
    public void onWindowFocusChanged(boolean focused) {   
        if(focused)   
            super.onWindowFocusChanged(focused);   
    }   
    
    
    @Override  
    public boolean isFocused() {   
        return true;   
    }   
    


}
