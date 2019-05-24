package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZRCLNO {
    DBParm LNTCLNO_RD;
    brParm LNTCLNO_BR = new brParm();
    boolean pgmRtn = false;
    int WS_BRANCH_BR = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    LNRCLNO LNRCLNO = new LNRCLNO();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    LNCRCLNO LNCRCLNO;
    LNRCLNO LNRCLNO1;
    public void MP(SCCGWA SCCGWA, LNCRCLNO LNCRCLNO) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRCLNO = LNCRCLNO;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZRCLNO return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNRCLNO1 = (LNRCLNO) LNCRCLNO.REC_PTR;
        LNCRCLNO.RETURN_INFO = 'F';
        IBS.init(SCCGWA, LNRCLNO);
        IBS.CLONE(SCCGWA, LNRCLNO1, LNRCLNO);
        LNCRCLNO.RC.RC_MMO = "LN";
        LNCRCLNO.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.BRANCH_BR);
        WS_BRANCH_BR = BPCPQORG.BRANCH_BR;
        if (LNCRCLNO.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRCLNO.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRCLNO.FUNC == 'R') {
            B030_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRCLNO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRCLNO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRCLNO.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCRCLNO.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, LNRCLNO, LNRCLNO1);
    }
    public void B010_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_REC_PROC();
        if (pgmRtn) return;
    }
    public void B020_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_REC_PROC();
        if (pgmRtn) return;
    }
    public void B030_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_UPDATE_PROC();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_REC_PROC();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_REC_PROC();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (LNCRCLNO.OPT == 'S') {
            B060_20_STBR_BY_ALL();
            if (pgmRtn) return;
        } else if (LNCRCLNO.OPT == 'R') {
            B060_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (LNCRCLNO.OPT == 'E') {
            B060_40_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + LNCRCLNO.OPT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B060_20_STBR_BY_ALL() throws IOException,SQLException,Exception {
        T000_STARTBR_PROC_BY_ALL();
        if (pgmRtn) return;
    }
    public void B060_30_READ_NEXT_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_PROC();
        if (pgmRtn) return;
    }
    public void B060_40_END_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_PROC();
        if (pgmRtn) return;
    }
    public void T000_READ_REC_PROC() throws IOException,SQLException,Exception {
        LNRCLNO.KEY.CL_DOMI_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        LNTCLNO_RD = new DBParm();
        LNTCLNO_RD.TableName = "LNTCLNO";
        IBS.READ(SCCGWA, LNRCLNO, LNTCLNO_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "333333");
            LNCRCLNO.RETURN_INFO = 'F';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "4444444");
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CLNO_NOT_EXIST, LNCRCLNO.RC);
            LNCRCLNO.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRCLNO.KEY.CL_DOMI_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        LNRCLNO.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRCLNO.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRCLNO.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRCLNO.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTCLNO_RD = new DBParm();
        LNTCLNO_RD.TableName = "LNTCLNO";
        LNTCLNO_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNRCLNO, LNTCLNO_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCRCLNO.RC);
            LNCRCLNO.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        LNRCLNO.KEY.CL_DOMI_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        LNTCLNO_RD = new DBParm();
        LNTCLNO_RD.TableName = "LNTCLNO";
        LNTCLNO_RD.upd = true;
        IBS.READ(SCCGWA, LNRCLNO, LNTCLNO_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CLNO_NOT_EXIST, LNCRCLNO.RC);
            LNCRCLNO.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRCLNO.KEY.CL_DOMI_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        LNRCLNO.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRCLNO.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTCLNO_RD = new DBParm();
        LNTCLNO_RD.TableName = "LNTCLNO";
        IBS.REWRITE(SCCGWA, LNRCLNO, LNTCLNO_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        LNRCLNO.KEY.CL_DOMI_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        LNTCLNO_RD = new DBParm();
        LNTCLNO_RD.TableName = "LNTCLNO";
        IBS.DELETE(SCCGWA, LNRCLNO, LNTCLNO_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CLNO_NOT_EXIST, LNCRCLNO.RC);
            LNCRCLNO.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC_BY_ALL() throws IOException,SQLException,Exception {
        LNTCLNO_BR.rp = new DBParm();
        LNTCLNO_BR.rp.TableName = "LNTCLNO";
        LNTCLNO_BR.rp.order = "CL_CCY,CL_BUSI_KND";
        IBS.STARTBR(SCCGWA, LNRCLNO, LNTCLNO_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRCLNO, this, LNTCLNO_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CLNO_NOT_EXIST, LNCRCLNO.RC);
            LNCRCLNO.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTCLNO_BR);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCRCLNO.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LNCRCLNO=");
            CEP.TRC(SCCGWA, LNCRCLNO);
            CEP.TRC(SCCGWA, "LNRCLNO =");
            CEP.TRC(SCCGWA, LNRCLNO);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
