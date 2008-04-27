package org.judgeos.gwt.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.ScrollPanel;
import org.judgeos.gwt.client.model.Contest;
import org.judgeos.gwt.client.model.Member;
import org.judgeos.gwt.client.model.MemberProblemStatus;
import org.judgeos.gwt.client.model.Problem;
import org.judgeos.gwt.client.model.Results;

import java.util.ArrayList;

public class ResultsWidget extends ScrollPanel  {
	private Grid resultsTable;
	private AsyncCallback callback = new JudgeosCallback() {
		public void onSuccess(Object result) {
			super.onSuccess(result);
			fillResults((Results) result);
		}
	};

	public ResultsWidget() {
		ControlPanel.showFetchingData();
		ControlPanel.showMessage(ControlPanel.messages.fetchingData());

		JudgeosServiceAsync service = ControlPanel.getService();
		service.getResults(callback);
	}

	private void fillResults(Results results) {
		/**
		 * @gwt.typeArgs <org.judgeos.gwt.client.model.Member>
		 */
		ArrayList positions = results.getPositions();
		Contest contest = results.getContest();
		/**
		 * @gwt.typeArgs <org.judgeos.gwt.client.model.Problem>
		 */
		ArrayList problems = contest.getProblems();

		int membersCount = positions.size();
		int problemsCount = contest.getProblems().size();

		resultsTable = new Grid(
			1 + membersCount,
			1 + problemsCount + 1
		);

		int i, j;
		Member member;
		MemberProblemStatus memberProblemStatus;

		resultsTable.setText(0, 0, contest.getName());
		for (j = 0; j < problemsCount; j++) {
			resultsTable.setText(0, 0, ((Problem)problems.get(j)).getName());
		}
		resultsTable.setText(0, j + 1, "");

		for (i = 0; i < membersCount; i++) {
			member = (Member)positions.get(i);
			resultsTable.setText(i, 0, member.getName());
			for (j = 0; j < problemsCount; j++) {
				memberProblemStatus = (MemberProblemStatus) member.getProblemStatus().get(problems.get(j));
				resultsTable.setText(i, j,
					memberProblemStatus.getSolutionsCount() == 0 ? "" : (
						(memberProblemStatus.isAccepted() ? "+" : "-") + memberProblemStatus.getSolutionsCount()
					)
				);
			}
			resultsTable.setText(i, 0, "---");
		}
	}

}
