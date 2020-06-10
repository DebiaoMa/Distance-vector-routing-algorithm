package rip;

public class Distance {
    Routing[] arr=new Routing[7];

    public static Routing[] init(Routing[] array){//随机生成距离
        for(int i=0;i<7;i++) {
            for(int j=0;j<7;j++){
                if(i==j) {
                    array[i].dis[j]=0;
                } else{
                    if(array[i].dis[j]==0){
                        array[i].dis[j]=(int)(Math.random()*19 + 1);

                        if(array[i].dis[j]>15 || array[i].dis[j]==0)
                        {
                            array[i].dis[j]=1000;
                            array[j].dis[i]=1000;
                        }
                    }
                }
                array[i].name[j]=array[j].m;
                array[i].next[j]=array[j].m;
            }
        }
        for(int n=0;n<7;n++){
            System.out.println("这是初始值");
            System.out.println("这是结点"+array[n].m+"的当前路由表");

            for(int e=0;e<7;e++){
                System.out.println(array[n].name[e]+" "+array[n].dis[e]+"下一跳地址是"+array[n].next[e]);
            }
        }

        return array;
    }

public static Routing[] exchange(Routing[] array){//检查是否有更小距离进行修正
    boolean flag = true;
    int count=0;

    while(true){
    for(int i = 0;i < 7;i++) {
        for(int j = 0; j < 7; j++) {
            for(int m = 0; m < 7; m++) {
                if(array[i].dis[m]==1000) {
                    continue;//不能互通的路由器不能交换信息
                }
                if(array[i].dis[j] > array[i].dis[m]+array[m].dis[j]) {
                    array[i].dis[j] = array[i].dis[m]+array[m].dis[j];
                    array[i].next[j] = array[m].m;
                    flag = false;
                }
            }
        }
    }
    count++;
    for(int n = 0; n < 7; n++){
        System.out.println("这是第"+count+"轮的结果");
        System.out.println("这是结点"+array[n].m+"的当前路由表");

        for(int e = 0; e<7; e++){
            System.out.println(array[n].name[e]+ " " + array[n].dis[e] + " 下一跳地址是" + array[n].next[e]);
        }
    }
    if(flag){
        System.out.println("已经达到稳定状态,一共运行了"+count+"轮");
        return array;
    }
    flag = true;
    }
}
public static void main(String[] args){
    try{
        Routing[] arr=new Routing[7];
        Routing[] b=new Routing[7];
        arr[0]=new Routing('a');
        arr[1]=new Routing('b');
        arr[2]=new Routing('c');
        arr[3]=new Routing('d');
        arr[4]=new Routing('e');
        arr[5]=new Routing('f');
        arr[6]=new Routing('g');
        b[0]=new Routing('a');
        b[1]=new Routing('b');
        b[2]=new Routing('c');
        b[3]=new Routing('d');
        b[4]=new Routing('e');
        b[5]=new Routing('f');
        b[6]=new Routing('g');
        arr= init(arr);

        for(int i=0;i<7;i++){
            b[i].m=arr[i].m;

            for(int j=0;j<7;j++){
                b[i].dis[j]=arr[i].dis[j];
                b[i].name[j]=arr[i].name[j];

            }
        }

        arr = exchange(arr);
        Myframe mf = new Myframe(arr,b);
        mf.setVisible(true);
       }catch(Exception e){
           e.printStackTrace();
       }
    }

}


