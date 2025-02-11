package test;

import model.Request;
//import gui.GUI_Dang_Nhap;
import service.Service;

import ui.GUI_HOME;

public class MainClient {

	public static void main(String[] args) {
		
		try {
			String ip = "localhost";
			int portNumber = 12345;
            Service.getInstance().connectServer(ip, portNumber);
          
            Request<String> request = new Request<>("LIST_PROJECT", null);
            Service.getInstance().sendMessage(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
	
	}
}
