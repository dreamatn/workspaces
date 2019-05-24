package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSRTSC {
    int JIBS_tmp_int;
    String OUTPUT_FMT = "BP191";
    String BV_CODE = "00200";
    short NUM = 1;
    String R_ADW_TLSC = "BP-R-ADW-TLSC       ";
    String R_ADW_SCHS = "BP-R-ADW-SCHS       ";
    String F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY";
    String REC_NHIS = "BP-REC-NHIS         ";
    BPZSRTSC_WS_VARIABLES WS_VARIABLES = new BPZSRTSC_WS_VARIABLES();
    BPZSRTSC_WS_HIS_REMARKS WS_HIS_REMARKS = new BPZSRTSC_WS_HIS_REMARKS();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRTLSC BPRTLSC = new BPRTLSC();
    BPRSCHS BPRSCHS = new BPRSCHS();
    BPCRTLSC BPCRTLSC = new BPCRTLSC();
    BPCRSCHS BPCRSCHS = new BPCRSCHS();
    BPCOSCHS BPCOSCHS = new BPCOSCHS();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCPEBAS BPCPEBAS = new BPCPEBAS();
    BPCORTSC BPCORTSC = new BPCORTSC();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    SCCGCPT SCCGCPT;
    SCCGMSG SCCGMSG;
    BPCPQBNK_DATA_INFO DATA_INFO;
    BPRTRT BPRTRT;
    SCCAWAC SCCAWAC;
    BPCSRTSC BPCSRTSC;
    public void MP(SCCGWA SCCGWA, BPCSRTSC BPCSRTSC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSRTSC = BPCSRTSC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSRTSC return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGCPT = (SCCGCPT) SC_AREA.CMPT_AREA_PTR;
        SCCGMSG = (SCCGMSG) SC_AREA.MSG_AREA_PTR;
        DATA_INFO = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        SCCAWAC = new SCCAWAC();
        IBS.init(SCCGWA, SCCAWAC);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWAC_AREA_PTR, SCCAWAC);
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, BPCORTSC);
        IBS.init(SCCGWA, BPCRTLSC);
        IBS.init(SCCGWA, BPRTLSC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B020_RETURN_CORD_PROC();
            B060_HISTORY_PROC();
        } else {
            B060_HISTORY_CANCEL_PROC();
            B040_CAN_RECORD_PROC();
        }
        B050_REC_NHIS();
        B070_CHALK_IT_UP();
        B080_OUTPUT_RTSC_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSRTSC.SC_TYPE);
        CEP.TRC(SCCGWA, BPCSRTSC.CODE_NO);
        CEP.TRC(SCCGWA, BPCSRTSC.MC_NO);
        CEP.TRC(SCCGWA, BPCSRTSC.POOL_BOX_NO);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            BPCRTLSC.FUNC = 'Q';
            BPRTLSC.KEY.BR = BPCSRTSC.BR;
            BPRTLSC.KEY.CODE_NO = BPCSRTSC.CODE_NO;
            BPRTLSC.KEY.PL_BOX_NO = BPCSRTSC.POOL_BOX_NO;
            S000_CALL_BPZRTLSC();
            if (BPCRTLSC.RETURN_INFO == 'N') {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_CARD_NOTFND;
                S000_ERR_MSG_PROC();
            }
            if (BPRTLSC.SC_STS == '1') {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_CARD_TOBE_DSTR;
                S000_ERR_MSG_PROC();
            }
            if (BPCSRTSC.SC_TYPE != ' ') {
                if (BPCSRTSC.SC_TYPE != BPRTLSC.SC_TYPE) {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_CARDTPYE_ERR;
                    S000_ERR_MSG_PROC();
                }
            }
        } else {
            if (BP_AREA.CANCEL_AREA.CAN_AC_DATE != SCCGWA.COMM_AREA.AC_DATE) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_CANCEL_NO_TERTIAN;
                S000_ERR_MSG_PROC();
            }
            BPRSCHS.KEY.BR = BPCSRTSC.BR;
            BPRSCHS.KEY.TX_DT = BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            BPRSCHS.KEY.TX_JRN = BP_AREA.CANCEL_AREA.CAN_JRN_NO;
            BPRSCHS.KEY.CODE_NO = BPCSRTSC.CODE_NO;
            CEP.TRC(SCCGWA, BPRSCHS.KEY.BR);
            CEP.TRC(SCCGWA, BPRSCHS.KEY.TX_DT);
            CEP.TRC(SCCGWA, BPRSCHS.KEY.TX_JRN);
            CEP.TRC(SCCGWA, BPRSCHS.KEY.CODE_NO);
            BPCRSCHS.FUNC = 'Q';
            S000_CALL_BPZRSCHS();
            if (BPRSCHS.TX_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_NOT_ORIGIN_BR;
                S000_ERR_MSG_PROC();
            }
            if (!BPRSCHS.TX_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_NOT_ORIGIN_TLR;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B020_RETURN_CORD_PROC() throws IOException,SQLException,Exception {
        BPRTLSC.KEY.BR = BPCSRTSC.BR;
        BPRTLSC.KEY.PL_BOX_NO = BPCSRTSC.POOL_BOX_NO;
        BPRTLSC.KEY.CODE_NO = BPCSRTSC.CODE_NO;
        CEP.TRC(SCCGWA, BPRTLSC.KEY.PL_BOX_NO);
        CEP.TRC(SCCGWA, BPRTLSC.KEY.CODE_NO);
        BPCRTLSC.FUNC = 'U';
        S000_CALL_BPZRTLSC();
        BPCRTLSC.FUNC = 'D';
        S000_CALL_BPZRTLSC();
    }
    public void B040_CAN_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLSC);
        BPCRTLSC.FUNC = 'A';
        BPRTLSC.KEY.BR = BPRSCHS.KEY.BR;
        BPRTLSC.KEY.PL_BOX_NO = BPRSCHS.PL_BOX_NO;
        BPRTLSC.SC_DATE = BPRSCHS.SC_DATE;
        BPRTLSC.SC_TYPE = BPRSCHS.SC_TYPE;
        BPRTLSC.KEY.CODE_NO = BPRSCHS.KEY.CODE_NO;
        BPRTLSC.MC_NO = BPRSCHS.MC_NO;
        BPRTLSC.SC_STS = '0';
        BPRTLSC.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRTLSC.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, BPRTLSC.KEY.PL_BOX_NO);
        CEP.TRC(SCCGWA, BPRTLSC.KEY.CODE_NO);
        S000_CALL_BPZRTLSC();
    }
    public void B080_OUTPUT_RTSC_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCORTSC);
        BPCORTSC.BR = BPRTLSC.KEY.BR;
        BPCORTSC.POOL_BOX_NO = BPRTLSC.KEY.PL_BOX_NO;
        BPCORTSC.SC_DATE = BPRTLSC.SC_DATE;
        BPCORTSC.SC_TYPE = BPRTLSC.SC_TYPE;
        BPCORTSC.CODE_NO = BPRTLSC.KEY.CODE_NO;
        BPCORTSC.MC_NO = BPRTLSC.MC_NO;
        BPCORTSC.SC_STS = BPRTLSC.SC_STS;
        BPCORTSC.DRAW_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCORTSC.TX_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCORTSC.TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCORTSC.TX_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCORTSC.DRW_NM = BPCSRTSC.DRW_NM;
        BPCORTSC.DRW_ID_TP = BPCSRTSC.DRW_ID_TYP;
        BPCORTSC.DRW_ID_NO = BPCSRTSC.DRW_ID_NO;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = OUTPUT_FMT;
        CEP.TRC(SCCGWA, BPCORTSC);
        SCCFMT.DATA_PTR = BPCORTSC;
        SCCFMT.DATA_LEN = 390;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B060_HISTORY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRSCHS);
        BPCRSCHS.FUNC = 'A';
        BPRSCHS.KEY.BR = BPCSRTSC.BR;
        BPRSCHS.PL_BOX_NO = BPCSRTSC.POOL_BOX_NO;
        BPRSCHS.KEY.TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRSCHS.KEY.TX_JRN = SCCGWA.COMM_AREA.JRN_NO;
        BPRSCHS.SC_DATE = BPRTLSC.SC_DATE;
        BPRSCHS.SC_TYPE = BPRTLSC.SC_TYPE;
        BPRSCHS.KEY.CODE_NO = BPRTLSC.KEY.CODE_NO;
        BPRSCHS.MC_NO = BPRTLSC.MC_NO;
        BPRSCHS.TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRSCHS.TX_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRSCHS.TX_CODE = "" + SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        JIBS_tmp_int = BPRSCHS.TX_CODE.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) BPRSCHS.TX_CODE = "0" + BPRSCHS.TX_CODE;
        BPRSCHS.DRW_NM = BPCSRTSC.DRW_NM;
        BPRSCHS.DRW_ID_TP = BPCSRTSC.DRW_ID_TYP;
        BPRSCHS.DRW_ID_NO = BPCSRTSC.DRW_ID_NO;
        BPRSCHS.REC_STS = '0';
        BPRSCHS.TX_TYPE = '3';
        CEP.TRC(SCCGWA, BPRTLSC.KEY.PL_BOX_NO);
        CEP.TRC(SCCGWA, BPRTLSC.SC_DATE);
        CEP.TRC(SCCGWA, BPRTLSC.SC_TYPE);
        CEP.TRC(SCCGWA, BPRTLSC.KEY.CODE_NO);
        CEP.TRC(SCCGWA, BPRTLSC.MC_NO);
        CEP.TRC(SCCGWA, BPRTLSC.UPD_DT);
        CEP.TRC(SCCGWA, BPRTLSC.UPD_TLR);
        S000_CALL_BPZRSCHS();
    }
    public void B050_REC_NHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        WS_HIS_REMARKS.HIS_BVCODE = " ";
        WS_HIS_REMARKS.HIS_HEADNO = " ";
        WS_HIS_REMARKS.HIS_BEGNO = BPCSRTSC.DRW_ID_NO;
        WS_HIS_REMARKS.HIS_ENDNO = " ";
        WS_HIS_REMARKS.HIS_NUMNO = 1;
        BPCPNHIS.INFO.TX_RMK = IBS.CLS2CPY(SCCGWA, WS_HIS_REMARKS);
        S000_CALL_BPZPNHIS();
    }
    public void B060_HISTORY_CANCEL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRSCHS);
        IBS.init(SCCGWA, BPCRSCHS);
        BPRSCHS.KEY.BR = BPCSRTSC.BR;
        BPRSCHS.PL_BOX_NO = BPCSRTSC.POOL_BOX_NO;
        BPRSCHS.KEY.TX_DT = BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        BPRSCHS.KEY.TX_JRN = BP_AREA.CANCEL_AREA.CAN_JRN_NO;
        BPRSCHS.SC_DATE = BPRTLSC.SC_DATE;
        BPRSCHS.SC_TYPE = BPRTLSC.SC_TYPE;
        BPRSCHS.KEY.CODE_NO = BPCSRTSC.CODE_NO;
        BPRSCHS.MC_NO = BPRTLSC.MC_NO;
        BPRSCHS.TX_BR = BPRTLSC.KEY.BR;
        BPRSCHS.TX_TLR = BPRTLSC.UPD_TLR;
        BPRSCHS.TX_CODE = "" + SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        JIBS_tmp_int = BPRSCHS.TX_CODE.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) BPRSCHS.TX_CODE = "0" + BPRSCHS.TX_CODE;
        BPRSCHS.DRW_NM = BPCSRTSC.DRW_NM;
        BPRSCHS.DRW_ID_TP = BPCSRTSC.DRW_ID_TYP;
        BPRSCHS.DRW_ID_NO = BPCSRTSC.DRW_ID_NO;
        BPRSCHS.REC_STS = '0';
        BPRSCHS.TX_TYPE = '0';
        CEP.TRC(SCCGWA, BPRSCHS.PL_BOX_NO);
        CEP.TRC(SCCGWA, BPRSCHS.KEY.BR);
        CEP.TRC(SCCGWA, BPRSCHS.KEY.TX_DT);
        CEP.TRC(SCCGWA, BPRSCHS.KEY.TX_JRN);
        CEP.TRC(SCCGWA, BPRTLSC.SC_TYPE);
        CEP.TRC(SCCGWA, BPRTLSC.KEY.CODE_NO);
        CEP.TRC(SCCGWA, BPRTLSC.MC_NO);
        CEP.TRC(SCCGWA, BPRTLSC.UPD_DT);
        CEP.TRC(SCCGWA, BPRTLSC.UPD_TLR);
        BPCRSCHS.FUNC = 'U';
        S000_CALL_BPZRSCHS();
        BPRSCHS.REC_STS = '1';
        BPCRSCHS.FUNC = 'M';
        S000_CALL_BPZRSCHS();
    }
    public void B070_CHALK_IT_UP() throws IOException,SQLException,Exception {
        if (BPCFBVQU.TX_DATA.AC_TYP == '0') {
            B071_SET_EWA_BASIC_INF();
            B072_SET_EWA_EVENTS();
        }
    }
    public void B071_SET_EWA_BASIC_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPEBAS);
        S000_CALL_BPZPEBAS();
    }
    public void B072_SET_EWA_EVENTS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "BVF";
        BPCPOEWA.DATA.EVENT_CODE = "CR";
        BPCPOEWA.DATA.BR_OLD = BPCSRTSC.BR;
        BPCPOEWA.DATA.BR_NEW = BPCSRTSC.BR;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = DATA_INFO.LOC_CCY1;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = NUM;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.PORT = BV_CODE;
        BPCPOEWA.DATA.PROD_CODE = BV_CODE;
        S000_CALL_BPZPOEWA();
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
    }
    public void S000_CALL_BPZRTLSC() throws IOException,SQLException,Exception {
        BPCRTLSC.POINTER = BPRTLSC;
        BPCRTLSC.LEN = 736;
        IBS.CALLCPN(SCCGWA, R_ADW_TLSC, BPCRTLSC);
        if (BPCRTLSC.RETURN_INFO != 'F') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_CARD_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRSCHS() throws IOException,SQLException,Exception {
        BPCRSCHS.POINTER = BPRSCHS;
        BPCRSCHS.LEN = 565;
        IBS.CALLCPN(SCCGWA, R_ADW_SCHS, BPCRSCHS);
        if (BPCRSCHS.RETURN_INFO != 'F') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, F_BV_PRM_QUERY, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPEBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-BASIC-EWA", BPCPEBAS);
        if (BPCPEBAS.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPEBAS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
