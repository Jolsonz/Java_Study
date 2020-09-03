用泛型重写了一下
注意comparable接口是能指定泛型的

另外类型推断：
ArrayList<Integer> list =  new ArrayList<Integer>();//后面那个Integer通常写不写都行

Comparator c = new Comparator<Employee>()貌似有个奇怪的特性，不能把类型写在前面，它不认，它要写在后面

   <E>为Element的首字母，一般表示集合中的元素。

   <T>为Type的首字母，表示传输参数的类型。
   
   可能是这种原因