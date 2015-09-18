package com.baiyufan.filter.wrappers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * A custom response wrapper that captures all output in a buffer.
 * 
 * BufferedHttpResponseWrapper is an extension of HttpServlet-ResponseWrapper
 * and overrides all methods that affect the Writer or OutputStream back to the
 * client. This allows us to fully capture and control the response before
 * anything is sent back to the client browser.
 * 
 * 
 */
public class BufferedHttpResponseWrapper extends HttpServletResponseWrapper {
	private BufferedServletOutputStream bufferedServletOut = new BufferedServletOutputStream();

	private PrintWriter printWriter = null;
	private ServletOutputStream outputStream = null;

	public BufferedHttpResponseWrapper(HttpServletResponse origResponse) {
		super(origResponse);
	}

	public byte[] getBuffer() {
		return this.bufferedServletOut.getBuffer();
	}

	/**
	 * According to the servlet API, either getWriter( ) or getOutputStream( )
	 * can be called, but not both. This custom response wrapper class cannot
	 * know which is needed, so it must support both. This is definitely an area
	 * where the servlet filtering API can make things a lot easier for
	 * programmers.
	 */
	public PrintWriter getWriter() throws IOException {
		if (this.outputStream != null) {
			throw new IllegalStateException(
					"The Servlet API forbids calling getWriter( ) after"
							+ " getOutputStream( ) has been called");
		}

		if (this.printWriter == null) {
			this.printWriter = new PrintWriter(this.bufferedServletOut);
		}
		return this.printWriter;
	}

	public ServletOutputStream getOutputStream() throws IOException {
		if (this.printWriter != null) {
			throw new IllegalStateException(
					"The Servlet API forbids calling getOutputStream( ) after"
							+ " getWriter( ) has been called");
		}

		if (this.outputStream == null) {
			this.outputStream = this.bufferedServletOut;
		}
		return this.outputStream;
	}

	// override methods that deal with the response buffer

	public void flushBuffer() throws IOException {
		if (this.outputStream != null) {
			this.outputStream.flush();
		} else if (this.printWriter != null) {
			this.printWriter.flush();
		}
	}

	public int getBufferSize() {
		return this.bufferedServletOut.getBuffer().length;
	}

	public void reset() {
		this.bufferedServletOut.reset();
	}

	public void resetBuffer() {
		this.bufferedServletOut.reset();
	}

	public void setBufferSize(int size) {
		this.bufferedServletOut.setBufferSize(size);
	}
}