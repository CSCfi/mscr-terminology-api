package fi.vm.yti.terminology.api.mscr;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class FakePIDServiceImpl implements PIDService {
	
	public String mint(PIDType type) {
		return "urn:IAMNOTAPID:" + UUID.randomUUID();
	}

	@Override
	public String mintPartIdentifier(String pid) {		 
		return pid + "@mapping=" + UUID.randomUUID();
	}
}
