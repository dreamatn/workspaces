package com.hisun.CM;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCSVBLT;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCTLM_MSG;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCPRMR;

public class CMZS9900 {
    boolean pgmRtn = false;
    CMZS9900_WS_TR_DATA WS_TR_DATA = new CMZS9900_WS_TR_DATA();
    String WS_BV_STA_18 = " ";
    String WS_BV_END_18 = " ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    long WS_AP_BATNO = 0;
    CMZS9900_WS_FILE_SEQ WS_FILE_SEQ = new CMZS9900_WS_FILE_SEQ();
    CMZS9900_WS_PARM WS_PARM = new CMZS9900_WS_PARM();
    char WS_BSP_FUNC = ' ';
    char WS_CMTBSPM_FLG = ' ';
    char WS_PROC1_FLG = ' ';
    char WS_PROC2_FLG = ' ';
    char WS_PROC3_FLG = ' ';
    char WS_STS_CHK_FLG = ' ';
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCRBSPC SCRBSPC = new SCRBSPC();
    SCCBSPS SCCBSPS = new SCCBSPS();
    SCCBSP SCCBSP = new SCCBSP();
    SCCRWBTL SCCRWBTL = new SCCRWBTL();
    SCRBBTL SCRBBTL = new SCRBBTL();
    SCCCTLM_MSG SCCCTLM_MSG = new SCCCTLM_MSG();
    SCCPRMR SCCPRMR = new SCCPRMR();
    BPCFITYP BPCFITYP = new BPCFITYP();
    CMCO9900 CMCO9900 = new CMCO9900();
    CMCTLIBB CMCTLIBB = new CMCTLIBB();
    CMRTRSTA CMRTRSTA = new CMRTRSTA();
    CMRBSPM CMRBSPM = new CMRBSPM();
    DCCUBVIQ DCCUBVIQ = new DCCUBVIQ();
    CMCBSPIN CMCBSPIN = new CMCBSPIN();
    BPCSVBLT BPCSVBLT = new BPCSVBLT();
    SCRHBBTL SCRHBBTL = new SCRHBBTL();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CMCS9900 CMCS9900;
    public void MP(SCCGWA SCCGWA, CMCS9900 CMCS9900) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CMCS9900 = CMCS9900;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CMZS9900 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMCO9900);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_PROC();
        if (pgmRtn) return;
        if (WS_BSP_FUNC == '2') {
            B200_BSP_INQ_PROC();
            if (pgmRtn) return;
        } else if (WS_BSP_FUNC == '1') {
            B300_BSP_ADD_PROC();
            if (pgmRtn) return;
            B200_BSP_INQ_PROC();
            if (pgmRtn) return;
        } else if (WS_BSP_FUNC == '4') {
            B400_BSP_DEL_PROC();
            if (pgmRtn) return;
        } else if (WS_BSP_FUNC == '3') {
            B500_BSP_RESUB_PROC();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_FUN_TYP_ERR);
        }
        B900_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        WS_BSP_FUNC = CMCS9900.FUNC;
        JIBS_tmp_str[0] = "" + CMCS9900.BUS_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(3 - 1, 3 + 6 - 1).trim().length() == 0) WS_FILE_SEQ.WS_BUS_DATE = 0;
        else WS_FILE_SEQ.WS_BUS_DATE = Integer.parseInt(JIBS_tmp_str[0].substring(3 - 1, 3 + 6 - 1));
        WS_FILE_SEQ.WS_BAT_SEQ = "" + CMCS9900.BAT_SEQ;
        JIBS_tmp_int = WS_FILE_SEQ.WS_BAT_SEQ.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) WS_FILE_SEQ.WS_BAT_SEQ = "0" + WS_FILE_SEQ.WS_BAT_SEQ;
        WS_FILE_SEQ.WS_BUS_TYP = CMCS9900.BUS_TYP;
        WS_PARM.WS_TRI_INFO.WS_TSF_FILE = CMCS9900.TSF_FILE;
        if ((CMCS9900.TSF_FILE.trim().length() == 0 
            || CMCS9900.TSF_FILE.charAt(0) == 0X00) 
            && WS_BSP_FUNC == '1') {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_ERR_MUST_INPUT);
        }
        IBS.init(SCCGWA, BPCFITYP);
        IBS.init(SCCGWA, SCCPRMR);
        SCCPRMR.FUNC = ' ';
        BPCFITYP.KEY.TYP = "FITYP";
        BPCFITYP.KEY.CD = CMCS9900.BUS_TYP;
        S000_CALL_SCZPRMR();
        if (pgmRtn) return;
        if (BPCFITYP.DATA_TXT.BSP_SERV_CODE.trim().length() == 0 
            || BPCFITYP.DATA_TXT.BSP_SERV_CODE.charAt(0) == 0X00) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_FITYP_PARM_ERR);
        }
        IBS.init(SCCGWA, SCRBSPC);
        SCRBSPC.KEY.SERV_CODE = BPCFITYP.DATA_TXT.BSP_SERV_CODE;
        CEP.TRC(SCCGWA, SCRBSPC.KEY.SERV_CODE);
        T000_READ_SCTBSPC();
        if (pgmRtn) return;
    }
    public void B200_BSP_INQ_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFITYP);
        IBS.init(SCCGWA, SCCPRMR);
        SCCPRMR.FUNC = ' ';
        BPCFITYP.KEY.TYP = "FITYP";
        BPCFITYP.KEY.CD = CMCS9900.BUS_TYP;
        S000_CALL_SCZPRMR();
        if (pgmRtn) return;
        if (BPCFITYP.DATA_TXT.BSP_SERV_CODE.trim().length() == 0 
            || BPCFITYP.DATA_TXT.BSP_SERV_CODE.charAt(0) == 0X00) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_FITYP_PARM_ERR);
        }
        JIBS_tmp_str[0] = "" + CMCS9900.BUS_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        JIBS_tmp_str[1] = "" + WS_AP_BATNO;
        JIBS_f0 = "";
        for (int i=0;i<12-JIBS_tmp_str[1].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + WS_AP_BATNO;
        JIBS_NumStr = JIBS_tmp_str[0].substring(3 - 1, 3 + 6 - 1) + JIBS_NumStr.substring(6);
        WS_AP_BATNO = Long.parseLong(JIBS_NumStr);
        JIBS_tmp_str[0] = "" + WS_AP_BATNO;
        JIBS_f0 = "";
        for (int i=0;i<12-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + WS_AP_BATNO;
        JIBS_tmp_str[1] = "" + CMCS9900.BAT_SEQ;
        JIBS_tmp_int = JIBS_tmp_str[1].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
        JIBS_NumStr = JIBS_NumStr.substring(0, 7 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(7 + 6 - 1);
        WS_AP_BATNO = Long.parseLong(JIBS_NumStr);
        IBS.init(SCCGWA, SCCRWBTL);
        SCCRWBTL.PTR = SCRBBTL;
        SCCRWBTL.LEN = 837;
        SCRBBTL.KEY.SERV_CODE = BPCFITYP.DATA_TXT.BSP_SERV_CODE;
        SCRBBTL.KEY.AP_TYPE = WS_FILE_SEQ.WS_BUS_TYP;
        SCRBBTL.KEY.AP_BATNO = WS_AP_BATNO;
        SCCRWBTL.FUNC = 'Q';
        S000_CALL_SCZRWBTL();
        if (pgmRtn) return;
        if (SCCRWBTL.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, SCRHBBTL);
            SCRHBBTL.KEY.SERV_CODE = BPCFITYP.DATA_TXT.BSP_SERV_CODE;
            SCRHBBTL.KEY.AP_TYPE = WS_FILE_SEQ.WS_BUS_TYP;
            SCRHBBTL.KEY.AP_BATNO = WS_AP_BATNO;
            S000_READ_SCTHBBTL_FIRST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                CEP.TRC(SCCGWA, SCRHBBTL.REDEFINES27.REDEFINES29.STEP_CTL[1-1].STEP_STS);
                CEP.TRC(SCCGWA, SCRHBBTL.REDEFINES27.REDEFINES29.STEP_CTL[1-1].STEP_PROC_NAME);
                CEP.TRC(SCCGWA, SCRHBBTL.REDEFINES27.REDEFINES29.STEP_CTL[2-1].STEP_STS);
                CEP.TRC(SCCGWA, SCRHBBTL.REDEFINES27.REDEFINES29.STEP_CTL[2-1].STEP_PROC_NAME);
                CEP.TRC(SCCGWA, SCRHBBTL.REDEFINES27.REDEFINES29.STEP_CTL[3-1].STEP_STS);
                CEP.TRC(SCCGWA, SCRHBBTL.REDEFINES27.REDEFINES29.STEP_CTL[3-1].STEP_PROC_NAME);
                for (WS_I = 1; WS_I <= 3 
                    && WS_STS_CHK_FLG != 'Y'; WS_I += 1) {
                    if (SCRHBBTL.REDEFINES27.REDEFINES29.STEP_CTL[WS_I-1].STEP_PROC_NAME.trim().length() > 0) {
                        CMCO9900.BAT_STS = SCRHBBTL.REDEFINES27.REDEFINES29.STEP_CTL[WS_I-1].STEP_STS;
                        if (SCRHBBTL.REDEFINES27.REDEFINES29.STEP_CTL[WS_I-1].STEP_STS == 'E' 
                            || SCRHBBTL.REDEFINES27.REDEFINES29.STEP_CTL[WS_I-1].STEP_STS == 'P') {
                            WS_STS_CHK_FLG = 'Y';
                        }
                    }
                }
                CMCO9900.BAT_SCNT = SCRHBBTL.CHK_CNT;
                CMCO9900.BAT_FCNT = SCRHBBTL.ERR_CNT;
                CMCO9900.TSF_FILE = SCRHBBTL.TSF_FILE;
                CMCO9900.RTN_FILE = SCRHBBTL.RTN_FILE;
                CMCO9900.BUS_TYP = CMCS9900.BUS_TYP;
                CMCO9900.BUS_DATE = CMCS9900.BUS_DATE;
                CMCO9900.BAT_SEQ = CMCS9900.BAT_SEQ;
                CMCO9900.BAT_TCNT = CMCS9900.BAT_TCNT;
            } else {
                CMCO9900.BAT_STS = 'N';
            }
        } else {
            CEP.TRC(SCCGWA, SCRBBTL.REDEFINES28.REDEFINES30.STEP_CTL[1-1].STEP_STS);
            CEP.TRC(SCCGWA, SCRBBTL.REDEFINES28.REDEFINES30.STEP_CTL[1-1].STEP_PROC_NAME);
            CEP.TRC(SCCGWA, SCRBBTL.REDEFINES28.REDEFINES30.STEP_CTL[2-1].STEP_STS);
            CEP.TRC(SCCGWA, SCRBBTL.REDEFINES28.REDEFINES30.STEP_CTL[2-1].STEP_PROC_NAME);
            CEP.TRC(SCCGWA, SCRBBTL.REDEFINES28.REDEFINES30.STEP_CTL[3-1].STEP_STS);
            CEP.TRC(SCCGWA, SCRBBTL.REDEFINES28.REDEFINES30.STEP_CTL[3-1].STEP_PROC_NAME);
            for (WS_I = 1; WS_I <= 3 
                && WS_STS_CHK_FLG != 'Y'; WS_I += 1) {
                if (SCRBBTL.REDEFINES28.REDEFINES30.STEP_CTL[WS_I-1].STEP_PROC_NAME.trim().length() > 0) {
                    CMCO9900.BAT_STS = SCRBBTL.REDEFINES28.REDEFINES30.STEP_CTL[WS_I-1].STEP_STS;
                    if (SCRBBTL.REDEFINES28.REDEFINES30.STEP_CTL[WS_I-1].STEP_STS == 'E' 
                        || SCRBBTL.REDEFINES28.REDEFINES30.STEP_CTL[WS_I-1].STEP_STS == 'P') {
                        WS_STS_CHK_FLG = 'Y';
                    }
                }
            }
            CEP.TRC(SCCGWA, CMCO9900.BAT_STS);
            CMCO9900.BAT_SCNT = SCRBBTL.CHK_CNT;
            CMCO9900.BAT_FCNT = SCRBBTL.ERR_CNT;
            CMCO9900.TSF_FILE = SCRBBTL.TSF_FILE;
            CMCO9900.RTN_FILE = SCRBBTL.RTN_FILE;
            CMCO9900.BUS_TYP = CMCS9900.BUS_TYP;
            CMCO9900.BUS_DATE = CMCS9900.BUS_DATE;
            CMCO9900.BAT_SEQ = CMCS9900.BAT_SEQ;
            CMCO9900.BAT_TCNT = CMCS9900.BAT_TCNT;
        }
        IBS.init(SCCGWA, CMRBSPM);
        CMRBSPM.KEY.AP_TYPE = WS_FILE_SEQ.WS_BUS_TYP;
        CMRBSPM.KEY.AP_BATNO = WS_AP_BATNO;
        T000_READ_CMTBSPM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CMRBSPM.KEY.AP_TYPE);
        CEP.TRC(SCCGWA, CMRBSPM.KEY.AP_BATNO);
        CEP.TRC(SCCGWA, CMRBSPM);
        CEP.TRC(SCCGWA, CMRBSPM.TR_DATA);
        if (WS_CMTBSPM_FLG == 'Y') {
            CMCO9900.BAT_CTL = CMRBSPM.TR_DATA;
            CEP.TRC(SCCGWA, CMCO9900.BAT_CTL);
        }
    }
    public void B300_BSP_ADD_PROC() throws IOException,SQLException,Exception {
        B310_GEN_BSP_REL_INF_PROC();
        if (pgmRtn) return;
        B320_REGISTER_BSP_INFO();
        if (pgmRtn) return;
        B330_SPECIAL_PROC();
        if (pgmRtn) return;
        B390_SUB_BSP_PROC();
        if (pgmRtn) return;
    }
    public void B310_GEN_BSP_REL_INF_PROC() throws IOException,SQLException,Exception {
        WS_PARM.WS_HOST_FNAME_INFO.WS_RTN_FIL.WS_RTN_BUS_TYP = CMCS9900.BUS_TYP;
        WS_PARM.WS_BUS_TYPE_INFO.WS_BUS_TYPE2 = CMCS9900.BUS_TYP;
        WS_PARM.WS_HOST_FNAME_INFO.WS_RTN_FIL.WS_RTN_BUS_DATE = CMCS9900.BUS_DATE;
        WS_PARM.WS_BUS_TYPE_INFO.WS_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_str[0] = "" + CMCS9900.BUS_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        JIBS_tmp_str[1] = "" + WS_AP_BATNO;
        JIBS_f0 = "";
        for (int i=0;i<12-JIBS_tmp_str[1].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + WS_AP_BATNO;
        JIBS_NumStr = JIBS_tmp_str[0].substring(3 - 1, 3 + 6 - 1) + JIBS_NumStr.substring(6);
        WS_AP_BATNO = Long.parseLong(JIBS_NumStr);
        JIBS_tmp_str[0] = "" + WS_AP_BATNO;
        JIBS_f0 = "";
        for (int i=0;i<12-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + WS_AP_BATNO;
        JIBS_tmp_str[1] = "" + CMCS9900.BAT_SEQ;
        JIBS_tmp_int = JIBS_tmp_str[1].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
        JIBS_NumStr = JIBS_NumStr.substring(0, 7 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(7 + 6 - 1);
        WS_AP_BATNO = Long.parseLong(JIBS_NumStr);
        WS_PARM.WS_BUS_TYPE_INFO.WS_BAT_NO2 = "" + WS_AP_BATNO;
        JIBS_tmp_int = WS_PARM.WS_BUS_TYPE_INFO.WS_BAT_NO2.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) WS_PARM.WS_BUS_TYPE_INFO.WS_BAT_NO2 = "0" + WS_PARM.WS_BUS_TYPE_INFO.WS_BAT_NO2;
        WS_PARM.WS_BUS_TYPE_INFO.WS_REQ_SYS_ID = SCCGWA.COMM_AREA.REQ_SYSTEM;
        WS_PARM.WS_BUS_TYPE_INFO.WS_SERV_CODE = BPCFITYP.DATA_TXT.BSP_SERV_CODE;
        WS_PARM.WS_HOST_FNAME_INFO.WS_RTN_FIL.WS_RTN_BAT_NO = WS_PARM.WS_BUS_TYPE_INFO.WS_BAT_NO2;
        WS_PARM.WS_TRO_INF.WS_RTN_FILE = IBS.CLS2CPY(SCCGWA, WS_PARM.WS_HOST_FNAME_INFO.WS_RTN_FIL);
        CMCO9900.RTN_FILE = IBS.CLS2CPY(SCCGWA, WS_PARM.WS_HOST_FNAME_INFO.WS_RTN_FIL);
        WS_PARM.WS_TRI_INFO.WS_TSF_FILE = CMCS9900.TSF_FILE;
        WS_PARM.WS_HOST_FNAME_INFO.WS_TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        WS_PARM.WS_BUS_TYPE_INFO.WS_TR_TLR = SCCGWA.COMM_AREA.TL_ID;
    }
    public void B320_REGISTER_BSP_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCBSPS);
        SCCBSPS.FUN = 'A';
        SCCBSPS.SERV_CODE = BPCFITYP.DATA_TXT.BSP_SERV_CODE;
        SCCBSPS.AP_TYPE = CMCS9900.BUS_TYP;
        SCCBSPS.AP_BATNO = WS_AP_BATNO;
        SCCBSPS.TSF_FILE = CMCS9900.TSF_FILE;
        SCCBSPS.RTN_FILE = IBS.CLS2CPY(SCCGWA, WS_PARM.WS_HOST_FNAME_INFO.WS_RTN_FIL);
        SCCBSPS.STS = 'P';
        S000_CALL_SCZBSPS();
        if (pgmRtn) return;
    }
    public void B330_SPECIAL_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CMCS9900.BUS_TYP);
        if (CMCS9900.BUS_TYP.equalsIgnoreCase("CM0001")) {
            B341_OPEN_CARD_BV_PROC();
            if (pgmRtn) return;
        } else if (CMCS9900.BUS_TYP.equalsIgnoreCase("CM0002")) {
            B342_OPEN_REAL_CARD_BV_PROC();
            if (pgmRtn) return;
        } else if (CMCS9900.BUS_TYP.equalsIgnoreCase("DDCLAC")
            || CMCS9900.BUS_TYP.equalsIgnoreCase("DCATTR")) {
            B340_GEN_CMTBSPM_REC();
            if (pgmRtn) return;
        } else {
            if (CMCS9900.BAT_CTL.trim().length() > 0 
                && CMCS9900.BAT_CTL.charAt(0) != 0X00) {
                B340_GEN_CMTBSPM_REC();
                if (pgmRtn) return;
            }
        }
    }
    public void B340_GEN_CMTBSPM_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMRBSPM);
        CMRBSPM.KEY.AP_TYPE = CMCS9900.BUS_TYP;
        CMRBSPM.KEY.AP_BATNO = WS_AP_BATNO;
        CMRBSPM.IN_DATA = CMCS9900.BAT_CTL;
        CEP.TRC(SCCGWA, CMRBSPM.IN_DATA.trim().length());
        CEP.TRC(SCCGWA, CMRBSPM.IN_DATA);
        T000_WRITE_CMTBSPM();
        if (pgmRtn) return;
    }
    public void B341_OPEN_CARD_BV_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CMCS9900.BAT_CTL);
        if (CMCS9900.BAT_CTL.trim().length() > 0 
            && CMCS9900.BAT_CTL.charAt(0) != 0X00) {
            IBS.CPY2CLS(SCCGWA, CMCS9900.BAT_CTL, WS_TR_DATA);
            CEP.TRC(SCCGWA, WS_TR_DATA.WS_PROD_CD);
            CEP.TRC(SCCGWA, WS_TR_DATA.WS_CARD_CLS_PROD);
            CEP.TRC(SCCGWA, WS_TR_DATA.WS_TL_ID);
            CEP.TRC(SCCGWA, WS_TR_DATA.WS_OPEN_CARD_FLG);
            IBS.init(SCCGWA, DCCUBVIQ);
            IBS.init(SCCGWA, CMCBSPIN);
            DCCUBVIQ.CARD_PRDT_CODE = WS_TR_DATA.WS_PROD_CD;
            DCCUBVIQ.CARD_CLS_CD = WS_TR_DATA.WS_CARD_CLS_PROD;
            S000_CALL_DCZUBVIQ();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DCCUBVIQ.BV_CD_NO);
            if (DCCUBVIQ.RC.RC_CODE == 0) {
                IBS.init(SCCGWA, BPCSVBLT);
                BPCSVBLT.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPCSVBLT.TLR = WS_TR_DATA.WS_TL_ID;
                BPCSVBLT.BV_CODE = DCCUBVIQ.BV_CD_NO;
                BPCSVBLT.NUM = (int) CMCS9900.BAT_TCNT;
                S000_CALL_BPZSVBLT();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCSVBLT.O_NO_CNT);
                CEP.TRC(SCCGWA, BPCSVBLT.O_BV_DATA[1-1].O_BEG_NO);
                CEP.TRC(SCCGWA, BPCSVBLT.O_BV_DATA[1-1].O_END_NO);
                CEP.TRC(SCCGWA, BPCSVBLT.O_BV_DATA[1-1].O_BV_NUM);
                if (BPCSVBLT.O_NO_CNT > 5) {
                    IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_BV_GT_FIVE, CMCBSPIN.RC);
                }
            }
            CMCBSPIN.BV_CODE = DCCUBVIQ.BV_CD_NO;
            if (BPCSVBLT.RC.RC_CODE != 0) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSVBLT.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CMCBSPIN.RC);
            }
            if (DCCUBVIQ.RC.RC_CODE != 0) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUBVIQ.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CMCBSPIN.RC);
            }
            CEP.TRC(SCCGWA, CMCBSPIN.RC);
            for (WS_I = 1; WS_I <= BPCSVBLT.O_NO_CNT 
                && (CMCBSPIN.RC.RC_CODE == 0); WS_I += 1) {
                CEP.TRC(SCCGWA, WS_I);
                CEP.TRC(SCCGWA, BPCSVBLT.O_BV_DATA[WS_I-1].O_BEG_NO);
                CEP.TRC(SCCGWA, BPCSVBLT.O_BV_DATA[WS_I-1].O_END_NO);
                CEP.TRC(SCCGWA, BPCSVBLT.O_BV_DATA[WS_I-1].O_BV_NUM);
                WS_BV_STA_18 = BPCSVBLT.O_BV_DATA[WS_I-1].O_BEG_NO;
                WS_BV_END_18 = BPCSVBLT.O_BV_DATA[WS_I-1].O_END_NO;
                CMCBSPIN.INPUT[WS_I-1].STA_BV_NO_18 = WS_BV_STA_18;
