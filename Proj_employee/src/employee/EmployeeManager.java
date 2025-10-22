package employee;

import java.io.*;
import java.util.*;

public class EmployeeManager {
	// ������ ���� ����
	public static final String FILE = "src/data/employes.txt";

	// �λ� ���� �޴�
	public void run(Scanner sc) {
		while (true) {
			System.out.println("\n[�λ����]");// \n�� �ٹٲ�
			System.out.println("\n1. ���� ���");
			System.out.println("2. ���� ��� ����");
			System.out.println("3. ���� �˻�");
			System.out.println("4. ��� ���");
			System.out.println("5. �ڷΰ���");
			System.out.println("����: ");
			String sel = sc.nextLine();
			// �޴� ��ȣ �Է� ���1(if-if else):������ �б�(true/false)�� ����
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
				System.out.println("�߸� �Է� �ϼ˽��ϴ�. 1~5�� �����ϼ���");
		}
	}

// 1 ���� ���
	private void addE(Scanner sc) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE, true))) {// ���(����) �غ�
			System.out.println("�̸�: ");
			String name = sc.nextLine();
			System.out.println("�μ�: ");
			String dept = sc.nextLine();
			System.out.println("����: ");
			String pos = sc.nextLine();
			bw.write(name + "," + dept + "," + pos);
			bw.newLine();
			System.out.println("��� �Ϸ�");
		} catch (IOException e) {
			System.out.println("���� ����:" + e.getMessage());
		}
	}

// 2 ���� ���
	private void listE() {
		try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {// ��� ����(�б�) �غ�
			System.out.println("\n[���� ���]");
			String line;
			while ((line = br.readLine()) != null)
				System.out.println(line);
		} catch (IOException e) {
			System.out.println("���� ����");
		}
	}

//3 �˻��� �̸�
	private void searchE(Scanner sc) {// ���� �˻�(��ĳ��) �� ����(�б�) �غ�
		System.out.println("�˻��� �̸�: ");
		String keyword = sc.nextLine();
		try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
			boolean found = false;
			String line;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(",");
				if (parts[0].equals(keyword)) {
					System.out.println("�˻� ���:" + line);
					found = true;
				}
			}
			if (!found)
				System.out.println("�ش� ���� ����.");
		} catch (IOException e) {
			System.out.println("���� ����.");
		}
	}

//4 ��� ó��
	private void deleteE(Scanner sc) {// ���� ��� ���(����), ����(�б�), �˻�(��ĳ��) �غ�
		System.out.println("��� ó���� ���� �̸�: ");
		String keyword = sc.nextLine();
		File file = new File(FILE);
		File dFile = new File("src/data/employes_temp.txt");// �ӽ����� ����:�ӽ� ������ �����ؾ� �� ���Ͽ��� ���m�� �� �ʱ�ȭ�� �ȵ�
		try (BufferedReader br = new BufferedReader(new FileReader(file));
				BufferedWriter bw = new BufferedWriter(new FileWriter(dFile))) {
			String line;
			boolean found = false;// ó���� ��ã�� ���·� ����
			while ((line = br.readLine()) != null) {// ���� �� ������ �̿��� �۾�����
				String[] parts = line.split(",");
				if (parts[0].equals(keyword)) {// line �ȿ� keyword�� ��������� true
					found = true;// ���� �̸��� ã�� ���� true
					continue;// �ƴϸ� false ���·� �ٽ� �ǵ��ư�
				}
				bw.write(line);
				bw.newLine();
			}
			if (found)// false(found)������ �ʱ�ȭ
				System.out.println("��� ó�� �Ϸ�");
			else// ������ ��� false�� �и�
				System.out.println("�ش� ���� ����");
		} catch (IOException e) {
			System.out.println("���� ����: " + e.getMessage());
			return;
		}
		if (!file.delete() || !dFile.renameTo(file))// delete ������ ���� �õ�, �� �����(�ڹٿ����� ����⸦ �ڵ����� ����������)
			System.out.println("���� ó�� �� ���� �߻�");
	}
}
