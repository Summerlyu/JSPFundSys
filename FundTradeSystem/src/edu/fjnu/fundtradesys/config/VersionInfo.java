package edu.fjnu.fundtradesys.config;

public final class VersionInfo {
	// application info
	public final static String APP_NAME = "������ƽ̨";
	public final static String APP_VERSION = "1.0";
	public final static String APP_STATUS = "Beta";
	public final static String APP_BUILDATE = "2012-12-23";
	public final static String APP_BUILDVER = "#2";

	// application author
	public final static String APP_AUTHOR = "�ڶ�С��";
	public final static String APP_WORKSTUDIO = "����ʦ�����ѧԺ 2010�����ʰ� ";

	public final static String buildFooterStr() {
		StringBuffer sb = new StringBuffer();

		sb.append(APP_NAME);
		sb.append(" " + "(�汾:" + APP_STATUS + APP_VERSION);
		sb.append(" &nbsp;&nbsp;Build:" + APP_BUILDVER);
		sb.append(" " + APP_BUILDATE + ")");
		sb.append("&nbsp;&nbsp;&nbsp;&nbsp;�Ŷ�:&nbsp;<a href=\"mailto:302451283@qq.com\">"
				+ APP_AUTHOR + "</a><br/>");
		sb.append("(C)&nbsp;" + APP_WORKSTUDIO + "&nbsp;&nbsp;");

		return sb.toString();
	}

}