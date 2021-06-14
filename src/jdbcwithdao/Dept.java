package jdbcwithdao;

public class Dept {
	private int deptnum;
	private String deptname;
	private String loc;
public Dept(int deptnum, String deptname, String loc) {
	this.deptnum = deptnum;
	this.deptname = deptname;
	this.loc = loc;
	}
public int getDeptnum() {
	return deptnum;
}
public void setDeptnum(int deptnum) {
	this.deptnum = deptnum;
}
public String getDeptname() {
	return deptname;
}
public void setDeptname(String deptname) {
	this.deptname = deptname;
}
public String getLoc() {
	return loc;
}
public void setLoc(String loc) {
	this.loc = loc;
}
@Override
public String toString() {
	return "Dept [deptnum=" + deptnum + ", deptname=" + deptname + ", loc=" + loc + "]";
}
	

}
