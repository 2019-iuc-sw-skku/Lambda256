package kr.co.softcampus.login.j_giftcon;


/**
 * @name giftlist
 * @descriptions 상품권 자료형
 */

public class j_giftlist {
    private String name, category1, category2;
    private int cost, count;

    public j_giftlist(String name, String category1, String category2, int cost, int count) {
        this.name = name;
        this.category1 = category1;
        this.category2 = category2;
        this.cost = cost;
        this.count = count;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory1() {
        return category1;
    }

    public void setCategory1(String category1) {
        this.category1 = category1;
    }

    public String getCategory2() {
        return category2;
    }

    public void setCategory2(String category2) {
        this.category2 = category2;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
