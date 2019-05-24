package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZQHSEQ {
    int JIBS_tmp_int;
    brParm BPTHSEQ_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    BPZQHSEQ_WS_MSGID WS_MSGID = new BPZQHSEQ_WS_MSGID();
    int WS_LEN = 0;
    int WS_I = 0;
    int WS_J = 0;
    BPZQHSEQ_WS_MM_DATA[] WS_MM_DATA = new BPZQHSEQ_WS_MM_DATA[12];
    BPZQHSEQ_WS_COND_FLG WS_COND_FLG = new BPZQHSEQ_WS_COND_FLG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPRHSEQ BPRHSEQ = new BPRHSEQ();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRSEQ BPRSEQ = new BPRSEQ();
    BPCRMSEQ BPCRMSEQ = new BPCRMSEQ();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCQHSEQ BPCQHSEQ;
    BPRHSEQ BPRHSEQ1;
    public BPZQHSEQ() {
        for (int i=0;i<12;i++) WS_MM_DATA[i] = new BPZQHSEQ_WS_MM_DATA();
    }
    public void MP(SCCGWA SCCGWA, BPCQHSEQ BPCQHSEQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCQHSEQ = BPCQHSEQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZQHSEQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCQHSEQ.RC);
        BPRHSEQ1 = (BPRHSEQ) BPCQHSEQ.INFO.POINTER;
        IBS.init(SCCGWA, BPRHSEQ);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRHSEQ1);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRHSEQ);
        CEP.TRC(SCCGWA, BPCQHSEQ);
        CEP.TRC(SCCGWA, BPCQHSEQ);
        CEP.TRC(SCCGWA, BPRHSEQ.KEY.TYPE);
        CEP.TRC(SCCGWA, BPRHSEQ.KEY.CODE);
        CEP.TRC(SCCGWA, BPRHSEQ.KEY.SEQ_NO);
        CEP.TRC(SCCGWA, BPRHSEQ.CI_TYPE);
        CEP.TRC(SCCGWA, BPRHSEQ.CI_NO);
        CEP.TRC(SCCGWA, BPRHSEQ.AC_NO);
        CEP.TRC(SCCGWA, "LW1");
        CEP.TRC(SCCGWA, "LW2");
        CEP.TRC(SCCGWA, BPRHSEQ.CT_NO);
        CEP.TRC(SCCGWA, BPRHSEQ.USED_FLAG);
        CEP.TRC(SCCGWA, BPRHSEQ.USED_DATE);
        CEP.TRC(SCCGWA, BPRHSEQ.CI_NAME);
        CEP.TRC(SCCGWA, BPRHSEQ.AC_OFFICER);
        CEP.TRC(SCCGWA, BPRHSEQ.REMARK);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCQHSEQ.INFO.FUNC == 'C') {
            B100_CHECK_CI_PROC();
            if (pgmRtn) return;
        } else if (BPCQHSEQ.INFO.FUNC == 'A') {
            T000_STARTBR_BPTHSEQ_AC();
            if (pgmRtn) return;
            T000_READNEXT_BPTHSEQ();
            if (pgmRtn) return;
            T000_ENDBR_BPTHSEQ();
            if (pgmRtn) return;
        } else if (BPCQHSEQ.INFO.FUNC == 'T') {
            T000_STARTBR_BPTHSEQ_CNTR();
            if (pgmRtn) return;
            T000_READNEXT_BPTHSEQ();
            if (pgmRtn) return;
            T000_ENDBR_BPTHSEQ();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCQHSEQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_CHECK_CI_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTHSEQ_CI();
        if (pgmRtn) return;
        T000_READNEXT_BPTHSEQ();
        if (pgmRtn) return;
        if (BPCQHSEQ.RETURN_INFO == 'F') {
            T000_ENDBR_BPTHSEQ();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_BPTHSEQ_CI_BY_ITV();
            if (pgmRtn) return;
            T000_READNEXT_BPTHSEQ();
            if (pgmRtn) return;
            if (BPCQHSEQ.RETURN_INFO == 'F') {
                B110_UPDATE_BPTSEQ_WITH_MAX_CI();
                if (pgmRtn) return;
                T000_ENDBR_BPTHSEQ();
                if (pgmRtn) return;
            } else {
                T000_ENDBR_BPTHSEQ();
                if (pgmRtn) return;
            }
        }
    }
    public void B110_UPDATE_BPTSEQ_WITH_MAX_CI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRSEQ);
        BPRSEQ.KEY.TYPE = "CINO";
        if (BPRHSEQ.CI_NO == null) BPRHSEQ.CI_NO = "";
        JIBS_tmp_int = BPRHSEQ.CI_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) BPRHSEQ.CI_NO += " ";
        BPRSEQ.KEY.NAME = BPRHSEQ.CI_NO.substring(0, 3);
        BPCRMSEQ.FUNC = 'R';
        CEP.TRC(SCCGWA, BPRSEQ.KEY.TYPE);
        CEP.TRC(SCCGWA, BPRSEQ.KEY.NAME);
        BPCRMSEQ.PTR = BPRSEQ;
        BPCRMSEQ.LEN = 289;
        S000_CALL_BPZRMSEQ();
        if (pgmRtn) return;
        if (BPCRMSEQ.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "XXXXXXXXX");
        CEP.TRC(SCCGWA, BPRSEQ.OLD_SEQ_NO);
        CEP.TRC(SCCGWA, BPRSEQ.SEQ_NO);
        if (BPRHSEQ.CI_NO == null) BPRHSEQ.CI_NO = "";
        JIBS_tmp_int = BPRHSEQ.CI_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) BPRHSEQ.CI_NO += " ";
        if (BPRHSEQ.CI_NO.substring(4 - 1, 4 + 5 - 1).trim().length() == 0) BPRSEQ.OLD_SEQ_NO = 0;
        else BPRSEQ.OLD_SEQ_NO = Long.parseLong(BPRHSEQ.CI_NO.substring(4 - 1, 4 + 5 - 1));
        if (BPRHSEQ.MAX_CI == null) BPRHSEQ.MAX_CI = "";
        JIBS_tmp_int = BPRHSEQ.MAX_CI.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) BPRHSEQ.MAX_CI += " ";
        if (BPRHSEQ.MAX_CI.substring(4 - 1, 4 + 5 - 1).trim().length() == 0) BPRSEQ.SEQ_NO = 0;
        else BPRSEQ.SEQ_NO = Long.parseLong(BPRHSEQ.MAX_CI.substring(4 - 1, 4 + 5 - 1));
        BPCRMSEQ.FUNC = 'U';
        CEP.TRC(SCCGWA, BPRSEQ.KEY.TYPE);
        CEP.TRC(SCCGWA, BPRSEQ.KEY.NAME);
        CEP.TRC(SCCGWA, "YYYYYYYYY");
        CEP.TRC(SCCGWA, BPRSEQ.OLD_SEQ_NO);
        CEP.TRC(SCCGWA, BPRSEQ.SEQ_NO);
        S000_CALL_BPZRMSEQ();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTHSEQ_CI() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "STARTBR-CI");
        BPTHSEQ_BR.rp = new DBParm();
        BPTHSEQ_BR.rp.TableName = "BPTHSEQ";
        BPTHSEQ_BR.rp.where = "CI_NO = :BPRHSEQ.CI_NO";
        IBS.STARTBR(SCCGWA, BPRHSEQ, this, BPTHSEQ_BR);
    }
    public void T000_STARTBR_BPTHSEQ_CI_BY_ITV() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "STARTBR-CI BY INTERVAL");
        BPTHSEQ_BR.rp = new DBParm();
        BPTHSEQ_BR.rp.TableName = "BPTHSEQ";
        BPTHSEQ_BR.rp.where = "CI_NO <= :BPRHSEQ.CI_NO "
            + "AND MAX_CI >= :BPRHSEQ.CI_NO";
        IBS.STARTBR(SCCGWA, BPRHSEQ, this, BPTHSEQ_BR);
    }
    public void T000_STARTBR_BPTHSEQ_AC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "STARTBR-AC");
        BPTHSEQ_BR.rp = new DBParm();
        BPTHSEQ_BR.rp.TableName = "BPTHSEQ";
        BPTHSEQ_BR.rp.where = "AC_NO = :BPRHSEQ.AC_NO";
        IBS.STARTBR(SCCGWA, BPRHSEQ, this, BPTHSEQ_BR);
    }
    public void T000_STARTBR_BPTHSEQ_CNTR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "STARTBR-CNTR");
        BPTHSEQ_BR.rp = new DBParm();
        BPTHSEQ_BR.rp.TableName = "BPTHSEQ";
        BPTHSEQ_BR.rp.where = "CT_NO = :BPRHSEQ.CT_NO";
        IBS.STARTBR(SCCGWA, BPRHSEQ, this, BPTHSEQ_BR);
    }
    public void T000_READNEXT_BPTHSEQ() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "NEXT BROWER");
        BPTHSEQ_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRHSEQ, this, BPTHSEQ_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "RECORD FOUND");
            BPCQHSEQ.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "RECORD NOT FOUND");
            BPCQHSEQ.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTHSEQ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTHSEQ() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTHSEQ_BR);
    }
    public void S000_CALL_BPZRMSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MAINT-SEQ", BPCRMSEQ);
        if (BPCRMSEQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRMSEQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCQHSEQ.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCQHSEQ = ");
            CEP.TRC(SCCGWA, BPCQHSEQ);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
