package com.example.victoriastrawberry;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.*;
import android.view.*;
import android.content.Context;
import java.util.*;

public class MainActivity extends Activity {
    @Override public void onCreate(Bundle b) {
        super.onCreate(b);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        setContentView(new StrawberryView(this));
    }
    static class StrawberryView extends View {
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG); Random rnd = new Random(7); boolean result=false; ArrayList<Heart> hearts=new ArrayList<>(); RectF button=new RectF();
        StrawberryView(Context c){super(c); p.setTypeface(Typeface.create("sans",Typeface.BOLD)); setLayerType(View.LAYER_TYPE_SOFTWARE,null);}
        void makeHearts(){hearts.clear(); for(int i=0;i<42;i++) hearts.add(new Heart(rnd.nextFloat(),rnd.nextFloat(),18+rnd.nextInt(42),rnd.nextBoolean()));}
        @Override protected void onDraw(Canvas c){int w=getWidth(),h=getHeight(); LinearGradient g=new LinearGradient(0,0,w,h,Color.rgb(255,170,202),Color.rgb(255,224,236),Shader.TileMode.CLAMP); p.setShader(g); c.drawRect(0,0,w,h,p); p.setShader(null); if(!result)drawStart(c,w,h);else drawResult(c,w,h);}
        void drawStart(Canvas c,int w,int h){p.setColor(Color.WHITE);p.setTextAlign(Paint.Align.CENTER);p.setTextSize(Math.min(w,h)*.065f);c.drawText("Для Виктории ❤️",w/2,h*.28f,p);button.set(w*.12f,h*.43f,w*.88f,h*.57f);p.setShadowLayer(22,0,10,0x55000000);p.setColor(Color.rgb(235,35,111));c.drawRoundRect(button,80,80,p);p.clearShadowLayer();p.setColor(Color.WHITE);p.setTextSize(Math.min(w,h)*.075f);c.drawText("Жми сюда",w/2,h*.515f,p);p.setTextSize(Math.min(w,h)*.035f);p.setTypeface(Typeface.create("sans",Typeface.NORMAL));c.drawText("✨ маленькая кнопка с большим сюрпризом ✨",w/2,h*.67f,p);p.setTypeface(Typeface.create("sans",Typeface.BOLD));}
        void drawResult(Canvas c,int w,int h){for(Heart x:hearts)drawHeart(c,x.x*w,x.y*h,x.size,x.pink?0xffff4f92:0xffe91e63);p.setTextAlign(Paint.Align.CENTER);p.setTypeface(Typeface.create("sans",Typeface.BOLD));p.setShadowLayer(8,0,4,0x55000000);p.setColor(Color.WHITE);p.setTextSize(Math.min(w,h)*.13f);c.drawText("Виктория",w/2,h*.34f,p);p.setShadowLayer(5,0,3,0x44000000);p.setTextSize(Math.min(w,h)*.085f);c.drawText("значит",w/2,h*.45f,p);p.setTextSize(Math.min(w,h)*.115f);c.drawText("клубника 🍓",w/2,h*.57f,p);p.clearShadowLayer();drawStrawberry(c,w*.5f,h*.76f,Math.min(w,h)*.11f);}
        void drawHeart(Canvas c,float x,float y,float s,int color){p.setColor(color);Path q=new Path();q.moveTo(x,y+s*.8f);q.cubicTo(x-s*1.6f,y-s*.1f,x-s*1.05f,y-s*1.35f,x,y-s*.55f);q.cubicTo(x+s*1.05f,y-s*1.35f,x+s*1.6f,y-s*.1f,x,y+s*.8f);c.drawPath(q,p);}
        void drawStrawberry(Canvas c,float x,float y,float s){p.setColor(0xffe52b45);Path q=new Path();q.moveTo(x,y-s);q.cubicTo(x-s*1.2f,y-s*.55f,x-s*.9f,y+s*.9f,x,y+s*1.15f);q.cubicTo(x+s*.9f,y+s*.9f,x+s*1.2f,y-s*.55f,x,y-s);c.drawPath(q,p);p.setColor(0xff42a84a);Path l=new Path();l.moveTo(x,y-s*.92f);l.lineTo(x-s*.55f,y-s*1.3f);l.lineTo(x-s*.1f,y-s*.72f);l.lineTo(x+s*.1f,y-s*1.38f);l.lineTo(x+s*.55f,y-s*.92f);l.close();c.drawPath(l,p);p.setColor(0xffffd34e);for(int i=0;i<8;i++){double a=i*Math.PI/4;c.drawCircle((float)(x+Math.cos(a)*s*.45),(float)(y+Math.sin(a)*s*.6),s*.045f,p);}}
        @Override public boolean onTouchEvent(MotionEvent e){if(e.getAction()==MotionEvent.ACTION_UP&&!result&&button.contains(e.getX(),e.getY())){result=true;makeHearts();invalidate();}return true;}
        static class Heart{float x,y,size;boolean pink;Heart(float a,float b,float s,boolean p){x=a;y=b;size=s;pink=p;}}
    }
}
