package board.command;

import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import mvc.command.CommandHandler;




public class ArticleImageUploadHandler implements CommandHandler {
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		String uploadPath = request.getSession().getServletContext().getRealPath("/");
		int size = 1024 * 1024 * 10;
		
		String filename = "";
		String oriFile = "";
		System.out.println("들어가나?");
		try {
			MultipartRequest multi 
				= new MultipartRequest(request, uploadPath, size, "UTF-8", new DefaultFileRenamePolicy());
			Enumeration<?> files = multi.getFileNames();
			String str = (String) files.nextElement();
			
			filename = multi.getFilesystemName(str);
			oriFile = multi.getOriginalFileName(str);
			
			String boardImage = filename;
			
			System.out.println("/boardimg/" + filename);
			PrintWriter out;
			out = response.getWriter();
			out.print("/boardimg/" + filename);
			
			return null;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return oriFile;
	}

}
