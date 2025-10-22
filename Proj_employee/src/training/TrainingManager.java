package training;

import java.io.*;
import java.util.*;

public class TrainingManager {
	private static final String FILE = "src/data/training.txt";

	public void run(Scanner sc) {// 교육 목록
		while (true) {
			System.out.println("\n[교육관리]");
			System.out.println("\n1. 교육 등록");
			System.out.println("2. 교육 목록");
			System.out.println("3. 완료된 교육 삭제");
			System.out.println("4. 뒤로가기");
			System.out.println("선택: ");
			String sel = sc.nextLine();

			if (sel.equals("1"))
				addTraining(sc);
			else if (sel.equals("2"))
				listTraining();
			else if (sel.equals("3"))
				deleteTraining(sc);
			else if (sel.equals("4"))
				break;
			else 
				System.out.println("잘못 입력 하셧습니다. 1~4중 선택하세요");
		}
	}

// 1 교육 등록
	private void addTraining(Scanner sc) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE, true))) {
			System.out.println("교육명: ");
			String name = sc.nextLine();
			System.out.println("담당자: ");
			String instructor = sc.nextLine();
			bw.write(name + "," + instructor);
			bw.newLine();
			System.out.println("등록 완료");
		} catch (IOException e) {
			System.out.println("파일 오류");
		}
	}
// 2 교육 리스트
	private void listTraining() {
		try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
			System.out.println("\n[교육목록]");
			String line;
			while ((line = br.readLine()) != null)
				System.out.println(line);
		} catch (IOException e) {
			System.out.println("파일 오류");
		}
	}
// 3 완료된 교육 삭제
	private void deleteTraining(Scanner sc) {
		System.out.println("완료된 교육명(반드시 교육명을 정확히 입력하세요.): ");
		String keyword = sc.nextLine();
		File file = new File(FILE);
		File dFile = new File("src/data/training_temp.txt");// 임시파일 지정:임시 파일을 지정해야 본 파일에서 지웟을 때 초기화가 안됨
		try (BufferedReader br = new BufferedReader(new FileReader(file));
				BufferedWriter bw = new BufferedWriter(new FileWriter(dFile))) {
			String line;//한줄 씩 읽기 위한 변수
			boolean found = false;//직원 검색 여부 초기화
			while ((line = br.readLine()) != null) {// 읽은 줄 한줄을 이용해 작업수행
				String[] parts = line.split(",");
				if (parts[0].equals(keyword)) {// line 안에 keyword가 들어있으면 true
					found = true;// 교육명을 찾은 순간 true
					continue;// 아니면 false 상태로 다시 되돌아감
				}
				bw.write(line);
				bw.newLine();
			}
			if (found)// false(found)값으로 초기화
				System.out.println("삭제 처리 완료");
			else// 거짓일 경우 false로 분리
				System.out.println("해당 교육명 없음");
		} catch (IOException e) {
			System.out.println("파일 오류: " + e.getMessage());
			return;
		}
		if (!file.delete() || !dFile.renameTo(file))// delete 데이터 삭제 시도, 밑 temp 파일 덮어쓰기(자바에서는 덮어쓰기를 자동으로 해주지않음)
			System.out.println("파일 처리 중 오류 발생");
	}
}
