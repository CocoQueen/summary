package com.coco.zxingdemo.progress;

import android.graphics.PointF;

/**
 * Created by ydx on 18-10-8.
 */

public class ChartUtil {

    /**
     * 依圆心坐标，半径，扇形角度，计算出扇形终射线与圆弧交叉点的xy坐标
     *
     * @param cirX
     * @param cirY
     * @param radius
     * @param cirAngle
     * @return
     */
    public static PointF calcArcEndPointXY(float cirX, float cirY, float radius, float cirAngle) {
        float posX = 0.0f;
        float posY = 0.0f;
        //将角度转换为弧度
        float arcAngle = (float) (Math.PI * cirAngle / 180.0);
        if (cirAngle < 90) {
            posX = (float) (cirX + Math.cos(arcAngle) * radius);
            posY = (float) (cirY + Math.sin(arcAngle) * radius);
        } else if (cirAngle == 90) {
            posX = cirX;
            posY = cirY + radius;
        } else if (cirAngle > 90 && cirAngle < 180) {
            arcAngle = (float) (Math.PI * (180 - cirAngle) / 180.0);
            posX = cirX - (float) (Math.cos(arcAngle)) * radius;
            posY = cirY + (float) (Math.sin(arcAngle)) * radius;

        } else if (cirAngle == 180) {
            posX = cirX - radius;
            posY = cirY;
        } else if (cirAngle > 180 && cirAngle < 270) {
            arcAngle = (float) (Math.PI * (cirAngle - 180) / 180.0);
            posX = cirX - (float) (Math.cos(arcAngle)) * radius;
            posY = cirY - (float) (Math.sin(arcAngle)) * radius;
        } else if (cirAngle == 270) {
            posX = cirX;
            posY = cirY - radius;
        } else {
            arcAngle = (float) (Math.PI * (360 - cirAngle) / 180.0);
            posX = cirX + (float) (Math.cos(arcAngle)) * radius;
            posY = cirY - (float) (Math.sin(arcAngle)) * radius;
        }
        return new PointF(posX, posY);
    }

    public static PointF calcArcEndPointXY(float cirX, float cirY, float radius, float cirAngle, float orginAngle) {
        cirAngle = (orginAngle + cirAngle) % 360;
        return calcArcEndPointXY(cirX, cirY, radius, cirAngle);
    }

    public OnProgressChangeListener changeListener;

    public void setChangeListener(OnProgressChangeListener changeListener) {
        this.changeListener = changeListener;
    }

    public interface OnProgressChangeListener {
        void onProgressChange(int duration, int progress);

        void onProgressChangeEnd(int duration, int progress);
    }
}
