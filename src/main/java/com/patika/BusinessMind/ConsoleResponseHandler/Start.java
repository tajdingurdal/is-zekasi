package com.patika.BusinessMind.ConsoleResponseHandler;

import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.patika.BusinessMind.Converter.UserResponseConverter;
import com.patika.BusinessMind.Model.HighLevelManager;
import com.patika.BusinessMind.Repository.HighLevelManagerRepository;
import com.patika.BusinessMind.Response.HighLevelManagerResponse;
import com.patika.BusinessMind.Service.abstracts.IHighLevelManagerService;

@Service
public class Start {
 
	public static Scanner scanner = new Scanner(System.in);

	private static IHighLevelManagerService highLevelManagerService;
	private static TechnicalRequirements requirements;
	private static UserResponseConverter userResponseConverter;

	@Autowired
	public Start(IHighLevelManagerService highLevelManagerService, TechnicalRequirements requirements,
			UserResponseConverter userResponseConverter) {
		this.highLevelManagerService = highLevelManagerService;
		this.requirements = requirements;
		this.userResponseConverter = userResponseConverter;
	}

	public static void firstScreen() {

		System.out.println("1. Giriş Yap\n2. Kayıt Ol\n3. Geri");
		String response = scanner.nextLine();

		if (response.equals("1")) {
			girisYap();
		} else if (response.equals("2")) {
			kayıtOl();
		} else if (response.equals("3")) {
			firstScreen();
		} else {
			System.out.println("Lütfen doğru seçeneği giriniz.");
			firstScreen();
		}
	}

	private static void kayıtOl() {
		while (true) {
			System.out.print("Name: ");
			String name = scanner.nextLine();

			if (name.equals("3")) {
				firstScreen();
			}

			System.out.print("Surname: ");
			String surname = scanner.nextLine();

			if (surname.equals("3")) {
				firstScreen();
			}

			System.out.print("Email: ");
			String email = scanner.nextLine();

			if (email.equals("3")) {
				firstScreen();
			}

			if (highLevelManagerService.existsByEmail(email)) {
				System.out.println("Bu email ile üyelik zaten mevcut. Lütfen farklı bir email giriniz.");
				continue;
			}

			System.out.print("Şifre: ");
			String password = scanner.nextLine();

			HighLevelManager user = new HighLevelManager();
			user.setEmail(email);
			user.setPassword(password);
			user.setName(name);
			user.setSurname(surname);
			highLevelManagerService.save(user);

			HighLevelManagerResponse response = userResponseConverter.apply(user);

			System.out.println("\nRegistration Successful!\n " + response + "\n");
			firstScreen();
			break;
		}

	}

	private static void girisYap() {
		while (true) {
			String password1 = "";
			System.out.print("Email: ");
			String email = scanner.nextLine();
			if (email.equals("3")) {
				firstScreen();
			}

			Iterable<HighLevelManager> allUsers = highLevelManagerService.findAll();

			for (HighLevelManager user : allUsers) {
				if (user.getEmail().equals(email.trim().toLowerCase())) {
					password1 += user.getPassword();
				}
			}

			// üye sistemde yok ise
			if (!highLevelManagerService.existsByEmail(email.trim().toLowerCase())) {
				System.out.println("Email mevcut değil");
				continue;
			}

			System.out.print("Şifre: ");
			String password = scanner.nextLine();

			// email var ama password yanlış girdiyse
			if (highLevelManagerService.existsByEmail(email.trim().toLowerCase()) && !password.equals(password1)) {
				System.out.println("Lütfen şifrenizi doğru giriniz.");
				continue;
			}

			System.out.println("Giriş başarılı");
			requirements.startRequirements();

			break;
		}
	}

}
