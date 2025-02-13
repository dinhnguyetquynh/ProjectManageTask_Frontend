package test;

import model.Request;
//import gui.GUI_Dang_Nhap;
import service.Service;

import ui.GUI_HOME;
import ui.GUI_LOGIN;

public class MainClient {

	public static void main(String[] args) {
		GUI_LOGIN.screenDangNhap();
		try {
			String ip = "localhost";
			int portNumber = 12345;
            Service.getInstance().connectServer(ip, portNumber);
          
           
        } catch (Exception e) {
            e.printStackTrace();
        }
	
	}
}
