package qnaBoard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardService {
	BoardDAO boardDAO;
	
	public BoardService() {
		boardDAO = new BoardDAO();
	}
	
	public Map<String, Integer> listArticles(Map<String, Integer> pagingMap) {
		Map articleMap=new HashMap<>();
		List<ArticleVO> articleList=boardDAO.selectAllArticles(pagingMap);
		int totalArticles=boardDAO.selectToArticles();
		articleMap.put("articleList", articleList);
		articleMap.put("totalArticles", totalArticles);
		return articleMap;
	}
	
	public void addArticle(ArticleVO articleVO) {

//		for(int i=0;i<100;i++) {
			boardDAO.insertNewArticle(articleVO);
//		}
	}
	
	public ArticleVO viewArticle(int q_code) {
		ArticleVO articleVO = null;
		articleVO = boardDAO.selectArticle(q_code);
		System.out.println(articleVO.getA_text());
		return articleVO;
	}
	
	public void modArticle(ArticleVO articleVO) {
		boardDAO.updateArticle(articleVO);
	}
	
	public void removeArticle(int q_code) {
		boardDAO.deleteArticle(q_code);
	}
	
	public void removeReply(int q_code) {
		boardDAO.deleteReply(q_code);
	}
	
	public void addReply(ArticleVO articleVO) {
		boardDAO.insertNewReply(articleVO);
	}
}
