package JDBC3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.ConnectionManager;
import utilities.Driver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginTest {

    WebDriver driver;

    {
        driver=Driver.getDriver();
    }


    @Test(priority = 1)
    public void gotoSite(){
        driver.get("https://www.saucedemo.com/");
    }


    @Test(priority = 2,dataProvider="getData")
    public void loginTest(Object[]arr){


        WebElement username= driver.findElement(By.xpath("//input[@id='user-name']"));

        username.clear();
        username.sendKeys( arr[0].toString());
        WebElement password= driver.findElement(By.xpath("//input[@id='password']"));
        password.clear();
        password.sendKeys(arr[1].toString());
    }

    @Test(priority = 3)
    public void closeDriver(){
        driver.close();
        driver.quit();
    }

    @DataProvider
    public Object[][] getData(){


        try {
            ConnectionManager cm=new ConnectionManager("jdbc:sqlite:src/main/resources/data.sqlite");
            ResultSet row1= cm.getResultSet("SELECT COUNT(*) FROM yasin");
            int row= row1.getInt(1);
            ResultSet rs= cm.getResultSet("SELECT adi,soyadi,yas FROM yasin");
            int col=rs.getMetaData().getColumnCount();




            Object [][] arr=new Object[row][col];

            int rowNum=0;
            while (rs.next()){

                Object [] rows=new Object[col];
                for (int i = 1; i <=col ; i++) {

                    rows[i-1]=rs.getString(i);
                }

                arr[rowNum++]=rows;
            }
            cm.getConn().close();

            return arr;
        } catch (SQLException e) {
           throw new RuntimeException(e);
        }



    }
}
