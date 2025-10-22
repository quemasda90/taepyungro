package attendance;

import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

public class AttendanceManager {
	// ������ ���� ����
	private static final String FILE = "src/data/attendance.txt";

	// ���� �޴�
	public void run(Scanner sc) {
		while (true) {
			System.out.println("\n[���°���]");
			System.out.println("\n1. ��� ���");
			System.out.println("2. ��� ���");
			System.out.println("3. ���� ��� ����");
			System.out.println("4. �ڷΰ���");
			System.out.println("����: ");
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
				System.out.println("�߸� �Է� �ϼ˽��ϴ�. 1~4�� �����ϼ���");
		}
	}

// 1 ��� ���
	private void checkIn(Scanner sc) {
		writeRecord(sc, "���");
	}

// 2 ��� ���
	private void checkOut(Scanner sc) {
		writeRecord(sc, "���");
	}

// 1,2 ����� ��� ���� writeRecord ����(�� ��� ���� �ص��ǳ� ������ ��ġ�� �κ��� ���� �������� ���� ��Ƴ���.)
	private void writeRecord(Scanner sc, String type) {// �Ķ���� 2�� ��ĳ��,����� ��� ����(üũ�� üũ �ƿ����� ��,��� ����)
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE, true))) {// BufferedWriter ���
			System.out.println("�̸�: ");
			String name = sc.nextLine();
			Date now = new Date();// �ð� ����
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm");// �̴���ϸ� ���ʿ��� ������ ���ͼ� SimpleDataFormat ���
																			// (EX:UTC)
			String time = sd.format(now);
			bw.write(name + "," + type + "," + time);
			bw.newLine();
			System.out.println(type + "��� �Ϸ�");
		} catch (IOException e) {
			System.out.println("���� ����");
		}
	}

//  3 ���±��
	private void showRecords() {
		try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
			System.out.println("\n[���±��]");
			String line;
			while ((line = br.readLine()) != null)
				System.out.println(line);
		} catch (IOException e) {
			System.out.println("���� ����");
		}
	}
}
