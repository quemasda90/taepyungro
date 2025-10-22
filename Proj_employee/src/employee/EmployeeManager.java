package employee;

import java.io.*;
import java.util.*;

public class EmployeeManager {
	// 데이터 파일 지정
	public static final String FILE = "src/data/employes.txt";

	// 인사 관련 메뉴
	public void run(Scanner sc) {
		while (true) {
			System.out.println("\n[인사관리]");// \n은 줄바꿈
			System.out.println("\n1. 직원 등록");
			System.out.println("2. 직원 목록 보기");
			System.out.println("3. 직원 검색");
			System.out.println("4. 퇴사 등록");
			System.out.println("5. 뒤로가기");
			System.out.println("선택: ");
			String sel = sc.nextLine();
			// 메뉴 번호 입력 방법1(if-if else):간단한 분기(true/false)에 적합
			if (sel.equals("1"))
				addE(sc);
			else if (sel.equals("2"))
				listE();
			else if (sel.equals("3"))
				searchE(sc);
			else if (sel.equals("4"))
				deleteE(sc);
			else if (sel.equals("5"))
				break;
			else
				System.out.println("잘못 입력 하셧습니다. 1~5중 선택하세요");
		}
	}

// 1 직원 등록
	private void addE(Scanner sc) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE, true))) {// 등록(쓰기) 준비
			System.out.println("이름: ");
			String name = sc.nextLine();
			System.out.println("부서: ");
			String dept = sc.nextLine();
			System.out.println("직급: ");
			String pos = sc.nextLine();
			bw.write(name + "," + dept + "," + pos);
			bw.newLine();
			System.out.println("등록 완료");
		} catch (IOException e) {
			System.out.println("파일 오류:" + e.getMessage());
		}
	}

// 2 직원 목록
	private void listE() {
		try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {// 목록 보기(읽기) 준비
			System.out.println("\n[직원 목록]");
			String line;
			while ((line = br.readLine()) != null)
				System.out.println(line);
		} catch (IOException e) {
			System.out.println("파일 오류");
		}
	}

//3 검색할 이름
	private void searchE(Scanner sc) {// 직원 검색(스캐너) 및 보기(읽기) 준비
		System.out.println("검색할 이름: ");
		String keyword = sc.nextLine();
		try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
			boolean found = false;
			String line;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(",");
				if (parts[0].equals(keyword)) {
					System.out.println("검색 결과:" + line);
					found = true;
				}
			}
			if (!found)
				System.out.println("해당 직원 없음.");
		} catch (IOException e) {
			System.out.println("파일 오류.");
		}
	}

//4 퇴사 처리
	private void deleteE(Scanner sc) {// 직원 퇴사 등록(쓰기), 보기(읽기), 검색(스캐너) 준비
		System.out.println("퇴사 처리할 직원 이름: ");
		String keyword = sc.nextLine();
		File file = new File(FILE);
		File dFile = new File("src/data/employes_temp.txt");// 임시파일 지정:임시 파일을 지정해야 본 파일에서 지웟을 때 초기화가 안됨
		try (BufferedReader br = new BufferedReader(new FileReader(file));
				BufferedWriter bw = new BufferedWriter(new FileWriter(dFile))) {
			String line;
			boolean found = false;// 처음은 못찾은 상태로 설정
			while ((line = br.readLine()) != null) {// 읽은 줄 한줄을 이용해 작업수행
				String[] parts = line.split(",");
				if (parts[0].equals(keyword)) {// line 안에 keyword가 들어있으면 true
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
