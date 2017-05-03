package com.mr_gao.deleteedittext;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.mr_gao.deleteableedittext.R;

/**
 * Created by Mr_g on 2017/4/18.
 */

public class DeleteableEditText extends RelativeLayout {
    private  Context mContext;

    public DeleteableEditText(Context context) {
        this(context,null);
    }

    public DeleteableEditText(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DeleteableEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;
        initAttrs(attrs);
        init();
    }

    private void init() {

    }

    private void initAttrs(AttributeSet attrs) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.deleteedittext, this, true);


    }
}
