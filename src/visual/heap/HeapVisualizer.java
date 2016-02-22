package visual.heap;

import simplegui.GUIListener;
import simplegui.KeyboardListener;
import simplegui.SimpleGUI;

import java.awt.*;
import java.util.Random;

/**
 * Visual Heap
 * Created 11/2/15
 * @author Connor Crawford.
 */
public class HeapVisualizer implements GUIListener, KeyboardListener {
    private static final int SIZE = 30;
    private MyHeap heap = new MyHeap(), heapCopy = new MyHeap();
    private SimpleGUI simpleGUI = new SimpleGUI(640, 100);

    public HeapVisualizer() {
        simpleGUI.registerToGUI(this);
        simpleGUI.registerToKeyboard(this);
        simpleGUI.labelButton1("Insert");
        simpleGUI.labelButton2("Delete");
        simpleGUI.print("Enter a number > ");
        this.visualize();
    }

    /**
     * Draws the grid with SimpleGUI
     * Draws green box if that node did not change
     * Draws red box if that node did change
     * Draws gray box if that node is not used
     */
    private void visualize() {
        simpleGUI.eraseAllDrawables();
        int startXPos = simpleGUI.getWidth() / 2 - 10 * SIZE;
        int startYPos = simpleGUI.getHeight() / 2 - SIZE / 2;
        Color fill, border;
        for (int i = 0; i < MyHeap.SIZE; i++) {
            if (i < heap.getLastIndex()) {
                if (heap.data[i] == heapCopy.data[i]) {
                    fill = Color.GREEN;
                    border = new Color(64, 128, 0);
                } else {
                    fill = Color.RED;
                    border = new Color(128, 0, 0);
                }
            } else {
                fill = Color.LIGHT_GRAY;
                border = Color.DARK_GRAY;
            }
            simpleGUI.drawFilledBox(startXPos + (SIZE * i), startYPos, SIZE, SIZE, fill, 1.0, null);
            simpleGUI.drawBox(startXPos + (SIZE * i), startYPos, SIZE, SIZE, border, 1.0, 2, null);
            if (i < heap.getLastIndex())
                simpleGUI.drawText(heap.data[i] + "", startXPos + (SIZE * i) + 7, startYPos + SIZE/2 + 5);
        }
    }

    @Override
    public void reactToButton1() {
        for (int i = 0; i < MyHeap.SIZE; i++)
            heapCopy.data[i] = heap.data[i];
        int rand = new Random().nextInt(100);
        heap.insert(rand);
        visualize();
    }

    @Override
    public void reactToButton2() {
        for (int i = 0; i < MyHeap.SIZE; i++)
            heapCopy.data[i] = heap.data[i];
        heap.deleteMin();
        visualize();
    }

    @Override
    public void reactToSwitch(boolean b) {

    }

    @Override
    public void reactToSlider(int i) {

    }

    @Override
    public void reactToKeyboardEntry(String s) {
        for (int i = 0; i < MyHeap.SIZE; i++)
            heapCopy.data[i] = heap.data[i];
        heap.insert((int)simpleGUI.keyReadDouble());
        visualize();
    }

    @Override
    public void reactToKeyboardSingleKey(String s) {

    }
}
