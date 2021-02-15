package com.newsapp.ui.component;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import androidx.annotation.ColorInt;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import com.newsapp.R;

public class EBDialogProgressBar extends FrameLayout {
    private @ColorInt
    int inputProgressBarColor;
    private boolean vlInputProgressIndeterminate;
    private boolean vlOutsideTouchDismissProgress;
    private ProgressBar mProgressBar;
    private Dialog mDialog;
    public EBDialogProgressBar(Context context) {
        super(context);
        configure(context,null);
    }

    public EBDialogProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        configure(context,attrs);
    }

    private void configure(Context context, AttributeSet attrs) {
        final TypedArray attributes = context.obtainStyledAttributes(
                attrs,
                R.styleable.EBProgressBar,
                0, 0);

        initializeView(context);
        initByAttributes(context,attributes);
        attributes.recycle();

    }
    public  void showEBProgress(){
        if(mDialog!=null)
            mDialog.show();
    }
    public  void hideEBProgress(){
        if(mDialog!=null && mDialog.isShowing())
            mDialog.dismiss();
    }

    private void initializeView(Context context) {
        mDialog = new Dialog(context);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.eb_progressbar);
        mProgressBar=mDialog.findViewById(R.id.ebProgressbar);
        mDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

    }

    private void initByAttributes(Context context,TypedArray attributes) {
        inputProgressBarColor = attributes.getColor(R.styleable.EBProgressBar_ebProgressBarColor, ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
        vlInputProgressIndeterminate = attributes.getBoolean(R.styleable.EBProgressBar_ebProgressIndeterminate, true);
        vlOutsideTouchDismissProgress = attributes.getBoolean(R.styleable.EBProgressBar_ebDismisOnScreen, false);
        setAttributesToView();
    }

    public void setVlOutsideTouchDismissProgress(boolean vlOutsideTouchDismissProgress) {
        this.vlOutsideTouchDismissProgress = vlOutsideTouchDismissProgress;
        setAttributesToView();
    }

    public void setInputProgressBarColor(@ColorInt int inputProgressBarColor){
        this.inputProgressBarColor = inputProgressBarColor;
        setAttributesToView();
    }

    public void setVlInputProgressIndeterminate(boolean vlInputProgressIndeterminate){
        this.vlInputProgressIndeterminate = vlInputProgressIndeterminate;
        setAttributesToView();
    }


    /*TODO : apply the attributes to the view  */
    @SuppressLint("ResourceType")
    private void setAttributesToView() {

        mDialog.setCanceledOnTouchOutside(vlOutsideTouchDismissProgress);
        mDialog.setCancelable(vlOutsideTouchDismissProgress);
        mProgressBar.setIndeterminate(vlInputProgressIndeterminate);
         if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Drawable wrapDrawable = DrawableCompat.wrap(mProgressBar.getIndeterminateDrawable());
            DrawableCompat.setTint(wrapDrawable, getResources().getColor(inputProgressBarColor));
            mProgressBar.setIndeterminateDrawable(DrawableCompat.unwrap(wrapDrawable));
        }else{
            mProgressBar.getIndeterminateDrawable()
                    .setColorFilter(inputProgressBarColor, PorterDuff.Mode.SRC_IN);
        }

        mProgressBar.setVisibility(VISIBLE);

    }

}

