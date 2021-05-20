package kr.ac.kpu.game.s2016180006.pokeman.framework.game;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.HashMap;

import kr.ac.kpu.game.s2016180006.pokeman.framework.iface.GameObject;
import kr.ac.kpu.game.s2016180006.pokeman.framework.iface.Recyclable;
import kr.ac.kpu.game.s2016180006.pokeman.framework.view.GameView;

public class BaseGame {
    private static final String TAG = BaseGame.class.getSimpleName();
    // singleton
    private static BaseGame instance;

    public static BaseGame get() {
        return instance;
    }
    public float frameTime;

    protected BaseGame(){
        instance = this;
    }

    //    Player player;
    ArrayList<ArrayList<GameObject>> layers;
    private static HashMap<Class,ArrayList<GameObject>> recycleBin = new HashMap<>();

    public void recycle(GameObject object) {
        Class clazz = object.getClass();
        ArrayList<GameObject> array = recycleBin.get(clazz);
        if(array == null){
            array = new ArrayList<>();
            recycleBin.put(clazz,array);
        }
        array.add(object);
    }
    public GameObject get(Class clazz){
        ArrayList<GameObject> array = recycleBin.get(clazz);
        if(array==null||array.isEmpty())return null;
        return array.remove(0);
    }

    public boolean initResources() {
        return false;
    }

    protected void initLayers(int layerCount) {
        layers = new ArrayList<>();
        for(int i = 0; i < layerCount; i++){
            layers.add(new ArrayList<>());
        }
    }

    public void update() {
        //if (!initialized) return;
        for(ArrayList<GameObject> objects : layers) {
            for (GameObject o : objects) {
                o.update();
            }
        }
    }

    public void draw(Canvas canvas) {
        for(ArrayList<GameObject> objects : layers) {
            for (GameObject o : objects) {
                o.draw(canvas);
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {

        return false;
    }

    public void add(int layerIndex, GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                ArrayList<GameObject> objects = layers.get(layerIndex);
                objects.add(gameObject);
            }
        });
    }

    public void remove(GameObject gameObject) {
        if(gameObject instanceof Recyclable){
            ((Recyclable) gameObject).recycle();
            recycle(gameObject);
        }
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                for(ArrayList<GameObject> objects : layers) {
                    boolean removed = objects.remove(gameObject);
                    if(removed) {
                        break;
                    }
                }
            }
        });
    }

    public ArrayList<GameObject> objectsAt(int layerIndex) {
        return layers.get(layerIndex);
    }
}