package main;

import java.util.Scanner;
import attendance.AttendanceManager;
import employee.EmployeeManager;
import payroll.PayrollManager;
import training.TrainingManager;

public class MainApp {
	public static void main(String[] args) {
		// �Է� ��ĵ ����
		Scanner sc = new Scanner(System.in);
		// �� ���� ��� �ν��Ͻ� ����
		EmployeeManager em = new EmployeeManager();
		AttendanceManager at = new AttendanceManager();
		TrainingManager tr = new TrainingManager();
		PayrollManager pa = new PayrollManager();
		// �޴� ���
		while (true) {
			System.out.println("==== ����κ��� ���� ���� �ý��� ====");
			System.out.println("1. �λ� ����");
			System.out.println("2. ���� ����");
			System.out.println("3. ���� ����");
			System.out.println("4. �޿� ����");
			System.out.println("5. ����");
			System.out.println("����: ");
			// ����� �Է� �ޱ�
			String sel = sc.nextLine();
			// �޴� ��ȣ �Է� ���1(switch-case):��Ȯ�� �޴� ���� ������ ���ա�break �� ���!
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
				System.out.println("���α׷� ����");
				sc.close();
				return;
			default:
				System.out.println("�߸� �Է� �ϼ˽��ϴ�. 1~5�� �����ϼ���");
			}
		}
	}
}
