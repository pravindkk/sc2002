package com.hotel.db;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.hotel.system.Room;
import com.hotel.system.enums.BedType;
import com.hotel.system.enums.RoomStatus;
import com.hotel.system.enums.RoomType;

/**
 * Represents the derived class of DB , which is used to read and write all data to the text file that contains Rooms.
 * @author Pravind
 * @version 1.0
 * @since 1.0
 */

public class RoomDB extends DB{
    private File database = new File("hotel/room.csv");
    private String path;

    public RoomDB(){
        super();
        try {
            database.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.path = database.getAbsolutePath();

    }

    
    /** 
     * @return The fucntion getPath returns the path of the file as a String
     */
 
    public String getPath() {
        return this.path;
    }

    
    /** 
     * @param fileName The name of the text file is passed in as a String input
     * @return ArrayList of the data in the text file is returned
     * @throws IOException Due to communication with the DataBase IOexception is required
     */

    @Override
	public ArrayList read(String fileName) throws IOException {
        List<String[]> listing = super.readAllData(fileName);
        ArrayList allData = new ArrayList();


        for (String[] row : listing) {


            String roomId = row[0];
			String roomType = row[1];
			String bedType = row[2];
			String withView = row[3];
			String roomStatus = row[4];
			String roomRate = row[5];
			String roomFloor = row[6];
			String roomNumber = row[7];
			String wifiEnabled = row[8];
			String smokingStatus = row[9];

            Room room = new Room(RoomType.valueOf(roomType),
                                 RoomStatus.valueOf(roomStatus),
                                 BedType.valueOf(bedType), 
                                 roomId, 
                                 Boolean.parseBoolean(withView), 
                                 Float.parseFloat(roomRate), 
                                 roomFloor, 
                                 Integer.valueOf(roomNumber), 
                                 Boolean.parseBoolean(wifiEnabled), 
                                 Boolean.parseBoolean(smokingStatus)
                                 );
            allData.add(room);

        }

        return allData;
		
	}

    
    /** 
     * @param fileName The name of the text file that the data is going to be written to is passed in as a String input
     * @param al ArrayList of the data that is going to be written to is passed as a input
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    
    @Override
    public void save(String fileName, List al) throws IOException {
        // TODO Auto-generated method stub

        List<String[]> toWrite = new ArrayList<String[]>();
        

        for (int i=0; i<al.size(); i++) {
            Room r = (Room) al.get(i);

            toWrite.add(new String[] { r.getRoomId(),
                                       r.getRoomType().toString(),
                                       r.getBedType().toString(),
                                       String.valueOf(r.getWithView()),
                                       r.getRoomStatus().toString(),
                                       String.valueOf(r.getRoomRate()),
                                       r.getRoomFloor(),
                                       String.valueOf(r.getRoomNumber()),
                                       String.valueOf(r.getWifiEnabled()),
                                       String.valueOf(r.getSmokingStatus())
                                     });

            
        }

        super.writeAllData(fileName, toWrite);

        
    }
}
