package cn.itcast.ssm;

//饿汉式
public class EagerSingleton {

    private static EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() {
    }

    public static EagerSingleton getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        int[] arr={1,2,3,5,7,9,11,15};
        int key=2;
        int i = commonBinarySearch(arr, key);
        System.out.println(i);
    }


    /**
     * 不使用递归的二分查找
     * title:commonBinarySearch
     *
     * @param arr
     * @param key
     * @return 关键字位置
     */
    public static int commonBinarySearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int middle = 0;            //定义middle

        if (key < arr[low] || key > arr[high] || low > high) {
            return -1;
        }

        while (low <= high) {
            middle = (low + high) / 2;
            if (arr[middle] > key) {
                //比关键字大则关键字在左区域
                high = middle - 1;
            } else if (arr[middle] < key) {
                //比关键字小则关键字在右区域
                low = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;        //最后仍然没有找到，则返回-1
    }

    //冒泡排序
   /* public static void main(String[] args) {
        int[] arr = {1, 6, 3, 2, 5};
        //控制比较轮数
        for (int i = 1; i < arr.length ; i++) {
            //每一轮都比上一轮少1次
            for (int j = 0; j < arr.length  - i; j++) {
                //比较相邻两个数
                if (arr[j] > arr[j + 1]) {
                    int temp;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }*/

   /* public static void main(String[] args) {
        int[] arr = {1, 6, 3, 2, 5};
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
                if (min != j) {
                    int temp;
                    temp = arr[j];
                    arr[j] = arr[min];
                    arr[min] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }*/

    //选择排序
    /*public static void main(String[] args) {
        int[] arr = {1, 6, 3, 2, 5};
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            if (min != i) {
                int temp;
                temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
        System.out.println(Arrays.toString(arr));
    }*/

}
