package domain;


import lombok.Data;
/**
 * 
 * @author 문진수
 * 제로베이스 백엔드 3기 
 *
 */
@Data
public class HistoryDTO {

	private double MyLatitude;
	private double MyLongitude;
	private String VisitedDate;
	private String ExtraId;

}
