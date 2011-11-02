package com.zombietank.email;

import static com.zombietank.email.support.InternetAddressBuilder.newAddress;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.InternetAddress;

import org.springframework.core.io.Resource;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
public class Email implements Serializable {
	private static final long serialVersionUID = 1L;
	private final List<InternetAddress> to = new ArrayList<InternetAddress>(1);
	private final List<InternetAddress> cc = new ArrayList<InternetAddress>(1);
	private final List<InternetAddress> bcc = new ArrayList<InternetAddress>(1);
	private final List<Resource> attachments = new ArrayList<Resource>(0);
	private final Map<String, String> headers = new HashMap<String, String>();
	private InternetAddress from;
	private InternetAddress replyTo;
	private String subject = "";
	private Priority priority = Priority.NORMAL;
	private String message = "";
	private boolean html = false;

	public Email to(String address) {
		return to(newAddress(address));
	}

	public Email to(String address, String personal) {
		return to(newAddress(address, personal));
	}

	public Email to(InternetAddress address) {
		to.add(address);
		return this;
	}

	public void clearTo() {
		to.clear();
	}

	public Email from(String address) {
		return from(newAddress(address));
	}

	public Email from(String address, String personal) {
		return from(newAddress(address, personal));
	}

	public Email from(InternetAddress address) {
		setFrom(address);
		return this;
	}

	public Email replyTo(String address) {
		return replyTo(newAddress(address));
	}

	public Email replyTo(String address, String personal) {
		return replyTo(newAddress(address, personal));
	}

	public Email replyTo(InternetAddress address) {
		setReplyTo(address);
		return this;
	}

	public Email cc(String address) {
		return cc(newAddress(address));
	}

	public Email cc(String address, String personal) {
		return cc(newAddress(address, personal));
	}

	public void clearCc() {
		cc.clear();
	}

	public Email cc(InternetAddress address) {
		cc.add(address);
		return this;
	}

	public Email bcc(String address) {
		return bcc(newAddress(address));
	}

	public Email bcc(String address, String personal) {
		return bcc(newAddress(address, personal));
	}

	public Email bcc(InternetAddress address) {
		bcc.add(address);
		return this;
	}

	public void clearBcc() {
		bcc.clear();
	}

	public Email withSubject(String subject) {
		setSubject(subject);
		return this;
	}

	public Email withMessage(String message) {
		setMessage(message);
		return this;
	}

	public Email withPriority(Priority priority) {
		setPriority(priority);
		return this;
	}

	public Email withAttachment(Resource attachment) {
		attachments.add(attachment);
		return this;
	}

	public Email inHtml(boolean inHtml) {
		setHtml(inHtml);
		return this;
	}

	public boolean hasReplyTo() {
		return replyTo != null;
	}

	public boolean hasAttachments() {
		return !attachments.isEmpty();
	}

	/***
	 * Custom toString. Roo wasn't handling list fields quite like I'd hope.
	 */
	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Attachments: ").append(getAttachments() == null ? "null" : getAttachments().toString()).append(", ");
        sb.append("To: ").append(getTo() == null ? "null" : getTo()).append(", ");
        sb.append("From: ").append(getFrom()).append(", ");
        sb.append("ReplyTo: ").append(getReplyTo()).append(", ");
        sb.append("Bcc: ").append(getBcc() == null ? "null" : getBcc()).append(", ");
        sb.append("Cc: ").append(getCc() == null ? "null" : getCc()).append(", ");
        sb.append("Priority: ").append(getPriority()).append(", ");
        sb.append("Subject: ").append(getSubject()).append(", ");
        sb.append("Message: ").append(getMessage()).append(", ");
        sb.append("Html: ").append(isHtml());
        return sb.toString();
    }
}
