package visual.heap;

/**
 * Visual Heap
 * Created 11/2/15
 * @author Connor Crawford.
 */
public class MyHeap {
    public static int SIZE = 20;
    private int lastIndex = 0;
    final int[] data = new int[MyHeap.SIZE];

    /**
     * Inserts an integer value into the MyHeap
     * @param value the value to be inserted
     */
    public void insert(int value) {
        if (lastIndex < SIZE) {
            data[lastIndex] = value;
            int currentIndex = lastIndex, parentIndex = getParentIndex(currentIndex);
            lastIndex++;
            if (currentIndex != 0) {
                // Sift up
                while (data[currentIndex] < data[parentIndex]) {
                    int temp = data[parentIndex];
                    data[parentIndex] = data[currentIndex];
                    data[currentIndex] = temp;
                    currentIndex = parentIndex;
                    parentIndex = getParentIndex(currentIndex);
                }
            }
        } else
            System.out.println("Error: attempting to insert more elements than available. Please delete an element and try again.");
    }

    /**
     * Deletes the min element from the heap
     */
    public void deleteMin() {
        if (lastIndex == 0) {
            System.out.println("Error: attempting to delete from an empty heap.");
            return;
        }
        data[0] = data[--lastIndex];
        if (lastIndex != 0) {
            int parentIndex = 0, currentIndex, leftCIndex, rightCIndex;
            // Sift down
            do {
                // Calculate the indices of the parent's children
                leftCIndex = parentIndex * 2 + 1;
                rightCIndex = parentIndex * 2 + 2;

                // Figure out which whether to compare the left or right child or if complete
                if (rightCIndex >= lastIndex) {
                    if (leftCIndex >= lastIndex)
                        return; // No more children, can return
                    currentIndex = leftCIndex; // No right child, left child only option
                } else {
                    if (data[leftCIndex] <= data[rightCIndex])
                        currentIndex = leftCIndex;
                    else
                        currentIndex = rightCIndex;
                }
                if (data[parentIndex] > data[currentIndex]) {
                    // Swap
                    int temp = data[currentIndex];
                    data[currentIndex] = data[parentIndex];
                    data[parentIndex] = temp;
                    parentIndex = currentIndex;
                } else
                    return;
            } while (rightCIndex < lastIndex && leftCIndex < lastIndex);
        }
    }

    /**
     * Finds the specified node's parent index
     * @param childIndex the index of the child node whose parent index is being found
     * @return the index of the child's parent node
     */
    private int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    /**
     * Getter for lastIndex
     * @return the index of the last element in the MyHeap
     */
    public int getLastIndex() {
        return lastIndex;
    }

}

