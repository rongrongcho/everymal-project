package hosList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilterService {
	FilterDAO filterDAO;
	
	public FilterService() {
		filterDAO = new FilterDAO();
	}
	
	public Map listArticles(Map pagingMap) {
		Map articleMap=new HashMap<>();
		List<FilterVO> hosList=filterDAO.selectAllArticles(pagingMap);
		int totalHos=filterDAO.selectToArticles(pagingMap);
		articleMap.put("hosList", hosList);
		articleMap.put("totalHos", totalHos);
		return articleMap;
	}

	public Map revListArticles(Map pagingMap) {
		Map articleMap=new HashMap<>();
		List<FilterVO> hosList=filterDAO.selectRevAllArticles(pagingMap);
		int totalHos=filterDAO.selectToArticles(pagingMap);
		articleMap.put("hosList", hosList);
		articleMap.put("totalHos", totalHos);
		return articleMap;
	}
}
