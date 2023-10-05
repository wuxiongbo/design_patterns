package my_demo.iterable;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyIterable<T> implements Iterable<T>{


    private final T[] arr;

    public MyIterable(Class<T> kind) {
        arr = (T[]) Array.newInstance(kind, 10);
    }


    @Override
    public Iterator<T> iterator() {
        return new Itr(arr);
    }

    public T set(int index, T element) {
        T oldValue = arr[index];
        arr[index] = element;
        return oldValue;
    }



    private class Itr implements Iterator<T>{

        private int cursor;
        private final T[] arr;

        Itr(T[] arr) {
            this.arr = arr;
        }


        @Override
        public boolean hasNext() {
            return cursor < arr.length;
        }

        @Override
        public T next() {

            int i = cursor;

            if (i >= arr.length) {
                throw new NoSuchElementException();
            }

            cursor = i + 1;

            return arr[i];
        }
    }

}
