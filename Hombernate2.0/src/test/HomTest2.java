package test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.chd.hombernateFactory.HomSession;
import cn.chd.hombernateFactory.HombernateFactory;

/**
 * @author : homels
 * @date ����ʱ�䣺2018��6��30�� ����10:25:01
 * @ClassName: HomTest2
 * @Description: ���Զ��߳�����
 * @version
 */
public class HomTest2 {
	public static void main(String[] args) {
		List<Thread> threadList = new ArrayList<Thread>();
		for (int i = 0; i <= 3; i++) {
			Thread subThread = new Thread(new workrun(i));
			subThread.start();
			threadList.add(subThread);
		}
		for (Iterator<Thread> iterator = threadList.iterator(); iterator.hasNext();) {
			Thread thread = iterator.next();
			// �ȴ�thread�߳̽������ٽ������߳�(��ǰ�߳�)
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

class workrun implements Runnable {
	int i;

	public workrun(int i) {
		this.i = i;
	}

	@Override
	public void run() {
		HombernateFactory hbf = HombernateFactory.getInstance();
		HomSession session = hbf.getNewSession();

		List<HomUser> list = new ArrayList<HomUser>();
		String sql = "SELECT * FROM USER";
		try {
			list = session.query(sql, HomUser.class, "default");
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (HomUser u : list) {
			System.out.println(this.i + ":" + u.getName() + u.getAge());
		}
	}
}
