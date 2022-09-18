package domain;


import lombok.Data;

@Data
public class HistoryDTO {

	private double MyLatitude;
	private double MyLongitude;
	private String VisitedDate;
	private String ExtraId;

}
