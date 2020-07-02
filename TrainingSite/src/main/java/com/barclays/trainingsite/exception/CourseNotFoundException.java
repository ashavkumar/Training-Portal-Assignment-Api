package com.barclays.trainingsite.exception;

@SuppressWarnings("serial")
public class CourseNotFoundException extends Exception {

	public CourseNotFoundException() {
		super();
	}

	public CourseNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public CourseNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public CourseNotFoundException(String arg0) {
		super(arg0);
	}

	public CourseNotFoundException(Throwable arg0) {
		super(arg0);
	}
}
