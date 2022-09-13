package DTO;

import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class HistoryDTO {
	
	private double MyLatitude;
	private double MyLongitude;
	private String VisitedDate;
	private String ExtraId;
	


}
