package algorithm.data_structure;

import java.util.Random;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/1 15:14
 */
public class IndexHeap {

    private int[] data;
    private int[] indexs;    //存储索引的数组
    private int count; //当前节点数
    private int capacity; //容量(不包括索引0)
    private int[] reverse;     //reverse[i]的值表示索引i在indexs[j]==i中的位置j;即indexs[reverse[i]]=i

    public IndexHeap(int capacity) {
        this.data=new int[capacity+1];  //因为索引0不存节点，所以长度加一
        this.indexs=new int[capacity+1];
        this.reverse=new int[capacity+1];
        for(int i=0;i<=capacity;i++){
            reverse[i]=0;
        }
        this.capacity=capacity;
        this.count=0;
    }
    //将一个无序数组构造成一个最大堆          相当于堆排序
    public IndexHeap(int[] arr,int n){
        data=new int[n+1];
        capacity=n;
        for(int i=0;i<n;i++){
            data[i+1]=arr[i];
        }
        count=n;
        for(int i=count/2;i>=1;i--){  //i=count/2:i是最后一个叶子节点的父节点(最后一个非叶子节点)
            shiftDown(i);
        }
    }

    private void shiftUp(int i){
        while((i>1)&&(data[indexs[i/2]]<data[indexs[i]])){  //data[indexs[i/2]]为当前节点的父节点
            swap(indexs,i,i/2);           //交换i,i/2两个位置的索引
            reverse[indexs[i]]=i;
            reverse[indexs[i/2]]=i/2;
            i=i/2;   //更新位置
        }
    }
    private void shiftDown(int k){
        while((2*k)<=count){     //有左子节点
            int j=2*k;      //这轮循环,data[k]和data[j]交换位置
            if((j+1)<=count&&(data[indexs[j+1]]>data[indexs[j]])){ //有右子节点且右边的更大
                j+=1;
            }
            if(data[indexs[k]]>=data[indexs[j]])  //如果父节点大于等于子节点,则停止循环
                break;
            swap(indexs,k,j);
            reverse[indexs[k]]=k;
            reverse[indexs[j]]=j;
            k=j;       //k被赋为当前位置,为下次循环做初始化
        }
    }
    public int size() {
        return count;
    }
    public boolean isEmpty(){
        return count==0;
    }
    public void insert(int i,int a){   //传入的索引(从0开始)和值
        assert((count+1)<=capacity);   //防止数组越界
        assert(i+1>=1&&i+1<=capacity);
        i+=1;
        data[i]=a;     //从索引1开始存
        indexs[count+1]=i;    //从索引1开始存
        reverse[i]=count+1;   //同样从1开始存

        count++;
        shiftUp(count);  //由于可能新添加的数违背最大堆的定义，所以要重排序
    }
    public int extractMaxIndex(){      //弹出最大值的索引，即根节点的索引
        assert(count>0);
        int ret=indexs[1]-1;     //返回的索引包括0
        swap(indexs,1,count);    //将最后数放到第一位置,保持完全二叉树的结构
        reverse[indexs[1]]=1;
        reverse[indexs[count]]=0;//赋值0，相当于删除这个索引
        count--;
        shiftDown(1);         //将第一个位置的索引移至合适位置,保持最大堆性质
        return ret;
    }

    public boolean contain(int i){
        assert(i>=0&&i+1<=capacity);
        return reverse[i+1]!=0;
    }
    public int getdata(int i){
        assert(contain(i));
        return data[i+1];
    }
    //修改i索引的data值
    public void set(int i,int newitem){
        assert(contain(i));
        i+=1;
        data[i]=newitem;
//方法一:找到indexs[j]=i,j表示data[i]在堆中的位置
        //之后再shiftUp(j),再shiftDown(j),位置可互换
//		for(int j=1;j<=count;j++){         //时间复杂度O(n)
//			if(indexs[j]==i){
//				shiftUp(j);
//				shiftDown(j);
//				return;      //完成目标,退出函数
//			}
//		}
//方法二:********优化******************
        int j=reverse[i];        //优化结果:时间复杂度O(logn)
        shiftUp(j);
        shiftDown(j);
    }

    public static void swap(int[] arr,int a,int b){
        int c=arr[a];
        arr[a]=arr[b];
        arr[b]=c;
    }

    public static void main(String[] args) {
        IndexHeap heap=new IndexHeap(100);
        System.out.print("插入的随机数:");
        for(int i=0;i<20;i++){
            int rand=new Random().nextInt(100)+1;
            System.out.print(rand+" ");
            heap.insert(i,rand);
        }
        System.out.println();
        System.out.println();
        System.out.print("heap.extractMax():");
        while(!heap.isEmpty()){
            System.out.print(heap.getdata(heap.extractMaxIndex())+" ");   //从大到小输出
        }
    }
}
