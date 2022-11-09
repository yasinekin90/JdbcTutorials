package utilities;

public enum Rdms {
    //relational database management system
    //bu classın olustuurlmus sabitleri
    mysql_sakila(1,"a"),
    mysql_tempdb(2,"b"),
    sqlite(3,"c");

    private int num;
    private String str;


    Rdms(int num,String str){
        this.num=num;
        this.str=str;
    }

    public int getNum() {
        return num;
    }

    public String getStr() {
        return str;
    }
}
