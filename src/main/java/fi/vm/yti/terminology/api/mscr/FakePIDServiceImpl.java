package fi.vm.yti.terminology.api.mscr;

import java.util.UUID;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile({"dev"})
@Service
public class FakePIDServiceImpl implements PIDService {
	
	public String mint(String internalId) throws Exception {
		return "urn:IAMNOTAPID:" + UUID.randomUUID();
	}

	@Override
	public String mintPartIdentifier(String pid) {		 
		return pid + "@mapping=" + UUID.randomUUID();
	}
}
