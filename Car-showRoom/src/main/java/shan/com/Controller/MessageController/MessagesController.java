package shan.com.Controller.MessageController;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.WebContentGenerator;

//import actvila.customer.utils.MessageSourceToJson;

@Controller
public class MessagesController extends WebContentGenerator {
//	@Resource
//	private MessageSourceToJson messageSource;

	/**
	 * 1 year cache
	 */
	private int DEFAULT_CACHE_SECONDS = 31556926;

	public MessagesController() {
		setCacheSeconds(DEFAULT_CACHE_SECONDS);
	}

	@RequestMapping(value = "/messages.js", headers = "Accept=*/*", method = RequestMethod.GET, produces = "application/javascript;charset=UTF-8")
	@ResponseBody
	public String messages(@RequestParam(required = false) String jsonp, HttpServletRequest httpServletRequest,
			HttpServletResponse httpResponse) throws IOException, ServletException {
		return messages(LocaleContextHolder.getLocale(), jsonp, httpServletRequest, httpResponse);
	}

	@RequestMapping(value = "/messages-{locale}.js", headers = "Accept=*/*", method = RequestMethod.GET, produces = "application/javascript;charset=UTF-8")
	@ResponseBody
	public String messages(@PathVariable Locale locale,
			@RequestParam(required = false) String jsonp, HttpServletRequest httpServletRequest,
			HttpServletResponse httpResponse) throws IOException, ServletException {
		LocaleContextHolder.setLocale(locale);

		try {
			httpResponse.setCharacterEncoding("UTF-8");
			checkAndPrepare(httpServletRequest, httpResponse, true);
			final StringWriter messageAsResponse = new StringWriter();

//			if (isJSONP(jsonp)) {
//				messageAsResponse.append(jsonp + "(").append(messageSource.messagesAsJson(locale)).append(");");
//			} else {
//				messageAsResponse.append("var messageSource = ").append(messageSource.messagesAsJson(locale)).append(";");
//			}
			return messageAsResponse.toString();
		} finally {
			LocaleContextHolder.resetLocaleContext();
		}
	}

	private boolean isJSONP(String jsonp) {
		return jsonp != null && jsonp.trim().length() != 0;
	}
}
