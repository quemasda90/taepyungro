package main;

import java.util.Scanner;
import attendance.AttendanceManager;
import employee.EmployeeManager;
import payroll.PayrollManager;
import training.TrainingManager;

public class MainApp {
	public static void main(String[] args) {
		// 입력 스캔 시작
		Scanner sc = new Scanner(System.in);
		// 각 관리 기능 인스턴스 생성
		EmployeeManager em = new EmployeeManager();
		AttendanceManager at = new AttendanceManager();
		TrainingManager tr = new TrainingManager();
		PayrollManager pa = new PayrollManager();
		// 메뉴 출력
		while (true) {
			System.out.println("==== 태평로빌딩 직원 관리 시스템 ====");
			System.out.println("1. 인사 관리");
			System.out.println("2. 근태 관리");
			System.out.println("3. 교육 관리");
			System.out.println("4. 급여 관리");
			System.out.println("5. 종료");
			System.out.println("선택: ");
			// 사용자 입력 받기
			String sel = sc.nextLine();
			// 메뉴 번호 입력 방법1(switch-case):명확한 메뉴 선택 구조에 적합※break 꼭 사용!
			switch (sel) {
			case "1":
				em.run(sc);
				break;
			case "2":
				at.run(sc);
				break;
			case "3":
				tr.run(sc);
				break;
			case "4":
				pa.run(sc);
				break;
			case "5":
				System.out.println("프로그램 종료");
				sc.close();
				return;
			default:
				System.out.println("잘못 입력 하셧습니다. 1~5중 선택하세요");
			}
		}
	}
}
