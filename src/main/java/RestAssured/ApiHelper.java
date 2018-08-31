package RestAssured;

import static io.restassured.RestAssured.given;

import java.security.SecureRandom;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiHelper {

	static final String AB = "abcdefghijklmnopqrstuvwxyz";
	static SecureRandom rnd = new SecureRandom();
	static String projectName = "project_name "+randomString(5);



	public static String randomString(int len) {
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}
	

	public static Response login() throws InterruptedException {
		RestAssured.baseURI = "https://api.taiga.io";
		JSONObject requestParams = new JSONObject();
		requestParams.put("username", "sqa.days@yandex.ru"); // Cast
		requestParams.put("password", "Armenia2018");
		requestParams.put("type", "normal");


		return given().header("Content-Type","application/json").body(requestParams.toJSONString()).post("/api/v1/auth");
		
	}
	
	
	public static Response createProject() throws InterruptedException {
		RestAssured.baseURI = "https://api.taiga.io";
		JSONObject request = new JSONObject();
		request.put("is_private", "false"); // Cast
		request.put("creation_template", "1");
		request.put("name", projectName);
		request.put("description", "Test "+randomString(10));

		
		return given().header("Content-Type","application/json").header("Authorization","Bearer "+accessToken()+"").body(request.toJSONString()).post("/api/v1/projects");
		
	}
	
	
	public static String accessToken() throws InterruptedException {
		
		return login().then().extract().path("auth_token");
	}
	
	
	public void deleteProject() throws InterruptedException {
		RestAssured.baseURI = "https://api.taiga.io";
		given().header("Authorization","Bearer "+accessToken()+"").delete("/api/v1/projects/"+projectId()+"");
		
	}
	
	public int projectId() throws InterruptedException {
		
        return createProject().then().extract().path("priorities[0].project_id");
		
	}
	public String url() {
		return "https://tree.taiga.io/project/sqadaystest-"+projectName.replace(" ", "-").toLowerCase()+"/";
	}
	public String responseAsString() throws InterruptedException {
		return login().asString();
	}
	

}
