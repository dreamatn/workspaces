package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFPLCK {
    brParm BPTBOXP_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_P_INQ_ORG_STATION = "BP-P-INQ-ORG-STATION";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String CPN_R_STARTBR_TLVB = "BP-R-STARTBR-TLVB";
    String CPN_R_BPTVHPB_MTN = "BP-R-BPTVHPB-MTN";
    String WS_ERR_MSG = " ";
    String WS_ERR_INF = " ";
    int WS_CNT = 0;
    char WS_MSG_TYP = ' ';
    BPZFPLCK_WS_ERROR_INFO WS_ERROR_INFO = new BPZFPLCK_WS_ERROR_INFO();
    char WS_TBL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    BPCRVHPB BPCRVHPB = new BPCRVHPB();
    BPCRTLVB BPCRTLVB = new BPCRTLVB();
    BPRBOXP BPRBOXP = new BPRBOXP();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPRVHPB BPRVHPB = new BPRVHPB();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCFPLCK BPCFPLCK;
    public void MP(SCCGWA SCCGWA, BPCFPLCK BPCFPLCK) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFPLCK = BPCFPLCK;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFPLCK return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_CHECK_PLAN();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCFPLCK.BR == ' ') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCFPLCK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_CHECK_PLAN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOCLWD);
        BPCOCLWD.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
        BPCOCLWD.WDAYS = 2;
        BPCOCLWD.CAL_CODE = "CN";
        S000_CALL_BPZPCLWD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCOCLWD.DATE2);
        BPRBOXP.KEY.PLAN_DATE = BPCOCLWD.DATE2;
        BPRBOXP.KEY.BR = BPCFPLCK.BR;
        CEP.TRC(SCCGWA, BPRBOXP.KEY.BR);
        WS_TBL_FLAG = 'Y';
        BPTBOXP_BR.rp = new DBParm();
        BPTBOXP_BR.rp.TableName = "BPTBOXP";
        BPTBOXP_BR.rp.where = "PLAN_DATE = :BPRBOXP.KEY.PLAN_DATE "
            + "AND BR = :BPRBOXP.KEY.BR";
        IBS.STARTBR(SCCGWA, BPRBOXP, this, BPTBOXP_BR);
        T00_BOXP_READNEXT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_TBL_FLAG);
        while (WS_TBL_FLAG != 'N') {
            CEP.TRC(SCCGWA, BPRBOXP.KEY.BOX_TYPE);
            CEP.TRC(SCCGWA, BPRBOXP.PLAN_TLR);
            CEP.TRC(SCCGWA, WS_ERROR_INFO);
            if (BPRBOXP.PLAN_TLR.trim().length() > 0) {
                if (BPRBOXP.KEY.BOX_TYPE == '0') {
                    IBS.init(SCCGWA, BPRTLVB);
                    BPRTLVB.KEY.BR = BPRBOXP.KEY.BR;
                    BPRTLVB.CRNT_TLR = BPRBOXP.PLAN_TLR;
                    BPRTLVB.PLBOX_TP = '3';
                    BPCRTLVB.INFO.FUNC = 'R';
                    BPCRTLVB.INFO.LEN = 187;
                    BPCRTLVB.INFO.POINTER = BPRTLVB;
                    S000_CALL_BPZRTLVB();
                    if (pgmRtn) return;
                    BPCRTLVB.INFO.FUNC = 'N';
                    BPCRTLVB.INFO.POINTER = BPRTLVB;
                    BPCRTLVB.INFO.LEN = 187;
                    S000_CALL_BPZRTLVB();
                    if (pgmRtn) return;
                    if (BPCRTLVB.RETURN_INFO == 'F') {
                        WS_CNT += 1;
                        WS_ERROR_INFO.WS_BOX_INFO[WS_CNT-1].WS_BOXNO_INFO = BPRBOXP.KEY.BOX_NO;
                    }
                } else {
                    IBS.init(SCCGWA, BPRVHPB);
                    IBS.init(SCCGWA, BPCRVHPB);
                    BPRVHPB.CUR_TLR = BPRBOXP.PLAN_TLR;
                    BPRVHPB.POLL_BOX_IND = '0';
                    BPRVHPB.STS = 'N';
                    BPCRVHPB.INFO.FUNC = 'F';
                    S000_CALL_BPZRVHPB();
                    if (pgmRtn) return;
                    if (BPCRVHPB.RETURN_INFO == 'F') {
                        WS_CNT += 1;
                        WS_ERROR_INFO.WS_BOX_INFO[WS_CNT-1].WS_BOXNO_INFO = BPRBOXP.KEY.BOX_NO;
                    }
                }
            } else {
            }
            T00_BOXP_READNEXT();
            if (pgmRtn) return;
        }
        if (BPCRVHPB.RETURN_INFO == 'F' 
            || BPCRTLVB.RETURN_INFO == 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PLAN_CHK_ERR;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            if (JIBS_tmp_str[0].equalsIgnoreCase("9994610")) {
                WS_MSG_TYP = 'E';
            }
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T00_BOXP_READNEXT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRBOXP, this, BPTBOXP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        }
    }
    public void S000_CALL_BPZRVHPB() throws IOException,SQLException,Exception {
        BPCRVHPB.INFO.POINTER = BPRVHPB;
        BPCRVHPB.INFO.LEN = 152;
        IBS.CALLCPN(SCCGWA, CPN_R_BPTVHPB_MTN, BPCRVHPB);
    }
    public void S000_CALL_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-WORK-DAY", BPCOCLWD);
    }
    public void S000_CALL_BPZRTLVB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_STARTBR_TLVB, BPCRTLVB);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        SCCMSG SCCMSG = new SCCMSG();
        IBS.init(SCCGWA, SCCMSG);
        SCCMSG.MSGID = WS_ERR_MSG;
        SCCMSG.TYPE = WS_MSG_TYP;
        SCCMSG.INFO = WS_ERROR_INFO;
        CEP.ERR(SCCGWA, SCCMSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
