package daili;

public class LocalAccess implements IAccess{
	
	private Integer index;

	public LocalAccess(Integer index) {
		this.index = index;
	}
	

	@Override
	public String getUrl(String name) {
		return "url---" + name + "----" + index;
	}
}
