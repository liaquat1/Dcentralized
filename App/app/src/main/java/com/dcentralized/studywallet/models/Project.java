package com.dcentralized.studywallet.models;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.dcentralized.studywallet.R;
import com.dcentralized.studywallet.tasks.ProjectFinishTask;
import com.dcentralized.studywallet.tasks.TransactionAddTask;
import com.google.firebase.firestore.Exclude;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * This class represents a project in the application
 *
 * @author Tom de Wildt
 */
public class Project implements Serializable{
	private static final String TAG = Project.class.getSimpleName();
	private String id;
	private String name;
	private String description;
	private Language language;
	private Company owner;
	private int reward;
	private Date time;
	private double difficulty;
	private boolean finished;
	private boolean taken;

	/**
	 * Empty constructor for loading from firestore
	 *
	 * @author Tom de Wildt
	 */
	public Project() {
		// Empty
	}

	/**
	 * Retrieves the logo drawable for the project
	 *
	 * @param context activity context
	 * @return logo or empty drawable
	 * @author Tom de Wildt
	 */
	public Drawable getLogo(Context context) {
		switch (language) {
			case Java:
				return context.getResources().getDrawable(R.drawable.java_logo);
			case CS:
				return context.getResources().getDrawable(R.drawable.cs_marcel_logo);
			case Python:
				return context.getResources().getDrawable(R.drawable.python_logo);
			case Javascript:
				return context.getResources().getDrawable(R.drawable.javascript_logo);
			case Angular:
				return context.getResources().getDrawable(R.drawable.angular_logo);
			case React:
				return context.getResources().getDrawable(R.drawable.react_logo);
			case CPP:
				return context.getResources().getDrawable(R.drawable.cpp_logo);
			case C:
				return context.getResources().getDrawable(R.drawable.c_logo);
			case Go:
				return context.getResources().getDrawable(R.drawable.go_logo);
			case PHP:
				return context.getResources().getDrawable(R.drawable.php_logo);
			case Swift:
				return context.getResources().getDrawable(R.drawable.swift_logo);
			case Kotlin:
				return context.getResources().getDrawable(R.drawable.kotlin_logo);
			case Android:
				return context.getResources().getDrawable(R.drawable.android_logo);
			case HTML:
				return context.getResources().getDrawable(R.drawable.html_logo);
			default:
				return new ColorDrawable(Color.TRANSPARENT);
		}
	}

	public void finish(String userId) {
		try {
			Transaction transaction = null;
			if (time.after(new Date())) {
				transaction = new Transaction(name, reward / 2);
			} else {
				transaction = new Transaction(name, reward);
			}

			ProjectFinishTask projectTask = new ProjectFinishTask(id);
			TransactionAddTask transactionTask = new TransactionAddTask(transaction, userId);

			if (projectTask.execute().get() && transactionTask.execute().get()) {
				finished = true;
			}
		} catch (ExecutionException e) {
			Log.e(TAG, "ExecutionException occurred", e);
		} catch (InterruptedException e) {
			Log.e(TAG, "InterruptException occurred", e);
		}
	}

	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return this.name;
	}
	public String getDescription() {
		return this.description;
	}
	public Language getLanguage() {
		return this.language;
	}
	@Exclude
	public Company getOwner() {
		return this.owner;
	}
	public void setOwner(Company company) {
		this.owner = company;
	}
	public int getReward() {
		return this.reward;
	}
	public Date getTime() {
		return this.time;
	}
	public double getDifficulty() {
		return this.difficulty;
	}
	public boolean getFinished() {
		return this.finished;
	}
	public boolean getTaken() {
		return this.taken;
	}
}