

import java.util.ArrayList;
import java.util.List;
import com.hisun.SC.*;;

public class SYS_HEADO {
	public String SvcCd         ;
	public String SvcScnCd      ;
	public String SvcVer        ;
	public String CnsmrCd       ;
	public String CnsmrSeqNo    ;
	public String ChnlTp        ;
	public String TmnlCd        ;
	public String OrigCnsmrCd   ;
	public String OrigCnsmrSeqNo;
	public String SplrCd        ;
	public String SplrSeqNo     ;
	public String TranDt        ;
	public String TranTm        ;
	public String MacNd         ;
	public String MacVal        ;
	public String SysDealSt     ;
	public List<Ret> Ret = new ArrayList<Ret>();

	public String getSvcCd() {
        return SvcCd;
    }

	public String getSvcScnCd() {
        return SvcScnCd;
    }

	public String getSvcVer() {
        return SvcVer;
    }

	public String getCnsmrCd() {
        return CnsmrCd;
    }

	public String getCnsmrSeqNo() {
        return CnsmrSeqNo;
    }

	public String getTranDt() {
        return TranDt;
    }

	public String getTranTm() {
        return TranTm;
    }

	public String getTmnlCd() {
        return TmnlCd;
    }

	public String getChnlTp() {
        return ChnlTp;
    }

	public String getOrigCnsmrCd() {
        return OrigCnsmrCd;
    }

	public String getOrigCnsmrSeqNo() {
        return OrigCnsmrSeqNo;
    }

	public String getSplrCd() {
        return SplrCd;
    }

	public String getSplrSeqNo() {
        return SplrSeqNo;
    }

	public String getMacNd() {
        return MacNd;
    }

	public String getMacVal() {
        return MacVal;
    }

	public String getSysDealSt() {
        return SysDealSt;
    }

	public List<Ret> getRet() {
        return Ret;
    }

}
