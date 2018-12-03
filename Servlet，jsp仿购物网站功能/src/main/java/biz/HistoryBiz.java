package biz;

import java.util.List;
import bean.*;


import dao.HistoryDAO;

public class HistoryBiz {
	
	private HistoryDAO hidao = new HistoryDAO();
	public List<HistoryInfo> findAll(){
		return hidao.findAll();
	}
	
	public List<StateInfo> findByUserId(int userid){
		return hidao.findByUserId(userid);
	}
	
public List<HistoryInfo> dividByPage(int page){
		return hidao.dividByPage(page);
	}
	public void saveUser( HistoryInfo hi){
		hidao.saveHistory(hi);
	}
	
	
}
