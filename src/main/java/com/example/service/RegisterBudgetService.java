package com.example.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Category;
import com.example.domain.LivingBudget;
import com.example.domain.Salary;
import com.example.domain.User;
import com.example.form.LivingBudgetForm;
import com.example.repository.CategoryRepository;
import com.example.repository.LivingBudgetRepository;
import com.example.repository.SalaryRepository;

/**
 * 予算登録するサービス.
 * 
 * @author riho.ikeda
 *
 */
@Service
@Transactional
public class RegisterBudgetService {

	@Autowired
	private SalaryRepository salaryRepository;

	@Autowired
	private LivingBudgetRepository livingBudgetRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private SalaryService salaryService;

	@Autowired
	private HttpSession session;

	/**
	 * 予算情報をインサートする.
	 * 
	 * @param livingBudgetForm
	 */
	public void registerBudget(LivingBudgetForm livingBudgetForm) {
		Salary salary = new Salary();
		User user = (User)session.getAttribute("user");
		String userId = user.getUserId();
		salary.setUserId(userId);
		salary.setManSalary(livingBudgetForm.getIntManSalary());
		salary.setWomanSalary(livingBudgetForm.getIntWomanSalary());
		Timestamp timestamp = salaryService.makeTimestamp(livingBudgetForm.getDate());
		salary.setDate(timestamp);
		salaryService.salaryInsert(salary);
		
		
		LivingBudget livingBudget = new LivingBudget();
		List<String> checkNameList = new ArrayList<>();
		for (String categoryName : livingBudgetForm.getCategoryNameList()) {
			if (!(categoryName.equals(""))) {
				checkNameList.add(categoryName);
			}
		}
		livingBudgetForm.setCategoryNameList(checkNameList); // formのnameが空の所を消去してリストを更新.
		List<Integer> checkBudgetList = new ArrayList<>();
		for (Integer budget : livingBudgetForm.getBudgedList()) {
			if (!(budget == null)) {
				checkBudgetList.add(budget);
			}
		}
		livingBudgetForm.setBudgedList(checkBudgetList); // formのbudgetがnullの所を消去してリストを更新.
		livingBudget.setSalaryId(livingBudgetForm.getSalaryId());
		livingBudget.setUserId(livingBudgetForm.getUserId());
		System.out.println(livingBudget.getUserId());
		System.out.println(livingBudgetForm.getUserId());
		Date date = new Date();
		livingBudget.setDate(date);
		livingBudgetRepository.insert(livingBudget);
		Category category = null;
		if (livingBudgetForm.getCategoryNameList() != null) {
			Integer count = 0;
			List<Category> CategoryList = new ArrayList<>();
			for (String categoryName : livingBudgetForm.getCategoryNameList()) {
				category = new Category();
				category.setLivingBudgetId(livingBudget.getId());
				category.setCategoryName(categoryName);
				CategoryList.add(category);
			}
			livingBudget.setCategoryList(CategoryList);
			for (Integer budget : livingBudgetForm.getBudgedList()) {
				category = new Category();
				category = livingBudget.getCategoryList().get(count);
				category.setBudget(budget);
				count += 1;
				categoryRepository.insert(category);
			}
		}
	};

	/**
	 * 確認画面を表示させる.
	 * 
	 * @param livingBudgetForm
	 * @return
	 */
	public Salary comfirm(LivingBudgetForm livingBudgetForm) {
		return salaryRepository.findBySalaryId(livingBudgetForm.getSalaryId());

	}

	/**
	 * 直近の設定予算を検索する.
	 * 
	 * @param userId ユーザID
	 * @return 直近の設定予算.
	 */
	public LivingBudget latestBudget(String userId) {
		List<LivingBudget> livingBudgetList = livingBudgetRepository.findByUserId(userId);
		if (livingBudgetList == null) {
			return null;
		}
		LivingBudget livingBudget = livingBudgetList.get(0);
		List<Category> categoryList = categoryRepository.findByLivingBudgetId(livingBudget.getId());
		livingBudget.setCategoryList(categoryList);
		return livingBudget;

	}

	/**
	 * 過去の予算と給料一覧一式を取得するメソッド.
	 * 
	 * @return 予算一覧
	 */
	public List<Salary> getAllSalaryList() {
		User user = (User) session.getAttribute("user");
		System.out.println(user);
		String userId = user.getUserId();
		List<Salary> salaryList = salaryRepository.findByUserIdUsingResultSetExtractor(userId);
		return salaryList;
	}

	/**
	 * 1ページに表示する履歴リストを取得するメソッド.
	 * 
	 * @param pageNumber  現在のページ番号
	 * @param onePageView 1ページ辺りの表示件数
	 * @return 履歴リスト
	 */
	public List<Salary> getOnePageSalaryList(int pageNumber, int onePageView, int historyCount,
			List<Salary> allSalaryList) {
		User user = (User) session.getAttribute("user");
		String userId = user.getUserId();
		int maxIndex = historyCount - 1;
		int minSalaryIndex = onePageView * pageNumber - 1;
		if (minSalaryIndex >= maxIndex) {
			minSalaryIndex = maxIndex;
		}
		System.out.println(minSalaryIndex);
		int maxSalaryIndex = onePageView * pageNumber - (onePageView - 1) - 1;
		if (maxSalaryIndex <= 0) {
			maxSalaryIndex = 0;
		}
		System.out.println(maxSalaryIndex);
		List<Salary> salaryList = new ArrayList<>();
		if (historyCount != 0) {
			int minSalaryId = allSalaryList.get(minSalaryIndex).getId();
			System.out.println(minSalaryId);
			int maxSalaryId = allSalaryList.get(maxSalaryIndex).getId();
			System.out.println(maxSalaryId);
			salaryList = salaryRepository.findByUserIdWithMinSalaryIdAndmaxSalaryIdUsingResultSetExtractor(userId, minSalaryId,
					maxSalaryId);
		}
		return salaryList;

	}

	/**
	 * ページ番号リストの大きさを決めるメソッド.
	 * 
	 * @param historyCount 履歴の総数
	 * @param onePageView  1ページ辺りの表示件数
	 * @return ページ番号リストの大きさ
	 */
	public int makePagingNumberSize(int historyCount, int onePageView) {
		// 1ページ辺りに表示させる件数を基に分割するページ数を決める
		int number;
		if (historyCount % onePageView == 0) {
			number = historyCount / onePageView;
		} else {
			number = historyCount / onePageView + 1;
		}
		return number;
	}

	/**
	 * ページングに使うoffsetの番号を作成するメソッド.
	 * 
	 * @param pageNumber  現在のページ番号
	 * @param onePageView 1ページ辺りの表示数
	 * @return offsetの数字を返す
	 */
	public int makeOffset(int pageNumber, int onePageView) {
		int offset = 0;
		if (pageNumber == 0) {
			pageNumber = 1;
		}
		offset = onePageView * (pageNumber - 1);
		return offset;
	}

	/**
	 * ページング番号リストを作るメソッド. 現在のページが5以下の場合は1~5のリストを作る. 5より大きい場合は下限を１として±5のリストを作る.
	 * 
	 * 
	 * @param historyCount  履歴の数
	 * @param onePageView   1ページ辺りの表示件数
	 * @param nowPageNumber 現在のページ番号
	 * @return 履歴画面へ遷移
	 */
	public List<String> makePagingNumberList(int historyCount, int onePageView, Integer nowPageNumber) {
		List<String> pagingNumberList = new ArrayList<>();
		int startNumber;
		int limitNumber = makePagingNumberSize(historyCount, onePageView) - 1;
		if (nowPageNumber - 5 <= 0) {
			startNumber = 1;
		} else {
			startNumber = nowPageNumber;
			if (limitNumber - startNumber <= 5) {
				startNumber = limitNumber - 5;
			}
		}
		int listCount = 0;
		for (int i = startNumber - 5; i < nowPageNumber + 5; i++) {
			if (i >= 1) {
				if (listCount == 0) {
					if (i != 1) {
						pagingNumberList.add("前へ");
					}
					pagingNumberList.add(String.valueOf(i));
				} else {
					pagingNumberList.add(String.valueOf(i));
				}
			}
			if (i == limitNumber) {
				break;
			}
			listCount++;
		}
		if (listCount >= 6) {
			pagingNumberList.add("次へ");
		}
		return pagingNumberList;
	}

}