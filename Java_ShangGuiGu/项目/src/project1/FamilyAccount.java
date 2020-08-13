package project1;

/**
 * @anthor Jolson
 * @Create 2020-08-08 14:09
 */
public class FamilyAccount {
    public static void main(String[] args) {
        String details = "收支\t账户金额\t收支金额\t说    明\n";//每次收入支出都把明细串到这里面来
        int balance = 10000;//账户内的余额，初始值1w
        boolean loopFlag = true;
        do{
            System.out.println("\n----------------家庭收支记账软件---------------\n");
            System.out.println("                  1 收支明细");
            System.out.println("                  2 登记收入");
            System.out.println("                  3 登记支出");
            System.out.println("                  4 退    出\n");
            System.out.print("                  请选择(1-4)：");//这个时候不需要换行
            char key = Utility.readMenuSelection();
            System.out.println();
            switch (key){
                case '1':
                    System.out.println("-----------------当前收支明细记录-----------------");
                    System.out.println(details);
                    System.out.println("--------------------------------------------------");
                    break;
                case '2':
                    System.out.println("-----------------登记收入-----------------");
                    System.out.print("请输入收入: ");
                    int income = Utility.readNumber();
                    System.out.print("请输入说明: ");
                    String info1 = Utility.readString();

                    balance+=income;//收入增加
                    details+="收入\t"+balance+"\t\t"+income+"\t\t"+info1+"\n";
                    System.out.println("-----------------登记完成-----------------");
                    break;
                case '3':
                    System.out.println("-----------------登记支出-----------------");
                    System.out.print("请输入支出: ");
                    int outcome = Utility.readNumber();
                    if (outcome > balance) {
                        System.out.println("余额不够支出，驳回请求");
                        break;
                    }
                    System.out.print("请输入说明: ");
                    String info2 = Utility.readString();

                    balance-=outcome;//支出
                    details+="支出\t"+balance+"\t\t"+outcome+"\t\t"+info2+"\n";
                    System.out.println("-----------------登记完成-----------------");

                    break;
                case '4':
                    System.out.println("请确认是否退出(Y/N)");
                    char temp = Utility.readConfirmSelection();
                    if(temp == 'Y')//结束循环
                        loopFlag = false;
                    break;
            }


        }while (loopFlag);


    }
}
