package vo;

public class ViewVO {
	String seg_owner;
	String seg_name;
	String operation;
	String sql_redo;
	String sql_undo;
	public String getSeg_owner() {
		return seg_owner;
	}
	public void setSeg_owner(String seg_owner) {
		this.seg_owner = seg_owner;
	}
	public String getSeg_name() {
		return seg_name;
	}
	public void setSeg_name(String seg_name) {
		this.seg_name = seg_name;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getSql_redo() {
		return sql_redo;
	}
	public void setSql_redo(String sql_redo) {
		this.sql_redo = sql_redo;
	}
	public String getSql_undo() {
		return sql_undo;
	}
	public void setSql_undo(String sql_undo) {
		this.sql_undo = sql_undo;
	}
	@Override
	public String toString() {
		return "ViewVO [seg_owner=" + seg_owner + ", seg_name=" + seg_name
				+ ", operation=" + operation + ", sql_redo=" + sql_redo
				+ ", sql_undo=" + sql_undo + "]";
	}
	
}
