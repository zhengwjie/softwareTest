package com.zwj.cn;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
public class p01 {
	private WebDriver driver;
	private Map<String, String> map=new HashedMap<String, String>();
	@Before
	public void setUp()
	{
		/* System.setProperty("webdriver.firefox.marionette","C:\\Program Files\\MozillaFirefox\\geckodriver.exe");*/
		String pathString="E:\\大三下课程资料\\softwareTest\\lab\\Lab02\\Selenium+Lab2020.xlsx";
		try {
			InputStream inputStream=new FileInputStream(pathString);
			Workbook wbWorkbook=new XSSFWorkbook(inputStream);
			Sheet sheet=wbWorkbook.getSheetAt(0);
			int rownum=sheet.getPhysicalNumberOfRows();
			Row row=sheet.getRow(0);
			int colnum=row.getPhysicalNumberOfCells();
			for(int i=0;i<rownum;++i)
			{
				row=sheet.getRow(i);
				if(row!=null)
				{
					String usrname=row.getCell(1).getRichStringCellValue().getString();
					String password=row.getCell(2).getRichStringCellValue().getString();
					System.out.println(usrname+" "+password);
					if(usrname.length()>0)
					    map.put(usrname, password);
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("读取文件失败");
			e.printStackTrace();
		}
		driver = new FirefoxDriver();
	}
	@Test
	public void test() throws InterruptedException
	{
		driver.get("http://103.120.226.190/selenium-demo/git-repo");
		for(Map.Entry<String, String> entry:map.entrySet())
		{
			WebElement user_numberElement=driver.findElement(By.name("user_number"));
			WebElement passwordElement=driver.findElement(By.name("password"));
			WebElement butclick=driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div[2]/div/form/div[3]"));
			String usrnameString=entry.getKey();
			String passwordString=entry.getValue();
			user_numberElement.sendKeys(usrnameString);
			passwordElement.sendKeys(passwordString);
			butclick.click();
			WebElement code2=driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div[2]/div/form/div[5]/code"));
			System.out.println(code2.getText().toString());
			System.out.println(passwordString);
			assertEquals(code2.getText().toString(), passwordString);
			Thread.sleep(300);
		}
	}
	@After
	public void tearDown()
	{
		driver.close();
	}

}
