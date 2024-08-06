package fi.vm.yti.terminology.api.mscr;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import fi.vm.yti.terminology.api.util.RestUtils;


@Profile({"test", "prod"})
@Service
public class PIDMSServiceImpl implements PIDService {

	@Value("${pidms.apikey}")
	private String apikey;

	@Value("${pidms.prefix}")
	private String prefix;

	@Value("${pidms.url}")
	private String url;

	@Value("${pidms.resolveUrlPrefix}")
	private String resolveUrlPrefix;
	
	@Override
	public String mint(String internalId) throws Exception {
		URL url = new URL(this.url + "/v1/pid");
		HttpURLConnection con = null;
		try {
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.addRequestProperty("apikey", apikey);
			con.addRequestProperty("Content-type", "application/json");
			con.setDoOutput(true);
			String generatedUrl = resolveUrlPrefix + "/terminology-api/api/v1/vocabulary?graphId=" + URLEncoder.encode(internalId);
  			String body = "{ \"url\": \"" + generatedUrl + "\", \"type\": \"Handle\", \"persist\": \"0\"}";
  			
  			OutputStream os = con.getOutputStream();
  			OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");    
  			osw.write(body);
  			osw.flush();
  			osw.close();
  			os.close();
  			
			int status = con.getResponseCode();
			if (status != 200) {
				throw new Exception("Could not mint PID using url " + url);
			}
			String content = RestUtils.readContent(con.getInputStream());
			return "http://hdl.handle.net/" + content;
		} catch (Exception ex) {
			throw ex;
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
	}

	@Override
	public String mintPartIdentifier(String pid) {
		return pid + "@concept=" + UUID.randomUUID();
	}



}
