package app.utils;


public class CommonUtils {
	
	
	/**通用方法
	 * 
	 * @param se
	 * @param totalCount
	 * @param numPerPage
	 * @param pageNumShown
	 * @param currentPate
	 * @return
	 */
	public static int pageStaretEnd(boolean se,Integer totalCount,Integer numPerPage,Integer pageNumShown,Integer currentPate){
		
	//		int totalCount = 110;//总条数 
	//		int numPerPage = 5;//每页数据条数 
	//		int pageNumShown = 10 ;//展现数 
	//		int currentPate = 19;//当前页面 
			
		int ne_half = (int) Math.ceil(pageNumShown/2);//展现中折半 
		int np = (int) Math.ceil(totalCount/numPerPage);//总页数 
		int upper_limit = np - pageNumShown;//总页数 - 展现数 
		
		int cdn = currentPate - ne_half;//当前页面 - 折半数 
		int min = cdn > upper_limit ? upper_limit : cdn;
		int start = currentPate > ne_half ? min > 0 ? min:0 : 0;
		
		int can = currentPate + ne_half;  
		int end = currentPate > ne_half ? (can > np ? np:can) : (pageNumShown > np? np:pageNumShown);
		
		return se ? start:end;
	}
	
	
	/**
	 * 无依赖方法
	 * @param totalCount
	 * @param numPerPage
	 * @param pageNumShown
	 * @param currentPate
	 * @return
	 */
	public static int pageStartV(Integer totalCount,Integer numPerPage,Integer pageNumShown,Integer currentPate){
		
//		int totalCount = 110;//总条数 
//		int numPerPage = 5;//每页数据条数 
//		int pageNumShown = 10 ;//展现数 
//		int currentPate = 19;//当前页面 
		
		int ne_half = pageNumShown%2 == 0 ?(pageNumShown/2):(pageNumShown/2+1);//展现中折半 
		int np = totalCount%numPerPage == 0 ?(totalCount/numPerPage):(totalCount/numPerPage+1);//总页数 
		int upper_limit = np - pageNumShown;//总页数 - 展现数 
		
		int cdn = currentPate - ne_half;//当前页面 - 折半数 
		int min = cdn > upper_limit ? upper_limit : cdn;
		int start = currentPate > ne_half ? min > 0 ? min:0 : 0;
		
		int can = currentPate + ne_half;  
		int end = currentPate > ne_half ? (can > np ? np:can) : (pageNumShown > np? np:pageNumShown);
		
		return start;
	}
	
	
	/**
	 * 无依赖方法
	 * @param totalCount
	 * @param numPerPage
	 * @param pageNumShown
	 * @param currentPate
	 * @return
	 */
	public static int pageEndV(Integer totalCount,Integer numPerPage,Integer pageNumShown,Integer currentPate){
		
//		int totalCount = 110;//总条数 
//		int numPerPage = 5;//每页数据条数 
//		int pageNumShown = 10 ;//展现数 
//		int currentPate = 19;//当前页面 
		
		int ne_half = pageNumShown%2 == 0 ?(pageNumShown/2):(pageNumShown/2+1);//展现中折半 
		int np = totalCount%numPerPage == 0 ?(totalCount/numPerPage):(totalCount/numPerPage+1);//总页数 
		int upper_limit = np - pageNumShown;//总页数 - 展现数 
		
		int cdn = currentPate - ne_half;//当前页面 - 折半数 
		int min = cdn > upper_limit ? upper_limit : cdn;
		int start = currentPate > ne_half ? min > 0 ? min:0 : 0;
		
		int can = currentPate + ne_half;  
		int end = currentPate > ne_half ? (can > np ? np:can) : (pageNumShown > np? np:pageNumShown);
		
		return end;
	}

}
