package mz.co.exchange.api.core.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SpaController {
  private final Logger LOG = LoggerFactory.getLogger(SpaController.class);
  private static final String REGEX = "{_:^(?!index\\.html|api|swagger-ui|actuator|_nuxt|favicon\\.ico|sw\\.js).*$}";
  private static final String REGEX_VARIANT = REGEX + "/**";

  @GetMapping(value = { REGEX, REGEX_VARIANT," "})
  public String redirect(HttpServletRequest req) {
    LOG.info("URL {} entered directly into the Browser, redirecting...", req.getRequestURI());
    return "forward:/index.html";
  }
}
