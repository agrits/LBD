package pl.fis.artur.kasza.lbd.models;

import java.text.NumberFormat;
import java.util.Locale;

import javax.enterprise.inject.Produces;

import pl.fis.artur.kasza.lbd.qualifiers.Formatting;

public class User {
	private String username, password, firstName, lastName, role, sessionID, ip, lang;
	private int id;
	public User() {
		 	
	}
	public User(String username, String password, String firstName, String lastName, String role, int id) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getRole() {
		return role;
	}
	public int getId() {
		return id;
	}
	public String getSessionID() {
		return sessionID;
	}
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	
	@Produces @Formatting
	public NumberFormat getNumberFormat() {
		if(lang != null)
			return NumberFormat.getNumberInstance(Locale.forLanguageTag(lang));
		else
			return NumberFormat.getNumberInstance(Locale.getDefault());
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [username=");
		builder.append(username);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", role=");
		builder.append(role);
		builder.append(", sessionID=");
		builder.append(sessionID);
		builder.append(", ip=");
		builder.append(ip);
		builder.append(", lang=");
		builder.append(lang);
		builder.append(", id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}
	
	
}
