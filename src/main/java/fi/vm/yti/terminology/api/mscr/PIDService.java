package fi.vm.yti.terminology.api.mscr;


public interface PIDService {

	public String mint(PIDType type);

	public String mintPartIdentifier(String pid);
}
