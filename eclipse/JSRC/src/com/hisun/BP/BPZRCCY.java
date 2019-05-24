package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.*;
import com.hisun.TC.*;

public class BPZRCCY {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String CPN_REC_NHIS = "BP-REC-NHIS  ";
    String K_HIS_COPYBOOK_NAME = "BPCOHCCY";
    String CPN_R_FEE_BPZPRMM = "BP-PARM-MAINTAIN    ";
    String CPN_R_FEE_BPZRMBPM = "BP-R-MBRW-PARM      ";
    String CPN_INQ_BRW_CCY = "BP-R-BRW-CCY";
    String WS_DATA = " ";
    int WS_EFF_DT = 0;
    int WS_EXP_DT = 0;
    String WS_CCY = " ";
    short WS_CCY_CD = 0;
    short WS_CNT1 = 0;
    char WS_CCY_CD_FLG = ' ';
    BPZRCCY_WS_HIS_REMARKS WS_HIS_REMARKS = new BPZRCCY_WS_HIS_REMARKS();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPCOHCCY BPCOOHCY = new BPCOHCCY();
    BPCOHCCY BPCONHCY = new BPCOHCCY();
    SCCCALL SCCCALL = new SCCCALL();
    BPRBCCY BPRBCCY = new BPRBCCY();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCRBCCY BPCRBCCY = new BPCRBCCY();
    TCCASMSG TCCASMSG = new TCCASMSG();
    BPRMCCY BPRMCCY = new BPRMCCY();
    SCCGWA SCCGWA;
    BPCRCCY BPCRCCY;
    public void MP(SCCGWA SCCGWA, BPCRCCY BPCRCCY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRCCY = BPCRCCY;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRCCY return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBCCY);
        IBS.init(SCCGWA, BPCOOHCY);
        IBS.init(SCCGWA, BPCONHCY);
        IBS.init(SCCGWA, BPCPNHIS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCRCCY.DATA.CHG_CCY);
        CEP.TRC(SCCGWA, BPCRCCY.DATA.CCY);
        CEP.TRC(SCCGWA, BPCRCCY.DATA.CCY_CD);
        if ((BPCRCCY.OP_FUNC == 'A' 
            || BPCRCCY.OP_FUNC == 'M') 
            && BPCRCCY.DATA.EXP_DT <= SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_INV_EXP_DT, BPCRCCY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCRCCY.OP_FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRCCY.OP_FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRCCY.OP_FUNC == 'M') {
            B030_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRCCY.OP_FUNC == 'D') {
            B040_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRCCY.OP_FUNC == 'S') {
            B051_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRCCY.OP_FUNC == 'R') {
            B052_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRCCY.OP_FUNC == 'E') {
            B053_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRCCY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "DEVSOS-BPZRCCY");
        CEP.TRC(SCCGWA, BPCRCCY.DATA.CCY);
    }
    public void B010_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        S000_TRANS_BPRBCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRCCY.DATA.CCY);
        if (BPCRCCY.DATA.CCY.trim().length() > 0) {
            IBS.init(SCCGWA, BPCRBCCY);
            BPCRBCCY.INFO.OP_FUNC = 'I';
            BPRBCCY.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
            BPRBCCY.KEY.CD = BPCRCCY.DATA.CCY;
            BPRBCCY.EFF_DT = WS_EFF_DT;
            CEP.TRC(SCCGWA, BPRBCCY.KEY.CD);
            CEP.TRC(SCCGWA, BPRBCCY.EFF_DT);
            BPCRBCCY.INFO.POINTER = BPRBCCY;
            BPCRBCCY.INFO.LEN = 391;
            S000_CALL_BPZRBCCY();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "2222");
            S010_TRANS_RCCY_DATA();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCRCCY.DATA);
            CEP.TRC(SCCGWA, BPCRCCY.DATA.TR_FLG);
        } else {
            T000_BROWSE_BPRBCCY();
            if (pgmRtn) return;
        }
    }
    public void B020_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        S000_TRANS_BPRBCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRCCY.DATA.CCY);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRCCY.DATA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCONHCY);
        WS_DATA = IBS.CLS2CPY(SCCGWA, BPCRCCY.DATA);
        CEP.TRC(SCCGWA, WS_CCY);
        IBS.CPY2CLS(SCCGWA, WS_DATA, BPCRCCY.DATA);
        S000_TRANS_BPRBCCY();
        if (pgmRtn) return;
        BPCRBCCY.INFO.OP_FUNC = 'A';
        BPCRBCCY.INFO.POINTER = BPRBCCY;
        BPCRBCCY.INFO.LEN = 391;
        S000_CALL_BPZRBCCY();
        if (pgmRtn) return;
        S010_TRANS_RCCY_DATA();
        if (pgmRtn) return;
        B020_01_HISTORY_RECORD();
        if (pgmRtn) return;
    }
    public void B030_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        S000_TRANS_BPRBCCY();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRCCY.DATA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCONHCY);
        WS_DATA = IBS.CLS2CPY(SCCGWA, BPCRCCY.DATA);
        CEP.TRC(SCCGWA, BPCONHCY);
        IBS.CPY2CLS(SCCGWA, WS_DATA, BPCRCCY.DATA);
        CEP.TRC(SCCGWA, BPCRCCY.DATA);
        S000_TRANS_BPRBCCY();
        if (pgmRtn) return;
        BPCRBCCY.INFO.OP_FUNC = 'U';
        BPCRBCCY.INFO.POINTER = BPRBCCY;
        BPCRBCCY.INFO.LEN = 391;
        S000_CALL_BPZRBCCY();
        if (pgmRtn) return;
        S010_TRANS_RCCY_DATA();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRCCY.DATA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCOOHCY);
        R000_CHECK_DATA_CHG();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCONHCY);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCRCCY.DATA);
        S000_TRANS_BPRBCCY();
        if (pgmRtn) return;
        if (BPRBCCY.EFF_DT <= SCCGWA.COMM_AREA.AC_DATE 
            && BPCRCCY.DATA.EFF_DT > SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_INV_EFF_DT, BPCRCCY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPRBCCY.EFF_DT > SCCGWA.COMM_AREA.AC_DATE 
            && BPCRCCY.DATA.EFF_DT <= SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_INV_EFF_DT, BPCRCCY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        BPCRBCCY.INFO.OP_FUNC = 'M';
        BPCRBCCY.INFO.POINTER = BPRBCCY;
        BPCRBCCY.INFO.LEN = 391;
        S000_CALL_BPZRBCCY();
        if (pgmRtn) return;
        S010_TRANS_RCCY_DATA();
        if (pgmRtn) return;
        B030_01_HISTORY_RECORD();
        if (pgmRtn) return;
    }
    public void B040_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
    }
    public void B051_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRBCCY);
        S000_TRANS_BPRBCCY();
        if (pgmRtn) return;
        T000_STARTBR_BPRBCCY();
        if (pgmRtn) return;
    }
    public void B052_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        S000_TRANS_BPRBCCY();
        if (pgmRtn) return;
        T000_READNEXT_BPRBCCY();
        if (pgmRtn) return;
        S010_TRANS_RCCY_DATA();
        if (pgmRtn) return;
    }
    public void B053_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPRBCCY();
        if (pgmRtn) return;
    }
    public void B020_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_NAME;
        WS_HIS_REMARKS.WS_REMARKS = "CCY DETAILS INFO: ";
        WS_HIS_REMARKS.WS_HIS_FUNC = "ADD";
        WS_HIS_REMARKS.WS_HIS_CCY = BPCRCCY.DATA.CCY;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = IBS.CLS2CPY(SCCGWA, WS_HIS_REMARKS);
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B030_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_NAME;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        WS_HIS_REMARKS.WS_REMARKS = "CCY DETAILS INFO: ";
        WS_HIS_REMARKS.WS_HIS_FUNC = "MOD";
        WS_HIS_REMARKS.WS_HIS_CCY = BPCRCCY.DATA.CCY;
        BPCPNHIS.INFO.TX_RMK = IBS.CLS2CPY(SCCGWA, WS_HIS_REMARKS);
        BPCPNHIS.INFO.OLD_DAT_PT = BPCOOHCY;
        BPCPNHIS.INFO.NEW_DAT_PT = BPCONHCY;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void T000_BROWSE_BPRBCCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRBCCY);
        WS_CCY = BPCRCCY.DATA.CCY;
        WS_CCY_CD = BPCRCCY.DATA.CCY_CD;
        T000_STARTBR_BPRBCCY();
        if (pgmRtn) return;
        for (WS_CNT1 = 1; WS_CNT1 <= 99 
            && WS_CCY_CD_FLG != 'S' 
            && BPCRBCCY.RETURN_INFO != 'N'; WS_CNT1 += 1) {
            T000_READNEXT_BPRBCCY();
            if (pgmRtn) return;
            S010_TRANS_RCCY_DATA();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCRCCY.DATA);
            CEP.TRC(SCCGWA, BPCRCCY.DATA.CCY_CD);
            CEP.TRC(SCCGWA, WS_CCY_CD);
            if (BPCRCCY.DATA.CCY_CD == WS_CCY_CD) {
                CEP.TRC(SCCGWA, "*** CCY-CD FOUND ***");
                WS_CCY_CD_FLG = 'S';
                CEP.TRC(SCCGWA, BPCRCCY.DATA.CCY);
                CEP.TRC(SCCGWA, WS_CCY);
                CEP.TRC(SCCGWA, BPCRCCY.OP_FUNC);
                if (BPCRCCY.OP_FUNC == 'A') {
                    if (!BPCRCCY.DATA.CCY.equalsIgnoreCase(WS_CCY)) {
                        WS_CCY_CD_FLG = 'C';
                    } else {
                        CEP.TRC(SCCGWA, "DEVSOS01");
                        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CCY_REC_EXIST, BPCRCCY.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                } else if (BPCRCCY.OP_FUNC == 'M') {
                    if (BPCRCCY.DATA.CCY.equalsIgnoreCase(WS_CCY)) {
                    } else {
                        CEP.TRC(SCCGWA, "DEVSOS02");
                        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CCY_REC_EXIST, BPCRCCY.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                }
            }
        }
        T000_ENDBR_BPRBCCY();
        if (pgmRtn) return;
        if (WS_CNT1 > 99 
            && BPCRCCY.OP_FUNC == 'I') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CCY_REC_NOTFND, BPCRCCY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BPRBCCY() throws IOException,SQLException,Exception {
        BPCRBCCY.INFO.OP_FUNC = 'S';
        BPCRBCCY.INFO.POINTER = BPRBCCY;
        BPCRBCCY.INFO.LEN = 391;
        S000_CALL_BPZRBCCY();
        if (pgmRtn) return;
    }
    public void T000_READNEXT_BPRBCCY() throws IOException,SQLException,Exception {
        BPCRBCCY.INFO.OP_FUNC = 'R';
        BPCRBCCY.INFO.POINTER = BPRBCCY;
        BPCRBCCY.INFO.LEN = 391;
        S000_CALL_BPZRBCCY();
        if (pgmRtn) return;
    }
    public void T000_ENDBR_BPRBCCY() throws IOException,SQLException,Exception {
        BPCRBCCY.INFO.OP_FUNC = 'E';
        BPCRBCCY.INFO.POINTER = BPRBCCY;
        BPCRBCCY.INFO.LEN = 391;
        S000_CALL_BPZRBCCY();
        if (pgmRtn) return;
    }
    public void R030_CALL_TCCASMSG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TCCASMSG);
        IBS.init(SCCGWA, BPRMCCY);
        if (BPCRCCY.OP_FUNC == 'A') {
            BPRMCCY.FUNC = 'A';
        }
        if (BPCRCCY.OP_FUNC == 'M') {
            BPRMCCY.FUNC = 'U';
        }
        if (BPCRCCY.OP_FUNC == 'D') {
            BPRMCCY.FUNC = 'D';
        }
        BPRMCCY.FID = "BPRMCCY";
        BPRMCCY.DATA.CCY = BPCRCCY.DATA.CCY;
        BPRMCCY.DATA.CCY_CD = BPCRCCY.DATA.CCY_CD;
        BPRMCCY.DATA.CUR_NM = BPCRCCY.DATA.CUR_NM;
        BPRMCCY.DATA.EFF_DT = BPCRCCY.DATA.EFF_DT;
        BPRMCCY.DATA.EXP_DT = BPCRCCY.DATA.EXP_DT;
        BPRMCCY.DATA.CNTY_CD = BPCRCCY.DATA.CNTY_CD;
        BPRMCCY.DATA.CITY_CD = BPCRCCY.DATA.CITY_CD;
        BPRMCCY.DATA.UNIT_CURU_NAME = BPCRCCY.DATA.UNIT_CURU_NAME;
        BPRMCCY.DATA.CENT_CURU_NAME = BPCRCCY.DATA.CENT_CURU_NAME;
        BPRMCCY.DATA.RGN_CCY = BPCRCCY.DATA.RGN_CCY;
        BPRMCCY.DATA.DEC_MTH = BPCRCCY.DATA.DEC_MTH;
        BPRMCCY.DATA.CASH_MTH = BPCRCCY.DATA.CASH_MTH;
        BPRMCCY.DATA.RND_MTH = BPCRCCY.DATA.RND_MTH;
        BPRMCCY.DATA.TR_FLG = BPCRCCY.DATA.TR_FLG;
        BPRMCCY.DATA.CASH_FLG = BPCRCCY.DATA.CASH_FLG;
        BPRMCCY.DATA.CHGU_MTH = BPCRCCY.DATA.CHGU_MTH;
        BPRMCCY.DATA.EXH_FLG = BPCRCCY.DATA.EXH_FLG;
        BPRMCCY.DATA.CALR_STD = BPCRCCY.DATA.CALR_STD;
        BPRMCCY.DATA.CAL_CD = BPCRCCY.DATA.CAL_CD;
        BPRMCCY.DATA.UPT_DT = BPCRCCY.DATA.UPT_DT;
        BPRMCCY.DATA.UPT_TLR = BPCRCCY.DATA.UPT_TLR;
        BPRMCCY.DATA.ISR_DAYS = BPCRCCY.DATA.ISR_DAYS;
        BPRMCCY.DATA.BAL_DAYS = BPCRCCY.DATA.BAL_DAYS;
        BPRMCCY.DATA.CHG_CCY = BPCRCCY.DATA.CHG_CCY;
        BPRMCCY.DATA.CUR_CNM = BPCRCCY.DATA.CUR_CNM;
        TCCASMSG.REQ_APP = "INTGRT";
        TCCASMSG.MSG_CODE = "TCAS003";
        TCCASMSG.ENA_MODE = '1';
        TCCASMSG.LEN = 213;
        TCCASMSG.DATA = IBS.CLS2CPY(SCCGWA, BPRMCCY);
        IBS.CALLCPN(SCCGWA, "TC-ASYNC-MSG", TCCASMSG);
        CEP.TRC(SCCGWA, TCCASMSG.RTNCODE);
        if (TCCASMSG.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRCCY.RC);
            JIBS_tmp_str[1] = "" + TCCASMSG.RTNCODE;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_tmp_str[0] = JIBS_tmp_str[0].substring(0, 3 - 1) + JIBS_tmp_str[1] + JIBS_tmp_str[0].substring(3 + 4 - 1);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[1], BPCRCCY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRBCCY() throws IOException,SQLException,Exception {
        BPRBCCY.EFF_DT = WS_EFF_DT;
        BPRBCCY.EXP_DT = WS_EXP_DT;
        CEP.TRC(SCCGWA, WS_EFF_DT);
        CEP.TRC(SCCGWA, WS_EXP_DT);
        IBS.CALLCPN(SCCGWA, CPN_INQ_BRW_CCY, BPCRBCCY);
        CEP.TRC(SCCGWA, BPCRBCCY.RETURN_INFO);
        if (BPCRBCCY.INFO.OP_FUNC == 'R' 
            || BPCRBCCY.INFO.OP_FUNC == 'I' 
            || BPCRBCCY.INFO.OP_FUNC == 'U') {
            R000_CHECK_RETURNCODE();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCRCCY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_TRANS_BPRBCCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRBCCY);
        BPRBCCY.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        BPRBCCY.KEY.CD = BPCRCCY.DATA.CCY;
        BPRBCCY.DESC = BPCRCCY.DATA.CUR_NM;
        BPRBCCY.CDESC = BPCRCCY.DATA.CUR_CNM;
        if ((BPCRCCY.OP_FUNC != 'D') 
            && BPCRCCY.DATA.EFF_DT < SCCGWA.COMM_AREA.AC_DATE) {
            WS_EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            WS_EFF_DT = BPCRCCY.DATA.EFF_DT;
        }
        WS_EXP_DT = BPCRCCY.DATA.EXP_DT;
        S020_TRANS_BCCY_DATA();
        if (pgmRtn) return;
    }
    public void S010_TRANS_RCCY_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "3333");
        BPCRCCY.DATA.CCY = BPRBCCY.CCY;
        BPCRCCY.DATA.CCY_CD = BPRBCCY.CCY_NO;
        BPCRCCY.DATA.CUR_NM = BPRBCCY.CUR_NM;
        BPCRCCY.DATA.EFF_DT = BPRBCCY.EFF_DT;
        BPCRCCY.DATA.EXP_DT = BPRBCCY.EXP_DT;
        BPCRCCY.DATA.CNTY_CD = BPRBCCY.CNTY_CD;
        BPCRCCY.DATA.CITY_CD = BPRBCCY.CITY_CD;
        BPCRCCY.DATA.UNIT_CURU_NAME = BPRBCCY.UNIT_CURU_NAME;
        BPCRCCY.DATA.CENT_CURU_NAME = BPRBCCY.CENT_CURU_NAME;
        BPCRCCY.DATA.RGN_CCY = BPRBCCY.RGN_CCY;
        BPCRCCY.DATA.DEC_MTH = BPRBCCY.DEC_MTH;
        BPCRCCY.DATA.CASH_MTH = BPRBCCY.CASH_MTH;
        BPCRCCY.DATA.RND_MTH = BPRBCCY.RND_MTH;
        BPCRCCY.DATA.TR_FLG = BPRBCCY.TR_FLG;
        BPCRCCY.DATA.CASH_FLG = BPRBCCY.CASH_FLG;
        BPCRCCY.DATA.CHGU_MTH = BPRBCCY.CHGU_MTH;
        BPCRCCY.DATA.EXH_FLG = BPRBCCY.EXH_FLG;
        BPCRCCY.DATA.CALR_STD = BPRBCCY.CALR_STD;
        BPCRCCY.DATA.CAL_CD = BPRBCCY.CAL_CD;
        BPCRCCY.DATA.UPT_DT = BPRBCCY.UPT_DT;
        BPCRCCY.DATA.UPT_TLR = BPRBCCY.UPT_TLR_NO;
        BPCRCCY.DATA.ISR_DAYS = BPRBCCY.ISR_DAYS;
        BPCRCCY.DATA.BAL_DAYS = BPRBCCY.BAL_DAYS;
        BPCRCCY.DATA.CHG_CCY = BPRBCCY.CHG_CCY;
        BPCRCCY.DATA.CUR_CNM = BPRBCCY.CUR_CNM;
        BPCRCCY.DATA.HANG_LMT_AMT = BPRBCCY.HANG_LMT_AMT;
    }
    public void S020_TRANS_BCCY_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "TRANS TO BCCY");
        BPRBCCY.CCY = BPCRCCY.DATA.CCY;
        BPRBCCY.CCY_NO = BPCRCCY.DATA.CCY_CD;
        BPRBCCY.CUR_NM = BPCRCCY.DATA.CUR_NM;
        BPRBCCY.EFF_DT = BPCRCCY.DATA.EFF_DT;
        BPRBCCY.EXP_DT = BPCRCCY.DATA.EXP_DT;
        BPRBCCY.CNTY_CD = BPCRCCY.DATA.CNTY_CD;
        BPRBCCY.CITY_CD = BPCRCCY.DATA.CITY_CD;
        BPRBCCY.UNIT_CURU_NAME = BPCRCCY.DATA.UNIT_CURU_NAME;
        BPRBCCY.CENT_CURU_NAME = BPCRCCY.DATA.CENT_CURU_NAME;
        BPRBCCY.RGN_CCY = BPCRCCY.DATA.RGN_CCY;
        BPRBCCY.DEC_MTH = BPCRCCY.DATA.DEC_MTH;
        BPRBCCY.CASH_MTH = BPCRCCY.DATA.CASH_MTH;
        BPRBCCY.RND_MTH = BPCRCCY.DATA.RND_MTH;
        BPRBCCY.TR_FLG = BPCRCCY.DATA.TR_FLG;
        BPRBCCY.CASH_FLG = BPCRCCY.DATA.CASH_FLG;
        BPRBCCY.CHGU_MTH = BPCRCCY.DATA.CHGU_MTH;
        BPRBCCY.EXH_FLG = BPCRCCY.DATA.EXH_FLG;
        BPRBCCY.CALR_STD = BPCRCCY.DATA.CALR_STD;
        BPRBCCY.CAL_CD = BPCRCCY.DATA.CAL_CD;
        BPRBCCY.UPT_DT = BPCRCCY.DATA.UPT_DT;
        BPRBCCY.UPT_TLR_NO = BPCRCCY.DATA.UPT_TLR;
        BPRBCCY.ISR_DAYS = BPCRCCY.DATA.ISR_DAYS;
        BPRBCCY.BAL_DAYS = BPCRCCY.DATA.BAL_DAYS;
        BPRBCCY.CHG_CCY = BPCRCCY.DATA.CHG_CCY;
        BPRBCCY.CUR_CNM = BPCRCCY.DATA.CUR_CNM;
        BPRBCCY.HANG_LMT_AMT = BPCRCCY.DATA.HANG_LMT_AMT;
    }
    public void R000_CHECK_DATA_CHG() throws IOException,SQLException,Exception {
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCOOHCY);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCONHCY);
        if (JIBS_tmp_str[1].equalsIgnoreCase(JIBS_tmp_str[0])) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_DATA_NCHG_ERR, BPCRCCY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_RETURNCODE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCRBCCY.RETURN_INFO);
        if (BPCRBCCY.RETURN_INFO == 'F') {
            BPCRCCY.RC.RTNCODE = 0;
        } else if (BPCRBCCY.RETURN_INFO == 'N') {
            if (BPCRCCY.OP_FUNC != 'A') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CCY_REC_NOTFND, BPCRCCY.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TABLE_ACC_ERR, BPCRCCY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        BPCRBCCY.RC.RC_MMO = "BP";
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRCCY.RC.RTNCODE != 0) {
            CEP.TRC(SCCGWA, " BPCRBCCY = ");
            CEP.TRC(SCCGWA, BPCRBCCY);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
