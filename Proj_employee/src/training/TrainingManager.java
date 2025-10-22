package training;

import java.io.*;
import java.util.*;

public class TrainingManager {
	private static final String FILE = "src/data/training.txt";

	public void run(Scanner sc) {// ���� ���
		while (true) {
			System.out.println("\n[��������]");
			System.out.println("\n1. ���� ���");
			System.out.println("2. ���� ���");
			System.out.println("3. �Ϸ�� ���� ����");
			System.out.println("4. �ڷΰ���");
			System.out.println("����: ");
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
				System.out.println("�߸� �Է� �ϼ˽��ϴ�. 1~4�� �����ϼ���");
		}
	}

// 1 ���� ���
	private void addTraining(Scanner sc) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE, true))) {
			System.out.println("������: ");
			String name = sc.nextLine();
			System.out.println("�����: ");
			String instructor = sc.nextLine();
			bw.write(name + "," + instructor);
			bw.newLine();
			System.out.println("��� �Ϸ�");
		} catch (IOException e) {
			System.out.println("���� ����");
		}
	}
// 2 ���� ����Ʈ
	private void listTraining() {
		try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
			System.out.println("\n[�������]");
			String line;
			while ((line = br.readLine()) != null)
				System.out.println(line);
		} catch (IOException e) {
			System.out.println("���� ����");
		}
	}
// 3 �Ϸ�� ���� ����
	private void deleteTraining(Scanner sc) {
		System.out.println("�Ϸ�� ������(�ݵ�� �������� ��Ȯ�� �Է��ϼ���.): ");
		String keyword = sc.nextLine();
		File file = new File(FILE);
		File dFile = new File("src/data/training_temp.txt");// �ӽ����� ����:�ӽ� ������ �����ؾ� �� ���Ͽ��� ���m�� �� �ʱ�ȭ�� �ȵ�
		try (BufferedReader br = new BufferedReader(new FileReader(file));
				BufferedWriter bw = new BufferedWriter(new FileWriter(dFile))) {
			String line;//���� �� �б� ���� ����
			boolean found = false;//���� �˻� ���� �ʱ�ȭ
			while ((line = br.readLine()) != null) {// ���� �� ������ �̿��� �۾�����
				String[] parts = line.split(",");
				if (parts[0].equals(keyword)) {// line �ȿ� keyword�� ��������� true
					found = true;// �������� ã�� ���� true
					continue;// �ƴϸ� false ���·� �ٽ� �ǵ��ư�
				}
				bw.write(line);
				bw.newLine();
			}
			if (found)// false(found)������ �ʱ�ȭ
				System.out.println("���� ó�� �Ϸ�");
			else// ������ ��� false�� �и�
				System.out.println("�ش� ������ ����");
		} catch (IOException e) {
			System.out.println("���� ����: " + e.getMessage());
			return;
		}
		if (!file.delete() || !dFile.renameTo(file))// delete ������ ���� �õ�, �� temp ���� �����(�ڹٿ����� ����⸦ �ڵ����� ����������)
			System.out.println("���� ó�� �� ���� �߻�");
	}
}
