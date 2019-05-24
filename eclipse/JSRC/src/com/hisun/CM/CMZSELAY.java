package com.hisun.CM;

import com.hisun.SC.*;

import java.util.Date;
import java.text.SimpleDateFormat;

import java.io.IOException;
import java.sql.SQLException;

public class CMZSELAY {
    SimpleDateFormat JIBS_sdf;
    Date JIBS_date;
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm CMTDELAY_RD;
    String K_SYS_ERR = "SC6001";
    String PGM_SCSSCLDT = "SCSSCLDT";
    int WK_TIG_TIME = 090000;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    int WS_CNT = 0;
    int WS_DAY_CNT = 0;
    short WS_R = 0;
    String WS_ACO_NO = " ";
    String WS_DDAC_STSW = " ";
    String WS_TM_X = " ";
    short WS_TM_2 = 0;
    short WS_RC = 0;
    short WS_RC_DISP = 0;
    String WS_TS = " ";
    CMZSELAY_WS_TS_DETAIL WS_TS_DETAIL = new CMZSELAY_WS_TS_DETAIL();
    CMZSELAY_WS_TRIG_DATE WS_TRIG_DATE = new CMZSELAY_WS_TRIG_DATE();
    int WS_TRIG_DATE_INT = 0;
    CMZSELAY_WS_TRIG_TIME WS_TRIG_TIME = new CMZSELAY_WS_TRIG_TIME();
    int WS_TRIG_TIME_INT = 0;
    CMZSELAY_WS_CUR_DATE WS_CUR_DATE = new CMZSELAY_WS_CUR_DATE();
    int WS_CUR_DATE_INT = 0;
    CMZSELAY_WS_CUR_TIME WS_CUR_TIME = new CMZSELAY_WS_CUR_TIME();
    int WS_CUR_TIME_INT = 0;
    char WS_CMTDELAY_FLG = ' ';
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    CMRDELAY CMRDELAY = new CMRDELAY();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCBINF SCCBINF;
    CMCS1500 CMCS1500;
    CMCSELAY CMCSELAY;
    public void MP(SCCGWA SCCGWA, CMCSELAY CMCSELAY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CMCSELAY = CMCSELAY;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CMZSELAY return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CMRDELAY);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHECK_PROC();
        B200_ADD_INQ_MOD_MAIN_PROC();
    }
    public void B100_INPUT_CHECK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CMCSELAY.FUNC);
        CEP.TRC(SCCGWA, CMCSELAY.HLD_NO);
        CEP.TRC(SCCGWA, CMCSELAY.TRF_TYP);
        CEP.TRC(SCCGWA, CMCSELAY.TRO_AC);
        CEP.TRC(SCCGWA, CMCSELAY.TRI_AC);
        CEP.TRC(SCCGWA, CMCSELAY.CCY);
        CEP.TRC(SCCGWA, CMCSELAY.CCY_TYP);
        CEP.TRC(SCCGWA, CMCSELAY.TRF_AMT);
        CEP.TRC(SCCGWA, CMCSELAY.FEE_AMT);
        CEP.TRC(SCCGWA, CMCSELAY.TRIG_DATA);
        CEP.TRC(SCCGWA, CMCSELAY.RET_CODE);
        CEP.TRC(SCCGWA, CMCSELAY.RET_MSG);
        if (CMCSELAY.HLD_NO.trim().length() == 0 
            || CMCSELAY.HLD_NO.charAt(0) == 0X00) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_HLD_NO_MUST_IN);
        }
        if (CMCSELAY.FUNC == '1') {
            if (CMCSELAY.TRF_TYP == ' ' 
                || CMCSELAY.TRF_TYP == 0X00) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TRF_TYP_MUST_IN);
            }
            if (CMCSELAY.HLD_NO.trim().length() == 0 
                || CMCSELAY.HLD_NO.charAt(0) == 0X00) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_HLD_NO_MUST_IN);
            }
            if (CMCSELAY.TRIG_DATA.trim().length() == 0 
                || CMCSELAY.TRIG_DATA.charAt(0) == 0X00) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TRIG_DATA_MUST_INPUT);
            }
            if (CMCSELAY.TRO_AC.trim().length() == 0 
                || CMCSELAY.TRO_AC.charAt(0) == 0X00) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TRO_AC_MUST_INPUT);
            }
            if (CMCSELAY.TRI_AC.trim().length() == 0 
                || CMCSELAY.TRI_AC.charAt(0) == 0X00) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TRI_AC_MUST_INPUT);
            }
            if (CMCSELAY.CCY.trim().length() == 0 
                || CMCSELAY.CCY.charAt(0) == 0X00) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CCY_MUST_IN);
            }
            if (!CMCSELAY.CCY.equalsIgnoreCase("156") 
                && (CMCSELAY.CCY_TYP == ' ' 
                || CMCSELAY.CCY_TYP == 0X00)) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CCY_TYP_M_INPUT);
            }
            if (CMCSELAY.TRF_AMT == 0) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TRF_AMT_INPUT_ERR);
            }
        }
        if (CMCSELAY.FUNC == '5') {
            if (CMCSELAY.RET_CODE.trim().length() == 0 
                || CMCSELAY.RET_CODE.charAt(0) == 0X00) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_RET_CODE_MUST_INPUT);
            }
        }
        if (CMCSELAY.FUNC != '1' 
            && CMCSELAY.FUNC != '2' 
            && CMCSELAY.FUNC != '3' 
            && CMCSELAY.FUNC != '4' 
            && CMCSELAY.FUNC != '5' 
            && CMCSELAY.FUNC != '6' 
            && CMCSELAY.FUNC != '7') {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_FUNC_TYP_ERR);
        }
    }
    public void B200_ADD_INQ_MOD_MAIN_PROC() throws IOException,SQLException,Exception {
        if (CMCSELAY.FUNC == '1') {
            B210_ADD_RECORD_PROC();
        } else if (CMCSELAY.FUNC == '2') {
            B220_INQ_RECORD_PROC();
        } else if (CMCSELAY.FUNC == '3'
            || CMCSELAY.FUNC == '4'
            || CMCSELAY.FUNC == '5'
            || CMCSELAY.FUNC == '6'
            || CMCSELAY.FUNC == '7') {
            B230_REWRITE_RECORD_PROC();
        } else {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_FUNC_TYP_ERR);
        }
    }
    public void B210_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CMCSELAY.HLD_NO);
        IBS.init(SCCGWA, CMRDELAY);
        WS_DAY_CNT = 0;
        CMRDELAY.HLD_NO = CMCSELAY.HLD_NO;
        T000_READ_CMTDELAY();
        if (WS_CMTDELAY_FLG == 'Y') {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_DELAY_FOUND_ERR);
        }
        JIBS_sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        JIBS_date = new Date();
        WS_TS = JIBS_sdf.format(JIBS_date);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_TIME);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.TR_DATE+"", WS_CUR_DATE);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.TR_TIME+"", WS_CUR_TIME);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.TR_DATE+"", WS_TRIG_DATE);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.TR_TIME+"", WS_TRIG_TIME);
        if (CMCSELAY.TRF_TYP == '1') {
            WS_TRIG_TIME.WS_TRIG_HOUR += 2;
            if (WS_TRIG_TIME.WS_TRIG_HOUR > 24) {
                WS_TRIG_TIME.WS_TRIG_HOUR -= 24;
                WS_DAY_CNT += 1;
            }
        } else if (CMCSELAY.TRF_TYP == '2') {
            IBS.CPY2CLS(SCCGWA, WK_TIG_TIME+"", WS_TRIG_TIME);
            WS_DAY_CNT += 1;
        } else if (CMCSELAY.TRF_TYP == '3') {
            WS_DAY_CNT += 1;
        } else {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TRF_TYP_ERR);
        }
        CEP.TRC(SCCGWA, WS_DAY_CNT);
        if (WS_DAY_CNT >= 1) {
            IBS.init(SCCGWA, SCCCLDT);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TRIG_DATE);
            SCCCLDT.DATE1 = Integer.parseInt(JIBS_tmp_str[0]);
            SCCCLDT.DAYS = 1;
            S000_CALL_SCSSCLDT();
            IBS.CPY2CLS(SCCGWA, SCCCLDT.DATE2+"", WS_TRIG_DATE);
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TRIG_DATE);
        WS_TRIG_DATE_INT = Integer.parseInt(JIBS_tmp_str[0]);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TRIG_TIME);
        WS_TRIG_TIME_INT = Integer.parseInt(JIBS_tmp_str[0]);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_CUR_DATE);
        WS_CUR_DATE_INT = Integer.parseInt(JIBS_tmp_str[0]);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_CUR_TIME);
        WS_CUR_TIME_INT = Integer.parseInt(JIBS_tmp_str[0]);
        CEP.TRC(SCCGWA, WS_TRIG_DATE);
        CEP.TRC(SCCGWA, WS_TRIG_TIME);
        CEP.TRC(SCCGWA, WS_CUR_DATE);
        CEP.TRC(SCCGWA, WS_CUR_TIME);
        IBS.init(SCCGWA, CMRDELAY);
        CMRDELAY.KEY.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CMRDELAY.KEY.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        CMRDELAY.REQ_SYS = SCCGWA.COMM_AREA.REQ_SYSTEM;
        CMRDELAY.REQ_SYS_DATE = GWA_SC_AREA.REQ_SYS_DATE;
        CMRDELAY.REQ_SYS_JRN = GWA_SC_AREA.REQ_SYS_JRN;
        CMRDELAY.TR_DATE = WS_CUR_DATE_INT;
        CMRDELAY.TR_TIME = WS_CUR_TIME_INT;
        CMRDELAY.TRF_TYP = CMCSELAY.TRF_TYP;
        CMRDELAY.FR_CHNL = SCCGWA.COMM_AREA.CHNL;
        CMRDELAY.TR_STS = '1';
        CMRDELAY.TRIG_DATE = WS_TRIG_DATE_INT;
        CMRDELAY.TRIG_TIME = WS_TRIG_TIME_INT;
        CMRDELAY.HLD_NO = CMCSELAY.HLD_NO;
        CMRDELAY.TRO_AC = CMCSELAY.TRO_AC;
        CMRDELAY.TRI_AC = CMCSELAY.TRI_AC;
        CMRDELAY.CCY = CMCSELAY.CCY;
        CMRDELAY.CCY_TYP = CMCSELAY.CCY_TYP;
        CMRDELAY.TRF_AMT = CMCSELAY.TRF_AMT;
        CMRDELAY.FEE_AMT = CMCSELAY.FEE_AMT;
        CEP.TRC(SCCGWA, CMCSELAY.TRIG_DATA);
        CMRDELAY.BLOB_TRIG_DATA = CMCSELAY.TRIG_DATA;
        if (CMRDELAY.BLOB_TRIG_DATA == null) CMRDELAY.BLOB_TRIG_DATA = "";
        JIBS_tmp_int = CMRDELAY.BLOB_TRIG_DATA.length();
        for (int i=0;i<4096-JIBS_tmp_int;i++) CMRDELAY.BLOB_TRIG_DATA += " ";
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        CMRDELAY.BLOB_TRIG_DATA = CMRDELAY.BLOB_TRIG_DATA.substring(0, 4087 - 1) + JIBS_tmp_str[0] + CMRDELAY.BLOB_TRIG_DATA.substring(4087 + 6 - 1);
        CEP.TRC(SCCGWA, CMRDELAY.BLOB_TRIG_DATA);
        CEP.TRC(SCCGWA, CMRDELAY.BLOB_TRIG_DATA.trim().length());
        CMRDELAY.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CMRDELAY.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CMRDELAY.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CMRDELAY.LAST_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, CMRDELAY.KEY.AC_DATE);
        CEP.TRC(SCCGWA, CMRDELAY.KEY.JRN_NO);
        CEP.TRC(SCCGWA, CMRDELAY.REQ_SYS);
        CEP.TRC(SCCGWA, CMRDELAY.REQ_SYS_DATE);
        CEP.TRC(SCCGWA, CMRDELAY.REQ_SYS_JRN);
        CEP.TRC(SCCGWA, CMRDELAY.TR_DATE);
        CEP.TRC(SCCGWA, CMRDELAY.TR_TIME);
        CEP.TRC(SCCGWA, CMRDELAY.TRF_TYP);
        CEP.TRC(SCCGWA, CMRDELAY.FR_CHNL);
        CEP.TRC(SCCGWA, CMRDELAY.TR_STS);
        CEP.TRC(SCCGWA, CMRDELAY.TRIG_DATE);
        CEP.TRC(SCCGWA, CMRDELAY.TRIG_TIME);
        CEP.TRC(SCCGWA, CMRDELAY.HLD_NO);
        CEP.TRC(SCCGWA, CMRDELAY.TRO_AC);
        CEP.TRC(SCCGWA, CMRDELAY.TRI_AC);
        CEP.TRC(SCCGWA, CMRDELAY.CCY);
        CEP.TRC(SCCGWA, CMRDELAY.CCY_TYP);
        CEP.TRC(SCCGWA, CMRDELAY.TRF_AMT);
        CEP.TRC(SCCGWA, CMRDELAY.FEE_AMT);
        T000_WRITE_CMTDELAY();
        R_MOVE_CMTDELAY_OUTPUT();
    }
    public void B220_INQ_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CMCSELAY.HLD_NO);
        IBS.init(SCCGWA, CMRDELAY);
        CMRDELAY.HLD_NO = CMCSELAY.HLD_NO;
        T000_READ_CMTDELAY();
        if (WS_CMTDELAY_FLG == 'Y') {
            R_MOVE_CMTDELAY_OUTPUT();
        } else {
            WS_CMTDELAY_FLG = 'N';
        }
        CEP.TRC(SCCGWA, CMCSELAY.HLD_NO);
        CEP.TRC(SCCGWA, CMCSELAY.KEY.AC_DATE);
        CEP.TRC(SCCGWA, CMCSELAY.KEY.JRN_NO);
        CEP.TRC(SCCGWA, CMCSELAY.REQ_SYS);
        CEP.TRC(SCCGWA, CMCSELAY.REQ_SYS_DATE);
        CEP.TRC(SCCGWA, CMCSELAY.REQ_SYS_JRN);
        CEP.TRC(SCCGWA, CMCSELAY.TR_DATE);
        CEP.TRC(SCCGWA, CMCSELAY.TR_TIME);
        CEP.TRC(SCCGWA, CMCSELAY.FR_CHNL);
        CEP.TRC(SCCGWA, CMCSELAY.TR_STS);
        CEP.TRC(SCCGWA, CMCSELAY.TRIG_DATE);
        CEP.TRC(SCCGWA, CMCSELAY.TRIG_TIME);
        CEP.TRC(SCCGWA, CMCSELAY.TRF_AC_DATE);
        CEP.TRC(SCCGWA, CMCSELAY.TRF_JRN_NO);
        CEP.TRC(SCCGWA, CMCSELAY.TRIG_DATA);
        CEP.TRC(SCCGWA, CMCSELAY.TRF_TIME);
    }
    public void B230_REWRITE_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMRDELAY);
        CMRDELAY.HLD_NO = CMCSELAY.HLD_NO;
        CEP.TRC(SCCGWA, CMCSELAY.HLD_NO);
        CEP.TRC(SCCGWA, CMRDELAY.HLD_NO);
        T000_READUP_CMTDELAY();
        if (WS_CMTDELAY_FLG == 'N') {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_DELAY_NFD_ERR);
        }
        if (CMRDELAY.TR_STS != '1' 
            && CMCSELAY.FUNC == '3') {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_DELAY_CHG_PRO_ERR);
        }
        if ((CMRDELAY.TR_STS == '2' 
            || CMRDELAY.TR_STS == '3') 
            && CMCSELAY.FUNC == '6') {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_DELAY_CHG_CAN_ERR);
        }
        if (CMRDELAY.TR_STS != '2' 
            && (CMCSELAY.FUNC == '4' 
            || CMCSELAY.FUNC == '5')) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_DELAY_CHG_END_ERR);
        }
        CEP.TRC(SCCGWA, CMCSELAY.FUNC);
        if (CMCSELAY.FUNC == '3') {
            CMRDELAY.TR_STS = '2';
        } else if (CMCSELAY.FUNC == '4') {
            CMRDELAY.TR_STS = '3';
            CMRDELAY.TRF_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
            CMRDELAY.TRF_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
            CMRDELAY.TRF_DATE = SCCGWA.COMM_AREA.TR_DATE;
            CMRDELAY.TRF_TIME = SCCGWA.COMM_AREA.TR_TIME;
        } else if (CMCSELAY.FUNC == '5') {
            CMRDELAY.TR_STS = '4';
            CMRDELAY.RET_CODE = CMCSELAY.RET_CODE;
            CMRDELAY.RET_MSG = CMCSELAY.RET_MSG;
        } else if (CMCSELAY.FUNC == '6') {
            CMRDELAY.TR_STS = '5';
        } else if (CMCSELAY.FUNC == '7') {
            CMRDELAY.TR_STS = '6';
            CMRDELAY.TRF_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
            CMRDELAY.TRF_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
            CMRDELAY.TRF_DATE = SCCGWA.COMM_AREA.TR_DATE;
            CMRDELAY.TRF_TIME = SCCGWA.COMM_AREA.TR_TIME;
        } else {
        }
        CMRDELAY.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CMRDELAY.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CMRDELAY.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CMRDELAY.LAST_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_CMTDELAY();
        R_MOVE_CMTDELAY_OUTPUT();
    }
    public void R_MOVE_CMTDELAY_OUTPUT() throws IOException,SQLException,Exception {
        CMCSELAY.KEY.AC_DATE = CMRDELAY.KEY.AC_DATE;
        CMCSELAY.KEY.JRN_NO = CMRDELAY.KEY.JRN_NO;
        CMCSELAY.REQ_SYS = CMRDELAY.REQ_SYS;
        CMCSELAY.REQ_SYS_DATE = CMRDELAY.REQ_SYS_DATE;
        CMCSELAY.REQ_SYS_JRN = CMRDELAY.REQ_SYS_JRN;
        CMCSELAY.TR_DATE = CMRDELAY.TR_DATE;
        CMCSELAY.TR_TIME = CMRDELAY.TR_TIME;
        CMCSELAY.FR_CHNL = CMRDELAY.FR_CHNL;
        CMCSELAY.TR_STS = CMRDELAY.TR_STS;
        CMCSELAY.TRIG_DATE = CMRDELAY.TRIG_DATE;
        CMCSELAY.TRIG_TIME = CMRDELAY.TRIG_TIME;
        CMCSELAY.TRIG_DATA = CMRDELAY.BLOB_TRIG_DATA;
        CMCSELAY.TRIG_TIME = CMRDELAY.TRIG_TIME;
        CMCSELAY.TRF_AC_DATE = CMRDELAY.TRF_AC_DATE;
        CMCSELAY.TRF_JRN_NO = CMRDELAY.TRF_JRN_NO;
        CMCSELAY.TRF_DATE = CMRDELAY.TRF_DATE;
        CMCSELAY.TRF_TIME = CMRDELAY.TRF_TIME;
        CMCSELAY.TRF_AMT = CMRDELAY.TRF_AMT;
        CMCSELAY.FEE_AMT = CMRDELAY.FEE_AMT;
        CMCSELAY.CCY = CMRDELAY.CCY;
        CMCSELAY.CCY_TYP = CMRDELAY.CCY_TYP;
        CMCSELAY.RET_MSG = CMRDELAY.RET_MSG;
        CMCSELAY.RET_CODE = CMRDELAY.RET_CODE;
        CMCSELAY.HLD_NO = CMRDELAY.HLD_NO;
        CMCSELAY.TRO_AC = CMRDELAY.TRO_AC;
        CMCSELAY.TRI_AC = CMRDELAY.TRI_AC;
    }
    public void T000_READ_CMTDELAY() throws IOException,SQLException,Exception {
        CMTDELAY_RD = new DBParm();
        CMTDELAY_RD.TableName = "CMTDELAY";
        IBS.READ(SCCGWA, CMRDELAY, CMTDELAY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CMTDELAY_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CMTDELAY_FLG = 'N';
        } else {
        }
    }
    public void T000_READUP_CMTDELAY() throws IOException,SQLException,Exception {
        CMTDELAY_RD = new DBParm();
        CMTDELAY_RD.TableName = "CMTDELAY";
        CMTDELAY_RD.where = "HLD_NO = :CMRDELAY.HLD_NO";
        CMTDELAY_RD.upd = true;
        IBS.READ(SCCGWA, CMRDELAY, this, CMTDELAY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CMTDELAY_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CMTDELAY_FLG = 'N';
        } else {
        }
    }
    public void T000_WRITE_CMTDELAY() throws IOException,SQLException,Exception {
        CMTDELAY_RD = new DBParm();
        CMTDELAY_RD.TableName = "CMTDELAY";
        IBS.WRITE(SCCGWA, CMRDELAY, CMTDELAY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_REWRITE_CMTDELAY() throws IOException,SQLException,Exception {
        CMTDELAY_RD = new DBParm();
        CMTDELAY_RD.TableName = "CMTDELAY";
        IBS.REWRITE(SCCGWA, CMRDELAY, CMTDELAY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        WS_RC = 0;
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (WS_RC != 0) {
            SCCCLDT.RC = WS_RC;
        }
        CEP.TRC(SCCGWA, SCCCLDT.RC);
        if (SCCCLDT.RC != 0) {
            IBS.init(SCCGWA, SCCBINF);
            SCCBINF.ERR_TYPE = 'P';
            SCCBINF.ERR_ACTION = 'E';
            WS_RC_DISP = SCCCLDT.RC;
            SCCBINF.ERR_NAME = PGM_SCSSCLDT;
            WS_ERR_MSG = K_SYS_ERR;
            SCCBINF.OTHER_INFO = "CALL-SCSSCLDT ERROR  " + WS_RC_DISP;
            CEP.ERR(SCCGWA, K_SYS_ERR, SCCBINF);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
