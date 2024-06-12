package fi.vm.yti.terminology.api.mscr;


public interface PIDService {

	public String mint(String internalId) throws Exception;

	public String mintPartIdentifier(String pid) throws Exception;
}
