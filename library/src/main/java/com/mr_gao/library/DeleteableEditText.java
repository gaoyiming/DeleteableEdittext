package com.mr_gao.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.io.LineNumberReader;

/**
 * Created by Mr_g on 2017/4/18.
 */

public class DeleteableEditText extends RelativeLayout implements TextWatcher {
    private Context mContext;
    private LinearLayout content;
    private EditText edittext;

    private ImageView delete_img;
    private String hint;
    private RelativeLayout parent;
    private LayoutParams layoutParams;
    private LinearLayout.LayoutParams edit_params;

    public DeleteableEditText(Context context) {
        this(context, null);
    }

    public DeleteableEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DeleteableEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initAttrs(attrs);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.deleteedittext, this, true);
        delete_img = (ImageView) view.findViewById(R.id.delete);
        edittext = (EditText) view.findViewById(R.id.edittext);
        parent = (RelativeLayout) view.findViewById(R.id.parent);
        edittext.setHint(hint);
        edit_params = (LinearLayout.LayoutParams) edittext.getLayoutParams();


        edittext.setCursorVisible(false);
        content = (LinearLayout) view.findViewById(R.id.content);
        this.layoutParams = (LayoutParams) content.getLayoutParams();
        delete_img.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                edittext.setText("");
            }
        });
        edittext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                addListener();
            }
        });

        parent.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addListener();
            }
        });
        edittext.addTextChangedListener(this);

    }

    private void addListener() {
        layoutParams.removeRule(CENTER_HORIZONTAL);
        content.setLayoutParams(DeleteableEditText.this.layoutParams);

        edittext.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (!TextUtils.isEmpty(edittext.getText())) {
                        delete_img.setVisibility(VISIBLE);
                    } else {
                        delete_img.setVisibility(INVISIBLE);
                    }
                    DeleteableEditText.this.layoutParams.removeRule(CENTER_HORIZONTAL);
                    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    content.setLayoutParams(DeleteableEditText.this.layoutParams);
                    edit_params.weight = 1;
                    edittext.setLayoutParams(edit_params);
                    edittext.setCursorVisible(true);
                    edittext.setSelection(edittext.getText().length());
                } else {
                    DeleteableEditText.this.layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
                    content.setLayoutParams(DeleteableEditText.this.layoutParams);
                    layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;

                    delete_img.setVisibility(INVISIBLE);
                    edit_params.weight = 0;
                    edittext.setLayoutParams(edit_params);
                    edittext.setCursorVisible(false);

                }
            }
        });
        edittext.setFocusable(true);
        edittext.setCursorVisible(true);
        edittext.requestFocus();
    }

    private void initAttrs(AttributeSet attrs) {


        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.DeleteableEditText);
        hint = typedArray.getString(R.styleable.DeleteableEditText_hint);
        typedArray.recycle();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (edittext.length() > 0) {
            delete_img.setVisibility(VISIBLE);
        } else {
            delete_img.setVisibility(INVISIBLE);
        }

    }


}
