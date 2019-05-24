package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSSCMO {
    int JIBS_tmp_int;
    String K_OUTPUT_FMT = "BP190";
    String K_BV_CODE = "00200";
    String K_LOC_CCY = "344";
    char K_TMP_MOV_STS = '0';
    char K_STS_CAN = '1';
    char K_NORMAL_STS = '0';
    String CPN_R_ADW_SCMO = "BP-R-ADW-SCMO       ";
    String CPN_R_ADW_SCHS = "BP-R-ADW-SCHS       ";
    String CPN_R_ADW_SCOW = "BP-R-ADW-DMOV       ";
    String CPN_R_ADW_TLSC = "BP-R-ADW-TLSC       ";
    String CPN_R_ADW_TLSB = "BP-R-ADW-TLSB       ";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    int WS_CNT1 = 0;
    int WS_NUM = 0;
    BPZSSCMO_WS_CARD_INFO[] WS_CARD_INFO = new BPZSSCMO_WS_CARD_INFO[10];
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRSCHS BPRSCHS = new BPRSCHS();
    BPRTLSC BPRTLSC = new BPRTLSC();
    BPRDMOV BPRDMOV = new BPRDMOV();
    BPCRSCHS BPCRSCHS = new BPCRSCHS();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCRDMOV BPCRDMOV = new BPCRDMOV();
    BPCRTLSC BPCRTLSC = new BPCRTLSC();
    BPCOSCMO BPCOSCMO = new BPCOSCMO();
    BPCRTLSB BPCRTLSB = new BPCRTLSB();
    BPCPEBAS BPCPEBAS = new BPCPEBAS();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    SCCGCPT SCCGCPT;
    SCCGMSG SCCGMSG;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPRTRT BPRTRTT;
    SCCAWAC SCCAWAC;
    BPCSSCMO BPCSSCMO;
    public BPZSSCMO() {
        for (int i=0;i<10;i++) WS_CARD_INFO[i] = new BPZSSCMO_WS_CARD_INFO();
    }
    public void MP(SCCGWA SCCGWA, BPCSSCMO BPCSSCMO) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSSCMO = BPCSSCMO;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSSCMO return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        IBS.init(SCCGWA, BPCOSCMO);
        IBS.init(SCCGWA, BPRTLSC);
        IBS.init(SCCGWA, BPRDMOV);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            if (BPCSSCMO.FLG == 'N') {
                B010_PAT_RECORD_PROC();
            } else if (BPCSSCMO.FLG == 'Y') {
                B040_QUR_RECORD_PROC();
            } else {
                CEP.TRC(SCCGWA, BPCSSCMO.FLG);
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
                S000_ERR_MSG_PROC();
            }
        } else {
            B020_PAT_RECORD_CAN_PROC();
        }
        B070_CHALK_IT_UP();
        B060_REC_NHIS();
        B080_OUTPUT_SCMO_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCSSCMO.BR_CHG == SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ORG_SAME_NOTALLOWED;
            S000_ERR_MSG_PROC();
        }
    }
    public void B010_PAT_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLSC);
        IBS.init(SCCGWA, BPCRTLSB);
        for (WS_CNT = 1; WS_CNT <= 10 
            && BPCSSCMO.DATA_INFO[WS_CNT-1].CODE_NO.trim().length() != 0; WS_CNT += 1) {
            BPRTLSC.KEY.BR = BPCSSCMO.BR;
            BPRTLSC.KEY.PL_BOX_NO = BPCSSCMO.PL_BOX_NO;
            BPRTLSC.KEY.CODE_NO = BPCSSCMO.DATA_INFO[WS_CNT-1].CODE_NO;
            CEP.TRC(SCCGWA, BPRTLSC.KEY.BR);
            CEP.TRC(SCCGWA, BPRTLSC.KEY.PL_BOX_NO);
            CEP.TRC(SCCGWA, BPRTLSC.KEY.CODE_NO);
            BPCRTLSC.FUNC = 'U';
            S000_CALL_BPZRTLSC();
            WS_CARD_INFO[WS_CNT-1].WS_SC_DATE = BPRTLSC.SC_DATE;
            WS_CARD_INFO[WS_CNT-1].WS_SC_TYPE = BPRTLSC.SC_TYPE;
            WS_CARD_INFO[WS_CNT-1].WS_SC_STS = BPRTLSC.SC_STS;
            WS_CARD_INFO[WS_CNT-1].WS_CODE_NO = BPRTLSC.KEY.CODE_NO;
            WS_CARD_INFO[WS_CNT-1].WS_MC_NO = BPRTLSC.MC_NO;
            CEP.TRC(SCCGWA, "111");
            CEP.TRC(SCCGWA, BPCRTLSC.RETURN_INFO);
            if (BPCRTLSC.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CARD_NOTFND;
                S000_ERR_MSG_PROC();
            }
            CEP.TRC(SCCGWA, BPRTLSC.KEY.CODE_NO);
            CEP.TRC(SCCGWA, BPRTLSC.SC_DATE);
            BPRDMOV.KEY.MOV_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPRDMOV.KEY.CONF_NO = SCCGWA.COMM_AREA.JRN_NO;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
            CEP.TRC(SCCGWA, BPRDMOV.KEY.CONF_NO);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
            BPRDMOV.KEY.CODE_NO = BPRTLSC.KEY.CODE_NO;
            BPRDMOV.MOV_STS = K_TMP_MOV_STS;
            BPRDMOV.OUT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRDMOV.OUT_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPRDMOV.IN_BR = BPCSSCMO.BR_CHG;
            BPRDMOV.SC_DATE = BPRTLSC.SC_DATE;
            BPRDMOV.SC_TYPE = BPRTLSC.SC_TYPE;
            BPRDMOV.SC_STS = BPRTLSC.SC_STS;
            BPRDMOV.MC_NO = BPRTLSC.MC_NO;
            CEP.TRC(SCCGWA, BPRDMOV.KEY.CODE_NO);
            BPCRDMOV.FUNC = 'A';
            S000_CALL_BPZRDMOV();
            CEP.TRC(SCCGWA, BPRTLSC.KEY.BR);
            BPCRTLSC.FUNC = 'D';
            S000_CALL_BPZRTLSC();
            B060_HISTORY_PROC();
        }
    }
    public void B020_PAT_RECORD_CAN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLSC);
        IBS.init(SCCGWA, BPCRTLSB);
        BPRDMOV.KEY.CONF_NO = BPCSSCMO.CONF_NO;
        BPRDMOV.KEY.MOV_DT = BPCSSCMO.MOV_DT;
        CEP.TRC(SCCGWA, BPRDMOV.KEY.CONF_NO);
        CEP.TRC(SCCGWA, BPRDMOV.KEY.MOV_DT);
        BPCRDMOV.FUNC = 'J';
        S000_CALL_BPZRDMOV();
        BPCRDMOV.FUNC = 'R';
        S000_CALL_BPZRDMOV();
        if (BPCRDMOV.RETURN_INFO == 'N') {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRDMOV.RC);
            S000_ERR_MSG_PROC();
        }
        for (WS_CNT = 1; BPCRDMOV.RETURN_INFO != 'N' 
            && WS_CNT <= 1000; WS_CNT += 1) {
            IBS.init(SCCGWA, BPRSCHS);
            IBS.init(SCCGWA, BPCRSCHS);
            IBS.init(SCCGWA, BPRTLSC);
            IBS.init(SCCGWA, BPCRTLSC);
            if (BPRDMOV.MOV_STS == '2') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SCOWY_CANNOT_CAN;
                S000_ERR_MSG_PROC();
            }
            BPCRTLSC.FUNC = 'A';
            BPRTLSC.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRTLSC.KEY.PL_BOX_NO = BPCSSCMO.PL_BOX_NO;
            BPRTLSC.SC_DATE = BPRDMOV.SC_DATE;
            BPRTLSC.SC_TYPE = BPRDMOV.SC_TYPE;
            BPRTLSC.SC_STS = BPRDMOV.SC_STS;
            BPRTLSC.KEY.CODE_NO = BPRDMOV.KEY.CODE_NO;
            BPRTLSC.MC_NO = BPRDMOV.MC_NO;
            BPRTLSC.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPRTLSC.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            CEP.TRC(SCCGWA, BPRTLSC.KEY.PL_BOX_NO);
            CEP.TRC(SCCGWA, BPRTLSC.SC_DATE);
            CEP.TRC(SCCGWA, BPRTLSC.SC_TYPE);
            CEP.TRC(SCCGWA, BPRTLSC.KEY.CODE_NO);
            CEP.TRC(SCCGWA, BPRTLSC.MC_NO);
            CEP.TRC(SCCGWA, BPRTLSC.UPD_DT);
            CEP.TRC(SCCGWA, BPRTLSC.UPD_TLR);
            S000_CALL_BPZRTLSC();
            BPRDMOV.MOV_STS = K_STS_CAN;
            BPCRDMOV.FUNC = 'M';
            S000_CALL_BPZRDMOV();
            if (WS_CNT > 10) {
            } else {
                WS_CARD_INFO[WS_CNT-1].WS_SC_DATE = BPRTLSC.SC_DATE;
                WS_CARD_INFO[WS_CNT-1].WS_SC_TYPE = BPRTLSC.SC_TYPE;
                WS_CARD_INFO[WS_CNT-1].WS_SC_STS = BPRTLSC.SC_STS;
                WS_CARD_INFO[WS_CNT-1].WS_CODE_NO = BPRTLSC.KEY.CODE_NO;
                WS_CARD_INFO[WS_CNT-1].WS_MC_NO = BPRTLSC.MC_NO;
            }
            BPRSCHS.KEY.BR = BPRTLSC.KEY.BR;
            BPRSCHS.KEY.TX_DT = SCCGBPA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            BPRSCHS.KEY.TX_JRN = SCCGBPA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
            BPRSCHS.KEY.CODE_NO = BPRTLSC.KEY.CODE_NO;
            CEP.TRC(SCCGWA, BPRTLSC.KEY.PL_BOX_NO);
            CEP.TRC(SCCGWA, BPRTLSC.SC_DATE);
            CEP.TRC(SCCGWA, BPRTLSC.KEY.CODE_NO);
            BPCRSCHS.FUNC = 'U';
            S000_CALL_BPZRSCHS();
            if (BPCRSCHS.RETURN_INFO != 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_REC_NOTFND;
                S000_ERR_MSG_PROC();
            }
            BPRSCHS.REC_STS = '1';
            BPCRSCHS.FUNC = 'M';
            S000_CALL_BPZRSCHS();
            BPCRTLSC.FUNC = 'U';
            S000_CALL_BPZRTLSC();
            CEP.TRC(SCCGWA, "111");
            CEP.TRC(SCCGWA, BPCRTLSC.RETURN_INFO);
            if (BPCRTLSC.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CARD_NOTFND;
                S000_ERR_MSG_PROC();
            }
            CEP.TRC(SCCGWA, BPRTLSC.KEY.CODE_NO);
            CEP.TRC(SCCGWA, BPRTLSC.SC_DATE);
            BPCRDMOV.FUNC = 'R';
            S000_CALL_BPZRDMOV();
        }
        BPCRDMOV.FUNC = 'E';
        S000_CALL_BPZRDMOV();
    }
    public void B040_QUR_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLSC);
        IBS.init(SCCGWA, BPCRTLSB);
        BPCRTLSB.FUNC = 'L';
        BPRTLSC.KEY.PL_BOX_NO = BPCSSCMO.PL_BOX_NO;
        S000_CALL_BPZRTLSB();
        BPCRTLSB.FUNC = 'R';
        S000_CALL_BPZRTLSB();
        if (BPCRTLSB.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CARD_NOTFND;
            S000_ERR_MSG_PROC();
        }
        for (WS_CNT = 1; BPCRTLSB.RETURN_INFO != 'N' 
            && WS_CNT <= 1000; WS_CNT += 1) {
            BPRDMOV.KEY.MOV_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPRDMOV.KEY.CONF_NO = SCCGWA.COMM_AREA.JRN_NO;
            BPRDMOV.KEY.CODE_NO = BPRTLSC.KEY.CODE_NO;
            BPRDMOV.MOV_STS = K_TMP_MOV_STS;
            BPRDMOV.OUT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRDMOV.OUT_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPRDMOV.IN_BR = BPCSSCMO.BR_CHG;
            BPRDMOV.SC_DATE = BPRTLSC.SC_DATE;
            BPRDMOV.SC_TYPE = BPRTLSC.SC_TYPE;
            BPRDMOV.SC_STS = BPRTLSC.SC_STS;
            BPRDMOV.MC_NO = BPRTLSC.MC_NO;
            BPCRDMOV.FUNC = 'A';
            S000_CALL_BPZRDMOV();
            BPCRTLSC.FUNC = 'U';
            S000_CALL_BPZRTLSC();
            if (WS_CNT > 10) {
            } else {
                WS_CARD_INFO[WS_CNT-1].WS_SC_DATE = BPRTLSC.SC_DATE;
                WS_CARD_INFO[WS_CNT-1].WS_SC_TYPE = BPRTLSC.SC_TYPE;
                WS_CARD_INFO[WS_CNT-1].WS_SC_STS = BPRTLSC.SC_STS;
                WS_CARD_INFO[WS_CNT-1].WS_CODE_NO = BPRTLSC.KEY.CODE_NO;
                WS_CARD_INFO[WS_CNT-1].WS_MC_NO = BPRTLSC.MC_NO;
            }
            BPCRTLSC.FUNC = 'D';
            S000_CALL_BPZRTLSC();
            B060_HISTORY_PROC();
            BPCRTLSB.FUNC = 'R';
            S000_CALL_BPZRTLSB();
        }
        BPCRTLSB.FUNC = 'E';
        S000_CALL_BPZRTLSB();
    }
    public void B080_OUTPUT_SCMO_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOSCMO);
        BPCOSCMO.FUNC = BPCSSCMO.FLG;
        BPCOSCMO.OUT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCOSCMO.OUT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCOSCMO.IN_BR = BPCSSCMO.BR_CHG;
        BPCOSCMO.PL_BOX_NO = BPCSSCMO.PL_BOX_NO;
        BPCOSCMO.CONF_NO = SCCGWA.COMM_AREA.JRN_NO;
        BPCOSCMO.MOVE_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCSSCMO.CONF_NO = SCCGWA.COMM_AREA.JRN_NO;
        BPCSSCMO.MOV_DT = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, "11111");
        for (WS_CNT1 = 1; WS_CNT1 <= 10 
            && BPCSSCMO.DATA_INFO[WS_CNT1-1].CODE_NO.trim().length() != 0; WS_CNT1 += 1) {
            CEP.TRC(SCCGWA, WS_CNT1);
            BPCOSCMO.DATA_INFO[WS_CNT1-1].SC_DATE = WS_CARD_INFO[WS_CNT1-1].WS_SC_DATE;
            BPCOSCMO.DATA_INFO[WS_CNT1-1].MC_NO = WS_CARD_INFO[WS_CNT1-1].WS_MC_NO;
            BPCOSCMO.DATA_INFO[WS_CNT1-1].SC_TYPE = WS_CARD_INFO[WS_CNT1-1].WS_SC_TYPE;
            BPCOSCMO.DATA_INFO[WS_CNT1-1].CODE_NO = WS_CARD_INFO[WS_CNT1-1].WS_CODE_NO;
            BPCOSCMO.DATA_INFO[WS_CNT1-1].SC_STS = WS_CARD_INFO[WS_CNT1-1].WS_SC_STS;
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        CEP.TRC(SCCGWA, BPCOSCMO);
        SCCFMT.DATA_PTR = BPCOSCMO;
        SCCFMT.DATA_LEN = 1557;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B060_HISTORY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRSCHS);
        BPCRSCHS.FUNC = 'A';
        BPRSCHS.KEY.BR = BPCSSCMO.BR;
        BPRSCHS.PL_BOX_NO = BPCSSCMO.PL_BOX_NO;
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
        BPRSCHS.REC_STS = '0';
        BPRSCHS.TX_TYPE = '1';
        CEP.TRC(SCCGWA, BPRSCHS.KEY.CODE_NO);
        CEP.TRC(SCCGWA, BPRSCHS.KEY.TX_JRN);
        S000_CALL_BPZRSCHS();
    }
    public void B070_CHALK_IT_UP() throws IOException,SQLException,Exception {
        if (BPCFBVQU.TX_DATA.AC_TYP == '0') {
            B071_SET_EWA_BASIC_INF();
            B072_SET_EWA_EVENTS();
        }
    }
    public void B060_REC_NHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.TX_TYP_CD = "P905";
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
    public void B071_SET_EWA_BASIC_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPEBAS);
        S000_CALL_BPZPEBAS();
    }
    public void B072_SET_EWA_EVENTS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "BVF";
        if (BPCSSCMO.DATA_INFO[1-1].SC_STS == K_NORMAL_STS) {
            BPCPOEWA.DATA.EVENT_CODE = "CR";
        } else {
            BPCPOEWA.DATA.EVENT_CODE = "BVZUCR";
        }
        BPCPOEWA.DATA.BR_OLD = BPCSSCMO.BR;
        BPCPOEWA.DATA.BR_NEW = BPCSSCMO.BR;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCRBANK.LOC_CCY1;
        WS_NUM = WS_CNT - 1;
        CEP.TRC(SCCGWA, WS_NUM);
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = WS_NUM;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.PORT = K_BV_CODE;
        BPCPOEWA.DATA.PROD_CODE = K_BV_CODE;
        S000_CALL_BPZPOEWA();
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
    public void S000_CALL_BPZRDMOV() throws IOException,SQLException,Exception {
        BPCRDMOV.POINTER = BPRDMOV;
        BPCRDMOV.LEN = 225;
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_SCOW, BPCRDMOV);
    }
    public void S000_CALL_BPZRTLSC() throws IOException,SQLException,Exception {
        BPCRTLSC.POINTER = BPRTLSC;
        BPCRTLSC.LEN = 736;
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_TLSC, BPCRTLSC);
    }
    public void S000_CALL_BPZRTLSB() throws IOException,SQLException,Exception {
        BPCRTLSB.POINTER = BPRTLSC;
        BPCRTLSB.LEN = 736;
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_TLSB, BPCRTLSB);
    }
    public void S000_CALL_BPZPEBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-BASIC-EWA", BPCPEBAS);
        if (BPCPEBAS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPEBAS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_BV_PRM_QUERY, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
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
