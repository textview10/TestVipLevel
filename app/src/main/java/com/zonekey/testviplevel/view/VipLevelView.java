package com.zonekey.testviplevel.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.zonekey.testviplevel.R;

/**
 * Created by xu.wang
 * Date on  2017/10/16 14:32:39.
 *
 * @des
 */

public class VipLevelView extends LinearLayout {
    private View view;
    private ImageView iv_vip_level;
    private View line0, line1, line2, line3, line4, line5;
    private int lineWidth;          //分割线的宽度
    private ImageView iv_vip1;
    private int vipWidth = 0;       //vip 等级快读
    private int level = 0;      //当前等级
    private int choiceVipWidth;     //选中的Vip控件宽度
    private int disVip0, disVip1, disVip2, disVip3, disVip4;    //分别是vip0 - vip5需要位移的距离
    private int lineChoiceColor = Color.YELLOW;     //线条被选中的颜色
    private int lineUnchoiceColor = Color.GRAY;     //线条未被选中的颜色


    public VipLevelView(Context context) {
        this(context, null);
    }


    public VipLevelView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public VipLevelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        view = LayoutInflater.from(getContext()).inflate(R.layout.view_vip_level, this, false);
        initialView();
        this.addView(view);
    }

    private void initialView() {
        iv_vip_level = (ImageView) view.findViewById(R.id.iv_vip_choice);
        line0 = view.findViewById(R.id.view_vip_line0);
        line1 = view.findViewById(R.id.view_vip_line1);
        line2 = view.findViewById(R.id.view_vip_line2);
        line3 = view.findViewById(R.id.view_vip_line3);
        line4 = view.findViewById(R.id.view_vip_line4);
        line5 = view.findViewById(R.id.view_vip_line5);
        iv_vip1 = (ImageView) view.findViewById(R.id.iv_vip1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        lineWidth = line1.getMeasuredWidth();
        vipWidth = iv_vip1.getMeasuredWidth();
        //
        choiceVipWidth = iv_vip_level.getMeasuredWidth();
        iv_vip_level.setTranslationX(-choiceVipWidth);
        disVip0 = getLevelMoveDistance(0);
        disVip1 = getLevelMoveDistance(1);
        disVip2 = getLevelMoveDistance(2);
        disVip3 = getLevelMoveDistance(3);
        disVip4 = getLevelMoveDistance(4);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        setVipLevel(level);
    }

    /**
     * 设置vip等级
     * @param level
     */
    public void setVipLevel(int level) {
        if (level < 0 || level > 4) {
            return;
        }
        this.level = level;
        //有一个为0就不去画,一般发生在onMeasure前,会在onLayout后补画
        if (lineWidth == 0 || vipWidth == 0 || choiceVipWidth == 0){
            return;
        }
        changeAllLineInvaild(); //设置全部线条为未选中的颜色
        switch (level) {
            case 0:
                iv_vip_level.setImageResource(R.drawable.xuanzhong0);
                animTranslate(getLevelMoveDistance(0),0);
                break;

            case 1:
                iv_vip_level.setImageResource(R.drawable.xuanzhong1);
                animTranslate(getLevelMoveDistance(1),1);
                break;
            case 2:
                iv_vip_level.setImageResource(R.drawable.xuanzhong2);
                animTranslate(getLevelMoveDistance(2),2);
                break;

            case 3:
                iv_vip_level.setImageResource(R.drawable.xuanzhong3);
                animTranslate(getLevelMoveDistance(3),3);
                break;

            case 4:
                iv_vip_level.setImageResource(R.drawable.xuanzhong4);
                animTranslate(getLevelMoveDistance(4),4);
                break;
        }
    }

    /**
     * 获取现在的vip等级
     * @return
     */
    public int getLevel() {
        return level;
    }

    /**
     * 根据不同等级获取位置距离
     *
     * @param level
     * @return
     */
    private int getLevelMoveDistance(int level) {
        return (lineWidth + vipWidth) * (level + 1) - vipWidth / 2 - choiceVipWidth / 2;
    }

    private void animTranslate(int moveDistance,int level) {
        ValueAnimator animator = ValueAnimator.ofFloat(0, moveDistance);
        animator.setTarget(iv_vip_level);
        animator.setDuration((level +1) * 300).start();

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int moveDis = 0;
                moveDis += (Float) animation.getAnimatedValue();
                iv_vip_level.setTranslationX((Float) animation.getAnimatedValue());
                changeLineState(moveDis);
            }
        });
    }

    /**
     * 根据动画位移位置动态改变线条颜色,if else判断顺序不能变
     *
     * @param moveDis
     */
    private void changeLineState(int moveDis) {
        if (moveDis >= disVip4) {
            line5.setBackgroundColor(lineChoiceColor);
            line4.setBackgroundColor(lineChoiceColor);
        } else if (moveDis >= disVip3) {
            line3.setBackgroundColor(lineChoiceColor);
        } else if (moveDis >= disVip2) {
            line2.setBackgroundColor(lineChoiceColor);
        } else if (moveDis >= disVip1) {
            line1.setBackgroundColor(lineChoiceColor);
        } else if (moveDis >= disVip0) {
            line0.setBackgroundColor(lineChoiceColor);
        }
    }
    private void changeAllLineInvaild(){
        line0.setBackgroundColor(lineUnchoiceColor);
        line1.setBackgroundColor(lineUnchoiceColor);
        line2.setBackgroundColor(lineUnchoiceColor);
        line3.setBackgroundColor(lineUnchoiceColor);
        line4.setBackgroundColor(lineUnchoiceColor);
        line5.setBackgroundColor(lineUnchoiceColor);
    }
}
