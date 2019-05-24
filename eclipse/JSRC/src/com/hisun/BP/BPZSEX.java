package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.FX.FXCMSG_ERROR_MSG;
import com.hisun.FX.FXRTMDIF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGAPV;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGMSG;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class BPZSEX {
    boolean pgmRtn = false;
    String CPN_INQ_PUB_PARM = "BP-PARM-READ      ";
    String CPN_INQ_EXR_CODE = "BP-INQ-EXR-CODE   ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_WRITE_VCH = "BP-P-VWA-WRITE    ";
    String CPN_WRITE_VCH_B = "BP-P-VCH-WRITE-B  ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG      ";
    char K_DR_SIGN = 'D';
    char K_CR_SIGN = 'C';
    short K_PNT = 4;
    char K_RND = '2';
    String PGM_SCSSCKDT = "SCSSCKDT";
    String K_EXTM_CD = "999";
    String K_CCY_CNY = "156";
    double WS_B_US_AMT = 0;
    int WS_EX_HOUR = 0;
    int WS_EX_MINU = 0;
    int WS_EX_SECO = 0;
    int WS_TR_HOUR = 0;
    int WS_TR_MINU = 0;
    int WS_TR_SECO = 0;
    int WS_EX_SEC = 0;
    int WS_TR_SEC = 0;
    int WS_TM_DIF = 0;
    int WS_TMDIF_TM_DIF = 0;
    String WS_CCY = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    FXCMSG_ERROR_MSG FXCMSG_ERROR_MSG = new FXCMSG_ERROR_MSG();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPREXRT BPREXRT1 = new BPREXRT();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCQCCY BPCQCCYB = new BPCQCCY();
    BPCQCCY BPCQCCYS = new BPCQCCY();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPCOIEC BPCOIEC = new BPCOIEC();
    BPCTEXRM BPCTEXRM = new BPCTEXRM();
    FXCPGNFX FXCPGNFX = new FXCPGNFX();
    BPCFX BPCFX = new BPCFX();
    FXRTMDIF FXRTMDIF = new FXRTMDIF();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGAPV SCCGAPV;
    SCCGMSG SCCGMSG;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPRTLT BPRTLT;
    BPCEX BPCEX;
    public void MP(SCCGWA SCCGWA, BPCEX BPCEX) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCEX = BPCEX;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSEX return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        BPRTLT = (BPRTLT) SCCGWA.COMM_AREA.TLT_AREA_PTR;
        SCCGMSG = (SCCGMSG) GWA_SC_AREA.MSG_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B001_CHECK_INPUT();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, FXCPGNFX);
        FXCPGNFX.COMM_DATA.FUNC = 'E';
        FXCPGNFX.COMM_DATA.BU_CHNL = "CASH";
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            FXCPGNFX.COMM_DATA.TRN_DT = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            FXCPGNFX.COMM_DATA.JRN_NO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
        } else {
            FXCPGNFX.COMM_DATA.TRN_DT = SCCGWA.COMM_AREA.AC_DATE;
            FXCPGNFX.COMM_DATA.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
            FXCPGNFX.COMM_DATA.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            FXCPGNFX.COMM_DATA.STATUS = 'C';
            FXCPGNFX.COMM_DATA.TYP_SER = '2';
            FXCPGNFX.COMM_DATA.CI_NO = BPCEX.CI_NO;
            FXCPGNFX.COMM_DATA.ID_TYP = BPCEX.ID_TYP;
            FXCPGNFX.COMM_DATA.ID_NO = BPCEX.ID_NO;
            FXCPGNFX.COMM_DATA.PROD_CD = "1303020403";
            FXCPGNFX.COMM_DATA.PROD_TYP = "FX";
            FXCPGNFX.COMM_DATA.PROD_NM = "个人结售汇业�?";
            FXCPGNFX.COMM_DATA.VALUE_DT = SCCGWA.COMM_AREA.AC_DATE;
            FXCPGNFX.COMM_DATA.O_END_DT = SCCGWA.COMM_AREA.AC_DATE;
            FXCPGNFX.COMM_DATA.CTA_TYPE = '0';
            FXCPGNFX.COMM_DATA.PAC_FLG = '0';
            FXCPGNFX.COMM_DATA.BUY_CCY = BPCEX.BUY_CCY;
            FXCPGNFX.COMM_DATA.BUY_AMT = BPCEX.BUY_AMT;
            FXCPGNFX.COMM_DATA.B_CS_FLG = '0';
            FXCPGNFX.COMM_DATA.B_US_AMT = WS_B_US_AMT;
            FXCPGNFX.COMM_DATA.CAC_FLG = '0';
            FXCPGNFX.COMM_DATA.SELL_CCY = BPCEX.SELL_CCY;
            FXCPGNFX.COMM_DATA.SELL_AMT = BPCEX.SELL_AMT;
            if (FXCPGNFX.COMM_DATA.EX_CODE == null) FXCPGNFX.COMM_DATA.EX_CODE = "";
            JIBS_tmp_int = FXCPGNFX.COMM_DATA.EX_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) FXCPGNFX.COMM_DATA.EX_CODE += " ";
            if (BPCEX.BUY_CCY == null) BPCEX.BUY_CCY = "";
            JIBS_tmp_int = BPCEX.BUY_CCY.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) BPCEX.BUY_CCY += " ";
            FXCPGNFX.COMM_DATA.EX_CODE = BPCEX.BUY_CCY + FXCPGNFX.COMM_DATA.EX_CODE.substring(3);
            if (FXCPGNFX.COMM_DATA.EX_CODE == null) FXCPGNFX.COMM_DATA.EX_CODE = "";
            JIBS_tmp_int = FXCPGNFX.COMM_DATA.EX_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) FXCPGNFX.COMM_DATA.EX_CODE += " ";
            if (BPCEX.SELL_CCY == null) BPCEX.SELL_CCY = "";
            JIBS_tmp_int = BPCEX.SELL_CCY.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) BPCEX.SELL_CCY += " ";
            FXCPGNFX.COMM_DATA.EX_CODE = FXCPGNFX.COMM_DATA.EX_CODE.substring(0, 4 - 1) + BPCEX.SELL_CCY + FXCPGNFX.COMM_DATA.EX_CODE.substring(4 + 3 - 1);
            FXCPGNFX.COMM_DATA.EXR_TYPE = BPCEX.EXR_TYPE;
            FXCPGNFX.COMM_DATA.RATE_TM = BPCEX.READ_RT_TIME;
            FXCPGNFX.COMM_DATA.EX_TIME = SCCGWA.COMM_AREA.TR_TIME;
            FXCPGNFX.COMM_DATA.EX_RATE = BPCEX.TRN_EX_RATE;
            FXCPGNFX.COMM_DATA.B_S_FLG = '1';
            FXCPGNFX.COMM_DATA.ST_CHNL = SCCGWA.COMM_AREA.CHNL;
            FXCPGNFX.COMM_DATA.BU_CHNL = "CASH";
            FXCPGNFX.COMM_DATA.AC_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            FXCPGNFX.COMM_DATA.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            FXCPGNFX.COMM_DATA.RMK = "外币找零兑换交易";
            FXCPGNFX.COMM_DATA.EXST_CD1 = "132";
            FXCPGNFX.COMM_DATA.AMT_NS1 = BPCEX.BUY_AMT;
        }
        S000_CALL_FXZPGNFX();
        if (pgmRtn) return;
    }
    public void B001_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCEX.BUY_CCY);
        CEP.TRC(SCCGWA, BPCEX.SELL_CCY);
        CEP.TRC(SCCGWA, BPCEX.EXR_TYPE);
        CEP.TRC(SCCGWA, BPCEX.BUY_AMT);
        CEP.TRC(SCCGWA, BPCEX.SELL_AMT);
        CEP.TRC(SCCGWA, BPCEX.TRN_EX_RATE);
        CEP.TRC(SCCGWA, BPCEX.READ_RT_TIME);
        B011_03_EXR_TYPE_CHK();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "1111111111111111111");
        B011_05_CCY_CHK();
        if (pgmRtn) return;
        if (BPCEX.BUY_AMT == 0 
            || BPCEX.SELL_AMT == 0 
            || BPCEX.BUY_CCY.trim().length() == 0 
            || BPCEX.SELL_CCY.trim().length() == 0 
            || BPCEX.EXR_TYPE.trim().length() == 0 
            || BPCEX.READ_RT_TIME == 0 
            || BPCEX.READ_RT_TIME == BPCEX.TRN_EX_RATE) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_ERR_MUST_INPUT);
        }
        if (BPCEX.BUY_CCY.equalsIgnoreCase(BPCEX.SELL_CCY)) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_EX_CCY_SAME);
        } else {
            if (!BPCEX.SELL_CCY.equalsIgnoreCase("156")) {
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_PROC_CNY);
            }
        }
        B010_TRADE_TIME_CHK();
        if (pgmRtn) return;
    }
    public void B010_TRADE_TIME_CHK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, FXRTMDIF);
        FXRTMDIF.KEY.TYP = "EXTM";
        FXRTMDIF.KEY.CD = K_EXTM_CD;
        BPCPRMR.DAT_PTR = FXRTMDIF;
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        CEP.TRC(SCCGWA, FXRTMDIF.DATA_TXT.FX_RATE_MIN);
        CEP.TRC(SCCGWA, BPCEX.READ_RT_TIME);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_TIME);
        JIBS_tmp_str[0] = "" + BPCEX.READ_RT_TIME;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 2).trim().length() == 0) WS_EX_HOUR = 0;
        else WS_EX_HOUR = Integer.parseInt(JIBS_tmp_str[0].substring(0, 2));
        JIBS_tmp_str[0] = "" + BPCEX.READ_RT_TIME;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1).trim().length() == 0) WS_EX_MINU = 0;
        else WS_EX_MINU = Integer.parseInt(JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1));
        JIBS_tmp_str[0] = "" + BPCEX.READ_RT_TIME;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_EX_SECO = 0;
        else WS_EX_SECO = Integer.parseInt(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 2).trim().length() == 0) WS_TR_HOUR = 0;
        else WS_TR_HOUR = Integer.parseInt(JIBS_tmp_str[0].substring(0, 2));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1).trim().length() == 0) WS_TR_MINU = 0;
        else WS_TR_MINU = Integer.parseInt(JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_TR_SECO = 0;
        else WS_TR_SECO = Integer.parseInt(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        WS_EX_SEC = WS_EX_HOUR * 3600 + WS_EX_MINU * 60 + WS_EX_SECO;
        WS_TR_SEC = WS_TR_HOUR * 3600 + WS_TR_MINU * 60 + WS_TR_SECO;
        if (WS_EX_SEC > WS_TR_SEC) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_RT_TM_ERR);
        } else {
            WS_TM_DIF = WS_TR_SEC - WS_EX_SEC;
        }
        WS_TMDIF_TM_DIF = FXRTMDIF.DATA_TXT.FX_RATE_MIN * 60;
        if (WS_TM_DIF > WS_TMDIF_TM_DIF) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_RATE_TIMEOUT);
        }
    }
    public void B011_03_EXR_TYPE_CHK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPREXRT1);
        IBS.init(SCCGWA, BPCPRMR);
        BPREXRT1.KEY.TYP = "EXRT";
        BPREXRT1.KEY.CD = BPCEX.EXR_TYPE;
        BPCPRMR.DAT_PTR = BPREXRT1;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPRMR.RC.RC_RTNCODE);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INVALID_EXR_TYP);
        }
    }
    public void B011_05_CCY_CHK() throws IOException,SQLException,Exception {
        WS_CCY = BPCEX.BUY_CCY;
        R000_CHECK_CCY();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.DATA);
