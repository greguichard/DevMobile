package com.example.projetdevmobile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View implements GestureDetector.OnGestureListener  {

    private Activity activity;
    public void setActivity(Activity act){
        this.activity = act;
    }

    private GestureDetector gestureDetector;

    private Paint paint = new Paint();
    private GameBoard gameBoard = new GameBoard();


    private float gridWidth;
    private float gridSeparatorSize;
    private float cellWidth;
    private float marginGrid;

    public GameView(Context context) {
        super(context);
        this.init();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    private void init() {
        // Activate the gesture detector
        gestureDetector = new GestureDetector( getContext(),  this );
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // We compute some sizes
        gridSeparatorSize = (w / 9f) / 20f;
        marginGrid = w*0.05f;
        gridWidth = w*0.9f;                                  // Size of the grid (it's a square)
        cellWidth = gridWidth / 5f;                     // Size of a cell (it's a square too)

    }


    @Override
    protected void onDraw(Canvas canvas) {

        // --- On dessine les cellules ---
        for( int y=0; y<5; y++ ) {
            for( int x=0; x<5; x++ ) {

                int backgroundColor = Color.WHITE;

                // On change la couleur du fond si la valeur est à 1
                if ( gameBoard.cells[y][x].Value==1 ) {
                    backgroundColor = Color.BLACK;
                }

                // Draw the background for the current cell
                paint.setColor( backgroundColor );
                canvas.drawRect(x * cellWidth+marginGrid,y * cellWidth + 150 ,(x+1) * cellWidth+marginGrid,(y+1) * cellWidth + 150, paint);
            }
        }

        // --- On dessine les lignes de la grille ---
        paint.setColor( Color.BLACK );
        paint.setStrokeWidth( gridSeparatorSize );
        for( int i=0; i<=5; i++ ) {
            canvas.drawLine( i*cellWidth+marginGrid, 150, i*cellWidth+marginGrid, 150+cellWidth*5, paint );
            canvas.drawLine( marginGrid,150+i*cellWidth, cellWidth*5+marginGrid, 150+i*cellWidth, paint );
        }
    }


    // Override from View
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {

        // --- On verifie qu'on a cliqué sur la grille ---
        if ( e.getY() > 150 && e.getY() < 150+gridWidth && e.getX()>marginGrid && e.getX() < marginGrid+gridWidth) {
            int cellX = (int)( (e.getX()-marginGrid) / cellWidth );
            int cellY = (int)( (e.getY()-150) / cellWidth );

            gameBoard.currentCellX = cellX;
            gameBoard.currentCellY = cellY;
            gameBoard.pushValue();
            postInvalidate();
            if(gameBoard.win()){
                Intent intent = new Intent(activity.getBaseContext(),activity_save.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("score", gameBoard.nbCoups);
                activity.getBaseContext().startActivity(intent);
            }
            return true;
        }
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
