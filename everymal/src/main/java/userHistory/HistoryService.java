package userHistory;

import java.util.List;

import userMypage.UserVO;

public class HistoryService {
   
   HistoryDAO historyDAO;
   HistoryVO historyVO;
   
   
   
   
   public HistoryService() {
      historyDAO=new HistoryDAO();
   }




   public List<HistoryVO> listHReserv(HistoryVO historyVO) {
      List<HistoryVO> historyList=historyDAO.hosResList(historyVO); //지난 이용 내역
      
      return historyList;

      
   }
   
   
   public List<HistoryVO> listHRecent(HistoryVO historyVO){
      List<HistoryVO> recentReserv=historyDAO.hosRecent(historyVO); //최신 병원 예약 정보 
      return recentReserv;
   }
   
   public HistoryVO vHis(String res_code)  {
      HistoryVO historyVO=null;
      historyVO=historyDAO.selectHis(res_code);
      return historyVO;

   }
   
   
   //===========================================================
   
   
   public List<HistoryVO> listTxReserv(HistoryVO historyVO) {
      List<HistoryVO> historyList=historyDAO.txResList(historyVO); //지난 택시 이용 내역
      
      return historyList;
   }
   

   
   public List<HistoryVO> listTxRecent(HistoryVO historyVO){
      List<HistoryVO> recentReserv=historyDAO.txRecent(historyVO); //최신 택시 예약 정보 
      return recentReserv;
   }
   
   
   public HistoryVO vTaxiHis(String tx_res_code)  {
      HistoryVO historyVO=null;
      historyVO=historyDAO.selectTaxiHis(tx_res_code);
      return historyVO;

   }
   
   

}