package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSSCTD {
    int JIBS_tmp_int;
    String K_OUTPUT_FMT = "BP195";
    String K_BV_CODE = "002";
    String K_LOC_CCY = "344";
    char K_STS_DES = '1';
    char K_STS_NOR = '0';
    String CPN_R_ADW_TLSC = "BP-R-ADW-TLSC       ";
    String CPN_R_ADW_SCTD = "BP-R-ADW-SCTD       ";
    String CPN_R_ADW_TLSB = "BP-R-ADW-TLSB       ";
    String CPN_R_ADW_SCHS = "BP-R-ADW-SCHS       ";
    String CPN_R_BDW_SCHB = "BP-R-BDW-SCHB       ";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    String WS_CODE_NO = " ";
    int WS_CNT = 0;
    int WS_NUM = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRTLSC BPRTLSC = new BPRTLSC();
    BPRSCHS BPRSCHS = new BPRSCHS();
    BPCOSCTD BPCOSCTD = new BPCOSCTD();
    BPCSSCTD BPCPSCTD = new BPCSSCTD();
    BPCRSCTD BPCRSCTD = new BPCRSCTD();
    BPCRSCHS BPCRSCHS = new BPCRSCHS();
    BPCOSCHS BPCOSCHS = new BPCOSCHS();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCRSCHB BPCRSCHB = new BPCRSCHB();
    BPCRTLSC BPCRTLSC = new BPCRTLSC();
    BPCRTLSB BPCRTLSB = new BPCRTLSB();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    SCCGCPT SCCGCPT;
    SCCGMSG SCCGMSG;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPRTRT BPRTRTT;
    BPCSSCTD BPCSSCTD;
    SCCAWAC SCCAWAC;
    public void MP(SCCGWA SCCGWA, BPCSSCTD BPCSSCTD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSSCTD = BPCSSCTD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSSCTD return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGCPT = (SCCGCPT) SCCGSCA_SC_AREA.CMPT_AREA_PTR;
        SCCGMSG = (SCCGMSG) SCCGSCA_SC_AREA.MSG_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        SCCAWAC = new SCCAWAC();
        IBS.init(SCCGWA, SCCAWAC);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWAC_AREA_PTR, SCCAWAC);
        IBS.init(SCCGWA, BPCRSCTD);
        IBS.init(SCCGWA, BPCOSCTD);
        IBS.init(SCCGWA, BPCRTLSC);
        IBS.init(SCCGWA, BPCRTLSB);
        IBS.init(SCCGWA, BPRSCHS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            if (BPCSSCTD.FUNC == '0') {
                B010_PAT_RECORD_PROC();
            } else if (BPCSSCTD.FUNC == '1') {
                B020_ALL_RECORD_PROC();
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
                S000_ERR_MSG_PROC();
            }
            B070_CHALK_IT_UP();
        } else {
            if (BPCSSCTD.FUNC == '0') {
                B030_PAT_CANCEL_RECORD_PROC();
            } else if (BPCSSCTD.FUNC == '1') {
                B040_ALL_CANCEL_RECORD_PROC();
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
                S000_ERR_MSG_PROC();
            }
        }
        B050_REC_NHIS();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "1");
        CEP.TRC(SCCGWA, BPCSSCTD.FUNC);
        CEP.TRC(SCCGWA, BPCSSCTD.PL_BOX_NO);
    }
    public void B010_PAT_RECORD_PROC() throws IOException,SQLException,Exception {
        for (WS_CNT = 1; WS_CNT <= 10 
            && BPCSSCTD.DATA_INFO[WS_CNT-1].CODE_NO.trim().length() != 0; WS_CNT += 1) {
            IBS.init(SCCGWA, BPRTLSC);
            BPRTLSC.KEY.PL_BOX_NO = BPCSSCTD.PL_BOX_NO;
            BPRTLSC.KEY.CODE_NO = BPCSSCTD.DATA_INFO[WS_CNT-1].CODE_NO;
            BPRTLSC.SC_TYPE = BPCSSCTD.DATA_INFO[WS_CNT-1].SC_TYPE;
            BPRTLSC.SC_DATE = BPCSSCTD.DATA_INFO[WS_CNT-1].SC_DATE;
            BPRTLSC.MC_NO = BPCSSCTD.DATA_INFO[WS_CNT-1].MC_NO;
            BPRTLSC.UPD_TLR = BPCSSCTD.TLR;
            CEP.TRC(SCCGWA, BPRTLSC.KEY.BR);
            CEP.TRC(SCCGWA, BPRTLSC.KEY.PL_BOX_NO);
            CEP.TRC(SCCGWA, BPRTLSC.KEY.CODE_NO);
            BPCRTLSC.FUNC = 'U';
            S000_CALL_BPZRTLSC();
            BPRTLSC.SC_STS = K_STS_DES;
            BPCRTLSC.FUNC = 'M';
            S000_CALL_BPZRTLSC();
            R000_TRANS_DATA_PARAMETER();
            B050_HISTORY_PROC();
        }
        B060_OUTPUT_SCTD_RECORD();
    }
    public void B020_ALL_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLSC);
        BPCRTLSB.FUNC = 'F';
        BPRTLSC.KEY.PL_BOX_NO = BPCSSCTD.PL_BOX_NO;
        BPRTLSC.SC_STS = '0';
        S000_CALL_BPZRTLSB();
        BPCRTLSB.FUNC = 'R';
        S000_CALL_BPZRTLSB();
        for (WS_CNT = 1; BPCRTLSB.RETURN_INFO != 'N' 
            && WS_CNT <= 1000; WS_CNT += 1) {
            CEP.TRC(SCCGWA, BPRTLSC.KEY.BR);
            CEP.TRC(SCCGWA, BPRTLSC.KEY.PL_BOX_NO);
            CEP.TRC(SCCGWA, BPRTLSC.KEY.CODE_NO);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
            BPCRTLSC.FUNC = 'U';
            S000_CALL_BPZRTLSC();
            BPRTLSC.SC_STS = K_STS_DES;
            BPCRTLSC.FUNC = 'M';
            S000_CALL_BPZRTLSC();
            B050_HISTORY_PROC();
            BPCRTLSB.FUNC = 'R';
            S000_CALL_BPZRTLSB();
        }
        BPCRTLSB.FUNC = 'E';
        S000_CALL_BPZRTLSB();
        B060_OUTPUT_SCTD_RECORD();
    }
    public void B030_PAT_CANCEL_RECORD_PROC() throws IOException,SQLException,Exception {
        for (WS_CNT = 1; WS_CNT <= 10 
            && BPCSSCTD.DATA_INFO[WS_CNT-1].CODE_NO.trim().length() != 0; WS_CNT += 1) {
            IBS.init(SCCGWA, BPRTLSC);
            BPRTLSC.KEY.PL_BOX_NO = BPCSSCTD.PL_BOX_NO;
            BPRTLSC.KEY.CODE_NO = BPCSSCTD.DATA_INFO[WS_CNT-1].CODE_NO;
            BPRTLSC.SC_TYPE = BPCSSCTD.DATA_INFO[WS_CNT-1].SC_TYPE;
            BPRTLSC.SC_DATE = BPCSSCTD.DATA_INFO[WS_CNT-1].SC_DATE;
            BPRTLSC.MC_NO = BPCSSCTD.DATA_INFO[WS_CNT-1].MC_NO;
            BPRTLSC.UPD_TLR = BPCSSCTD.TLR;
            CEP.TRC(SCCGWA, BPRTLSC.KEY.BR);
            CEP.TRC(SCCGWA, BPRTLSC.KEY.PL_BOX_NO);
            CEP.TRC(SCCGWA, BPRTLSC.KEY.CODE_NO);
            BPCRTLSC.FUNC = 'U';
            S000_CALL_BPZRTLSC();
            BPRTLSC.SC_STS = K_STS_NOR;
            BPCRTLSC.FUNC = 'M';
            S000_CALL_BPZRTLSC();
            R000_TRANS_DATA_PARAMETER();
            B060_HISTORY_ALL_CANCEL_PROC();
        }
        B060_OUTPUT_SCTD_RECORD();
    }
    public void B040_ALL_CANCEL_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLSC);
        IBS.init(SCCGWA, BPCRSCHB);
        BPCRSCHB.FUNC = 'J';
        BPRSCHS.KEY.TX_DT = SCCGBPA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        BPRSCHS.KEY.TX_JRN = SCCGBPA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
        CEP.TRC(SCCGWA, BPRSCHS.KEY.TX_DT);
        CEP.TRC(SCCGWA, BPRSCHS.KEY.TX_JRN);
        S000_CALL_BPZRSCHB();
        BPCRSCHB.FUNC = 'N';
        S000_CALL_BPZRSCHB();
        if (BPCRSCHB.RETURN_INFO == 'N') {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRSCHB.RC);
            S000_ERR_MSG_PROC();
        }
        for (WS_CNT = 1; BPCRSCHB.RETURN_INFO != 'N' 
            && WS_CNT <= 1000; WS_CNT += 1) {
            BPCRSCHS.FUNC = 'U';
            S000_CALL_BPZRSCHS();
            if (BPCRSCHS.RETURN_INFO != 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_REC_NOTFND;
                S000_ERR_MSG_PROC();
            }
            CEP.TRC(SCCGWA, BPRSCHS.KEY.CODE_NO);
            BPRSCHS.REC_STS = '1';
            BPRSCHS.KEY.TX_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPRSCHS.TX_TLR = SCCGWA.COMM_AREA.TL_ID;
            CEP.TRC(SCCGWA, BPRSCHS.REC_STS);
            BPCRSCHS.FUNC = 'M';
            S000_CALL_BPZRSCHS();
            BPRTLSC.KEY.BR = BPRSCHS.KEY.BR;
            BPRTLSC.KEY.PL_BOX_NO = BPRSCHS.PL_BOX_NO;
            BPRTLSC.SC_DATE = BPRSCHS.SC_DATE;
            BPRTLSC.SC_TYPE = BPRSCHS.SC_TYPE;
            BPRTLSC.SC_STS = K_STS_NOR;
            BPRTLSC.KEY.CODE_NO = BPRSCHS.KEY.CODE_NO;
            BPRTLSC.MC_NO = BPRSCHS.MC_NO;
            BPRTLSC.UPD_DT = BPRSCHS.KEY.TX_DT;
            BPRTLSC.UPD_TLR = BPRSCHS.TX_TLR;
            BPCRTLSC.FUNC = 'U';
            S000_CALL_BPZRTLSC();
            BPRTLSC.SC_STS = K_STS_NOR;
            CEP.TRC(SCCGWA, BPRTLSC.SC_STS);
            BPCRTLSC.FUNC = 'M';
            S000_CALL_BPZRTLSC();
            BPCRSCHB.FUNC = 'N';
            S000_CALL_BPZRSCHB();
        }
        BPCRSCHB.FUNC = 'E';
        S000_CALL_BPZRSCHB();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRTLSC.KEY.BR);
        CEP.TRC(SCCGWA, BPCPSCTD.TLR);
        BPCOSCTD.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCOSCTD.FUNC = BPCSSCTD.FUNC;
        BPCOSCTD.DATA_INFO[WS_CNT-1].SC_DATE = BPRTLSC.SC_DATE;
        BPCOSCTD.DATA_INFO[WS_CNT-1].SC_TYPE = BPRTLSC.SC_TYPE;
        BPCOSCTD.DATA_INFO[WS_CNT-1].SC_STS = BPRTLSC.SC_STS;
        BPCOSCTD.DATA_INFO[WS_CNT-1].CODE_NO = BPRTLSC.KEY.CODE_NO;
        BPCOSCTD.DATA_INFO[WS_CNT-1].MC_NO = BPRTLSC.MC_NO;
        BPCOSCTD.DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCOSCTD.TLR = SCCGWA.COMM_AREA.TL_ID;
    }
    public void B060_OUTPUT_SCTD_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOSCTD;
        SCCFMT.DATA_LEN = 1533;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B050_HISTORY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRSCHS);
        BPCRSCHS.FUNC = 'A';
        BPRSCHS.KEY.BR = BPRTLSC.KEY.BR;
        BPRSCHS.PL_BOX_NO = BPRTLSC.KEY.PL_BOX_NO;
        BPRSCHS.KEY.TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRSCHS.KEY.TX_JRN = SCCGWA.COMM_AREA.JRN_NO;
        BPRSCHS.SC_DATE = BPRTLSC.SC_DATE;
        BPRSCHS.SC_TYPE = BPRTLSC.SC_TYPE;
        BPRSCHS.KEY.CODE_NO = BPRTLSC.KEY.CODE_NO;
        BPRSCHS.MC_NO = BPRTLSC.MC_NO;
        BPRSCHS.TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRSCHS.TX_TLR = BPCSSCTD.TLR;
        BPRSCHS.TX_CODE = "" + SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        JIBS_tmp_int = BPRSCHS.TX_CODE.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) BPRSCHS.TX_CODE = "0" + BPRSCHS.TX_CODE;
        BPRSCHS.REC_STS = '0';
        BPRSCHS.TX_TYPE = '4';
        CEP.TRC(SCCGWA, BPCSSCTD.BR);
        CEP.TRC(SCCGWA, BPCSSCTD.PL_BOX_NO);
        S000_CALL_BPZRSCHS();
    }
    public void B060_HISTORY_ALL_CANCEL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRSCHS);
        BPRSCHS.KEY.BR = BPRTLSC.KEY.BR;
        BPRSCHS.PL_BOX_NO = BPRTLSC.KEY.PL_BOX_NO;
        BPRSCHS.KEY.TX_DT = SCCGBPA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        BPRSCHS.KEY.TX_JRN = SCCGBPA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
        BPRSCHS.SC_DATE = BPRTLSC.SC_DATE;
        BPRSCHS.SC_TYPE = BPRTLSC.SC_TYPE;
        BPRSCHS.KEY.CODE_NO = BPRTLSC.KEY.CODE_NO;
        BPRSCHS.MC_NO = BPRTLSC.MC_NO;
        BPRSCHS.TX_BR = BPRTLSC.KEY.BR;
        BPRSCHS.TX_TLR = BPRTLSC.UPD_TLR;
        BPRSCHS.REC_STS = K_STS_NOR;
        CEP.TRC(SCCGWA, BPRSCHS.KEY.BR);
        CEP.TRC(SCCGWA, BPRSCHS.PL_BOX_NO);
        CEP.TRC(SCCGWA, BPRSCHS.KEY.CODE_NO);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        BPCRSCHS.FUNC = 'U';
        S000_CALL_BPZRSCHS();
        BPRSCHS.REC_STS = '1';
        BPCRSCHS.FUNC = 'M';
        S000_CALL_BPZRSCHS();
    }
    public void B070_CHALK_IT_UP() throws IOException,SQLException,Exception {
        if (BPCFBVQU.TX_DATA.AC_TYP == '0') {
            B071_SET_EWA_EVENTS();
        }
    }
    public void B071_SET_EWA_EVENTS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "BVF";
        BPCPOEWA.DATA.EVENT_CODE = "CR";
        BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCRBANK.LOC_CCY1;
        WS_NUM = WS_CNT - 1;
        CEP.TRC(SCCGWA, WS_NUM);
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = WS_NUM;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.PORT = K_BV_CODE;
        BPCPOEWA.DATA.PROD_CODE = K_BV_CODE;
        S000_CALL_BPZPOEWA();
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "BVF";
        BPCPOEWA.DATA.EVENT_CODE = "BVZUDR";
        BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCRBANK.LOC_CCY1;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = WS_NUM;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.PORT = K_BV_CODE;
        BPCPOEWA.DATA.PROD_CODE = K_BV_CODE;
        S000_CALL_BPZPOEWA();
    }
    public void B050_REC_NHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.TX_TYP_CD = "P910";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        S000_CALL_BPZPNHIS();
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INFO);
    }
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_BV_PRM_QUERY, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRSCTD() throws IOException,SQLException,Exception {
        BPCRSCTD.POINTER = BPRTLSC;
        BPCRSCTD.LEN = 736;
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_SCTD, BPCRSCTD);
    }
    public void S000_CALL_BPZRSCHS() throws IOException,SQLException,Exception {
        BPCRSCHS.POINTER = BPRSCHS;
        BPCRSCHS.LEN = 565;
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_SCHS, BPCRSCHS);
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRTLSC() throws IOException,SQLException,Exception {
        BPCRTLSC.POINTER = BPRTLSC;
        BPCRTLSC.LEN = 736;
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_TLSC, BPCRTLSC);
        if (BPCRTLSC.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CARD_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRSCHB() throws IOException,SQLException,Exception {
        BPCRSCHB.POINTER = BPRSCHS;
        BPCRSCHB.LEN = 565;
        IBS.CALLCPN(SCCGWA, CPN_R_BDW_SCHB, BPCRSCHB);
    }
    public void S000_CALL_BPZRTLSB() throws IOException,SQLException,Exception {
        BPCRTLSB.POINTER = BPRTLSC;
        BPCRTLSB.LEN = 736;
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_TLSB, BPCRTLSB);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
