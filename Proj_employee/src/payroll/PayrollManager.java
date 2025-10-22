package payroll;

import java.io.*;
import java.util.*;

public class PayrollManager {
	private static final String FILE = "src/data/payroll.txt";//데이터 파일 지정

	public void run(Scanner sc) {// 급여 관리 목록
		while (true) {
			System.out.println("\n[급여관리]");
			System.out.println("\n1. 급여 등록");
			System.out.println("2. 급여 조회");
			System.out.println("3. 급여 수정");
			System.out.println("4. 퇴사 등록");
			System.out.println("5. 뒤로가기");
			System.out.print("선택: ");
			String sel = sc.nextLine();

			if (sel.equals("1")) 
				addPayroll(sc);
			else if (sel.equals("2"))
				listPayroll();
			else if (sel.equals("3"))
				updatePayroll(sc);
			else if (sel.equals("4"))
				deletePayroll(sc);
			else if (sel.equals("5"))
				break;
			else 
				System.out.println("잘못 입력 하셧습니다. 1~5중 선택하세요");
		}
	}

	// 1️ 급여 등록
	private void addPayroll(Scanner sc) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE, true))) {// BufferedWriter 사용
			System.out.print("이름: ");
			String name = sc.nextLine();
			System.out.print("급여액: ");
			String salary = sc.nextLine();
			bw.write(name + "," + salary);
			bw.newLine();
			System.out.println("급여 등록 완료.");
		} catch (IOException e) {
			System.out.println("파일 오류: " + e.getMessage());
		}
	}

	// 2️ 급여 조회
	private void listPayroll() {
		try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
			System.out.println("\n[급여목록]");
			String line;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(","); // split(",") 한줄씩 읽은 데이터를 (",") 기준으로 나눔 EX)홍길동,300만원이란 내용을 자동으로 "홍길동",
													// "300만원"으로 나눠줌
				System.out.println("이름: " + parts[0] + ", 급여: " + parts[1]);// 여기선 이름, 급여 parts[0]는 이름, parts[1]는 급여 만약
																			// 위에 다른 객채가있으면 parts[2]까지 가능
			}
		} catch (IOException e) {
			System.out.println("파일 읽기 오류: " + e.getMessage());
		}
	}

	// 3️ 급여 수정
	private void updatePayroll(Scanner sc) {
		System.out.print("수정할 직원 이름: ");
		String keyword = sc.nextLine();
		File file = new File(FILE);
		File tempFile = new File("src/data/payroll_temp.txt");// 임시파일지정

		try (BufferedReader br = new BufferedReader(new FileReader(file));
				BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {// 읽기 쓰기 준비

			String line;
			boolean found = false;// 직원 검색 여부 초기화
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(",");// 쉼표 기준(split(","))으로 이름 급여 분리
				if (parts[0].equals(keyword)) {// 이름과 입력값 비교(만약 parts[0]을 지정 안하면 급여액까지 적어야함)
					found = true;// 직원 이름 일치함
					System.out.print("새 급여액: ");
					String newSalary = sc.nextLine();// 새로운 급여 입력
					bw.write(parts[0] + "," + newSalary);
				} else {
					bw.write(line);// 다른 직원은 그대로 기록
				}
				bw.newLine();
			}

			if (found)
				System.out.println("급여 수정 완료.");
			else
				System.out.println("해당 직원 없음.");

		} catch (IOException e) {
			System.out.println("파일 오류: " + e.getMessage());
			return;
		}

		if (!file.delete() || !tempFile.renameTo(file)) // 원본 파일 삭제 후 임시파일을 교체(덮어쓰기 개념이 아님)
			System.out.println("파일 처리 중 오류 발생");
	}

	// 4 퇴사 등록
	private void deletePayroll(Scanner sc) {// 직원 퇴사 등록(쓰기), 보기(읽기), 검색(스캐너) 준비
		System.out.println("퇴사 처리할 직원 이름: ");
		String keyword = sc.nextLine();
		File file = new File(FILE);
		File dFile = new File("src/data/payroll_temp.txt");// 임시파일 지정:임시 파일을 지정해야 본 파일에서 지웟을 때 초기화가 안됨
		try (BufferedReader br = new BufferedReader(new FileReader(file));
				BufferedWriter bw = new BufferedWriter(new FileWriter(dFile))) {
			String line;
			boolean found = false;// 처음은 못찾은 상태로 설정
			while ((line = br.readLine()) != null) {// 읽은 줄 한줄을 이용해 작업수행
				String[] parts = line.split(",");
				if (parts[0].equals(keyword)) {//parts[0] 직원 이름
					found = true;// 직원 이름을 찾은 순간 true
					continue;// 아니면 false 상태로 다시 되돌아감
				}
				bw.write(line);
				bw.newLine();
			}
			if (found)// false(found)값으로 초기화
				System.out.println("퇴사 처리 완료");
			else// 거짓일 경우 false로 분리
				System.out.println("해당 직원 없음");
		} catch (IOException e) {
			System.out.println("파일 오류: " + e.getMessage());
			return;
		}
		if (!file.delete() || !dFile.renameTo(file))// delete 데이터 삭제 시도, 밑 덮어쓰기(자바에서는 덮어쓰기를 자동으로 해주지않음)
			System.out.println("파일 처리 중 오류 발생");
	}
}
