package com.zwj.cn;

import static org.junit.Assert.assertTrue;

public class Pocket {
	private int bills[];
    private boolean visited[];
	public Pocket() {
		bills=new int[8];
		bills[0]=50;bills[1]=20;bills[2]=10;
		bills[3]=5;bills[4]=5;bills[5]=bills[6]=bills[7]=1;
		visited=new boolean[8];
		for(int i=0;i<visited.length;++i)
		{
			visited[i]=false;
		}
	}
	//deep first search
	public boolean judge(int x) {
		if(x==0)
			return true;
		if(x<0)
			return false;
		for(int i=0;i<bills.length;++i)
		{
			if(!visited[i])
			{
				visited[i]=true;
				if(judge(x-bills[i]))
				{	
					visited[i]=false;
					return true;
				}
				visited[i]=false;
			}
		}
		return false;
	}
}
