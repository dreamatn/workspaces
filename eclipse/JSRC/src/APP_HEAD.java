

import java.util.ArrayList;
import java.util.List;

public class APP_HEAD {
	public String ReSndFlg;  
	public String LglCd;     
	public String ClrgDt;    
	public String TlrNo;     
	public String BrchCd;    
	public String TlrPswd;   
	public String TlrLvl;    
	public String TlrTp;     
	public String ChkTlrFlg; 
	public List<EntrTlrArr> EntrTlrArr = new ArrayList<EntrTlrArr>();
	public String AuthFlg;   
	public List<AuthTlrArr> AuthTlrArr = new ArrayList<AuthTlrArr>();

	public String getReSndFlg() {
        return ReSndFlg;
    }

	public String getLglCd() {
        return LglCd;
    }

	public String getClrgDt() {
        return ClrgDt;
    }

	public String getTlrNo() {
        return TlrNo;
    }

	public String getBrchCd() {
        return BrchCd;
    }

	public String getTlrPswd() {
        return TlrPswd;
    }

	public String getTlrLvl() {
        return TlrLvl;
    }

	public String getTlrTp() {
        return TlrTp;
    }

	public String getChkTlrFlg() {
        return ChkTlrFlg;
    }

	public List<EntrTlrArr> getEntrTlrArr() {
        return EntrTlrArr;
    }

	public String getAuthFlg() {
        return AuthFlg;
    }

	public List<AuthTlrArr> getAuthTlrArr() {
        return AuthTlrArr;
    }

}
