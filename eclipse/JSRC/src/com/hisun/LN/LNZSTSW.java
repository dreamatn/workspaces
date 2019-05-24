package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSTSW {
    int JIBS_tmp_int;
    DBParm LNTCONT_RD;
    DBParm LNTSETL_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    char LNZSTSW_FILLER1 = ' ';
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNRSETL LNRSETL = new LNRSETL();
    LNRCONT LNRCONT = new LNRCONT();
    SCCGWA SCCGWA;
    LNCSTSW LNCSTSW;
    public void MP(SCCGWA SCCGWA, LNCSTSW LNCSTSW) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSTSW = LNCSTSW;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSTSW return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        WS_FOUND_FLG = ' ';
        LNCSTSW.RC.RC_APP = "LN";
        LNCSTSW.RC.RC_RTNCODE = 0;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B01_CHECK();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '4';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSTSW.LN_AC;
        LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        LNCICTLM.FUNC = '2';
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '3';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNCSTSW.LN_AC;
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
        if (LNCAPRDM.REC_DATA.PAY_MTH == '4') {
            if (LNCSTSW.FUNC == 'S') {
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                if (LNCICTLM.REC_DATA.CTL_STSW.substring(20 - 1, 20 + 1 - 1).equalsIgnoreCase("1")) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.DDALN_STSW_EXSIT, LNCSTSW.RC);
                    Z_RET();
                    if (pgmRtn) return;
                } else {
                    if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                    JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                    for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                    LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 20 - 1) + "1" + LNCICTLM.REC_DATA.CTL_STSW.substring(20 + 1 - 1);
                    S000_CALL_LNZICTLM();
                    if (pgmRtn) return;
                }
            } else if (LNCSTSW.FUNC == 'C') {
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                if (LNCICTLM.REC_DATA.CTL_STSW.substring(20 - 1, 20 + 1 - 1).equalsIgnoreCase("0")) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.DDALN_STSW_UNEXSIT, LNCSTSW.RC);
                    Z_RET();
                    if (pgmRtn) return;
                } else {
                    if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                    JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                    for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                    LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 20 - 1) + "0" + LNCICTLM.REC_DATA.CTL_STSW.substring(20 + 1 - 1);
                    S000_CALL_LNZICTLM();
                    if (pgmRtn) return;
                }
            } else {
                IBS.init(SCCGWA, SCCEXCP);
                SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCSTSW.FUNC + ")";
                CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
            } else {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.DDALN_NOT_C, LNCSTSW.RC);
                Z_RET();
                if (pgmRtn) return;
            }
    }
    public void B01_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNCSTSW.LN_AC;
        T000_READ_CONT_PROC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNRSETL);
        LNRSETL.KEY.CONTRACT_NO = LNCSTSW.LN_AC;
        LNRSETL.AC = LNCSTSW.DD_AC;
        LNRSETL.KEY.CCY = LNCSTSW.CCY;
        LNRSETL.CCY_TYP = LNCSTSW.CCY_TYP;
        T000_READ_LNTSETL_ATTR();
        if (pgmRtn) return;
    }
    public void T000_READ_CONT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRCONT.KEY.CONTRACT_NO);
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        IBS.READ(SCCGWA, LNRCONT, LNTCONT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_CONT_NOTFND, LNCSTSW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTSETL_ATTR() throws IOException,SQLException,Exception {
        LNTSETL_RD = new DBParm();
        LNTSETL_RD.TableName = "LNTSETL";
        LNTSETL_RD.where = "CONTRACT_NO = :LNRSETL.KEY.CONTRACT_NO "
            + "AND CI_TYPE = 'B' "
            + "AND AC = :LNRSETL.AC "
            + "AND CCY = :LNRSETL.KEY.CCY "
            + "AND CCY_TYP = :LNRSETL.CCY_TYP "
            + "AND SETTLE_TYPE = '2' "
            + "AND ( AC_TYP = '01' "
            + "OR AC_TYP = '05' )";
        LNTSETL_RD.fst = true;
        LNTSETL_RD.order = "CONTRACT_NO";
        IBS.READ(SCCGWA, LNRSETL, this, LNTSETL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SETL_NOTFND, LNCSTSW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            LNCSTSW.RC.RC_APP = LNCICTLM.RC.RC_APP;
            LNCSTSW.RC.RC_RTNCODE = LNCICTLM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZAPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-APRD-MAINT", LNCAPRDM);
        if (LNCAPRDM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCAPRDM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSTSW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
