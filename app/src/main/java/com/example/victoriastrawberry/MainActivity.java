package com.example.victoriastrawberry;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.*;
import android.view.*;
import android.content.Context;

public class MainActivity extends Activity {
    @Override public void onCreate(Bundle b) {
        super.onCreate(b);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        setContentView(new GreetingView(this));
    }

    static class GreetingView extends View {
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        RectF button = new RectF();
        boolean second = false;

        GreetingView(Context c) {
            super(c);
            p.setTypeface(Typeface.create("sans", Typeface.BOLD));
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        @Override protected void onDraw(Canvas c) {
            int w = getWidth(), h = getHeight();
            if (!second) drawPalmScreen(c, w, h);
            else drawMenScreen(c, w, h);
        }

        void drawPalmScreen(Canvas c, int w, int h) {
            LinearGradient sky = new LinearGradient(0, 0, 0, h,
                    Color.rgb(95, 205, 245), Color.rgb(255, 216, 145), Shader.TileMode.CLAMP);
            p.setShader(sky); c.drawRect(0, 0, w, h, p); p.setShader(null);

            p.setColor(Color.rgb(54, 184, 205));
            c.drawRect(0, h * .67f, w, h, p);
            p.setColor(Color.rgb(242, 207, 128));
            Path beach = new Path();
            beach.moveTo(0, h*.61f); beach.quadTo(w*.5f,h*.72f,w,h*.61f);
            beach.lineTo(w,h); beach.lineTo(0,h); beach.close(); c.drawPath(beach,p);

            drawPalm(c, w*.14f, h*.72f, h*.42f, -1);
            drawPalm(c, w*.83f, h*.68f, h*.48f, 1);
            drawSun(c, w*.78f, h*.17f, Math.min(w,h)*.09f);

            button.set(w*.13f, h*.43f, w*.87f, h*.57f);
            p.setShadowLayer(22,0,10,0x66000000);
            p.setColor(Color.rgb(225, 52, 93));
            c.drawRoundRect(button, 70, 70, p);
            p.clearShadowLayer();

            p.setTextAlign(Paint.Align.CENTER);
            p.setTypeface(Typeface.create("sans", Typeface.BOLD));
            p.setColor(Color.WHITE);
            p.setTextSize(Math.min(w,h)*.075f);
            c.drawText("Жми сюда", w/2f, h*.515f, p);
        }

        void drawMenScreen(Canvas c, int w, int h) {
            LinearGradient bg = new LinearGradient(0,0,w,h,
                    Color.rgb(247,205,151), Color.rgb(56,169,186), Shader.TileMode.CLAMP);
            p.setShader(bg); c.drawRect(0,0,w,h,p); p.setShader(null);

            drawPalm(c,w*.08f,h*.28f,h*.28f,-1);
            drawPalm(c,w*.93f,h*.30f,h*.30f,1);
            drawSun(c,w*.78f,h*.12f,Math.min(w,h)*.07f);

            // Three adult, dark-skinned, muscular figures in swim trunks.
            drawMan(c,w*.23f,h*.30f,h*.43f,0xff6b3e28,0xff173d73);
            drawMan(c,w*.50f,h*.27f,h*.47f,0xff4f2b1c,0xff8c1f2d);
            drawMan(c,w*.77f,h*.30f,h*.43f,0xff75462d,0xff174d48);

            // Huge cutlet / burger held in front.
            p.setShadowLayer(12,0,6,0x55000000);
            p.setColor(0xff6f351d);
            c.drawRoundRect(new RectF(w*.25f,h*.49f,w*.75f,h*.67f), 28,28,p);
            p.setColor(0xffb95a2b);
            c.drawRoundRect(new RectF(w*.28f,h*.47f,w*.72f,h*.54f), 30,30,p);
            p.setColor(0xfff1cf65);
            c.drawRect(w*.29f,h*.55f,w*.71f,h*.585f,p);
            p.setColor(0xff4d8a2c);
            c.drawRect(w*.29f,h*.585f,w*.71f,h*.615f,p);
            p.setColor(0xff7c3b20);
            c.drawRoundRect(new RectF(w*.28f,h*.615f,w*.72f,h*.67f), 25,25,p);
            p.clearShadowLayer();

            p.setTextAlign(Paint.Align.CENTER);
            p.setTypeface(Typeface.create("sans",Typeface.BOLD));
            p.setShadowLayer(7,0,4,0x77000000);
            p.setColor(Color.WHITE);
            p.setTextSize(Math.min(w,h)*.085f);
            c.drawText("Артем, с добрым",w/2f,h*.79f,p);
            c.drawText("утром ☀️",w/2f,h*.88f,p);
            p.clearShadowLayer();
        }

        void drawMan(Canvas c,float x,float top,float size,int skin,int trunks) {
            p.setColor(skin);
            c.drawCircle(x,top+size*.13f,size*.11f,p);
            Path body=new Path();
            body.moveTo(x-size*.13f,top+size*.25f);
            body.cubicTo(x-size*.28f,top+size*.35f,x-size*.25f,top+size*.57f,x-size*.12f,top+size*.63f);
            body.lineTo(x+size*.12f,top+size*.63f);
            body.cubicTo(x+size*.25f,top+size*.57f,x+size*.28f,top+size*.35f,x+size*.13f,top+size*.25f);
            body.close(); c.drawPath(body,p);
            p.setStrokeWidth(size*.075f); p.setStrokeCap(Paint.Cap.ROUND);
            c.drawLine(x-size*.12f,top+size*.30f,x-size*.25f,top+size*.55f,p);
            c.drawLine(x+size*.12f,top+size*.30f,x+size*.25f,top+size*.55f,p);
            p.setColor(trunks);
            c.drawRoundRect(new RectF(x-size*.13f,top+size*.59f,x+size*.13f,top+size*.76f),12,12,p);
            p.setColor(skin);
            p.setStrokeWidth(size*.09f);
            c.drawLine(x-size*.06f,top+size*.74f,x-size*.12f,top+size*.95f,p);
            c.drawLine(x+size*.06f,top+size*.74f,x+size*.12f,top+size*.95f,p);
        }

        void drawPalm(Canvas c,float x,float base,float height,int side) {
            p.setColor(0xff76502f); p.setStrokeWidth(height*.045f);
            c.drawLine(x,base,x+side*height*.08f,base-height,p);
            p.setColor(0xff1f7a42);
            for(int i=0;i<7;i++){
                double a=(-Math.PI*.85)+(i*Math.PI*.28);
                float ex=x+side*(float)Math.cos(a)*height*.25f;
                float ey=base-height+(float)Math.sin(a)*height*.16f;
                p.setStrokeWidth(height*.055f);
                c.drawLine(x+side*height*.08f,base-height,ex,ey,p);
            }
        }

        void drawSun(Canvas c,float x,float y,float r) {
            p.setColor(0xffffe38a); p.setShadowLayer(30,0,0,0x55fff0a0); c.drawCircle(x,y,r,p); p.clearShadowLayer();
        }

        @Override public boolean onTouchEvent(MotionEvent e) {
            if(e.getAction()==MotionEvent.ACTION_UP && !second && button.contains(e.getX(),e.getY())) {
                second=true; invalidate();
            }
            return true;
        }
    }
}
