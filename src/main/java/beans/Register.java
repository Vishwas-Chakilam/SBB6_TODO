package beans;

import java.util.Objects;

public class Register {
    private int regid;
    private String fname;
    private String lname;
    private String email;
    private String pass;
    private long mobile;
    private String address;
    public Register(){
        super();
    }

    public Register(int regid, String fname, String lname, String email, String pass, long mobile, String address) {
        super();
        this.address = address;
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.mobile = mobile;
        this.pass = pass;
        this.regid = regid;
    }

    public int getRegid() {
        return regid;
    }

    public void setRegid(int regid) {
        this.regid = regid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Register{");
        sb.append("regid=").append(regid);
        sb.append(", fname=").append(fname);
        sb.append(", lname=").append(lname);
        sb.append(", email=").append(email);
        sb.append(", pass=").append(pass);
        sb.append(", mobile=").append(mobile);
        sb.append(", address=").append(address);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.regid;
        hash = 89 * hash + Objects.hashCode(this.fname);
        hash = 89 * hash + Objects.hashCode(this.lname);
        hash = 89 * hash + Objects.hashCode(this.email);
        hash = 89 * hash + Objects.hashCode(this.pass);
        hash = 89 * hash + (int) (this.mobile ^ (this.mobile >>> 32));
        hash = 89 * hash + Objects.hashCode(this.address);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Register other = (Register) obj;
        if (this.regid != other.regid) {
            return false;
        }
        if (this.mobile != other.mobile) {
            return false;
        }
        if (!Objects.equals(this.fname, other.fname)) {
            return false;
        }
        if (!Objects.equals(this.lname, other.lname)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.pass, other.pass)) {
            return false;
        }
        return Objects.equals(this.address, other.address);
    }
    
}
