package attendance;

import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

public class AttendanceManager {
	// 데이터 파일 지정
	private static final String FILE = "src/data/attendance.txt";

	// 근태 메뉴
	public void run(Scanner sc) {
		while (true) {
			System.out.println("\n[근태관리]");
			System.out.println("\n1. 출근 등록");
			System.out.println("2. 퇴근 등록");
			System.out.println("3. 근태 기록 보기");
			System.out.println("4. 뒤로가기");
			System.out.println("선택: ");
			String sel = sc.nextLine();

			if (sel.equals("1"))
				checkIn(sc);
			else if (sel.equals("2"))
				checkOut(sc);
			else if (sel.equals("3"))
				showRecords();
			else if (sel.equals("4"))
				break;
			else 
				System.out.println("잘못 입력 하셧습니다. 1~4중 선택하세요");
		}
	}

// 1 출근 등록
	private void checkIn(Scanner sc) {
		writeRecord(sc, "출근");
	}

// 2 퇴근 등록
	private void checkOut(Scanner sc) {
		writeRecord(sc, "퇴근");
	}

// 1,2 출퇴근 등록 관련 writeRecord 선언(출 퇴근 따로 해도되나 내용이 겹치는 부분이 많아 가독성을 위해 모아놓음.)
	private void writeRecord(Scanner sc, String type) {// 파라미터 2개 스캐너,출퇴근 기록 유형(체크인 체크 아웃으로 출,퇴근 지정)
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE, true))) {// BufferedWriter 사용
			System.out.println("이름: ");
			String name = sc.nextLine();
			Date now = new Date();// 시간 설정
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 이대로하면 불필요한 정보가 나와서 SimpleDataFormat 사용
																			// (EX:UTC)
			String time = sd.format(now);
			bw.write(name + "," + type + "," + time);
			bw.newLine();
			System.out.println(type + "등록 완료");
		} catch (IOException e) {
			System.out.println("파일 오류");
		}
	}

//  3 근태기록
	private void showRecords() {
		try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
			System.out.println("\n[근태기록]");
			String line;
			while ((line = br.readLine()) != null)
				System.out.println(line);
		} catch (IOException e) {
			System.out.println("파일 오류");
		}
	}
}
