package service;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

import java.lang.reflect.Type;

import model.Account;
import model.Gender;
import model.Priority;
import model.Project;
import model.Request;
import model.Status;
import model.Task;
import model.User;
import ui.GUI_HOME;
import utils.GsonHelper;

public class Service {

	private static Service instance;
	// Truyền nhận dữ liệu bằng object
	private Socket client;
	// Danh sách các lớp implement MessageListener -> thông báo cho tất cả các lớp khi có dữ liệu
	private List<MessageListener> listeners = new ArrayList<>();
	BufferedReader in;
	PrintWriter out;
	private DataInputStream dis;
	
	public static Service getInstance() {
		if (instance == null) {
			instance = new Service();
		}
		return instance;
	}

	private Service() {
	}

	public void connectServer(String ip, int portNumber) {
		try {
			client = new Socket(ip, portNumber);
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
			// Thread để lắng nghe dữ liệu từ server
			new Thread(() -> {
				try {
					while (true) {
						// Nếu nhận được dữ liệu (Request)
						String receive = in.readLine();
						System.out.println("Request nhan dc la:"+receive);
						if (receive != null) {
							try (JsonReader reader = Json.createReader(new StringReader(receive))) {
								JsonObject jo = reader.readObject();
								if (jo != null) {
									String message = jo.getString("message");
									JsonObject joData = jo.getJsonObject("data");
									handleReceive(message, joData);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}				
					}
				} catch (Exception e) {
					System.out.println("Kết nối bị gián đoạn: " + e.getMessage());
					e.printStackTrace();
				} finally {
					closeConnection();
				}
			}).start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}
	
	private void handleReceive(String message, JsonObject joData) throws ParseException {
		switch (message) {
		case "LOGIN":
			if (joData != null) {
				if (!joData.containsKey("account")) {
					System.out.println("Lỗi: Không nhận được account!");
					break;
				}
				// Nếu không tìm thấy account
				if (joData.isNull("account")) {
					System.out.println("khong tim thay account"); 
					break;
				}
				// Nếu có account
				JsonObject joAcc = joData.getJsonObject("account");
				
				Gson gson = new Gson();
				Account accReceive = gson.fromJson(joAcc.toString(), Account.class);
				Request<Account> request = new Request<Account>(message, accReceive);
				notifyListeners(request);
			}
			break;
		case "LIST_PROJECT":
			Gson gson = new Gson();
			List<Project> projects = new ArrayList<Project>();
			
			JsonArray jaProject = joData.getJsonArray("listproject");
			for(JsonValue jv : jaProject) {
				JsonObject joProject = (JsonObject)jv;
				Project p = gson.fromJson(joProject.toString(), Project.class);
				projects.add(p);
				
			}
			for(Project p: projects) {
				System.out.println("Project nhan lai dc la:"+p);
			}
			Request<List<Project>> request = new Request<List<Project>>("LIST_PROJECT", projects);
			notifyListeners(request);
			break;
			
		case "LIST_TASKS":
			List<Task> tasks = new ArrayList<Task>();
			
			JsonArray jaTask = joData.getJsonArray("listTask");
			for(JsonValue jv : jaTask) {
				JsonObject joTask = (JsonObject)jv;
//				int id, String title, String description, Priority priority, Date createAt, Date dueDate,
//				Status status
				int id = joTask.getInt("id");
				String title = joTask.getString("title");
				String description = joTask.getString("description");
				String priority = joTask.getString("priority");
				Priority priority1 = Priority.valueOf(priority);
				
				String createAt = joTask.getString("createAt");
				
		        String  dueDate = joTask.getString("dueDate");
		        
	            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME; // Định dạng mặc định

	            // Chuyển từ String sang LocalDateTime
	            LocalDateTime createDate = LocalDateTime.parse(createAt, formatter);
	            LocalDateTime dueDate1 = LocalDateTime.parse(dueDate , formatter);
		   
		        
		        String status = joTask.getString("status");
		        Status stt = Status.valueOf(status);
		        
		        Task task = new Task(id, title, description, priority1, createDate, dueDate1, stt);
				
			    
				tasks.add(task);
				
			}
			for(Task t:tasks) {
				System.out.println("Task nhan lai dc ở client la:"+t);
			}
			Request<List<Task>> request1 = new Request<List<Task>>("LIST_TASKS", tasks);
			notifyListeners(request1);
			break;
			
		case "LISTS_USER_PROJECT":
			Gson gson1 = new Gson();
			List<User> listUser = new ArrayList<User>();
			JsonArray jaUsers = joData.getJsonArray("listUser");
			for(JsonValue jv:jaUsers) {
				JsonObject joUser = (JsonObject) jv;
				int userId = joUser.getInt("id");
				String userName = joUser.getString("name");
				String gender = joUser.getString("gender");
				Gender gender1 = Gender.valueOf(gender);
				int age = joUser.getInt("age");
				String email = joUser.getString("email");
				User user = new User(userId, userName, gender1, age, email);
				listUser.add(user);	
			}
			for(User u:listUser) {
				System.out.println("USER NHẬN LẠI ĐƯỢC LÀ:"+u);
			}
			Request<List<User>> reqUser = new Request<List<User>>("LISTS_USER_PROJECT", listUser);
			notifyListeners(reqUser);
			break;
		
		}
	}
	
	// Hàm gửi dữ liệu đến server
	public void sendMessage(String request) {
		if (out != null) {
			try {
				out.println(request);
				out.flush();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Gặp lỗi trong khi gửi dữ liệu!");
			}
		}
	}
	
	// Thêm một UI vào danh sách lắng nghe
    public void addMessageListener(MessageListener listener) {
        listeners.add(listener);
    }
    
    // Gỡ bỏ listener nếu không cần nữa
    public void removeMessageListener(MessageListener listener) {
        listeners.remove(listener);
    }
    
    // Thông báo cho tất cả UI khi nhận được dữ liệu
    private void notifyListeners(Request<?> request) {
        for (MessageListener listener : new ArrayList<>(listeners)) {
            listener.onMessageReceived(request);
        }
    }

    // Đóng luồng sau khi ngắt kết nối
	public void closeConnection() {
		try {
			if (in != null)
				in.close();
			if (out != null)
				out.close();
			if (client != null && !client.isClosed())
				client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
