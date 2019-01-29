package com.moremoregreen.androidcurvedbottomnavigation;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.sdsmdg.harjot.vectormaster.VectorMasterView;
import com.sdsmdg.harjot.vectormaster.models.PathModel;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    CurvedBottomNavigationView bottomNavigationView;
    VectorMasterView fab0, fab1, fab2;
    RelativeLayout lin_id;
    PathModel outline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //View
        bottomNavigationView = findViewById(R.id.bottom_nav);
        fab0 = findViewById(R.id.fab0);
        fab1 = findViewById(R.id.fab1);
        fab2 = findViewById(R.id.fab2);
        lin_id = findViewById(R.id.lin_id);
        bottomNavigationView.inflateMenu(R.menu.main_menu);

        //Set event for bottom nav
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_shopping:
                Toast.makeText(this, "按到購物", Toast.LENGTH_SHORT).show();
                //Animation
                draw(6);
                //Find the correct path using name
                lin_id.setX(bottomNavigationView.mFirstCurveControlPoint1.x);
                fab0.setVisibility(View.VISIBLE);
                fab1.setVisibility(View.GONE);
                fab2.setVisibility(View.GONE);
                drawAnimation(fab0);

                break;
            case R.id.action_shipping:
                Toast.makeText(this, "按到運輸", Toast.LENGTH_SHORT).show();
                //Animation
                draw(2);
                //Find the correct path using name
                lin_id.setX(bottomNavigationView.mFirstCurveControlPoint1.x);
                fab0.setVisibility(View.GONE);
                fab1.setVisibility(View.VISIBLE);
                fab2.setVisibility(View.GONE);
                drawAnimation(fab1);

                break;
            case R.id.action_customer:
                Toast.makeText(this, "按到客戶", Toast.LENGTH_SHORT).show();
                //Animation
                draw();
                //Find the correct path using name
                lin_id.setX(bottomNavigationView.mFirstCurveControlPoint1.x);
                fab0.setVisibility(View.GONE);
                fab1.setVisibility(View.GONE);
                fab2.setVisibility(View.VISIBLE);
                drawAnimation(fab2);
                break;
        }
        return true;
    }

    private void draw() {
        bottomNavigationView.mFirstCurveStartPoint.set((bottomNavigationView.mNavigationBarWidth*10/12)
        -(bottomNavigationView.CURVE_CIRCLE_RADIUS*2)
        -(bottomNavigationView.CURVE_CIRCLE_RADIUS/3) , 0);

        bottomNavigationView.mFirstCurveEndPoint.set(bottomNavigationView.mNavigationBarWidth *10/ 12, bottomNavigationView.CURVE_CIRCLE_RADIUS
                + (bottomNavigationView.CURVE_CIRCLE_RADIUS / 4));

        bottomNavigationView.mSecondCurveStartPoint = bottomNavigationView.mFirstCurveEndPoint;
        bottomNavigationView.mSecondCurveEndPoint.set((bottomNavigationView.mNavigationBarWidth *10/12)
                + (bottomNavigationView.CURVE_CIRCLE_RADIUS * 2) + (bottomNavigationView.CURVE_CIRCLE_RADIUS / 3), 0);

        bottomNavigationView.mFirstCurveControlPoint1.set(bottomNavigationView.mFirstCurveStartPoint.x
                        + bottomNavigationView.CURVE_CIRCLE_RADIUS + (bottomNavigationView.CURVE_CIRCLE_RADIUS / 4),
                bottomNavigationView.mFirstCurveStartPoint.y);

        bottomNavigationView.mFirstCurveControlPoint2.set(bottomNavigationView.mFirstCurveEndPoint.x
                        - (bottomNavigationView.CURVE_CIRCLE_RADIUS * 2) + bottomNavigationView.CURVE_CIRCLE_RADIUS,
                bottomNavigationView.mFirstCurveEndPoint.y);
        //Second 再來一次
        bottomNavigationView.mSecondCurveControlPoint1.set(bottomNavigationView.mSecondCurveStartPoint.x
                        + (bottomNavigationView.CURVE_CIRCLE_RADIUS * 2) - bottomNavigationView.CURVE_CIRCLE_RADIUS,
                bottomNavigationView.mSecondCurveStartPoint.y);

        bottomNavigationView.mSecondCurveControlPoint2.set(bottomNavigationView.mSecondCurveEndPoint.x
                        - (bottomNavigationView.CURVE_CIRCLE_RADIUS + (bottomNavigationView.CURVE_CIRCLE_RADIUS / 4))
                , bottomNavigationView.mSecondCurveEndPoint.y);
    }

    private void drawAnimation(final VectorMasterView fab) {
        outline = fab.getPathModelByName("outline");   //Vector 記得加name
        outline.setStrokeColor(Color.WHITE);
        outline.setTrimPathEnd(0.0f);
        //Init valueAnimatior
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                outline.setTrimPathEnd((Float) valueAnimator.getAnimatedValue());
                fab.update();
            }
        });
        valueAnimator.start();
    }

    private void draw(int i) {
        bottomNavigationView.mFirstCurveStartPoint.set((bottomNavigationView.mNavigationBarWidth / i)
                - (bottomNavigationView.CURVE_CIRCLE_RADIUS * 2)
                - (bottomNavigationView.CURVE_CIRCLE_RADIUS / 3), 0);

        bottomNavigationView.mFirstCurveEndPoint.set(bottomNavigationView.mNavigationBarWidth / i, bottomNavigationView.CURVE_CIRCLE_RADIUS
                + (bottomNavigationView.CURVE_CIRCLE_RADIUS / 4));

        bottomNavigationView.mSecondCurveStartPoint = bottomNavigationView.mFirstCurveEndPoint;
        bottomNavigationView.mSecondCurveEndPoint.set((bottomNavigationView.mNavigationBarWidth / i)
                + (bottomNavigationView.CURVE_CIRCLE_RADIUS * 2) + (bottomNavigationView.CURVE_CIRCLE_RADIUS / 3), 0);

        bottomNavigationView.mFirstCurveControlPoint1.set(bottomNavigationView.mFirstCurveStartPoint.x
                        + bottomNavigationView.CURVE_CIRCLE_RADIUS + (bottomNavigationView.CURVE_CIRCLE_RADIUS / 4),
                bottomNavigationView.mFirstCurveStartPoint.y);

        bottomNavigationView.mFirstCurveControlPoint2.set(bottomNavigationView.mFirstCurveEndPoint.x
                        - (bottomNavigationView.CURVE_CIRCLE_RADIUS * 2) + bottomNavigationView.CURVE_CIRCLE_RADIUS,
                bottomNavigationView.mFirstCurveEndPoint.y);

        //Second 再來一次
        bottomNavigationView.mSecondCurveControlPoint1.set(bottomNavigationView.mSecondCurveStartPoint.x
                        + (bottomNavigationView.CURVE_CIRCLE_RADIUS * 2) - bottomNavigationView.CURVE_CIRCLE_RADIUS,
                bottomNavigationView.mSecondCurveStartPoint.y);

        bottomNavigationView.mSecondCurveControlPoint2.set(bottomNavigationView.mSecondCurveEndPoint.x
                        - (bottomNavigationView.CURVE_CIRCLE_RADIUS + (bottomNavigationView.CURVE_CIRCLE_RADIUS / 4))
                , bottomNavigationView.mSecondCurveEndPoint.y);


    }
}
